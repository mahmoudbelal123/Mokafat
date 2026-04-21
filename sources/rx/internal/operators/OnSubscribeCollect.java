package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action2;
import rx.functions.Func0;

/* JADX INFO: loaded from: classes.dex */
public final class OnSubscribeCollect<T, R> implements Observable.OnSubscribe<R> {
    final Func0<R> collectionFactory;
    final Action2<R, ? super T> collector;
    final Observable<T> source;

    public OnSubscribeCollect(Observable<T> source, Func0<R> collectionFactory, Action2<R, ? super T> collector) {
        this.source = source;
        this.collectionFactory = collectionFactory;
        this.collector = collector;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super R> t) {
        try {
            R initialValue = this.collectionFactory.call();
            new CollectSubscriber(t, initialValue, this.collector).subscribeTo(this.source);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            t.onError(ex);
        }
    }

    static final class CollectSubscriber<T, R> extends DeferredScalarSubscriberSafe<T, R> {
        final Action2<R, ? super T> collector;

        public CollectSubscriber(Subscriber<? super R> actual, R initialValue, Action2<R, ? super T> collector) {
            super(actual);
            this.value = initialValue;
            this.hasValue = true;
            this.collector = collector;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            try {
                this.collector.call(this.value, t);
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                unsubscribe();
                onError(ex);
            }
        }
    }
}
