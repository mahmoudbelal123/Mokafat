package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;

/* JADX INFO: loaded from: classes.dex */
public final class OperatorOnBackpressureLatest<T> implements Observable.Operator<T, T> {

    static final class Holder {
        static final OperatorOnBackpressureLatest<Object> INSTANCE = new OperatorOnBackpressureLatest<>();

        Holder() {
        }
    }

    public static <T> OperatorOnBackpressureLatest<T> instance() {
        return (OperatorOnBackpressureLatest<T>) Holder.INSTANCE;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> child) throws Throwable {
        LatestEmitter<T> producer = new LatestEmitter<>(child);
        LatestSubscriber<? super T> latestSubscriber = new LatestSubscriber<>(producer);
        producer.parent = latestSubscriber;
        child.add(latestSubscriber);
        child.add(producer);
        child.setProducer(producer);
        return latestSubscriber;
    }

    static final class LatestEmitter<T> extends AtomicLong implements Producer, Subscription, Observer<T> {
        static final Object EMPTY = new Object();
        static final long NOT_REQUESTED = -4611686018427387904L;
        private static final long serialVersionUID = -1364393685005146274L;
        final Subscriber<? super T> child;
        volatile boolean done;
        boolean emitting;
        boolean missed;
        LatestSubscriber<? super T> parent;
        Throwable terminal;
        final AtomicReference<Object> value = new AtomicReference<>(EMPTY);

        public LatestEmitter(Subscriber<? super T> child) {
            this.child = child;
            lazySet(NOT_REQUESTED);
        }

        @Override // rx.Producer
        public void request(long n) {
            long r;
            long u;
            if (n >= 0) {
                do {
                    r = get();
                    if (r == Long.MIN_VALUE) {
                        return;
                    }
                    if (r == NOT_REQUESTED) {
                        u = n;
                    } else {
                        u = r + n;
                        if (u < 0) {
                            u = LongCompanionObject.MAX_VALUE;
                        }
                    }
                } while (!compareAndSet(r, u));
                if (r == NOT_REQUESTED) {
                    this.parent.requestMore(LongCompanionObject.MAX_VALUE);
                }
                emit();
            }
        }

        long produced(long n) {
            long r;
            long u;
            do {
                r = get();
                if (r < 0) {
                    return r;
                }
                u = r - n;
            } while (!compareAndSet(r, u));
            return u;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (get() >= 0) {
                getAndSet(Long.MIN_VALUE);
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.value.lazySet(t);
            emit();
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.terminal = e;
            this.done = true;
            emit();
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.done = true;
            emit();
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x005e, code lost:
        
            r8.emitting = false;
            r1 = true;
         */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0077  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void emit() {
            /*
                r8 = this;
                monitor-enter(r8)
                boolean r0 = r8.emitting     // Catch: java.lang.Throwable -> L80
                r1 = 1
                if (r0 == 0) goto La
                r8.missed = r1     // Catch: java.lang.Throwable -> L80
                monitor-exit(r8)     // Catch: java.lang.Throwable -> L80
                return
            La:
                r8.emitting = r1     // Catch: java.lang.Throwable -> L80
                r0 = 0
                r8.missed = r0     // Catch: java.lang.Throwable -> L80
                monitor-exit(r8)     // Catch: java.lang.Throwable -> L80
                r1 = r0
            L11:
                long r2 = r8.get()     // Catch: java.lang.Throwable -> L74
                r4 = -9223372036854775808
                int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r6 != 0) goto L1d
                r1 = 1
                goto L62
            L1d:
                java.util.concurrent.atomic.AtomicReference<java.lang.Object> r4 = r8.value     // Catch: java.lang.Throwable -> L74
                java.lang.Object r4 = r4.get()     // Catch: java.lang.Throwable -> L74
                r5 = 0
                int r7 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
                if (r7 <= 0) goto L42
                java.lang.Object r5 = rx.internal.operators.OperatorOnBackpressureLatest.LatestEmitter.EMPTY     // Catch: java.lang.Throwable -> L74
                if (r4 == r5) goto L42
                r5 = r4
                rx.Subscriber<? super T> r6 = r8.child     // Catch: java.lang.Throwable -> L74
                r6.onNext(r5)     // Catch: java.lang.Throwable -> L74
                java.util.concurrent.atomic.AtomicReference<java.lang.Object> r6 = r8.value     // Catch: java.lang.Throwable -> L74
                java.lang.Object r7 = rx.internal.operators.OperatorOnBackpressureLatest.LatestEmitter.EMPTY     // Catch: java.lang.Throwable -> L74
                r6.compareAndSet(r4, r7)     // Catch: java.lang.Throwable -> L74
                r6 = 1
                r8.produced(r6)     // Catch: java.lang.Throwable -> L74
                java.lang.Object r6 = rx.internal.operators.OperatorOnBackpressureLatest.LatestEmitter.EMPTY     // Catch: java.lang.Throwable -> L74
                r4 = r6
            L42:
                java.lang.Object r5 = rx.internal.operators.OperatorOnBackpressureLatest.LatestEmitter.EMPTY     // Catch: java.lang.Throwable -> L74
                if (r4 != r5) goto L59
                boolean r5 = r8.done     // Catch: java.lang.Throwable -> L74
                if (r5 == 0) goto L59
                java.lang.Throwable r5 = r8.terminal     // Catch: java.lang.Throwable -> L74
                if (r5 == 0) goto L54
                rx.Subscriber<? super T> r6 = r8.child     // Catch: java.lang.Throwable -> L74
                r6.onError(r5)     // Catch: java.lang.Throwable -> L74
                goto L59
            L54:
                rx.Subscriber<? super T> r6 = r8.child     // Catch: java.lang.Throwable -> L74
                r6.onCompleted()     // Catch: java.lang.Throwable -> L74
            L59:
                monitor-enter(r8)     // Catch: java.lang.Throwable -> L74
                boolean r5 = r8.missed     // Catch: java.lang.Throwable -> L71
                if (r5 != 0) goto L6d
                r8.emitting = r0     // Catch: java.lang.Throwable -> L71
                r1 = 1
                monitor-exit(r8)     // Catch: java.lang.Throwable -> L71
            L62:
                if (r1 != 0) goto L6c
                monitor-enter(r8)
                r8.emitting = r0     // Catch: java.lang.Throwable -> L69
                monitor-exit(r8)     // Catch: java.lang.Throwable -> L69
                goto L6c
            L69:
                r0 = move-exception
                monitor-exit(r8)     // Catch: java.lang.Throwable -> L69
                throw r0
            L6c:
                return
            L6d:
                r8.missed = r0     // Catch: java.lang.Throwable -> L71
                monitor-exit(r8)     // Catch: java.lang.Throwable -> L71
                goto L11
            L71:
                r5 = move-exception
                monitor-exit(r8)     // Catch: java.lang.Throwable -> L71
                throw r5     // Catch: java.lang.Throwable -> L74
            L74:
                r2 = move-exception
                if (r1 != 0) goto L7f
                monitor-enter(r8)
                r8.emitting = r0     // Catch: java.lang.Throwable -> L7c
                monitor-exit(r8)     // Catch: java.lang.Throwable -> L7c
                goto L7f
            L7c:
                r0 = move-exception
                monitor-exit(r8)     // Catch: java.lang.Throwable -> L7c
                throw r0
            L7f:
                throw r2
            L80:
                r0 = move-exception
                monitor-exit(r8)     // Catch: java.lang.Throwable -> L80
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorOnBackpressureLatest.LatestEmitter.emit():void");
        }
    }

    static final class LatestSubscriber<T> extends Subscriber<T> {
        private final LatestEmitter<T> producer;

        LatestSubscriber(LatestEmitter<T> producer) {
            this.producer = producer;
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(0L);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.producer.onNext(t);
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.producer.onError(e);
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.producer.onCompleted();
        }

        void requestMore(long n) {
            request(n);
        }
    }
}
