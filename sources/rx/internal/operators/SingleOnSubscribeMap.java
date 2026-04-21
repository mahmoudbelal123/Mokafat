package rx.internal.operators;

import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;
import rx.plugins.RxJavaHooks;

/* JADX INFO: loaded from: classes.dex */
public final class SingleOnSubscribeMap<T, R> implements Single.OnSubscribe<R> {
    final Single<T> source;
    final Func1<? super T, ? extends R> transformer;

    public SingleOnSubscribeMap(Single<T> source, Func1<? super T, ? extends R> transformer) {
        this.source = source;
        this.transformer = transformer;
    }

    @Override // rx.functions.Action1
    public void call(SingleSubscriber<? super R> o) {
        MapSubscriber<T, R> parent = new MapSubscriber<>(o, this.transformer);
        o.add(parent);
        this.source.subscribe(parent);
    }

    static final class MapSubscriber<T, R> extends SingleSubscriber<T> {
        final SingleSubscriber<? super R> actual;
        boolean done;
        final Func1<? super T, ? extends R> mapper;

        public MapSubscriber(SingleSubscriber<? super R> actual, Func1<? super T, ? extends R> mapper) {
            this.actual = actual;
            this.mapper = mapper;
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            try {
                R result = this.mapper.call(t);
                this.actual.onSuccess(result);
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(ex, t));
            }
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable e) {
            if (this.done) {
                RxJavaHooks.onError(e);
            } else {
                this.done = true;
                this.actual.onError(e);
            }
        }
    }
}
