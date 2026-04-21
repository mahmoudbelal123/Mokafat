package rx.internal.operators;

import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.exceptions.AssemblyStackTraceException;

/* JADX INFO: loaded from: classes.dex */
public final class OnSubscribeOnAssemblyCompletable<T> implements Completable.OnSubscribe {
    public static volatile boolean fullStackTrace;
    final Completable.OnSubscribe source;
    final String stacktrace = OnSubscribeOnAssembly.createStacktrace();

    public OnSubscribeOnAssemblyCompletable(Completable.OnSubscribe source) {
        this.source = source;
    }

    @Override // rx.functions.Action1
    public void call(CompletableSubscriber t) {
        this.source.call(new OnAssemblyCompletableSubscriber(t, this.stacktrace));
    }

    static final class OnAssemblyCompletableSubscriber implements CompletableSubscriber {
        final CompletableSubscriber actual;
        final String stacktrace;

        public OnAssemblyCompletableSubscriber(CompletableSubscriber actual, String stacktrace) {
            this.actual = actual;
            this.stacktrace = stacktrace;
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription d) {
            this.actual.onSubscribe(d);
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            this.actual.onCompleted();
        }

        @Override // rx.CompletableSubscriber
        public void onError(Throwable e) {
            new AssemblyStackTraceException(this.stacktrace).attachTo(e);
            this.actual.onError(e);
        }
    }
}
