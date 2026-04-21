package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzw implements Runnable {
    private final /* synthetic */ zzcq zzahx;
    private final /* synthetic */ zzv zzahy;

    zzw(zzv zzvVar, zzcq zzcqVar) {
        this.zzahy = zzvVar;
        this.zzahx = zzcqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzahx.zzgr();
        if (zzk.isMainThread()) {
            this.zzahx.zzgn().zzc(this);
            return;
        }
        boolean zZzej = this.zzahy.zzej();
        zzv.zza(this.zzahy, 0L);
        if (zZzej) {
            this.zzahy.run();
        }
    }
}
