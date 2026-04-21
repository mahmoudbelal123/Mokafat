package rx.internal.operators;

import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.plugins.RxJavaHooks;

/* JADX INFO: loaded from: classes.dex */
public class OperatorCast<T, R> implements Observable.Operator<R, T> {
    final Class<R> castClass;

    public OperatorCast(Class<R> castClass) {
        this.castClass = castClass;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super R> o) {
        CastSubscriber<T, R> parent = new CastSubscriber<>(o, this.castClass);
        o.add(parent);
        return parent;
    }

    static final class CastSubscriber<T, R> extends Subscriber<T> {
        final Subscriber<? super R> actual;
        final Class<R> castClass;
        boolean done;

        public CastSubscriber(Subscriber<? super R> actual, Class<R> castClass) {
            this.actual = actual;
            this.castClass = castClass;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                R result = this.castClass.cast(t);
                this.actual.onNext(result);
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
            this.actual.setProducer(p);
        }
    }
}
