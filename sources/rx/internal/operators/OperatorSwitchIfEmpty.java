package rx.internal.operators;

import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.internal.producers.ProducerArbiter;
import rx.subscriptions.SerialSubscription;

/* JADX INFO: loaded from: classes.dex */
public final class OperatorSwitchIfEmpty<T> implements Observable.Operator<T, T> {
    private final Observable<? extends T> alternate;

    public OperatorSwitchIfEmpty(Observable<? extends T> alternate) {
        this.alternate = alternate;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> child) throws Throwable {
        SerialSubscription serial = new SerialSubscription();
        ProducerArbiter arbiter = new ProducerArbiter();
        ParentSubscriber<T> parent = new ParentSubscriber<>(child, serial, arbiter, this.alternate);
        serial.set(parent);
        child.add(serial);
        child.setProducer(arbiter);
        return parent;
    }

    static final class ParentSubscriber<T> extends Subscriber<T> {
        private final Observable<? extends T> alternate;
        private final ProducerArbiter arbiter;
        private final Subscriber<? super T> child;
        private boolean empty = true;
        private final SerialSubscription serial;

        ParentSubscriber(Subscriber<? super T> child, SerialSubscription serial, ProducerArbiter arbiter, Observable<? extends T> alternate) {
            this.child = child;
            this.serial = serial;
            this.arbiter = arbiter;
            this.alternate = alternate;
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.arbiter.setProducer(producer);
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (!this.empty) {
                this.child.onCompleted();
            } else if (!this.child.isUnsubscribed()) {
                subscribeToAlternate();
            }
        }

        private void subscribeToAlternate() {
            AlternateSubscriber<T> as = new AlternateSubscriber<>(this.child, this.arbiter);
            this.serial.set(as);
            this.alternate.unsafeSubscribe(as);
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.child.onError(e);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.empty = false;
            this.child.onNext(t);
            this.arbiter.produced(1L);
        }
    }

    static final class AlternateSubscriber<T> extends Subscriber<T> {
        private final ProducerArbiter arbiter;
        private final Subscriber<? super T> child;

        AlternateSubscriber(Subscriber<? super T> child, ProducerArbiter arbiter) {
            this.child = child;
            this.arbiter = arbiter;
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.arbiter.setProducer(producer);
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.child.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.child.onError(e);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.child.onNext(t);
            this.arbiter.produced(1L);
        }
    }
}
