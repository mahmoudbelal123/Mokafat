package rx;

import java.util.concurrent.TimeUnit;
import rx.annotations.Experimental;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.schedulers.SchedulerWhen;
import rx.internal.subscriptions.SequentialSubscription;

/* JADX INFO: loaded from: classes.dex */
public abstract class Scheduler {
    static final long CLOCK_DRIFT_TOLERANCE_NANOS = TimeUnit.MINUTES.toNanos(Long.getLong("rx.scheduler.drift-tolerance", 15).longValue());

    public abstract Worker createWorker();

    public static abstract class Worker implements Subscription {
        public abstract Subscription schedule(Action0 action0);

        public abstract Subscription schedule(Action0 action0, long j, TimeUnit timeUnit);

        public Subscription schedulePeriodically(final Action0 action, long initialDelay, long period, TimeUnit unit) {
            final long periodInNanos = unit.toNanos(period);
            final long firstNowNanos = TimeUnit.MILLISECONDS.toNanos(now());
            final long firstStartInNanos = firstNowNanos + unit.toNanos(initialDelay);
            SequentialSubscription first = new SequentialSubscription();
            final SequentialSubscription mas = new SequentialSubscription(first);
            Action0 recursiveAction = new Action0() { // from class: rx.Scheduler.Worker.1
                long count;
                long lastNowNanos;
                long startInNanos;

                {
                    this.lastNowNanos = firstNowNanos;
                    this.startInNanos = firstStartInNanos;
                }

                @Override // rx.functions.Action0
                public void call() {
                    long nextTick;
                    action.call();
                    if (!mas.isUnsubscribed()) {
                        long nowNanos = TimeUnit.MILLISECONDS.toNanos(Worker.this.now());
                        if (nowNanos + Scheduler.CLOCK_DRIFT_TOLERANCE_NANOS < this.lastNowNanos || nowNanos >= this.lastNowNanos + periodInNanos + Scheduler.CLOCK_DRIFT_TOLERANCE_NANOS) {
                            long nextTick2 = nowNanos + periodInNanos;
                            long j = periodInNanos;
                            long j2 = this.count + 1;
                            this.count = j2;
                            this.startInNanos = nextTick2 - (j * j2);
                            nextTick = nextTick2;
                        } else {
                            long j3 = this.startInNanos;
                            long j4 = this.count + 1;
                            this.count = j4;
                            nextTick = j3 + (j4 * periodInNanos);
                        }
                        this.lastNowNanos = nowNanos;
                        long delay = nextTick - nowNanos;
                        mas.replace(Worker.this.schedule(this, delay, TimeUnit.NANOSECONDS));
                    }
                }
            };
            first.replace(schedule(recursiveAction, initialDelay, unit));
            return mas;
        }

        public long now() {
            return System.currentTimeMillis();
        }
    }

    public long now() {
        return System.currentTimeMillis();
    }

    @Experimental
    public <S extends Scheduler & Subscription> S when(Func1<Observable<Observable<Completable>>, Completable> combine) {
        return new SchedulerWhen(combine, this);
    }
}
