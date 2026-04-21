package com.google.android.gms.tasks;

/* JADX INFO: loaded from: classes.dex */
final class zzf implements Runnable {
    private final /* synthetic */ Task zzafn;
    private final /* synthetic */ zze zzafp;

    zzf(zze zzeVar, Task task) {
        this.zzafp = zzeVar;
        this.zzafn = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Task task = (Task) this.zzafp.zzafl.then(this.zzafn);
            if (task == null) {
                this.zzafp.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
            task.addOnSuccessListener(TaskExecutors.zzagd, this.zzafp);
            task.addOnFailureListener(TaskExecutors.zzagd, this.zzafp);
            task.addOnCanceledListener(TaskExecutors.zzagd, this.zzafp);
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                this.zzafp.zzafm.setException((Exception) e.getCause());
            } else {
                this.zzafp.zzafm.setException(e);
            }
        } catch (Exception e2) {
            this.zzafp.zzafm.setException(e2);
        }
    }
}
