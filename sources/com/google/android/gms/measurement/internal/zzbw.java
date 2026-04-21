package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzbw implements Runnable {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzbv zzaqo;

    zzbw(zzbv zzbvVar, zzh zzhVar) {
        this.zzaqo = zzbvVar;
        this.zzaqn = zzhVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzaqo.zzamz.zzly();
        this.zzaqo.zzamz.zze(this.zzaqn);
    }
}
