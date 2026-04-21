package rx.internal.operators;

import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.AssemblyStackTraceException;

/* JADX INFO: loaded from: classes.dex */
public final class OnSubscribeOnAssemblySingle<T> implements Single.OnSubscribe<T> {
    public static volatile boolean fullStackTrace;
    final Single.OnSubscribe<T> source;
    final String stacktrace = OnSubscribeOnAssembly.createStacktrace();

    public OnSubscribeOnAssemblySingle(Single.OnSubscribe<T> source) {
        this.source = source;
    }

    @Override // rx.functions.Action1
    public void call(SingleSubscriber<? super T> singleSubscriber) {
        this.source.call((T) new OnAssemblySingleSubscriber(singleSubscriber, this.stacktrace));
    }

    static final class OnAssemblySingleSubscriber<T> extends SingleSubscriber<T> {
        final SingleSubscriber<? super T> actual;
        final String stacktrace;

        public OnAssemblySingleSubscriber(SingleSubscriber<? super T> actual, String stacktrace) {
            this.actual = actual;
            this.stacktrace = stacktrace;
            actual.add(this);
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable e) {
            new AssemblyStackTraceException(this.stacktrace).attachTo(e);
            this.actual.onError(e);
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            this.actual.onSuccess(t);
        }
    }
}
