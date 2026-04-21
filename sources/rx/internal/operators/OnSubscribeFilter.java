package rx.internal.operators;

import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;
import rx.plugins.RxJavaHooks;

/* JADX INFO: loaded from: classes.dex */
public final class OnSubscribeFilter<T> implements Observable.OnSubscribe<T> {
    final Func1<? super T, Boolean> predicate;
    final Observable<T> source;

    public OnSubscribeFilter(Observable<T> source, Func1<? super T, Boolean> predicate) {
        this.source = source;
        this.predicate = predicate;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super T> child) {
        FilterSubscriber<T> parent = new FilterSubscriber<>(child, this.predicate);
        child.add(parent);
        this.source.unsafeSubscribe(parent);
    }

    static final class FilterSubscriber<T> extends Subscriber<T> {
        final Subscriber<? super T> actual;
        boolean done;
        final Func1<? super T, Boolean> predicate;

        public FilterSubscriber(Subscriber<? super T> actual, Func1<? super T, Boolean> predicate) {
            this.actual = actual;
            this.predicate = predicate;
            request(0L);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                boolean result = this.predicate.call(t).booleanValue();
                if (result) {
                    this.actual.onNext(t);
                } else {
                    request(1L);
                }
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(ex, t));
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
            super.setProducer(p);
            this.actual.setProducer(p);
        }
    }
}
