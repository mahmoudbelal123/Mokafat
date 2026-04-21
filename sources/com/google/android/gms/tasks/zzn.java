package com.google.android.gms.tasks;

/* JADX INFO: loaded from: classes.dex */
final class zzn implements Runnable {
    private final /* synthetic */ Task zzafn;
    private final /* synthetic */ zzm zzafx;

    zzn(zzm zzmVar, Task task) {
        this.zzafx = zzmVar;
        this.zzafn = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzafx.mLock) {
            if (this.zzafx.zzafw != null) {
                this.zzafx.zzafw.onSuccess(this.zzafn.getResult());
            }
        }
    }
}
