package rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.MissingBackpressureException;
import rx.exceptions.OnErrorThrowable;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.atomic.SpscExactAtomicArrayQueue;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.Pow2;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes.dex */
public final class OperatorMerge<T> implements Observable.Operator<T, Observable<? extends T>> {
    final boolean delayErrors;
    final int maxConcurrent;

    static final class HolderNoDelay {
        static final OperatorMerge<Object> INSTANCE = new OperatorMerge<>(false, Integer.MAX_VALUE);

        HolderNoDelay() {
        }
    }

    static final class HolderDelayErrors {
        static final OperatorMerge<Object> INSTANCE = new OperatorMerge<>(true, Integer.MAX_VALUE);

        HolderDelayErrors() {
        }
    }

    public static <T> OperatorMerge<T> instance(boolean z) {
        if (z) {
            return (OperatorMerge<T>) HolderDelayErrors.INSTANCE;
        }
        return (OperatorMerge<T>) HolderNoDelay.INSTANCE;
    }

    public static <T> OperatorMerge<T> instance(boolean delayErrors, int maxConcurrent) {
        if (maxConcurrent <= 0) {
            throw new IllegalArgumentException("maxConcurrent > 0 required but it was " + maxConcurrent);
        }
        if (maxConcurrent == Integer.MAX_VALUE) {
            return instance(delayErrors);
        }
        return new OperatorMerge<>(delayErrors, maxConcurrent);
    }

    OperatorMerge(boolean delayErrors, int maxConcurrent) {
        this.delayErrors = delayErrors;
        this.maxConcurrent = maxConcurrent;
    }

    @Override // rx.functions.Func1
    public Subscriber<Observable<? extends T>> call(Subscriber<? super T> child) throws Throwable {
        MergeSubscriber<T> subscriber = new MergeSubscriber<>(child, this.delayErrors, this.maxConcurrent);
        MergeProducer<T> producer = new MergeProducer<>(subscriber);
        subscriber.producer = producer;
        child.add(subscriber);
        child.setProducer(producer);
        return subscriber;
    }

    static final class MergeProducer<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = -1214379189873595503L;
        final MergeSubscriber<T> subscriber;

        public MergeProducer(MergeSubscriber<T> subscriber) {
            this.subscriber = subscriber;
        }

        @Override // rx.Producer
        public void request(long n) throws Throwable {
            if (n <= 0) {
                if (n < 0) {
                    throw new IllegalArgumentException("n >= 0 required");
                }
            } else {
                if (get() == LongCompanionObject.MAX_VALUE) {
                    return;
                }
                BackpressureUtils.getAndAddRequest(this, n);
                this.subscriber.emit();
            }
        }

        public long produced(int n) {
            return addAndGet(-n);
        }
    }

    static final class MergeSubscriber<T> extends Subscriber<Observable<? extends T>> {
        static final InnerSubscriber<?>[] EMPTY = new InnerSubscriber[0];
        final Subscriber<? super T> child;
        final boolean delayErrors;
        volatile boolean done;
        boolean emitting;
        volatile ConcurrentLinkedQueue<Throwable> errors;
        final Object innerGuard = new Object();
        volatile InnerSubscriber<?>[] innerSubscribers = EMPTY;
        long lastId;
        int lastIndex;
        final int maxConcurrent;
        boolean missed;
        MergeProducer<T> producer;
        volatile Queue<Object> queue;
        int scalarEmissionCount;
        final int scalarEmissionLimit;
        volatile CompositeSubscription subscriptions;
        long uniqueId;

        public MergeSubscriber(Subscriber<? super T> child, boolean delayErrors, int maxConcurrent) {
            this.child = child;
            this.delayErrors = delayErrors;
            this.maxConcurrent = maxConcurrent;
            if (maxConcurrent == Integer.MAX_VALUE) {
                this.scalarEmissionLimit = Integer.MAX_VALUE;
                request(LongCompanionObject.MAX_VALUE);
            } else {
                this.scalarEmissionLimit = Math.max(1, maxConcurrent >> 1);
                request(maxConcurrent);
            }
        }

        Queue<Throwable> getOrCreateErrorQueue() {
            ConcurrentLinkedQueue<Throwable> q = this.errors;
            if (q == null) {
                synchronized (this) {
                    q = this.errors;
                    if (q == null) {
                        q = new ConcurrentLinkedQueue<>();
                        this.errors = q;
                    }
                }
            }
            return q;
        }

        CompositeSubscription getOrCreateComposite() {
            CompositeSubscription c = this.subscriptions;
            if (c == null) {
                boolean shouldAdd = false;
                synchronized (this) {
                    c = this.subscriptions;
                    if (c == null) {
                        c = new CompositeSubscription();
                        this.subscriptions = c;
                        shouldAdd = true;
                    }
                }
                if (shouldAdd) {
                    add(c);
                }
            }
            return c;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // rx.Observer
        public void onNext(Observable<? extends T> t) throws Throwable {
            if (t == null) {
                return;
            }
            if (t == Observable.empty()) {
                emitEmpty();
                return;
            }
            if (t instanceof ScalarSynchronousObservable) {
                tryEmit(((ScalarSynchronousObservable) t).get());
                return;
            }
            long j = this.uniqueId;
            this.uniqueId = j + 1;
            InnerSubscriber<T> inner = new InnerSubscriber<>(this, j);
            addInner(inner);
            t.unsafeSubscribe(inner);
            emit();
        }

        void emitEmpty() {
            int produced = this.scalarEmissionCount + 1;
            if (produced == this.scalarEmissionLimit) {
                this.scalarEmissionCount = 0;
                requestMore(produced);
            } else {
                this.scalarEmissionCount = produced;
            }
        }

        private void reportError() {
            List<Throwable> list = new ArrayList<>(this.errors);
            if (list.size() == 1) {
                this.child.onError(list.get(0));
            } else {
                this.child.onError(new CompositeException(list));
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) throws Throwable {
            getOrCreateErrorQueue().offer(e);
            this.done = true;
            emit();
        }

        @Override // rx.Observer
        public void onCompleted() throws Throwable {
            this.done = true;
            emit();
        }

        /* JADX WARN: Multi-variable type inference failed */
        void addInner(InnerSubscriber<T> inner) {
            getOrCreateComposite().add(inner);
            synchronized (this.innerGuard) {
                InnerSubscriber<?>[] a = this.innerSubscribers;
                int n = a.length;
                InnerSubscriber<?>[] innerSubscriberArr = new InnerSubscriber[n + 1];
                System.arraycopy(a, 0, innerSubscriberArr, 0, n);
                innerSubscriberArr[n] = inner;
                this.innerSubscribers = innerSubscriberArr;
            }
        }

        void removeInner(InnerSubscriber<T> inner) {
            RxRingBuffer q = inner.queue;
            if (q != null) {
                q.release();
            }
            this.subscriptions.remove(inner);
            synchronized (this.innerGuard) {
                InnerSubscriber<?>[] a = this.innerSubscribers;
                int n = a.length;
                int j = -1;
                int i = 0;
                while (true) {
                    if (i >= n) {
                        break;
                    }
                    if (!inner.equals(a[i])) {
                        i++;
                    } else {
                        j = i;
                        break;
                    }
                }
                if (j < 0) {
                    return;
                }
                if (n == 1) {
                    this.innerSubscribers = EMPTY;
                    return;
                }
                InnerSubscriber<?>[] b = new InnerSubscriber[n - 1];
                System.arraycopy(a, 0, b, 0, j);
                System.arraycopy(a, j + 1, b, j, (n - j) - 1);
                this.innerSubscribers = b;
            }
        }

        void tryEmit(InnerSubscriber<T> subscriber, T value) throws Throwable {
            boolean success = false;
            long r = this.producer.get();
            if (r != 0) {
                synchronized (this) {
                    r = this.producer.get();
                    if (!this.emitting && r != 0) {
                        this.emitting = true;
                        success = true;
                    }
                }
            }
            if (success) {
                RxRingBuffer subscriberQueue = subscriber.queue;
                if (subscriberQueue == null || subscriberQueue.isEmpty()) {
                    emitScalar(subscriber, value, r);
                    return;
                } else {
                    queueScalar(subscriber, value);
                    emitLoop();
                    return;
                }
            }
            queueScalar(subscriber, value);
            emit();
        }

        protected void queueScalar(InnerSubscriber<T> subscriber, T value) throws Throwable {
            RxRingBuffer q = subscriber.queue;
            if (q == null) {
                q = RxRingBuffer.getSpscInstance();
                subscriber.add(q);
                subscriber.queue = q;
            }
            try {
                q.onNext(NotificationLite.next(value));
            } catch (IllegalStateException ex) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.unsubscribe();
                    subscriber.onError(ex);
                }
            } catch (MissingBackpressureException ex2) {
                subscriber.unsubscribe();
                subscriber.onError(ex2);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:58:0x006d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        protected void emitScalar(rx.internal.operators.OperatorMerge.InnerSubscriber<T> r7, T r8, long r9) throws java.lang.Throwable {
            /*
                r6 = this;
                r0 = 0
                r1 = r0
                rx.Subscriber<? super T> r2 = r6.child     // Catch: java.lang.Throwable -> L8 java.lang.Throwable -> La
                r2.onNext(r8)     // Catch: java.lang.Throwable -> L8 java.lang.Throwable -> La
                goto L2b
            L8:
                r2 = move-exception
                goto L6b
            La:
                r2 = move-exception
                boolean r3 = r6.delayErrors     // Catch: java.lang.Throwable -> L8
                if (r3 != 0) goto L24
                rx.exceptions.Exceptions.throwIfFatal(r2)     // Catch: java.lang.Throwable -> L8
                r1 = 1
                r7.unsubscribe()     // Catch: java.lang.Throwable -> L8
                r7.onError(r2)     // Catch: java.lang.Throwable -> L8
                if (r1 != 0) goto L23
                monitor-enter(r6)
                r6.emitting = r0     // Catch: java.lang.Throwable -> L20
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L20
                goto L23
            L20:
                r0 = move-exception
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L20
                throw r0
            L23:
                return
            L24:
                java.util.Queue r3 = r6.getOrCreateErrorQueue()     // Catch: java.lang.Throwable -> L8
                r3.offer(r2)     // Catch: java.lang.Throwable -> L8
            L2b:
                r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r4 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
                if (r4 == 0) goto L3a
                rx.internal.operators.OperatorMerge$MergeProducer<T> r2 = r6.producer     // Catch: java.lang.Throwable -> L8
                r3 = 1
                r2.produced(r3)     // Catch: java.lang.Throwable -> L8
            L3a:
                r2 = 1
                r7.requestMore(r2)     // Catch: java.lang.Throwable -> L8
                monitor-enter(r6)     // Catch: java.lang.Throwable -> L8
                r2 = 1
                boolean r1 = r6.missed     // Catch: java.lang.Throwable -> L64
                if (r1 != 0) goto L53
                r6.emitting = r0     // Catch: java.lang.Throwable -> L64
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L64
                if (r2 != 0) goto L52
                monitor-enter(r6)
                r6.emitting = r0     // Catch: java.lang.Throwable -> L4f
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L4f
                goto L52
            L4f:
                r0 = move-exception
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L4f
                throw r0
            L52:
                return
            L53:
                r6.missed = r0     // Catch: java.lang.Throwable -> L64
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L64
                if (r2 != 0) goto L60
                monitor-enter(r6)
                r6.emitting = r0     // Catch: java.lang.Throwable -> L5d
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L5d
                goto L60
            L5d:
                r0 = move-exception
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L5d
                throw r0
            L60:
                r6.emitLoop()
                return
            L64:
                r1 = move-exception
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L64
                throw r1     // Catch: java.lang.Throwable -> L67
            L67:
                r1 = move-exception
                r5 = r2
                r2 = r1
                r1 = r5
            L6b:
                if (r1 != 0) goto L75
                monitor-enter(r6)
                r6.emitting = r0     // Catch: java.lang.Throwable -> L72
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L72
                goto L75
            L72:
                r0 = move-exception
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L72
                throw r0
            L75:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorMerge.MergeSubscriber.emitScalar(rx.internal.operators.OperatorMerge$InnerSubscriber, java.lang.Object, long):void");
        }

        public void requestMore(long n) {
            request(n);
        }

        void tryEmit(T value) throws Throwable {
            boolean success = false;
            long r = this.producer.get();
            if (r != 0) {
                synchronized (this) {
                    r = this.producer.get();
                    if (!this.emitting && r != 0) {
                        this.emitting = true;
                        success = true;
                    }
                }
            }
            if (success) {
                Queue<Object> mainQueue = this.queue;
                if (mainQueue == null || mainQueue.isEmpty()) {
                    emitScalar(value, r);
                    return;
                } else {
                    queueScalar(value);
                    emitLoop();
                    return;
                }
            }
            queueScalar(value);
            emit();
        }

        protected void queueScalar(T value) throws Throwable {
            Queue<Object> q = this.queue;
            if (q == null) {
                int mc = this.maxConcurrent;
                if (mc == Integer.MAX_VALUE) {
                    q = new SpscUnboundedAtomicArrayQueue(RxRingBuffer.SIZE);
                } else if (Pow2.isPowerOfTwo(mc)) {
                    if (UnsafeAccess.isUnsafeAvailable()) {
                        q = new SpscArrayQueue(mc);
                    } else {
                        q = new SpscAtomicArrayQueue(mc);
                    }
                } else {
                    q = new SpscExactAtomicArrayQueue(mc);
                }
                this.queue = q;
            }
            if (!q.offer(NotificationLite.next(value))) {
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(new MissingBackpressureException(), value));
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:61:0x0079  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        protected void emitScalar(T r8, long r9) throws java.lang.Throwable {
            /*
                r7 = this;
                r0 = 0
                r1 = r0
                rx.Subscriber<? super T> r2 = r7.child     // Catch: java.lang.Throwable -> L8 java.lang.Throwable -> Lb
                r2.onNext(r8)     // Catch: java.lang.Throwable -> L8 java.lang.Throwable -> Lb
                goto L2c
            L8:
                r2 = move-exception
                goto L77
            Lb:
                r2 = move-exception
                boolean r3 = r7.delayErrors     // Catch: java.lang.Throwable -> L8
                if (r3 != 0) goto L25
                rx.exceptions.Exceptions.throwIfFatal(r2)     // Catch: java.lang.Throwable -> L8
                r1 = 1
                r7.unsubscribe()     // Catch: java.lang.Throwable -> L8
                r7.onError(r2)     // Catch: java.lang.Throwable -> L8
                if (r1 != 0) goto L24
                monitor-enter(r7)
                r7.emitting = r0     // Catch: java.lang.Throwable -> L21
                monitor-exit(r7)     // Catch: java.lang.Throwable -> L21
                goto L24
            L21:
                r0 = move-exception
                monitor-exit(r7)     // Catch: java.lang.Throwable -> L21
                throw r0
            L24:
                return
            L25:
                java.util.Queue r3 = r7.getOrCreateErrorQueue()     // Catch: java.lang.Throwable -> L8
                r3.offer(r2)     // Catch: java.lang.Throwable -> L8
            L2c:
                r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r4 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
                r2 = 1
                if (r4 == 0) goto L3b
                rx.internal.operators.OperatorMerge$MergeProducer<T> r3 = r7.producer     // Catch: java.lang.Throwable -> L8
                r3.produced(r2)     // Catch: java.lang.Throwable -> L8
            L3b:
                int r3 = r7.scalarEmissionCount     // Catch: java.lang.Throwable -> L8
                int r3 = r3 + r2
                int r2 = r7.scalarEmissionLimit     // Catch: java.lang.Throwable -> L8
                if (r3 != r2) goto L49
                r7.scalarEmissionCount = r0     // Catch: java.lang.Throwable -> L8
                long r4 = (long) r3     // Catch: java.lang.Throwable -> L8
                r7.requestMore(r4)     // Catch: java.lang.Throwable -> L8
                goto L4b
            L49:
                r7.scalarEmissionCount = r3     // Catch: java.lang.Throwable -> L8
            L4b:
                monitor-enter(r7)     // Catch: java.lang.Throwable -> L8
                r2 = 1
                boolean r1 = r7.missed     // Catch: java.lang.Throwable -> L70
                if (r1 != 0) goto L5f
                r7.emitting = r0     // Catch: java.lang.Throwable -> L70
                monitor-exit(r7)     // Catch: java.lang.Throwable -> L70
                if (r2 != 0) goto L5e
                monitor-enter(r7)
                r7.emitting = r0     // Catch: java.lang.Throwable -> L5b
                monitor-exit(r7)     // Catch: java.lang.Throwable -> L5b
                goto L5e
            L5b:
                r0 = move-exception
                monitor-exit(r7)     // Catch: java.lang.Throwable -> L5b
                throw r0
            L5e:
                return
            L5f:
                r7.missed = r0     // Catch: java.lang.Throwable -> L70
                monitor-exit(r7)     // Catch: java.lang.Throwable -> L70
                if (r2 != 0) goto L6c
                monitor-enter(r7)
                r7.emitting = r0     // Catch: java.lang.Throwable -> L69
                monitor-exit(r7)     // Catch: java.lang.Throwable -> L69
                goto L6c
            L69:
                r0 = move-exception
                monitor-exit(r7)     // Catch: java.lang.Throwable -> L69
                throw r0
            L6c:
                r7.emitLoop()
                return
            L70:
                r1 = move-exception
                monitor-exit(r7)     // Catch: java.lang.Throwable -> L70
                throw r1     // Catch: java.lang.Throwable -> L73
            L73:
                r1 = move-exception
                r6 = r2
                r2 = r1
                r1 = r6
            L77:
                if (r1 != 0) goto L81
                monitor-enter(r7)
                r7.emitting = r0     // Catch: java.lang.Throwable -> L7e
                monitor-exit(r7)     // Catch: java.lang.Throwable -> L7e
                goto L81
            L7e:
                r0 = move-exception
                monitor-exit(r7)     // Catch: java.lang.Throwable -> L7e
                throw r0
            L81:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorMerge.MergeSubscriber.emitScalar(java.lang.Object, long):void");
        }

        void emit() throws Throwable {
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                } else {
                    this.emitting = true;
                    emitLoop();
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:126:0x013a A[Catch: all -> 0x0267, TRY_LEAVE, TryCatch #4 {all -> 0x0267, blocks: (B:108:0x0100, B:116:0x011b, B:119:0x0124, B:122:0x0129, B:123:0x012c, B:126:0x013a, B:140:0x014e, B:144:0x015a, B:160:0x0175, B:197:0x01e1, B:199:0x01ed, B:206:0x020a, B:209:0x0212, B:211:0x0218, B:234:0x0251, B:237:0x0262, B:242:0x026d, B:163:0x017a, B:167:0x018c, B:169:0x0196), top: B:292:0x0100 }] */
        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:269:0x0297
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
            	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
            	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
            */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void emitLoop() throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 683
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorMerge.MergeSubscriber.emitLoop():void");
        }

        boolean checkTerminate() {
            if (this.child.isUnsubscribed()) {
                return true;
            }
            Queue<Throwable> e = this.errors;
            if (!this.delayErrors && e != null && !e.isEmpty()) {
                try {
                    reportError();
                    return true;
                } finally {
                    unsubscribe();
                }
            }
            return false;
        }
    }

    static final class InnerSubscriber<T> extends Subscriber<T> {
        static final int LIMIT = RxRingBuffer.SIZE / 4;
        volatile boolean done;
        final long id;
        int outstanding;
        final MergeSubscriber<T> parent;
        volatile RxRingBuffer queue;

        public InnerSubscriber(MergeSubscriber<T> parent, long id) {
            this.parent = parent;
            this.id = id;
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            this.outstanding = RxRingBuffer.SIZE;
            request(RxRingBuffer.SIZE);
        }

        @Override // rx.Observer
        public void onNext(T t) throws Throwable {
            this.parent.tryEmit(this, t);
        }

        @Override // rx.Observer
        public void onError(Throwable e) throws Throwable {
            this.done = true;
            this.parent.getOrCreateErrorQueue().offer(e);
            this.parent.emit();
        }

        @Override // rx.Observer
        public void onCompleted() throws Throwable {
            this.done = true;
            this.parent.emit();
        }

        public void requestMore(long n) {
            int r = this.outstanding - ((int) n);
            if (r > LIMIT) {
                this.outstanding = r;
                return;
            }
            this.outstanding = RxRingBuffer.SIZE;
            int k = RxRingBuffer.SIZE - r;
            if (k > 0) {
                request(k);
            }
        }
    }
}
