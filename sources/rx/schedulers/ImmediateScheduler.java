package rx.schedulers;

import rx.Scheduler;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class ImmediateScheduler extends Scheduler {
    private ImmediateScheduler() {
        throw new IllegalStateException("No instances!");
    }

    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        return null;
    }
}
