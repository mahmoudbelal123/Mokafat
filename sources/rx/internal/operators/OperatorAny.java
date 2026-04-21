package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.producers.SingleDelayedProducer;
import rx.plugins.RxJavaHooks;

/* JADX INFO: loaded from: classes.dex */
public final class OperatorAny<T> implements Observable.Operator<Boolean, T> {
    final Func1<? super T, Boolean> predicate;
    final boolean returnOnEmpty;

    public OperatorAny(Func1<? super T, Boolean> predicate, boolean returnOnEmpty) {
        this.predicate = predicate;
        this.returnOnEmpty = returnOnEmpty;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super Boolean> subscriber) throws Throwable {
        final SingleDelayedProducer singleDelayedProducer = new SingleDelayedProducer(subscriber);
        Subscriber<T> subscriber2 = new Subscriber<T>() { // from class: rx.internal.operators.OperatorAny.1
            boolean done;
            boolean hasElements;

            @Override // rx.Observer
            public void onNext(T t) {
                if (this.done) {
                    return;
                }
                this.hasElements = true;
                try {
                    boolean result = OperatorAny.this.predicate.call(t).booleanValue();
                    if (result) {
                        this.done = true;
                        singleDelayedProducer.setValue(Boolean.valueOf(true ^ OperatorAny.this.returnOnEmpty));
                        unsubscribe();
                    }
                } catch (Throwable e) {
                    Exceptions.throwOrReport(e, this, t);
                }
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                if (!this.done) {
                    this.done = true;
                    subscriber.onError(e);
                } else {
                    RxJavaHooks.onError(e);
                }
            }

            @Override // rx.Observer
            public void onCompleted() {
                if (!this.done) {
                    this.done = true;
                    if (this.hasElements) {
                        singleDelayedProducer.setValue(false);
                    } else {
                        singleDelayedProducer.setValue(Boolean.valueOf(OperatorAny.this.returnOnEmpty));
                    }
                }
            }
        };
        subscriber.add(subscriber2);
        subscriber.setProducer(singleDelayedProducer);
        return subscriber2;
    }
}
