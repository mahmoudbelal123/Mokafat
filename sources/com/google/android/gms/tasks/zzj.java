package com.google.android.gms.tasks;

/* JADX INFO: loaded from: classes.dex */
final class zzj implements Runnable {
    private final /* synthetic */ Task zzafn;
    private final /* synthetic */ zzi zzaft;

    zzj(zzi zziVar, Task task) {
        this.zzaft = zziVar;
        this.zzafn = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzaft.mLock) {
            if (this.zzaft.zzafs != null) {
                this.zzaft.zzafs.onComplete(this.zzafn);
            }
        }
    }
}
