package rx.internal.operators;

import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes.dex */
public final class SingleOnErrorReturn<T> implements Single.OnSubscribe<T> {
    final Func1<Throwable, ? extends T> resumeFunction;
    final Single.OnSubscribe<T> source;

    public SingleOnErrorReturn(Single.OnSubscribe<T> source, Func1<Throwable, ? extends T> resumeFunction) {
        this.source = source;
        this.resumeFunction = resumeFunction;
    }

    @Override // rx.functions.Action1
    public void call(SingleSubscriber<? super T> t) {
        OnErrorReturnsSingleSubscriber onErrorReturnsSingleSubscriber = new OnErrorReturnsSingleSubscriber(t, this.resumeFunction);
        t.add(onErrorReturnsSingleSubscriber);
        this.source.call(onErrorReturnsSingleSubscriber);
    }

    static final class OnErrorReturnsSingleSubscriber<T> extends SingleSubscriber<T> {
        final SingleSubscriber<? super T> actual;
        final Func1<Throwable, ? extends T> resumeFunction;

        public OnErrorReturnsSingleSubscriber(SingleSubscriber<? super T> actual, Func1<Throwable, ? extends T> resumeFunction) {
            this.actual = actual;
            this.resumeFunction = resumeFunction;
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T value) {
            this.actual.onSuccess(value);
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable error) {
            try {
                T v = this.resumeFunction.call(error);
                this.actual.onSuccess(v);
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.actual.onError(ex);
            }
        }
    }
}
