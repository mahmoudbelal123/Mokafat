package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzeo implements Runnable {
    private final /* synthetic */ Runnable zzacf;
    private final /* synthetic */ zzfa zzasv;

    zzeo(zzel zzelVar, zzfa zzfaVar, Runnable runnable) {
        this.zzasv = zzfaVar;
        this.zzacf = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzasv.zzly();
        this.zzasv.zzg(this.zzacf);
        this.zzasv.zzlt();
    }
}
