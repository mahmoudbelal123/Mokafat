package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzd implements Runnable {
    private final /* synthetic */ zza zzafu;
    private final /* synthetic */ long zzafv;

    zzd(zza zzaVar, long j) {
        this.zzafu = zzaVar;
        this.zzafv = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzafu.zzr(this.zzafv);
    }
}
