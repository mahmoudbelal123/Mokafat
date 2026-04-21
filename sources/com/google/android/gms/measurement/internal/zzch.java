package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzch implements Runnable {
    private final /* synthetic */ zzbv zzaqo;
    private final /* synthetic */ String zzaqq;
    private final /* synthetic */ zzad zzaqr;

    zzch(zzbv zzbvVar, zzad zzadVar, String str) {
        this.zzaqo = zzbvVar;
        this.zzaqr = zzadVar;
        this.zzaqq = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzaqo.zzamz.zzly();
        this.zzaqo.zzamz.zzc(this.zzaqr, this.zzaqq);
    }
}
