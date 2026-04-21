package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzdq implements Runnable {
    private final /* synthetic */ zzdo zzarx;
    private final /* synthetic */ zzdn zzary;

    zzdq(zzdo zzdoVar, zzdn zzdnVar) {
        this.zzarx = zzdoVar;
        this.zzary = zzdnVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzarx.zza(this.zzary);
        this.zzarx.zzaro = null;
        this.zzarx.zzgg().zzb((zzdn) null);
    }
}
