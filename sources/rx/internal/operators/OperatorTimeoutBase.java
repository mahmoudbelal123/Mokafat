package rx.internal.operators;

import java.util.concurrent.TimeoutException;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.internal.producers.ProducerArbiter;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;

/* JADX INFO: loaded from: classes.dex */
class OperatorTimeoutBase<T> implements Observable.Operator<T, T> {
    final FirstTimeoutStub<T> firstTimeoutStub;
    final Observable<? extends T> other;
    final Scheduler scheduler;
    final TimeoutStub<T> timeoutStub;

    interface FirstTimeoutStub<T> extends Func3<TimeoutSubscriber<T>, Long, Scheduler.Worker, Subscription> {
    }

    interface TimeoutStub<T> extends Func4<TimeoutSubscriber<T>, Long, T, Scheduler.Worker, Subscription> {
    }

    OperatorTimeoutBase(FirstTimeoutStub<T> firstTimeoutStub, TimeoutStub<T> timeoutStub, Observable<? extends T> other, Scheduler scheduler) {
        this.firstTimeoutStub = firstTimeoutStub;
        this.timeoutStub = timeoutStub;
        this.other = other;
        this.scheduler = scheduler;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        Scheduler.Worker inner = this.scheduler.createWorker();
        subscriber.add(inner);
        SerializedSubscriber<T> synchronizedSubscriber = new SerializedSubscriber<>(subscriber);
        SerialSubscription serial = new SerialSubscription();
        synchronizedSubscriber.add(serial);
        TimeoutSubscriber<T> timeoutSubscriber = new TimeoutSubscriber<>(synchronizedSubscriber, this.timeoutStub, serial, this.other, inner);
        synchronizedSubscriber.add(timeoutSubscriber);
        synchronizedSubscriber.setProducer(timeoutSubscriber.arbiter);
        serial.set(this.firstTimeoutStub.call(timeoutSubscriber, 0L, inner));
        return timeoutSubscriber;
    }

    static final class TimeoutSubscriber<T> extends Subscriber<T> {
        long actual;
        final ProducerArbiter arbiter = new ProducerArbiter();
        final Scheduler.Worker inner;
        final Observable<? extends T> other;
        final SerialSubscription serial;
        final SerializedSubscriber<T> serializedSubscriber;
        boolean terminated;
        final TimeoutStub<T> timeoutStub;

        TimeoutSubscriber(SerializedSubscriber<T> serializedSubscriber, TimeoutStub<T> timeoutStub, SerialSubscription serial, Observable<? extends T> other, Scheduler.Worker inner) {
            this.serializedSubscriber = serializedSubscriber;
            this.timeoutStub = timeoutStub;
            this.serial = serial;
            this.other = other;
            this.inner = inner;
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer p) {
            this.arbiter.setProducer(p);
        }

        @Override // rx.Observer
        public void onNext(T value) {
            long a;
            boolean onNextWins = false;
            synchronized (this) {
                if (!this.terminated) {
                    long j = this.actual + 1;
                    this.actual = j;
                    a = j;
                    onNextWins = true;
                } else {
                    a = this.actual;
                }
            }
            if (onNextWins) {
                this.serializedSubscriber.onNext(value);
                this.serial.set(this.timeoutStub.call(this, Long.valueOf(a), value, this.inner));
            }
        }

        @Override // rx.Observer
        public void onError(Throwable error) {
            boolean onErrorWins = false;
            synchronized (this) {
                if (!this.terminated) {
                    this.terminated = true;
                    onErrorWins = true;
                }
            }
            if (onErrorWins) {
                this.serial.unsubscribe();
                this.serializedSubscriber.onError(error);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            boolean onCompletedWins = false;
            synchronized (this) {
                if (!this.terminated) {
                    this.terminated = true;
                    onCompletedWins = true;
                }
            }
            if (onCompletedWins) {
                this.serial.unsubscribe();
                this.serializedSubscriber.onCompleted();
            }
        }

        public void onTimeout(long seqId) {
            boolean timeoutWins = false;
            synchronized (this) {
                if (seqId == this.actual && !this.terminated) {
                    this.terminated = true;
                    timeoutWins = true;
                }
            }
            if (timeoutWins) {
                if (this.other == null) {
                    this.serializedSubscriber.onError(new TimeoutException());
                    return;
                }
                Subscriber<T> second = new Subscriber<T>() { // from class: rx.internal.operators.OperatorTimeoutBase.TimeoutSubscriber.1
                    @Override // rx.Observer
                    public void onNext(T t) {
                        TimeoutSubscriber.this.serializedSubscriber.onNext(t);
                    }

                    @Override // rx.Observer
                    public void onError(Throwable e) {
                        TimeoutSubscriber.this.serializedSubscriber.onError(e);
                    }

                    @Override // rx.Observer
                    public void onCompleted() {
                        TimeoutSubscriber.this.serializedSubscriber.onCompleted();
                    }

                    @Override // rx.Subscriber, rx.observers.AssertableSubscriber
                    public void setProducer(Producer p) {
                        TimeoutSubscriber.this.arbiter.setProducer(p);
                    }
                };
                this.other.unsafeSubscribe(second);
                this.serial.set(second);
            }
        }
    }
}
