package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;

/* JADX INFO: loaded from: classes.dex */
public final class OnSubscribeThrow<T> implements Observable.OnSubscribe<T> {
    private final Throwable exception;

    public OnSubscribeThrow(Throwable exception) {
        this.exception = exception;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super T> observer) {
        observer.onError(this.exception);
    }
}
