package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzcf implements Runnable {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzbv zzaqo;

    zzcf(zzbv zzbvVar, zzh zzhVar) {
        this.zzaqo = zzbvVar;
        this.zzaqn = zzhVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzaqo.zzamz.zzly();
        this.zzaqo.zzamz.zzd(this.zzaqn);
    }
}
