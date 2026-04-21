package rx.observables;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.annotations.Beta;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.functions.Func1;
import rx.internal.operators.BlockingOperatorLatest;
import rx.internal.operators.BlockingOperatorMostRecent;
import rx.internal.operators.BlockingOperatorNext;
import rx.internal.operators.BlockingOperatorToFuture;
import rx.internal.operators.BlockingOperatorToIterator;
import rx.internal.operators.NotificationLite;
import rx.internal.util.BlockingUtils;
import rx.internal.util.UtilityFunctions;
import rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes.dex */
public final class BlockingObservable<T> {
    static final Object ON_START = new Object();
    static final Object SET_PRODUCER = new Object();
    static final Object UNSUBSCRIBE = new Object();
    private final Observable<? extends T> o;

    private BlockingObservable(Observable<? extends T> o) {
        this.o = o;
    }

    public static <T> BlockingObservable<T> from(Observable<? extends T> o) {
        return new BlockingObservable<>(o);
    }

    public void forEach(final Action1<? super T> action1) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference atomicReference = new AtomicReference();
        BlockingUtils.awaitForComplete(countDownLatch, this.o.subscribe((Subscriber<? super Object>) new Subscriber<T>() { // from class: rx.observables.BlockingObservable.1
            @Override // rx.Observer
            public void onCompleted() {
                countDownLatch.countDown();
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                atomicReference.set(e);
                countDownLatch.countDown();
            }

            @Override // rx.Observer
            public void onNext(T args) {
                action1.call(args);
            }
        }));
        if (atomicReference.get() != null) {
            Exceptions.propagate((Throwable) atomicReference.get());
        }
    }

    public Iterator<T> getIterator() {
        return BlockingOperatorToIterator.toIterator(this.o);
    }

    public T first() {
        return blockForSingle(this.o.first());
    }

    public T first(Func1<? super T, Boolean> predicate) {
        return blockForSingle(this.o.first(predicate));
    }

    public T firstOrDefault(T defaultValue) {
        return blockForSingle(this.o.map(UtilityFunctions.identity()).firstOrDefault(defaultValue));
    }

    public T firstOrDefault(T defaultValue, Func1<? super T, Boolean> predicate) {
        return blockForSingle(this.o.filter(predicate).map(UtilityFunctions.identity()).firstOrDefault(defaultValue));
    }

    public T last() {
        return blockForSingle(this.o.last());
    }

    public T last(Func1<? super T, Boolean> predicate) {
        return blockForSingle(this.o.last(predicate));
    }

    public T lastOrDefault(T defaultValue) {
        return blockForSingle(this.o.map(UtilityFunctions.identity()).lastOrDefault(defaultValue));
    }

    public T lastOrDefault(T defaultValue, Func1<? super T, Boolean> predicate) {
        return blockForSingle(this.o.filter(predicate).map(UtilityFunctions.identity()).lastOrDefault(defaultValue));
    }

    public Iterable<T> mostRecent(T initialValue) {
        return BlockingOperatorMostRecent.mostRecent(this.o, initialValue);
    }

    public Iterable<T> next() {
        return BlockingOperatorNext.next(this.o);
    }

    public Iterable<T> latest() {
        return BlockingOperatorLatest.latest(this.o);
    }

    public T single() {
        return blockForSingle(this.o.single());
    }

    public T single(Func1<? super T, Boolean> predicate) {
        return blockForSingle(this.o.single(predicate));
    }

    public T singleOrDefault(T defaultValue) {
        return blockForSingle(this.o.map(UtilityFunctions.identity()).singleOrDefault(defaultValue));
    }

    public T singleOrDefault(T defaultValue, Func1<? super T, Boolean> predicate) {
        return blockForSingle(this.o.filter(predicate).map(UtilityFunctions.identity()).singleOrDefault(defaultValue));
    }

    public Future<T> toFuture() {
        return BlockingOperatorToFuture.toFuture(this.o);
    }

    public Iterable<T> toIterable() {
        return new Iterable<T>() { // from class: rx.observables.BlockingObservable.2
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return BlockingObservable.this.getIterator();
            }
        };
    }

    private T blockForSingle(Observable<? extends T> observable) {
        final AtomicReference<T> returnItem = new AtomicReference<>();
        final AtomicReference<Throwable> returnException = new AtomicReference<>();
        final CountDownLatch latch = new CountDownLatch(1);
        Subscription subscription = observable.subscribe((Subscriber<? super Object>) new Subscriber<T>() { // from class: rx.observables.BlockingObservable.3
            @Override // rx.Observer
            public void onCompleted() {
                latch.countDown();
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                returnException.set(e);
                latch.countDown();
            }

            @Override // rx.Observer
            public void onNext(T item) {
                returnItem.set(item);
            }
        });
        BlockingUtils.awaitForComplete(latch, subscription);
        if (returnException.get() != null) {
            Exceptions.propagate(returnException.get());
        }
        return returnItem.get();
    }

    @Beta
    public void subscribe() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Throwable[] thArr = {null};
        BlockingUtils.awaitForComplete(countDownLatch, this.o.subscribe((Subscriber<? super Object>) new Subscriber<T>() { // from class: rx.observables.BlockingObservable.4
            @Override // rx.Observer
            public void onNext(T t) {
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                thArr[0] = e;
                countDownLatch.countDown();
            }

            @Override // rx.Observer
            public void onCompleted() {
                countDownLatch.countDown();
            }
        }));
        Throwable th = thArr[0];
        if (th != null) {
            Exceptions.propagate(th);
        }
    }

    @Beta
    public void subscribe(Observer<? super T> observer) {
        Object objPoll;
        final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        Subscription subscriptionSubscribe = this.o.subscribe((Subscriber<? super Object>) new Subscriber<T>() { // from class: rx.observables.BlockingObservable.5
            @Override // rx.Observer
            public void onNext(T t) {
                linkedBlockingQueue.offer(NotificationLite.next(t));
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                linkedBlockingQueue.offer(NotificationLite.error(e));
            }

            @Override // rx.Observer
            public void onCompleted() {
                linkedBlockingQueue.offer(NotificationLite.completed());
            }
        });
        do {
            try {
                objPoll = linkedBlockingQueue.poll();
                if (objPoll == null) {
                    objPoll = linkedBlockingQueue.take();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                observer.onError(e);
                return;
            } finally {
                subscriptionSubscribe.unsubscribe();
            }
        } while (!NotificationLite.accept(observer, objPoll));
    }

    @Beta
    public void subscribe(Subscriber<? super T> subscriber) throws Throwable {
        final BlockingQueue<Object> queue = new LinkedBlockingQueue<>();
        final Producer[] theProducer = {null};
        Subscriber<T> s = new Subscriber<T>() { // from class: rx.observables.BlockingObservable.6
            @Override // rx.Observer
            public void onNext(T t) {
                queue.offer(NotificationLite.next(t));
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                queue.offer(NotificationLite.error(e));
            }

            @Override // rx.Observer
            public void onCompleted() {
                queue.offer(NotificationLite.completed());
            }

            @Override // rx.Subscriber, rx.observers.AssertableSubscriber
            public void setProducer(Producer p) {
                theProducer[0] = p;
                queue.offer(BlockingObservable.SET_PRODUCER);
            }

            @Override // rx.Subscriber, rx.observers.AssertableSubscriber
            public void onStart() {
                queue.offer(BlockingObservable.ON_START);
            }
        };
        subscriber.add(s);
        subscriber.add(Subscriptions.create(new Action0() { // from class: rx.observables.BlockingObservable.7
            @Override // rx.functions.Action0
            public void call() {
                queue.offer(BlockingObservable.UNSUBSCRIBE);
            }
        }));
        this.o.subscribe((Subscriber<? super Object>) s);
        while (!subscriber.isUnsubscribed()) {
            try {
                try {
                    Object o = queue.poll();
                    if (o == null) {
                        o = queue.take();
                    }
                    if (subscriber.isUnsubscribed() || o == UNSUBSCRIBE) {
                        break;
                    }
                    if (o == ON_START) {
                        subscriber.onStart();
                    } else if (o == SET_PRODUCER) {
                        subscriber.setProducer(theProducer[0]);
                    } else if (NotificationLite.accept(subscriber, o)) {
                        return;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    subscriber.onError(e);
                }
            } finally {
                s.unsubscribe();
            }
        }
    }

    @Beta
    public void subscribe(Action1<? super T> onNext) {
        subscribe(onNext, new Action1<Throwable>() { // from class: rx.observables.BlockingObservable.8
            @Override // rx.functions.Action1
            public void call(Throwable t) {
                throw new OnErrorNotImplementedException(t);
            }
        }, Actions.empty());
    }

    @Beta
    public void subscribe(Action1<? super T> onNext, Action1<? super Throwable> onError) {
        subscribe(onNext, onError, Actions.empty());
    }

    @Beta
    public void subscribe(final Action1<? super T> onNext, final Action1<? super Throwable> onError, final Action0 onCompleted) {
        subscribe(new Observer<T>() { // from class: rx.observables.BlockingObservable.9
            @Override // rx.Observer
            public void onNext(T t) {
                onNext.call(t);
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                onError.call(e);
            }

            @Override // rx.Observer
            public void onCompleted() {
                onCompleted.call();
            }
        });
    }
}
