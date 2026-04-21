package rx;

import rx.annotations.Experimental;

/* JADX INFO: loaded from: classes.dex */
@Experimental
public interface CompletableSubscriber {
    void onCompleted();

    void onError(Throwable th);

    void onSubscribe(Subscription subscription);
}
