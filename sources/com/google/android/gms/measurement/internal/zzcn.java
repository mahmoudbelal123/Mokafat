package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzcn implements Runnable {
    private final /* synthetic */ String zzaeq;
    private final /* synthetic */ zzbv zzaqo;
    private final /* synthetic */ String zzaqq;
    private final /* synthetic */ String zzaqt;
    private final /* synthetic */ long zzaqu;

    zzcn(zzbv zzbvVar, String str, String str2, String str3, long j) {
        this.zzaqo = zzbvVar;
        this.zzaqt = str;
        this.zzaqq = str2;
        this.zzaeq = str3;
        this.zzaqu = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzaqt == null) {
            this.zzaqo.zzamz.zzmb().zzgh().zza(this.zzaqq, (zzdn) null);
        } else {
            this.zzaqo.zzamz.zzmb().zzgh().zza(this.zzaqq, new zzdn(this.zzaeq, this.zzaqt, this.zzaqu));
        }
    }
}
