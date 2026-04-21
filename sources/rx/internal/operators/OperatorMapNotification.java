package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes.dex */
public final class OperatorMapNotification<T, R> implements Observable.Operator<R, T> {
    final Func0<? extends R> onCompleted;
    final Func1<? super Throwable, ? extends R> onError;
    final Func1<? super T, ? extends R> onNext;

    public OperatorMapNotification(Func1<? super T, ? extends R> onNext, Func1<? super Throwable, ? extends R> onError, Func0<? extends R> onCompleted) {
        this.onNext = onNext;
        this.onError = onError;
        this.onCompleted = onCompleted;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super R> child) throws Throwable {
        final MapNotificationSubscriber<T, R> parent = new MapNotificationSubscriber<>(child, this.onNext, this.onError, this.onCompleted);
        child.add(parent);
        child.setProducer(new Producer() { // from class: rx.internal.operators.OperatorMapNotification.1
            @Override // rx.Producer
            public void request(long n) {
                parent.requestInner(n);
            }
        });
        return parent;
    }

    static final class MapNotificationSubscriber<T, R> extends Subscriber<T> {
        static final long COMPLETED_FLAG = Long.MIN_VALUE;
        static final long REQUESTED_MASK = Long.MAX_VALUE;
        final Subscriber<? super R> actual;
        final Func0<? extends R> onCompleted;
        final Func1<? super Throwable, ? extends R> onError;
        final Func1<? super T, ? extends R> onNext;
        long produced;
        R value;
        final AtomicLong requested = new AtomicLong();
        final AtomicLong missedRequested = new AtomicLong();
        final AtomicReference<Producer> producer = new AtomicReference<>();

        public MapNotificationSubscriber(Subscriber<? super R> actual, Func1<? super T, ? extends R> onNext, Func1<? super Throwable, ? extends R> onError, Func0<? extends R> onCompleted) {
            this.actual = actual;
            this.onNext = onNext;
            this.onError = onError;
            this.onCompleted = onCompleted;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                this.produced++;
                this.actual.onNext(this.onNext.call(t));
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.actual, t);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            accountProduced();
            try {
                this.value = this.onError.call(e);
            } catch (Throwable ex) {
                Exceptions.throwOrReport(ex, this.actual, e);
            }
            tryEmit();
        }

        @Override // rx.Observer
        public void onCompleted() {
            accountProduced();
            try {
                this.value = this.onCompleted.call();
            } catch (Throwable ex) {
                Exceptions.throwOrReport(ex, this.actual);
            }
            tryEmit();
        }

        void accountProduced() {
            long p = this.produced;
            if (p != 0 && this.producer.get() != null) {
                BackpressureUtils.produced(this.requested, p);
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer p) {
            if (this.producer.compareAndSet(null, p)) {
                long r = this.missedRequested.getAndSet(0L);
                if (r != 0) {
                    p.request(r);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Producer already set!");
        }

        void tryEmit() {
            long j;
            do {
                j = this.requested.get();
                if ((j & Long.MIN_VALUE) != 0) {
                    return;
                }
            } while (!this.requested.compareAndSet(j, j | Long.MIN_VALUE));
            if (j != 0 || this.producer.get() == null) {
                if (!this.actual.isUnsubscribed()) {
                    this.actual.onNext(this.value);
                }
                if (!this.actual.isUnsubscribed()) {
                    this.actual.onCompleted();
                }
            }
        }

        void requestInner(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            }
            if (j == 0) {
                return;
            }
            while (true) {
                long j2 = this.requested.get();
                if ((j2 & Long.MIN_VALUE) != 0) {
                    long j3 = j2 & Long.MAX_VALUE;
                    if (this.requested.compareAndSet(j2, BackpressureUtils.addCap(j3, j) | Long.MIN_VALUE)) {
                        if (j3 == 0) {
                            if (!this.actual.isUnsubscribed()) {
                                this.actual.onNext(this.value);
                            }
                            if (!this.actual.isUnsubscribed()) {
                                this.actual.onCompleted();
                                return;
                            }
                            return;
                        }
                        return;
                    }
                } else {
                    if (this.requested.compareAndSet(j2, BackpressureUtils.addCap(j2, j))) {
                        AtomicReference<Producer> atomicReference = this.producer;
                        Producer producer = atomicReference.get();
                        if (producer != null) {
                            producer.request(j);
                            return;
                        }
                        BackpressureUtils.getAndAddRequest(this.missedRequested, j);
                        Producer producer2 = atomicReference.get();
                        if (producer2 != null) {
                            long andSet = this.missedRequested.getAndSet(0L);
                            if (andSet != 0) {
                                producer2.request(andSet);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                }
            }
        }
    }
}
