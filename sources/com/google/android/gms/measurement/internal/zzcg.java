package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzcg implements Runnable {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzbv zzaqo;
    private final /* synthetic */ zzad zzaqr;

    zzcg(zzbv zzbvVar, zzad zzadVar, zzh zzhVar) {
        this.zzaqo = zzbvVar;
        this.zzaqr = zzadVar;
        this.zzaqn = zzhVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzad zzadVarZzb = this.zzaqo.zzb(this.zzaqr, this.zzaqn);
        this.zzaqo.zzamz.zzly();
        this.zzaqo.zzamz.zzc(zzadVarZzb, this.zzaqn);
    }
}
