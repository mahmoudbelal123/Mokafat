package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzck implements Runnable {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzbv zzaqo;
    private final /* synthetic */ zzfh zzaqs;

    zzck(zzbv zzbvVar, zzfh zzfhVar, zzh zzhVar) {
        this.zzaqo = zzbvVar;
        this.zzaqs = zzfhVar;
        this.zzaqn = zzhVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzaqo.zzamz.zzly();
        this.zzaqo.zzamz.zzb(this.zzaqs, this.zzaqn);
    }
}
