package rx.internal.operators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.functions.FuncN;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.plugins.RxJavaHooks;

/* JADX INFO: loaded from: classes.dex */
public final class OnSubscribeCombineLatest<T, R> implements Observable.OnSubscribe<R> {
    final int bufferSize;
    final FuncN<? extends R> combiner;
    final boolean delayError;
    final Observable<? extends T>[] sources;
    final Iterable<? extends Observable<? extends T>> sourcesIterable;

    public OnSubscribeCombineLatest(Iterable<? extends Observable<? extends T>> sourcesIterable, FuncN<? extends R> combiner) {
        this(null, sourcesIterable, combiner, RxRingBuffer.SIZE, false);
    }

    public OnSubscribeCombineLatest(Observable<? extends T>[] sources, Iterable<? extends Observable<? extends T>> sourcesIterable, FuncN<? extends R> combiner, int bufferSize, boolean delayError) {
        this.sources = sources;
        this.sourcesIterable = sourcesIterable;
        this.combiner = combiner;
        this.bufferSize = bufferSize;
        this.delayError = delayError;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super R> s) throws Throwable {
        Observable<? extends T>[] sources = this.sources;
        int count = 0;
        if (sources == null) {
            if (this.sourcesIterable instanceof List) {
                List list = (List) this.sourcesIterable;
                sources = (Observable[]) list.toArray(new Observable[list.size()]);
                count = sources.length;
            } else {
                sources = new Observable[8];
                for (Observable<? extends T> p : this.sourcesIterable) {
                    if (count == sources.length) {
                        Observable<? extends T>[] b = new Observable[(count >> 2) + count];
                        System.arraycopy(sources, 0, b, 0, count);
                        sources = b;
                    }
                    sources[count] = p;
                    count++;
                }
            }
        } else {
            count = sources.length;
        }
        if (count == 0) {
            s.onCompleted();
            return;
        }
        LatestCoordinator<T, R> lc = new LatestCoordinator<>(s, this.combiner, count, this.bufferSize, this.delayError);
        lc.subscribe(sources);
    }

    static final class LatestCoordinator<T, R> extends AtomicInteger implements Producer, Subscription {
        static final Object MISSING = new Object();
        private static final long serialVersionUID = 8567835998786448817L;
        int active;
        final Subscriber<? super R> actual;
        final int bufferSize;
        volatile boolean cancelled;
        final FuncN<? extends R> combiner;
        int complete;
        final boolean delayError;
        volatile boolean done;
        final AtomicReference<Throwable> error;
        final Object[] latest;
        final SpscLinkedArrayQueue<Object> queue;
        final AtomicLong requested;
        final CombinerSubscriber<T, R>[] subscribers;

        public LatestCoordinator(Subscriber<? super R> actual, FuncN<? extends R> combiner, int count, int bufferSize, boolean delayError) {
            this.actual = actual;
            this.combiner = combiner;
            this.bufferSize = bufferSize;
            this.delayError = delayError;
            this.latest = new Object[count];
            Arrays.fill(this.latest, MISSING);
            this.subscribers = new CombinerSubscriber[count];
            this.queue = new SpscLinkedArrayQueue<>(bufferSize);
            this.requested = new AtomicLong();
            this.error = new AtomicReference<>();
        }

        public void subscribe(Observable<? extends T>[] sources) throws Throwable {
            CombinerSubscriber<T, R>[] combinerSubscriberArr = this.subscribers;
            int len = combinerSubscriberArr.length;
            for (int i = 0; i < len; i++) {
                combinerSubscriberArr[i] = new CombinerSubscriber<>(this, i);
            }
            lazySet(0);
            this.actual.add(this);
            this.actual.setProducer(this);
            for (int i2 = 0; i2 < len && !this.cancelled; i2++) {
                sources[i2].subscribe((Subscriber<? super Object>) combinerSubscriberArr[i2]);
            }
        }

        @Override // rx.Producer
        public void request(long n) {
            if (n < 0) {
                throw new IllegalArgumentException("n >= required but it was " + n);
            }
            if (n != 0) {
                BackpressureUtils.getAndAddRequest(this.requested, n);
                drain();
            }
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (!this.cancelled) {
                this.cancelled = true;
                if (getAndIncrement() == 0) {
                    cancel(this.queue);
                }
            }
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.cancelled;
        }

        void cancel(Queue<?> q) {
            q.clear();
            for (CombinerSubscriber<T, R> s : this.subscribers) {
                s.unsubscribe();
            }
        }

        void combine(Object value, int index) throws Throwable {
            boolean empty;
            boolean allSourcesFinished;
            CombinerSubscriber<T, R> combinerSubscriber = this.subscribers[index];
            synchronized (this) {
                try {
                    int sourceCount = this.latest.length;
                    try {
                        Object o = this.latest[index];
                        int activeCount = this.active;
                        try {
                            if (o == MISSING) {
                                activeCount++;
                                this.active = activeCount;
                            }
                            int completedCount = this.complete;
                            try {
                                if (value == null) {
                                    completedCount++;
                                    this.complete = completedCount;
                                } else {
                                    this.latest[index] = NotificationLite.getValue(value);
                                }
                                boolean allSourcesFinished2 = activeCount == sourceCount;
                                if (completedCount != sourceCount) {
                                    if (value == null) {
                                        try {
                                            empty = o == MISSING;
                                        } catch (Throwable th) {
                                            th = th;
                                            while (true) {
                                                try {
                                                    throw th;
                                                } catch (Throwable th2) {
                                                    th = th2;
                                                }
                                            }
                                        }
                                    }
                                }
                                if (empty) {
                                    this.done = true;
                                } else if (value != null && allSourcesFinished2) {
                                    this.queue.offer(combinerSubscriber, this.latest.clone());
                                } else if (value == null && this.error.get() != null && (o == MISSING || !this.delayError)) {
                                    this.done = true;
                                }
                                if (allSourcesFinished2 || value == null) {
                                    drain();
                                } else {
                                    combinerSubscriber.requestMore(1L);
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                allSourcesFinished = false;
                                while (true) {
                                    throw th;
                                }
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            allSourcesFinished = false;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        empty = false;
                        while (true) {
                            throw th;
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                    empty = false;
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:31:0x00a4, code lost:
        
            if (r5 == 0) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x00ad, code lost:
        
            if (r15 == kotlin.jvm.internal.LongCompanionObject.MAX_VALUE) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x00af, code lost:
        
            rx.internal.operators.BackpressureUtils.produced(r11, r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x00b2, code lost:
        
            r1 = addAndGet(-r14);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drain() {
            /*
                r24 = this;
                r7 = r24
                int r1 = r24.getAndIncrement()
                if (r1 == 0) goto L9
                return
            L9:
                rx.internal.util.atomic.SpscLinkedArrayQueue<java.lang.Object> r8 = r7.queue
                rx.Subscriber<? super R> r9 = r7.actual
                boolean r10 = r7.delayError
                java.util.concurrent.atomic.AtomicLong r11 = r7.requested
                r12 = 1
                r1 = 0
                r13 = r1
                r1 = r12
            L15:
                r14 = r1
                boolean r2 = r7.done
                boolean r3 = r8.isEmpty()
                r1 = r7
                r4 = r9
                r5 = r8
                r6 = r10
                boolean r1 = r1.checkTerminated(r2, r3, r4, r5, r6)
                if (r1 == 0) goto L27
                return
            L27:
                long r15 = r11.get()
                r17 = 0
                r1 = r17
            L2f:
                r5 = r1
                int r1 = (r5 > r15 ? 1 : (r5 == r15 ? 0 : -1))
                if (r1 == 0) goto La2
                boolean r4 = r7.done
                java.lang.Object r1 = r8.peek()
                r3 = r1
                rx.internal.operators.OnSubscribeCombineLatest$CombinerSubscriber r3 = (rx.internal.operators.OnSubscribeCombineLatest.CombinerSubscriber) r3
                if (r3 != 0) goto L41
                r1 = r12
                goto L42
            L41:
                r1 = 0
            L42:
                r19 = r1
                r1 = r7
                r2 = r4
                r20 = r3
                r3 = r19
                r21 = r4
                r4 = r9
                r22 = r5
                r5 = r8
                r6 = r10
                boolean r1 = r1.checkTerminated(r2, r3, r4, r5, r6)
                if (r1 == 0) goto L58
                return
            L58:
                if (r19 == 0) goto L5e
            L5b:
                r5 = r22
                goto La2
            L5e:
                r8.poll()
                java.lang.Object r1 = r8.poll()
                java.lang.Object[] r1 = (java.lang.Object[]) r1
                if (r1 != 0) goto L79
                r7.cancelled = r12
                r7.cancel(r8)
                java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
                java.lang.String r3 = "Broken queue?! Sender received but not the array."
                r2.<init>(r3)
                r9.onError(r2)
                return
            L79:
                rx.functions.FuncN<? extends R> r2 = r7.combiner     // Catch: java.lang.Throwable -> L93
                java.lang.Object r2 = r2.call(r1)     // Catch: java.lang.Throwable -> L93
                r13 = r2
                r9.onNext(r13)
                r2 = 1
                r4 = r20
                r4.requestMore(r2)
                r5 = r22
                long r19 = r5 + r2
                r1 = r19
                goto L2f
            L93:
                r0 = move-exception
                r4 = r20
                r5 = r22
                r2 = r0
                r7.cancelled = r12
                r7.cancel(r8)
                r9.onError(r2)
                return
            La2:
                int r1 = (r5 > r17 ? 1 : (r5 == r17 ? 0 : -1))
                if (r1 == 0) goto Lb2
                r1 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r3 = (r15 > r1 ? 1 : (r15 == r1 ? 0 : -1))
                if (r3 == 0) goto Lb2
                rx.internal.operators.BackpressureUtils.produced(r11, r5)
            Lb2:
                int r1 = -r14
                int r1 = r7.addAndGet(r1)
                if (r1 != 0) goto Lbb
            Lba:
                return
            Lbb:
                goto L15
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OnSubscribeCombineLatest.LatestCoordinator.drain():void");
        }

        boolean checkTerminated(boolean mainDone, boolean queueEmpty, Subscriber<?> childSubscriber, Queue<?> q, boolean delayError) {
            if (this.cancelled) {
                cancel(q);
                return true;
            }
            if (mainDone) {
                if (delayError) {
                    if (queueEmpty) {
                        Throwable e = this.error.get();
                        if (e != null) {
                            childSubscriber.onError(e);
                        } else {
                            childSubscriber.onCompleted();
                        }
                        return true;
                    }
                    return false;
                }
                Throwable e2 = this.error.get();
                if (e2 != null) {
                    cancel(q);
                    childSubscriber.onError(e2);
                    return true;
                }
                if (queueEmpty) {
                    childSubscriber.onCompleted();
                    return true;
                }
                return false;
            }
            return false;
        }

        void onError(Throwable e) {
            Throwable curr;
            Throwable next;
            AtomicReference<Throwable> localError = this.error;
            do {
                curr = localError.get();
                if (curr != null) {
                    if (curr instanceof CompositeException) {
                        CompositeException ce = (CompositeException) curr;
                        List<Throwable> es2 = new ArrayList<>(ce.getExceptions());
                        es2.add(e);
                        next = new CompositeException(es2);
                    } else {
                        next = new CompositeException(Arrays.asList(curr, e));
                    }
                } else {
                    next = e;
                }
            } while (!localError.compareAndSet(curr, next));
        }
    }

    static final class CombinerSubscriber<T, R> extends Subscriber<T> {
        boolean done;
        final int index;
        final LatestCoordinator<T, R> parent;

        public CombinerSubscriber(LatestCoordinator<T, R> parent, int index) {
            this.parent = parent;
            this.index = index;
            request(parent.bufferSize);
        }

        @Override // rx.Observer
        public void onNext(T t) throws Throwable {
            if (this.done) {
                return;
            }
            this.parent.combine(NotificationLite.next(t), this.index);
        }

        @Override // rx.Observer
        public void onError(Throwable t) throws Throwable {
            if (this.done) {
                RxJavaHooks.onError(t);
                return;
            }
            this.parent.onError(t);
            this.done = true;
            this.parent.combine(null, this.index);
        }

        @Override // rx.Observer
        public void onCompleted() throws Throwable {
            if (this.done) {
                return;
            }
            this.done = true;
            this.parent.combine(null, this.index);
        }

        public void requestMore(long n) {
            request(n);
        }
    }
}
