package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzbu implements Runnable {
    private final /* synthetic */ zzcr zzaqj;
    private final /* synthetic */ zzbt zzaqk;

    zzbu(zzbt zzbtVar, zzcr zzcrVar) {
        this.zzaqk = zzbtVar;
        this.zzaqj = zzcrVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzaqk.zza(this.zzaqj);
        this.zzaqk.start();
    }
}
