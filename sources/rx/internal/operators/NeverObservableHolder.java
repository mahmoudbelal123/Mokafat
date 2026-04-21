package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;

/* JADX INFO: loaded from: classes.dex */
public enum NeverObservableHolder implements Observable.OnSubscribe<Object> {
    INSTANCE;

    static final Observable<Object> NEVER = Observable.create(INSTANCE);

    public static <T> Observable<T> instance() {
        return (Observable<T>) NEVER;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super Object> child) {
    }
}
