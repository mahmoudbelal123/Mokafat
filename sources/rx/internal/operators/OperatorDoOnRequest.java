package rx.internal.operators;

import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes.dex */
public class OperatorDoOnRequest<T> implements Observable.Operator<T, T> {
    final Action1<? super Long> request;

    public OperatorDoOnRequest(Action1<? super Long> request) {
        this.request = request;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> child) throws Throwable {
        final ParentSubscriber<T> parent = new ParentSubscriber<>(child);
        child.setProducer(new Producer() { // from class: rx.internal.operators.OperatorDoOnRequest.1
            @Override // rx.Producer
            public void request(long n) {
                OperatorDoOnRequest.this.request.call(Long.valueOf(n));
                parent.requestMore(n);
            }
        });
        child.add(parent);
        return parent;
    }

    static final class ParentSubscriber<T> extends Subscriber<T> {
        private final Subscriber<? super T> child;

        ParentSubscriber(Subscriber<? super T> child) {
            this.child = child;
            request(0L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void requestMore(long n) {
            request(n);
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.child.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.child.onError(e);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.child.onNext(t);
        }
    }
}
