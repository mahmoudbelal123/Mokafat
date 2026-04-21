package rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.annotations.Experimental;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.operators.BufferUntilSubscriber;
import rx.observers.SerializedObserver;
import rx.subjects.PublishSubject;
import rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes.dex */
@Experimental
public class SchedulerWhen extends Scheduler implements Subscription {
    static final Subscription SUBSCRIBED = new Subscription() { // from class: rx.internal.schedulers.SchedulerWhen.3
        @Override // rx.Subscription
        public void unsubscribe() {
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return false;
        }
    };
    static final Subscription UNSUBSCRIBED = Subscriptions.unsubscribed();
    private final Scheduler actualScheduler;
    private final Subscription subscription;
    private final Observer<Observable<Completable>> workerObserver;

    public SchedulerWhen(Func1<Observable<Observable<Completable>>, Completable> combine, Scheduler actualScheduler) {
        this.actualScheduler = actualScheduler;
        PublishSubject<Observable<Completable>> workerSubject = PublishSubject.create();
        this.workerObserver = new SerializedObserver(workerSubject);
        this.subscription = combine.call(workerSubject.onBackpressureBuffer()).subscribe();
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        this.subscription.unsubscribe();
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.subscription.isUnsubscribed();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        final Scheduler.Worker actualWorker = this.actualScheduler.createWorker();
        BufferUntilSubscriber<ScheduledAction> actionSubject = BufferUntilSubscriber.create();
        final Observer<ScheduledAction> actionObserver = new SerializedObserver<>(actionSubject);
        Object map = actionSubject.map(new Func1<ScheduledAction, Completable>() { // from class: rx.internal.schedulers.SchedulerWhen.1
            @Override // rx.functions.Func1
            public Completable call(final ScheduledAction action) {
                return Completable.create(new Completable.OnSubscribe() { // from class: rx.internal.schedulers.SchedulerWhen.1.1
                    @Override // rx.functions.Action1
                    public void call(CompletableSubscriber actionCompletable) {
                        actionCompletable.onSubscribe(action);
                        action.call(actualWorker, actionCompletable);
                    }
                });
            }
        });
        Scheduler.Worker worker = new Scheduler.Worker() { // from class: rx.internal.schedulers.SchedulerWhen.2
            private final AtomicBoolean unsubscribed = new AtomicBoolean();

            @Override // rx.Subscription
            public void unsubscribe() {
                if (this.unsubscribed.compareAndSet(false, true)) {
                    actualWorker.unsubscribe();
                    actionObserver.onCompleted();
                }
            }

            @Override // rx.Subscription
            public boolean isUnsubscribed() {
                return this.unsubscribed.get();
            }

            @Override // rx.Scheduler.Worker
            public Subscription schedule(Action0 action, long delayTime, TimeUnit unit) {
                DelayedAction delayedAction = new DelayedAction(action, delayTime, unit);
                actionObserver.onNext(delayedAction);
                return delayedAction;
            }

            @Override // rx.Scheduler.Worker
            public Subscription schedule(Action0 action) {
                ImmediateAction immediateAction = new ImmediateAction(action);
                actionObserver.onNext(immediateAction);
                return immediateAction;
            }
        };
        this.workerObserver.onNext(map);
        return worker;
    }

    static abstract class ScheduledAction extends AtomicReference<Subscription> implements Subscription {
        protected abstract Subscription callActual(Scheduler.Worker worker, CompletableSubscriber completableSubscriber);

        public ScheduledAction() {
            super(SchedulerWhen.SUBSCRIBED);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void call(Scheduler.Worker actualWorker, CompletableSubscriber actionCompletable) {
            Subscription oldState = get();
            if (oldState == SchedulerWhen.UNSUBSCRIBED || oldState != SchedulerWhen.SUBSCRIBED) {
                return;
            }
            Subscription newState = callActual(actualWorker, actionCompletable);
            if (!compareAndSet(SchedulerWhen.SUBSCRIBED, newState)) {
                newState.unsubscribe();
            }
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get().isUnsubscribed();
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            Subscription oldState;
            Subscription newState = SchedulerWhen.UNSUBSCRIBED;
            do {
                oldState = get();
                if (oldState == SchedulerWhen.UNSUBSCRIBED) {
                    return;
                }
            } while (!compareAndSet(oldState, newState));
            if (oldState != SchedulerWhen.SUBSCRIBED) {
                oldState.unsubscribe();
            }
        }
    }

    static class ImmediateAction extends ScheduledAction {
        private final Action0 action;

        public ImmediateAction(Action0 action) {
            this.action = action;
        }

        @Override // rx.internal.schedulers.SchedulerWhen.ScheduledAction
        protected Subscription callActual(Scheduler.Worker actualWorker, CompletableSubscriber actionCompletable) {
            return actualWorker.schedule(new OnCompletedAction(this.action, actionCompletable));
        }
    }

    static class DelayedAction extends ScheduledAction {
        private final Action0 action;
        private final long delayTime;
        private final TimeUnit unit;

        public DelayedAction(Action0 action, long delayTime, TimeUnit unit) {
            this.action = action;
            this.delayTime = delayTime;
            this.unit = unit;
        }

        @Override // rx.internal.schedulers.SchedulerWhen.ScheduledAction
        protected Subscription callActual(Scheduler.Worker actualWorker, CompletableSubscriber actionCompletable) {
            return actualWorker.schedule(new OnCompletedAction(this.action, actionCompletable), this.delayTime, this.unit);
        }
    }

    static class OnCompletedAction implements Action0 {
        private Action0 action;
        private CompletableSubscriber actionCompletable;

        public OnCompletedAction(Action0 action, CompletableSubscriber actionCompletable) {
            this.action = action;
            this.actionCompletable = actionCompletable;
        }

        @Override // rx.functions.Action0
        public void call() {
            try {
                this.action.call();
            } finally {
                this.actionCompletable.onCompleted();
            }
        }
    }
}
