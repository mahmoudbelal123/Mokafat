package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;

/* JADX INFO: loaded from: classes.dex */
public final class OnSubscribeTakeLastOne<T> implements Observable.OnSubscribe<T> {
    final Observable<T> source;

    public OnSubscribeTakeLastOne(Observable<T> source) {
        this.source = source;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super T> t) {
        new TakeLastOneSubscriber(t).subscribeTo(this.source);
    }

    static final class TakeLastOneSubscriber<T> extends DeferredScalarSubscriber<T, T> {
        static final Object EMPTY = new Object();

        /* JADX WARN: Type inference failed for: r0v0, types: [R, java.lang.Object] */
        public TakeLastOneSubscriber(Subscriber<? super T> actual) {
            super(actual);
            this.value = EMPTY;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // rx.Observer
        public void onNext(T t) {
            this.value = t;
        }

        @Override // rx.internal.operators.DeferredScalarSubscriber, rx.Observer
        public void onCompleted() {
            Object o = this.value;
            if (o == EMPTY) {
                complete();
            } else {
                complete(o);
            }
        }
    }
}
