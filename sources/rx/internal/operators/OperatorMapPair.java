package rx.internal.operators;

import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.plugins.RxJavaHooks;

/* JADX INFO: loaded from: classes.dex */
public final class OperatorMapPair<T, U, R> implements Observable.Operator<Observable<? extends R>, T> {
    final Func1<? super T, ? extends Observable<? extends U>> collectionSelector;
    final Func2<? super T, ? super U, ? extends R> resultSelector;

    public static <T, U> Func1<T, Observable<U>> convertSelector(final Func1<? super T, ? extends Iterable<? extends U>> selector) {
        return new Func1<T, Observable<U>>() { // from class: rx.internal.operators.OperatorMapPair.1
            @Override // rx.functions.Func1
            public Observable<U> call(T t1) {
                return Observable.from((Iterable) selector.call(t1));
            }
        };
    }

    public OperatorMapPair(Func1<? super T, ? extends Observable<? extends U>> collectionSelector, Func2<? super T, ? super U, ? extends R> resultSelector) {
        this.collectionSelector = collectionSelector;
        this.resultSelector = resultSelector;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super Observable<? extends R>> o) {
        MapPairSubscriber<T, U, R> parent = new MapPairSubscriber<>(o, this.collectionSelector, this.resultSelector);
        o.add(parent);
        return parent;
    }

    static final class MapPairSubscriber<T, U, R> extends Subscriber<T> {
        final Subscriber<? super Observable<? extends R>> actual;
        final Func1<? super T, ? extends Observable<? extends U>> collectionSelector;
        boolean done;
        final Func2<? super T, ? super U, ? extends R> resultSelector;

        public MapPairSubscriber(Subscriber<? super Observable<? extends R>> actual, Func1<? super T, ? extends Observable<? extends U>> collectionSelector, Func2<? super T, ? super U, ? extends R> resultSelector) {
            this.actual = actual;
            this.collectionSelector = collectionSelector;
            this.resultSelector = resultSelector;
        }

        @Override // rx.Observer
        public void onNext(T outer) {
            try {
                Observable<? extends U> intermediate = this.collectionSelector.call(outer);
                this.actual.onNext(intermediate.map(new OuterInnerMapper(outer, this.resultSelector)));
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(ex, outer));
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            if (this.done) {
                RxJavaHooks.onError(e);
            } else {
                this.done = true;
                this.actual.onError(e);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.actual.onCompleted();
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer p) throws Throwable {
            this.actual.setProducer(p);
        }
    }

    static final class OuterInnerMapper<T, U, R> implements Func1<U, R> {
        final T outer;
        final Func2<? super T, ? super U, ? extends R> resultSelector;

        public OuterInnerMapper(T outer, Func2<? super T, ? super U, ? extends R> resultSelector) {
            this.outer = outer;
            this.resultSelector = resultSelector;
        }

        @Override // rx.functions.Func1
        public R call(U u) {
            return this.resultSelector.call(this.outer, u);
        }
    }
}
