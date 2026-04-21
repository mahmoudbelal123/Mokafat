package rx.subjects;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.annotations.Experimental;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.internal.operators.BackpressureUtils;
import rx.internal.operators.NotificationLite;
import rx.internal.util.atomic.SpscLinkedAtomicQueue;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.SpscLinkedQueue;
import rx.internal.util.unsafe.SpscUnboundedArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;

/* JADX INFO: loaded from: classes.dex */
@Experimental
public final class UnicastSubject<T> extends Subject<T, T> {
    final State<T> state;

    public static <T> UnicastSubject<T> create() {
        return create(16);
    }

    public static <T> UnicastSubject<T> create(int capacityHint) {
        State<T> state = new State<>(capacityHint, null);
        return new UnicastSubject<>(state);
    }

    public static <T> UnicastSubject<T> create(int capacityHint, Action0 onTerminated) {
        State<T> state = new State<>(capacityHint, onTerminated);
        return new UnicastSubject<>(state);
    }

    private UnicastSubject(State<T> state) {
        super(state);
        this.state = state;
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.state.onNext(t);
    }

    @Override // rx.Observer
    public void onError(Throwable e) throws Throwable {
        this.state.onError(e);
    }

    @Override // rx.Observer
    public void onCompleted() throws Throwable {
        this.state.onCompleted();
    }

    @Override // rx.subjects.Subject
    public boolean hasObservers() {
        return this.state.subscriber.get() != null;
    }

    static final class State<T> extends AtomicLong implements Producer, Observer<T>, Observable.OnSubscribe<T>, Subscription {
        private static final long serialVersionUID = -9044104859202255786L;
        volatile boolean caughtUp;
        volatile boolean done;
        boolean emitting;
        Throwable error;
        boolean missed;
        final Queue<Object> queue;
        final AtomicReference<Subscriber<? super T>> subscriber = new AtomicReference<>();
        final AtomicReference<Action0> terminateOnce;

        public State(int capacityHint, Action0 onTerminated) {
            Queue<Object> q;
            this.terminateOnce = onTerminated != null ? new AtomicReference<>(onTerminated) : null;
            if (capacityHint > 1) {
                q = UnsafeAccess.isUnsafeAvailable() ? new SpscUnboundedArrayQueue<>(capacityHint) : new SpscUnboundedAtomicArrayQueue<>(capacityHint);
            } else {
                q = UnsafeAccess.isUnsafeAvailable() ? new SpscLinkedQueue<>() : new SpscLinkedAtomicQueue<>();
            }
            this.queue = q;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (!this.done) {
                if (!this.caughtUp) {
                    boolean stillReplay = false;
                    synchronized (this) {
                        if (!this.caughtUp) {
                            this.queue.offer(NotificationLite.next(t));
                            stillReplay = true;
                        }
                    }
                    if (stillReplay) {
                        replay();
                        return;
                    }
                }
                Subscriber<? super T> s = this.subscriber.get();
                try {
                    s.onNext(t);
                } catch (Throwable ex) {
                    Exceptions.throwOrReport(ex, s, t);
                }
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) throws Throwable {
            if (!this.done) {
                doTerminate();
                this.error = e;
                this.done = true;
                if (!this.caughtUp) {
                    synchronized (this) {
                        try {
                            try {
                                boolean stillReplay = true ^ this.caughtUp;
                                if (stillReplay) {
                                    replay();
                                    return;
                                }
                            } catch (Throwable th) {
                                th = th;
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                }
                this.subscriber.get().onError(e);
            }
        }

        @Override // rx.Observer
        public void onCompleted() throws Throwable {
            if (!this.done) {
                doTerminate();
                this.done = true;
                if (!this.caughtUp) {
                    synchronized (this) {
                        try {
                            try {
                                boolean stillReplay = true ^ this.caughtUp;
                                if (stillReplay) {
                                    replay();
                                    return;
                                }
                            } catch (Throwable th) {
                                th = th;
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                }
                this.subscriber.get().onCompleted();
            }
        }

        @Override // rx.Producer
        public void request(long n) {
            if (n < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            }
            if (n > 0) {
                BackpressureUtils.getAndAddRequest(this, n);
                replay();
            } else if (this.done) {
                replay();
            }
        }

        @Override // rx.functions.Action1
        public void call(Subscriber<? super T> subscriber) throws Throwable {
            if (this.subscriber.compareAndSet(null, subscriber)) {
                subscriber.add(this);
                subscriber.setProducer(this);
            } else {
                subscriber.onError(new IllegalStateException("Only a single subscriber is allowed"));
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:46:0x0092, code lost:
        
            if (r5 == false) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x0098, code lost:
        
            if (r2.isEmpty() == false) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x009a, code lost:
        
            r20.caughtUp = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x009c, code lost:
        
            r20.emitting = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00a0, code lost:
        
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void replay() {
            /*
                r20 = this;
                r1 = r20
                monitor-enter(r20)
                boolean r2 = r1.emitting     // Catch: java.lang.Throwable -> Lab
                r3 = 1
                if (r2 == 0) goto Lc
                r1.missed = r3     // Catch: java.lang.Throwable -> Lab
                monitor-exit(r20)     // Catch: java.lang.Throwable -> Lab
                return
            Lc:
                r1.emitting = r3     // Catch: java.lang.Throwable -> Lab
                monitor-exit(r20)     // Catch: java.lang.Throwable -> Lab
                java.util.Queue<java.lang.Object> r2 = r1.queue
            L11:
                java.util.concurrent.atomic.AtomicReference<rx.Subscriber<? super T>> r4 = r1.subscriber
                java.lang.Object r4 = r4.get()
                rx.Subscriber r4 = (rx.Subscriber) r4
                r5 = 0
                if (r4 == 0) goto L8d
                boolean r7 = r1.done
                boolean r8 = r2.isEmpty()
                boolean r9 = r1.checkTerminated(r7, r8, r4)
                if (r9 == 0) goto L29
                return
            L29:
                long r9 = r20.get()
                r11 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
                if (r13 != 0) goto L38
                r11 = r3
                goto L39
            L38:
                r11 = 0
            L39:
                r5 = r11
                r11 = 0
                r13 = r7
                r14 = r8
                r7 = r11
            L3f:
                int r15 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
                if (r15 == 0) goto L83
                boolean r13 = r1.done
                java.lang.Object r15 = r2.poll()
                if (r15 != 0) goto L4e
                r16 = r3
                goto L50
            L4e:
                r16 = 0
            L50:
                r14 = r16
                boolean r16 = r1.checkTerminated(r13, r14, r4)
                if (r16 == 0) goto L59
                return
            L59:
                if (r14 == 0) goto L5c
                goto L83
            L5c:
                java.lang.Object r16 = rx.internal.operators.NotificationLite.getValue(r15)
                r17 = r16
                r6 = r17
                r4.onNext(r6)     // Catch: java.lang.Throwable -> L73
                r16 = 1
                long r18 = r9 - r16
                long r9 = r7 + r16
                r7 = r9
                r9 = r18
                goto L3f
            L73:
                r0 = move-exception
                r3 = r0
                r2.clear()
                rx.exceptions.Exceptions.throwIfFatal(r3)
                java.lang.Throwable r11 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r3, r6)
                r4.onError(r11)
                return
            L83:
                if (r5 != 0) goto L8d
                int r6 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
                if (r6 == 0) goto L8d
                long r11 = -r7
                r1.addAndGet(r11)
            L8d:
                monitor-enter(r20)
                boolean r6 = r1.missed     // Catch: java.lang.Throwable -> La7
                if (r6 != 0) goto La1
                if (r5 == 0) goto L9c
                boolean r6 = r2.isEmpty()     // Catch: java.lang.Throwable -> La7
                if (r6 == 0) goto L9c
                r1.caughtUp = r3     // Catch: java.lang.Throwable -> La7
            L9c:
                r3 = 0
                r1.emitting = r3     // Catch: java.lang.Throwable -> La7
                monitor-exit(r20)     // Catch: java.lang.Throwable -> La7
                return
            La1:
                r6 = 0
                r1.missed = r6     // Catch: java.lang.Throwable -> La7
                monitor-exit(r20)     // Catch: java.lang.Throwable -> La7
                goto L11
            La7:
                r0 = move-exception
                r3 = r0
                monitor-exit(r20)     // Catch: java.lang.Throwable -> La7
                throw r3
            Lab:
                r0 = move-exception
                r2 = r0
                monitor-exit(r20)     // Catch: java.lang.Throwable -> Lab
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.subjects.UnicastSubject.State.replay():void");
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            doTerminate();
            this.done = true;
            synchronized (this) {
                if (this.emitting) {
                    return;
                }
                this.emitting = true;
                this.queue.clear();
            }
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.done;
        }

        boolean checkTerminated(boolean done, boolean empty, Subscriber<? super T> s) {
            if (s.isUnsubscribed()) {
                this.queue.clear();
                return true;
            }
            if (done) {
                Throwable e = this.error;
                if (e != null) {
                    this.queue.clear();
                    s.onError(e);
                    return true;
                }
                if (empty) {
                    s.onCompleted();
                    return true;
                }
                return false;
            }
            return false;
        }

        void doTerminate() {
            Action0 a;
            AtomicReference<Action0> ref = this.terminateOnce;
            if (ref != null && (a = ref.get()) != null && ref.compareAndSet(a, null)) {
                a.call();
            }
        }
    }
}
