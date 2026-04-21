package rx.internal.operators;

import rx.Observable;
import rx.Single;
import rx.Subscriber;
import rx.internal.operators.SingleLiftObservableOperator;

/* JADX INFO: loaded from: classes.dex */
public final class SingleToObservable<T> implements Observable.OnSubscribe<T> {
    final Single.OnSubscribe<T> source;

    public SingleToObservable(Single.OnSubscribe<T> source) {
        this.source = source;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super T> t) {
        SingleLiftObservableOperator.WrapSubscriberIntoSingle wrapSubscriberIntoSingle = new SingleLiftObservableOperator.WrapSubscriberIntoSingle(t);
        t.add(wrapSubscriberIntoSingle);
        this.source.call(wrapSubscriberIntoSingle);
    }
}
