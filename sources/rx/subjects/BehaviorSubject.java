package rx.subjects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.internal.operators.NotificationLite;
import rx.subjects.SubjectSubscriptionManager;

/* JADX INFO: loaded from: classes.dex */
public final class BehaviorSubject<T> extends Subject<T, T> {
    private static final Object[] EMPTY_ARRAY = new Object[0];
    private final SubjectSubscriptionManager<T> state;

    public static <T> BehaviorSubject<T> create() {
        return create(null, false);
    }

    public static <T> BehaviorSubject<T> create(T defaultValue) {
        return create(defaultValue, true);
    }

    private static <T> BehaviorSubject<T> create(T defaultValue, boolean hasDefault) {
        final SubjectSubscriptionManager<T> state = new SubjectSubscriptionManager<>();
        if (hasDefault) {
            state.setLatest(NotificationLite.next(defaultValue));
        }
        state.onAdded = new Action1<SubjectSubscriptionManager.SubjectObserver<T>>() { // from class: rx.subjects.BehaviorSubject.1
            @Override // rx.functions.Action1
            public void call(SubjectSubscriptionManager.SubjectObserver<T> o) {
                o.emitFirst(state.getLatest());
            }
        };
        state.onTerminated = state.onAdded;
        return new BehaviorSubject<>(state, state);
    }

    protected BehaviorSubject(Observable.OnSubscribe<T> onSubscribe, SubjectSubscriptionManager<T> state) {
        super(onSubscribe);
        this.state = state;
    }

    @Override // rx.Observer
    public void onCompleted() {
        Object last = this.state.getLatest();
        if (last == null || this.state.active) {
            Object n = NotificationLite.completed();
            for (SubjectSubscriptionManager.SubjectObserver<T> bo : this.state.terminate(n)) {
                bo.emitNext(n);
            }
        }
    }

    @Override // rx.Observer
    public void onError(Throwable e) {
        Object last = this.state.getLatest();
        if (last == null || this.state.active) {
            Object n = NotificationLite.error(e);
            List<Throwable> errors = null;
            for (SubjectSubscriptionManager.SubjectObserver<T> bo : this.state.terminate(n)) {
                try {
                    bo.emitNext(n);
                } catch (Throwable e2) {
                    if (errors == null) {
                        errors = new ArrayList<>();
                    }
                    errors.add(e2);
                }
            }
            Exceptions.throwIfAny(errors);
        }
    }

    @Override // rx.Observer
    public void onNext(T v) {
        Object last = this.state.getLatest();
        if (last == null || this.state.active) {
            Object n = NotificationLite.next(v);
            for (SubjectSubscriptionManager.SubjectObserver<T> bo : this.state.next(n)) {
                bo.emitNext(n);
            }
        }
    }

    int subscriberCount() {
        return this.state.observers().length;
    }

    @Override // rx.subjects.Subject
    public boolean hasObservers() {
        return this.state.observers().length > 0;
    }

    public boolean hasValue() {
        Object o = this.state.getLatest();
        return NotificationLite.isNext(o);
    }

    public boolean hasThrowable() {
        Object o = this.state.getLatest();
        return NotificationLite.isError(o);
    }

    public boolean hasCompleted() {
        Object o = this.state.getLatest();
        return NotificationLite.isCompleted(o);
    }

    public T getValue() {
        Object latest = this.state.getLatest();
        if (NotificationLite.isNext(latest)) {
            return (T) NotificationLite.getValue(latest);
        }
        return null;
    }

    public Throwable getThrowable() {
        Object o = this.state.getLatest();
        if (NotificationLite.isError(o)) {
            return NotificationLite.getError(o);
        }
        return null;
    }

    public T[] getValues(T[] tArr) {
        Object latest = this.state.getLatest();
        if (NotificationLite.isNext(latest)) {
            if (tArr.length == 0) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), 1));
            }
            tArr[0] = NotificationLite.getValue(latest);
            if (tArr.length > 1) {
                tArr[1] = null;
            }
        } else if (tArr.length > 0) {
            tArr[0] = null;
        }
        return tArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object[] getValues() {
        Object[] values = getValues(EMPTY_ARRAY);
        if (values == EMPTY_ARRAY) {
            return new Object[0];
        }
        return values;
    }
}
