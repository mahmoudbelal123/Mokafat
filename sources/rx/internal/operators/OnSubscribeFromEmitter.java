package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Emitter;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Action1;
import rx.functions.Cancellable;
import rx.internal.subscriptions.CancellableSubscription;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.SpscUnboundedArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.SerialSubscription;

/* JADX INFO: loaded from: classes.dex */
public final class OnSubscribeFromEmitter<T> implements Observable.OnSubscribe<T> {
    final Action1<Emitter<T>> Emitter;
    final Emitter.BackpressureMode backpressure;

    public OnSubscribeFromEmitter(Action1<Emitter<T>> Emitter, Emitter.BackpressureMode backpressure) {
        this.Emitter = Emitter;
        this.backpressure = backpressure;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super T> subscriber) throws Throwable {
        BaseEmitter noneEmitter;
        switch (this.backpressure) {
            case NONE:
                noneEmitter = new NoneEmitter(subscriber);
                break;
            case ERROR:
                noneEmitter = new ErrorEmitter(subscriber);
                break;
            case DROP:
                noneEmitter = new DropEmitter(subscriber);
                break;
            case LATEST:
                noneEmitter = new LatestEmitter(subscriber);
                break;
            default:
                noneEmitter = new BufferEmitter(subscriber, RxRingBuffer.SIZE);
                break;
        }
        subscriber.add(noneEmitter);
        subscriber.setProducer(noneEmitter);
        this.Emitter.call(noneEmitter);
    }

    static abstract class BaseEmitter<T> extends AtomicLong implements Emitter<T>, Producer, Subscription {
        private static final long serialVersionUID = 7326289992464377023L;
        final Subscriber<? super T> actual;
        final SerialSubscription serial = new SerialSubscription();

        public BaseEmitter(Subscriber<? super T> actual) {
            this.actual = actual;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.actual.isUnsubscribed()) {
                return;
            }
            try {
                this.actual.onCompleted();
            } finally {
                this.serial.unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            if (this.actual.isUnsubscribed()) {
                return;
            }
            try {
                this.actual.onError(e);
            } finally {
                this.serial.unsubscribe();
            }
        }

        @Override // rx.Subscription
        public final void unsubscribe() {
            this.serial.unsubscribe();
            onUnsubscribed();
        }

        void onUnsubscribed() {
        }

        @Override // rx.Subscription
        public final boolean isUnsubscribed() {
            return this.serial.isUnsubscribed();
        }

        @Override // rx.Producer
        public final void request(long n) {
            if (BackpressureUtils.validate(n)) {
                BackpressureUtils.getAndAddRequest(this, n);
                onRequested();
            }
        }

        void onRequested() {
        }

        @Override // rx.Emitter
        public final void setSubscription(Subscription s) {
            this.serial.set(s);
        }

        @Override // rx.Emitter
        public final void setCancellation(Cancellable c) {
            setSubscription(new CancellableSubscription(c));
        }

        @Override // rx.Emitter
        public final long requested() {
            return get();
        }
    }

    static final class NoneEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 3776720187248809713L;

        public NoneEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            long r;
            if (this.actual.isUnsubscribed()) {
                return;
            }
            this.actual.onNext(t);
            do {
                r = get();
                if (r == 0) {
                    return;
                }
            } while (!compareAndSet(r, r - 1));
        }
    }

    static abstract class NoOverflowBaseEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 4127754106204442833L;

        abstract void onOverflow();

        public NoOverflowBaseEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        public void onNext(T t) {
            if (this.actual.isUnsubscribed()) {
                return;
            }
            if (get() != 0) {
                this.actual.onNext(t);
                BackpressureUtils.produced(this, 1L);
            } else {
                onOverflow();
            }
        }
    }

    static final class DropEmitter<T> extends NoOverflowBaseEmitter<T> {
        private static final long serialVersionUID = 8360058422307496563L;

        public DropEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        @Override // rx.internal.operators.OnSubscribeFromEmitter.NoOverflowBaseEmitter
        void onOverflow() {
        }
    }

    static final class ErrorEmitter<T> extends NoOverflowBaseEmitter<T> {
        private static final long serialVersionUID = 338953216916120960L;
        private boolean done;

        public ErrorEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        @Override // rx.internal.operators.OnSubscribeFromEmitter.NoOverflowBaseEmitter, rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            super.onNext(t);
        }

        @Override // rx.internal.operators.OnSubscribeFromEmitter.BaseEmitter, rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            super.onCompleted();
        }

        @Override // rx.internal.operators.OnSubscribeFromEmitter.BaseEmitter, rx.Observer
        public void onError(Throwable e) {
            if (this.done) {
                RxJavaHooks.onError(e);
            } else {
                this.done = true;
                super.onError(e);
            }
        }

        @Override // rx.internal.operators.OnSubscribeFromEmitter.NoOverflowBaseEmitter
        void onOverflow() {
            onError(new MissingBackpressureException("fromEmitter: could not emit value due to lack of requests"));
        }
    }

    static final class BufferEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 2427151001689639875L;
        volatile boolean done;
        Throwable error;
        final Queue<Object> queue;
        final AtomicInteger wip;

        public BufferEmitter(Subscriber<? super T> actual, int capacityHint) {
            super(actual);
            this.queue = UnsafeAccess.isUnsafeAvailable() ? new SpscUnboundedArrayQueue<>(capacityHint) : new SpscUnboundedAtomicArrayQueue<>(capacityHint);
            this.wip = new AtomicInteger();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.queue.offer(NotificationLite.next(t));
            drain();
        }

        @Override // rx.internal.operators.OnSubscribeFromEmitter.BaseEmitter, rx.Observer
        public void onError(Throwable e) {
            this.error = e;
            this.done = true;
            drain();
        }

        @Override // rx.internal.operators.OnSubscribeFromEmitter.BaseEmitter, rx.Observer
        public void onCompleted() {
            this.done = true;
            drain();
        }

        @Override // rx.internal.operators.OnSubscribeFromEmitter.BaseEmitter
        void onRequested() {
            drain();
        }

        @Override // rx.internal.operators.OnSubscribeFromEmitter.BaseEmitter
        void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        void drain() {
            if (this.wip.getAndIncrement() != 0) {
                return;
            }
            int iAddAndGet = 1;
            Subscriber<? super T> subscriber = this.actual;
            Queue<Object> queue = this.queue;
            do {
                long j = get();
                long j2 = 0;
                while (j2 != j) {
                    if (subscriber.isUnsubscribed()) {
                        queue.clear();
                        return;
                    }
                    boolean z = this.done;
                    Object objPoll = queue.poll();
                    boolean z2 = objPoll == null;
                    if (z && z2) {
                        Throwable th = this.error;
                        if (th != null) {
                            super.onError(th);
                            return;
                        } else {
                            super.onCompleted();
                            return;
                        }
                    }
                    if (z2) {
                        break;
                    }
                    subscriber.onNext((Object) NotificationLite.getValue(objPoll));
                    j2++;
                }
                if (j2 == j) {
                    if (subscriber.isUnsubscribed()) {
                        queue.clear();
                        return;
                    }
                    boolean z3 = this.done;
                    boolean zIsEmpty = queue.isEmpty();
                    if (z3 && zIsEmpty) {
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            super.onError(th2);
                            return;
                        } else {
                            super.onCompleted();
                            return;
                        }
                    }
                }
                if (j2 != 0) {
                    BackpressureUtils.produced(this, j2);
                }
                iAddAndGet = this.wip.addAndGet(-iAddAndGet);
            } while (iAddAndGet != 0);
        }
    }

    static final class LatestEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 4023437720691792495L;
        volatile boolean done;
        Throwable error;
        final AtomicReference<Object> queue;
        final AtomicInteger wip;

        public LatestEmitter(Subscriber<? super T> actual) {
            super(actual);
            this.queue = new AtomicReference<>();
            this.wip = new AtomicInteger();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.queue.set(NotificationLite.next(t));
            drain();
        }

        @Override // rx.internal.operators.OnSubscribeFromEmitter.BaseEmitter, rx.Observer
        public void onError(Throwable e) {
            this.error = e;
            this.done = true;
            drain();
        }

        @Override // rx.internal.operators.OnSubscribeFromEmitter.BaseEmitter, rx.Observer
        public void onCompleted() {
            this.done = true;
            drain();
        }

        @Override // rx.internal.operators.OnSubscribeFromEmitter.BaseEmitter
        void onRequested() {
            drain();
        }

        @Override // rx.internal.operators.OnSubscribeFromEmitter.BaseEmitter
        void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.lazySet(null);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x0054, code lost:
        
            if (r7 != r3) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x005a, code lost:
        
            if (r1.isUnsubscribed() == false) goto L33;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x005c, code lost:
        
            r2.lazySet(null);
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x005f, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0060, code lost:
        
            r9 = r15.done;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x0066, code lost:
        
            if (r2.get() != null) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0068, code lost:
        
            r10 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x006a, code lost:
        
            if (r9 == false) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x006c, code lost:
        
            if (r10 == false) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x006e, code lost:
        
            r5 = r15.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0070, code lost:
        
            if (r5 == null) goto L41;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0072, code lost:
        
            super.onError(r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x0076, code lost:
        
            super.onCompleted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0079, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x007c, code lost:
        
            if (r7 == 0) goto L46;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x007e, code lost:
        
            rx.internal.operators.BackpressureUtils.produced(r15, r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x0081, code lost:
        
            r0 = r15.wip.addAndGet(-r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:?, code lost:
        
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drain() {
            /*
                r15 = this;
                java.util.concurrent.atomic.AtomicInteger r0 = r15.wip
                int r0 = r0.getAndIncrement()
                if (r0 == 0) goto L9
                return
            L9:
                r0 = 1
                rx.Subscriber<? super T> r1 = r15.actual
                java.util.concurrent.atomic.AtomicReference<java.lang.Object> r2 = r15.queue
            Le:
                long r3 = r15.get()
                r5 = 0
                r7 = r5
            L15:
                int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
                r10 = 0
                r11 = 1
                r12 = 0
                if (r9 == 0) goto L52
                boolean r9 = r1.isUnsubscribed()
                if (r9 == 0) goto L26
                r2.lazySet(r12)
                return
            L26:
                boolean r9 = r15.done
                java.lang.Object r13 = r2.getAndSet(r12)
                if (r13 != 0) goto L30
                r14 = r11
                goto L31
            L30:
                r14 = r10
            L31:
                if (r9 == 0) goto L41
                if (r14 == 0) goto L41
                java.lang.Throwable r5 = r15.error
                if (r5 == 0) goto L3d
                super.onError(r5)
                goto L40
            L3d:
                super.onCompleted()
            L40:
                return
            L41:
                if (r14 == 0) goto L44
                goto L52
            L44:
                java.lang.Object r10 = rx.internal.operators.NotificationLite.getValue(r13)
                r1.onNext(r10)
                r10 = 1
                long r12 = r7 + r10
                r7 = r12
                goto L15
            L52:
                int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
                if (r9 != 0) goto L7a
                boolean r9 = r1.isUnsubscribed()
                if (r9 == 0) goto L60
                r2.lazySet(r12)
                return
            L60:
                boolean r9 = r15.done
                java.lang.Object r12 = r2.get()
                if (r12 != 0) goto L6a
                r10 = r11
            L6a:
                if (r9 == 0) goto L7a
                if (r10 == 0) goto L7a
                java.lang.Throwable r5 = r15.error
                if (r5 == 0) goto L76
                super.onError(r5)
                goto L79
            L76:
                super.onCompleted()
            L79:
                return
            L7a:
                int r9 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                if (r9 == 0) goto L81
                rx.internal.operators.BackpressureUtils.produced(r15, r7)
            L81:
                java.util.concurrent.atomic.AtomicInteger r5 = r15.wip
                int r6 = -r0
                int r0 = r5.addAndGet(r6)
                if (r0 != 0) goto L8c
            L8b:
                return
            L8c:
                goto Le
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OnSubscribeFromEmitter.LatestEmitter.drain():void");
        }
    }
}
