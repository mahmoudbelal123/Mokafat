package rx;

import rx.annotations.Experimental;
import rx.functions.Cancellable;

/* JADX INFO: loaded from: classes.dex */
@Experimental
public interface Emitter<T> extends Observer<T> {

    public enum BackpressureMode {
        NONE,
        ERROR,
        BUFFER,
        DROP,
        LATEST
    }

    long requested();

    void setCancellation(Cancellable cancellable);

    void setSubscription(Subscription subscription);
}
