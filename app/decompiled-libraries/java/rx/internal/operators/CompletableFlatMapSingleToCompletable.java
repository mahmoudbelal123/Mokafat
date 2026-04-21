package rx.internal.operators;

import rx.Completable;
import rx.CompletableSubscriber;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes.dex */
public final class CompletableFlatMapSingleToCompletable<T> implements Completable.OnSubscribe {
    final Func1<? super T, ? extends Completable> mapper;
    final Single<T> source;

    public CompletableFlatMapSingleToCompletable(Single<T> source, Func1<? super T, ? extends Completable> mapper) {
        this.source = source;
        this.mapper = mapper;
    }

    @Override // rx.functions.Action1
    public void call(CompletableSubscriber t) {
        SourceSubscriber<T> parent = new SourceSubscriber<>(t, this.mapper);
        t.onSubscribe(parent);
        this.source.subscribe(parent);
    }

    static final class SourceSubscriber<T> extends SingleSubscriber<T> implements CompletableSubscriber {
        final CompletableSubscriber actual;
        final Func1<? super T, ? extends Completable> mapper;

        public SourceSubscriber(CompletableSubscriber actual, Func1<? super T, ? extends Completable> mapper) {
            this.actual = actual;
            this.mapper = mapper;
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T value) {
            try {
                Completable c = this.mapper.call(value);
                if (c == null) {
                    onError(new NullPointerException("The mapper returned a null Completable"));
                } else {
                    c.subscribe(this);
                }
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                onError(ex);
            }
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable error) {
            this.actual.onError(error);
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            this.actual.onCompleted();
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription d) {
            add(d);
        }
    }
}
