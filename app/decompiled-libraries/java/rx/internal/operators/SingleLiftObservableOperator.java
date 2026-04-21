package rx.internal.operators;

import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.operators.SingleFromObservable;
import rx.internal.producers.SingleProducer;
import rx.plugins.RxJavaHooks;

/* JADX INFO: loaded from: classes.dex */
public final class SingleLiftObservableOperator<T, R> implements Single.OnSubscribe<R> {
    final Observable.Operator<? extends R, ? super T> lift;
    final Single.OnSubscribe<T> source;

    public SingleLiftObservableOperator(Single.OnSubscribe<T> source, Observable.Operator<? extends R, ? super T> lift) {
        this.source = source;
        this.lift = lift;
    }

    @Override // rx.functions.Action1
    public void call(SingleSubscriber<? super R> t) {
        SingleFromObservable.WrapSingleIntoSubscriber wrapSingleIntoSubscriber = new SingleFromObservable.WrapSingleIntoSubscriber(t);
        t.add(wrapSingleIntoSubscriber);
        try {
            Subscriber<? super T> inputAsSubscriber = RxJavaHooks.onSingleLift(this.lift).call(wrapSingleIntoSubscriber);
            SingleSubscriber singleSubscriberWrap = wrap(inputAsSubscriber);
            inputAsSubscriber.onStart();
            this.source.call(singleSubscriberWrap);
        } catch (Throwable ex) {
            Exceptions.throwOrReport(ex, t);
        }
    }

    public static <T> SingleSubscriber<T> wrap(Subscriber<T> subscriber) {
        WrapSubscriberIntoSingle<T> parent = new WrapSubscriberIntoSingle<>(subscriber);
        subscriber.add(parent);
        return parent;
    }

    static final class WrapSubscriberIntoSingle<T> extends SingleSubscriber<T> {
        final Subscriber<? super T> actual;

        WrapSubscriberIntoSingle(Subscriber<? super T> actual) {
            this.actual = actual;
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T value) throws Throwable {
            this.actual.setProducer(new SingleProducer(this.actual, value));
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable error) {
            this.actual.onError(error);
        }
    }
}
