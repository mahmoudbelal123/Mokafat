package rx.internal.operators;

import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.plugins.RxJavaHooks;

/* JADX INFO: loaded from: classes.dex */
public final class SingleDoAfterTerminate<T> implements Single.OnSubscribe<T> {
    final Action0 action;
    final Single<T> source;

    public SingleDoAfterTerminate(Single<T> source, Action0 action) {
        this.source = source;
        this.action = action;
    }

    @Override // rx.functions.Action1
    public void call(SingleSubscriber<? super T> t) {
        SingleDoAfterTerminateSubscriber<T> parent = new SingleDoAfterTerminateSubscriber<>(t, this.action);
        t.add(parent);
        this.source.subscribe(parent);
    }

    static final class SingleDoAfterTerminateSubscriber<T> extends SingleSubscriber<T> {
        final Action0 action;
        final SingleSubscriber<? super T> actual;

        public SingleDoAfterTerminateSubscriber(SingleSubscriber<? super T> actual, Action0 action) {
            this.actual = actual;
            this.action = action;
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T value) {
            try {
                this.actual.onSuccess(value);
            } finally {
                doAction();
            }
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable error) {
            try {
                this.actual.onError(error);
            } finally {
                doAction();
            }
        }

        void doAction() {
            try {
                this.action.call();
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                RxJavaHooks.onError(ex);
            }
        }
    }
}
