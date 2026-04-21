package rx.internal.operators;

import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.observers.SerializedSubscriber;

/* JADX INFO: loaded from: classes.dex */
public final class OperatorSampleWithObservable<T, U> implements Observable.Operator<T, T> {
    static final Object EMPTY_TOKEN = new Object();
    final Observable<U> sampler;

    public OperatorSampleWithObservable(Observable<U> sampler) {
        this.sampler = sampler;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        final AtomicReference atomicReference = new AtomicReference(EMPTY_TOKEN);
        final AtomicReference atomicReference2 = new AtomicReference();
        final Subscriber<U> subscriber2 = new Subscriber<U>() { // from class: rx.internal.operators.OperatorSampleWithObservable.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // rx.Observer
            public void onNext(U t) {
                Object localValue = atomicReference.getAndSet(OperatorSampleWithObservable.EMPTY_TOKEN);
                if (localValue != OperatorSampleWithObservable.EMPTY_TOKEN) {
                    serializedSubscriber.onNext(localValue);
                }
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                serializedSubscriber.onError(e);
                ((Subscription) atomicReference2.get()).unsubscribe();
            }

            @Override // rx.Observer
            public void onCompleted() {
                onNext(null);
                serializedSubscriber.onCompleted();
                ((Subscription) atomicReference2.get()).unsubscribe();
            }
        };
        Subscriber<T> subscriber3 = new Subscriber<T>() { // from class: rx.internal.operators.OperatorSampleWithObservable.2
            @Override // rx.Observer
            public void onNext(T t) {
                atomicReference.set(t);
            }

            @Override // rx.Observer
            public void onError(Throwable e) throws Throwable {
                serializedSubscriber.onError(e);
                subscriber2.unsubscribe();
            }

            @Override // rx.Observer
            public void onCompleted() throws Throwable {
                subscriber2.onNext(null);
                serializedSubscriber.onCompleted();
                subscriber2.unsubscribe();
            }
        };
        atomicReference2.lazySet(subscriber3);
        subscriber.add(subscriber3);
        subscriber.add(subscriber2);
        this.sampler.unsafeSubscribe(subscriber2);
        return subscriber3;
    }
}
