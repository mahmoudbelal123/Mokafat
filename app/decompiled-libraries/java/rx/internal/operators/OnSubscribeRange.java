package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;

/* JADX INFO: loaded from: classes.dex */
public final class OnSubscribeRange implements Observable.OnSubscribe<Integer> {
    private final int endIndex;
    private final int startIndex;

    public OnSubscribeRange(int start, int end) {
        this.startIndex = start;
        this.endIndex = end;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super Integer> childSubscriber) throws Throwable {
        childSubscriber.setProducer(new RangeProducer(childSubscriber, this.startIndex, this.endIndex));
    }

    static final class RangeProducer extends AtomicLong implements Producer {
        private static final long serialVersionUID = 4114392207069098388L;
        private final Subscriber<? super Integer> childSubscriber;
        private long currentIndex;
        private final int endOfRange;

        RangeProducer(Subscriber<? super Integer> childSubscriber, int startIndex, int endIndex) {
            this.childSubscriber = childSubscriber;
            this.currentIndex = startIndex;
            this.endOfRange = endIndex;
        }

        @Override // rx.Producer
        public void request(long requestedAmount) {
            if (get() == LongCompanionObject.MAX_VALUE) {
                return;
            }
            if (requestedAmount == LongCompanionObject.MAX_VALUE && compareAndSet(0L, LongCompanionObject.MAX_VALUE)) {
                fastPath();
            } else if (requestedAmount > 0) {
                long c = BackpressureUtils.getAndAddRequest(this, requestedAmount);
                if (c == 0) {
                    slowPath(requestedAmount);
                }
            }
        }

        void slowPath(long requestedAmount) {
            long emitted = 0;
            long endIndex = ((long) this.endOfRange) + 1;
            long emitted2 = this.currentIndex;
            Subscriber<? super Integer> childSubscriber = this.childSubscriber;
            while (true) {
                if (emitted != requestedAmount && emitted2 != endIndex) {
                    if (childSubscriber.isUnsubscribed()) {
                        return;
                    }
                    childSubscriber.onNext(Integer.valueOf((int) emitted2));
                    long index = emitted2 + 1;
                    long index2 = emitted + 1;
                    emitted = index2;
                    emitted2 = index;
                } else {
                    if (childSubscriber.isUnsubscribed()) {
                        return;
                    }
                    if (emitted2 == endIndex) {
                        childSubscriber.onCompleted();
                        return;
                    }
                    requestedAmount = get();
                    if (requestedAmount == emitted) {
                        this.currentIndex = emitted2;
                        requestedAmount = addAndGet(-emitted);
                        if (requestedAmount != 0) {
                            emitted = 0;
                        } else {
                            return;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }

        void fastPath() {
            long endIndex = ((long) this.endOfRange) + 1;
            Subscriber<? super Integer> childSubscriber = this.childSubscriber;
            for (long index = this.currentIndex; index != endIndex; index++) {
                if (childSubscriber.isUnsubscribed()) {
                    return;
                }
                childSubscriber.onNext(Integer.valueOf((int) index));
            }
            if (childSubscriber.isUnsubscribed()) {
                return;
            }
            childSubscriber.onCompleted();
        }
    }
}
