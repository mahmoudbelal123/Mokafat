package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzcl implements zzcn {
    private final /* synthetic */ zzck zzmq;

    zzcl(zzck zzckVar) {
        this.zzmq = zzckVar;
    }

    @Override // com.google.android.gms.common.api.internal.zzcn
    public final void zzc(BasePendingResult<?> basePendingResult) {
        this.zzmq.zzmo.remove(basePendingResult);
    }
}
