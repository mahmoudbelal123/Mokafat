package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes.dex */
public final class OperatorTakeLast<T> implements Observable.Operator<T, T> {
    final int count;

    public OperatorTakeLast(int count) {
        if (count < 0) {
            throw new IndexOutOfBoundsException("count cannot be negative");
        }
        this.count = count;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) throws Throwable {
        final TakeLastSubscriber<T> parent = new TakeLastSubscriber<>(subscriber, this.count);
        subscriber.add(parent);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTakeLast.1
            @Override // rx.Producer
            public void request(long n) {
                parent.requestMore(n);
            }
        });
        return parent;
    }

    static final class TakeLastSubscriber<T> extends Subscriber<T> implements Func1<Object, T> {
        final Subscriber<? super T> actual;
        final int count;
        final AtomicLong requested = new AtomicLong();
        final ArrayDeque<Object> queue = new ArrayDeque<>();

        public TakeLastSubscriber(Subscriber<? super T> actual, int count) {
            this.actual = actual;
            this.count = count;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.queue.size() == this.count) {
                this.queue.poll();
            }
            this.queue.offer(NotificationLite.next(t));
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.queue.clear();
            this.actual.onError(e);
        }

        @Override // rx.Observer
        public void onCompleted() {
            BackpressureUtils.postCompleteDone(this.requested, this.queue, this.actual, this);
        }

        @Override // rx.functions.Func1
        public T call(Object obj) {
            return (T) NotificationLite.getValue(obj);
        }

        void requestMore(long n) {
            if (n > 0) {
                BackpressureUtils.postCompleteRequest(this.requested, n, this.queue, this.actual, this);
            }
        }
    }
}
