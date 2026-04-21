package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzby implements Runnable {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzbv zzaqo;
    private final /* synthetic */ zzl zzaqp;

    zzby(zzbv zzbvVar, zzl zzlVar, zzh zzhVar) {
        this.zzaqo = zzbvVar;
        this.zzaqp = zzlVar;
        this.zzaqn = zzhVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzaqo.zzamz.zzly();
        this.zzaqo.zzamz.zzb(this.zzaqp, this.zzaqn);
    }
}
