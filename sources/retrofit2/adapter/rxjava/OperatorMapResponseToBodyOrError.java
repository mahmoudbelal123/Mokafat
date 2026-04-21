package retrofit2.adapter.rxjava;

import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: loaded from: classes.dex */
final class OperatorMapResponseToBodyOrError<T> implements Observable.Operator<T, Response<T>> {
    private static final OperatorMapResponseToBodyOrError<Object> INSTANCE = new OperatorMapResponseToBodyOrError<>();

    OperatorMapResponseToBodyOrError() {
    }

    static <R> OperatorMapResponseToBodyOrError<R> instance() {
        return (OperatorMapResponseToBodyOrError<R>) INSTANCE;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super Response<T>> call(final Subscriber<? super T> child) {
        return new Subscriber<Response<T>>(child) { // from class: retrofit2.adapter.rxjava.OperatorMapResponseToBodyOrError.1
            @Override // rx.Observer
            public void onNext(Response<T> response) {
                if (response.isSuccessful()) {
                    child.onNext(response.body());
                } else {
                    child.onError(new HttpException(response));
                }
            }

            @Override // rx.Observer
            public void onCompleted() {
                child.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                child.onError(e);
            }
        };
    }
}
