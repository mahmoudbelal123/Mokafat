package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.AssemblyStackTraceException;

/* JADX INFO: loaded from: classes.dex */
public final class OnSubscribeOnAssembly<T> implements Observable.OnSubscribe<T> {
    public static volatile boolean fullStackTrace;
    final Observable.OnSubscribe<T> source;
    final String stacktrace = createStacktrace();

    public OnSubscribeOnAssembly(Observable.OnSubscribe<T> source) {
        this.source = source;
    }

    static String createStacktrace() {
        StackTraceElement[] stacktraceElements = Thread.currentThread().getStackTrace();
        StringBuilder sb = new StringBuilder("Assembly trace:");
        for (StackTraceElement e : stacktraceElements) {
            String row = e.toString();
            if (fullStackTrace || (e.getLineNumber() > 1 && !row.contains("RxJavaHooks.") && !row.contains("OnSubscribeOnAssembly") && !row.contains(".junit.runner") && !row.contains(".junit4.runner") && !row.contains(".junit.internal") && !row.contains("sun.reflect") && !row.contains("java.lang.Thread.") && !row.contains("ThreadPoolExecutor") && !row.contains("org.apache.catalina.") && !row.contains("org.apache.tomcat."))) {
                sb.append("\n at ");
                sb.append(row);
            }
        }
        sb.append("\nOriginal exception:");
        return sb.toString();
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super T> subscriber) {
        this.source.call((T) new OnAssemblySubscriber(subscriber, this.stacktrace));
    }

    static final class OnAssemblySubscriber<T> extends Subscriber<T> {
        final Subscriber<? super T> actual;
        final String stacktrace;

        public OnAssemblySubscriber(Subscriber<? super T> actual, String stacktrace) {
            super(actual);
            this.actual = actual;
            this.stacktrace = stacktrace;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.actual.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            new AssemblyStackTraceException(this.stacktrace).attachTo(e);
            this.actual.onError(e);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.actual.onNext(t);
        }
    }
}
