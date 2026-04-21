package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzed implements Runnable {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzfh zzaqs;
    private final /* synthetic */ zzdr zzasg;
    private final /* synthetic */ boolean zzasj;

    zzed(zzdr zzdrVar, boolean z, zzfh zzfhVar, zzh zzhVar) {
        this.zzasg = zzdrVar;
        this.zzasj = z;
        this.zzaqs = zzfhVar;
        this.zzaqn = zzhVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzag zzagVar = this.zzasg.zzasa;
        if (zzagVar == null) {
            this.zzasg.zzgo().zzjd().zzbx("Discarding data. Failed to set user attribute");
        } else {
            this.zzasg.zza(zzagVar, this.zzasj ? null : this.zzaqs, this.zzaqn);
            this.zzasg.zzcy();
        }
    }
}
