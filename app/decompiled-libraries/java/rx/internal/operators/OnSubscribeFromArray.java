package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;

/* JADX INFO: loaded from: classes.dex */
public final class OnSubscribeFromArray<T> implements Observable.OnSubscribe<T> {
    final T[] array;

    public OnSubscribeFromArray(T[] array) {
        this.array = array;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super T> child) throws Throwable {
        child.setProducer(new FromArrayProducer(child, this.array));
    }

    static final class FromArrayProducer<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = 3534218984725836979L;
        final T[] array;
        final Subscriber<? super T> child;
        int index;

        public FromArrayProducer(Subscriber<? super T> child, T[] array) {
            this.child = child;
            this.array = array;
        }

        @Override // rx.Producer
        public void request(long n) {
            if (n < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            }
            if (n == LongCompanionObject.MAX_VALUE) {
                if (BackpressureUtils.getAndAddRequest(this, n) == 0) {
                    fastPath();
                }
            } else if (n != 0 && BackpressureUtils.getAndAddRequest(this, n) == 0) {
                slowPath(n);
            }
        }

        void fastPath() {
            Subscriber<? super T> child = this.child;
            Object[] arr$ = this.array;
            for (Object obj : arr$) {
                if (child.isUnsubscribed()) {
                    return;
                }
                child.onNext(obj);
            }
            if (child.isUnsubscribed()) {
                return;
            }
            child.onCompleted();
        }

        void slowPath(long j) {
            Subscriber<? super T> subscriber = this.child;
            T[] tArr = this.array;
            int length = tArr.length;
            long j2 = 0;
            int i = this.index;
            while (true) {
                if (j != 0 && i != length) {
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    subscriber.onNext(tArr[i]);
                    i++;
                    if (i == length) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onCompleted();
                            return;
                        }
                        return;
                    } else {
                        j2--;
                        j--;
                    }
                } else {
                    j = get() + j2;
                    if (j == 0) {
                        this.index = i;
                        j = addAndGet(j2);
                        if (j == 0) {
                            return;
                        } else {
                            j2 = 0;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
    }
}
