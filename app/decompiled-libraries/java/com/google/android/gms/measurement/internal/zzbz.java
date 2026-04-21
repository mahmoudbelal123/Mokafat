package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzbz implements Runnable {
    private final /* synthetic */ zzbv zzaqo;
    private final /* synthetic */ zzl zzaqp;

    zzbz(zzbv zzbvVar, zzl zzlVar) {
        this.zzaqo = zzbvVar;
        this.zzaqp = zzlVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzaqo.zzamz.zzly();
        this.zzaqo.zzamz.zzf(this.zzaqp);
    }
}
