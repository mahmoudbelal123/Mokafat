package rx;

import rx.annotations.Experimental;
import rx.functions.Cancellable;

/* JADX INFO: loaded from: classes.dex */
@Experimental
public interface SingleEmitter<T> {
    void onError(Throwable th);

    void onSuccess(T t);

    void setCancellation(Cancellable cancellable);

    void setSubscription(Subscription subscription);
}
