package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzdx extends zzv {
    private final /* synthetic */ zzdr zzasg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdx(zzdr zzdrVar, zzcq zzcqVar) {
        super(zzcqVar);
        this.zzasg = zzdrVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzv
    public final void run() {
        this.zzasg.zzgo().zzjg().zzbx("Tasks have been queued for a long time");
    }
}
