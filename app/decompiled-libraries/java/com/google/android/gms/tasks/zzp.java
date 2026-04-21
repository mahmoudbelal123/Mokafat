package com.google.android.gms.tasks;

import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes.dex */
final class zzp implements Runnable {
    private final /* synthetic */ Task zzafn;
    private final /* synthetic */ zzo zzafz;

    zzp(zzo zzoVar, Task task) {
        this.zzafz = zzoVar;
        this.zzafn = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Task taskThen = this.zzafz.zzafy.then(this.zzafn.getResult());
            if (taskThen == null) {
                this.zzafz.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
            taskThen.addOnSuccessListener(TaskExecutors.zzagd, this.zzafz);
            taskThen.addOnFailureListener(TaskExecutors.zzagd, this.zzafz);
            taskThen.addOnCanceledListener(TaskExecutors.zzagd, this.zzafz);
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                this.zzafz.onFailure((Exception) e.getCause());
            } else {
                this.zzafz.onFailure(e);
            }
        } catch (CancellationException e2) {
            this.zzafz.onCanceled();
        } catch (Exception e3) {
            this.zzafz.onFailure(e3);
        }
    }
}
