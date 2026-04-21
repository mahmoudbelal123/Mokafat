package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzb implements Runnable {
    private final /* synthetic */ String zzaet;
    private final /* synthetic */ long zzaft;
    private final /* synthetic */ zza zzafu;

    zzb(zza zzaVar, String str, long j) {
        this.zzafu = zzaVar;
        this.zzaet = str;
        this.zzaft = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzafu.zza(this.zzaet, this.zzaft);
    }
}
