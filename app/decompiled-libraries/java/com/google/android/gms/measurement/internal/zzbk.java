package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzbk implements Runnable {
    private final /* synthetic */ zzbt zzaoj;
    private final /* synthetic */ zzap zzaok;

    zzbk(zzbj zzbjVar, zzbt zzbtVar, zzap zzapVar) {
        this.zzaoj = zzbtVar;
        this.zzaok = zzapVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzaoj.zzkg() == null) {
            this.zzaok.zzjd().zzbx("Install Referrer Reporter is null");
            return;
        }
        zzbg zzbgVarZzkg = this.zzaoj.zzkg();
        zzbgVarZzkg.zzadj.zzgb();
        zzbgVarZzkg.zzcd(zzbgVarZzkg.zzadj.getContext().getPackageName());
    }
}
