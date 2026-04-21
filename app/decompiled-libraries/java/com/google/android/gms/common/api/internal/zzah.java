package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzah extends zzbe {
    private final /* synthetic */ zzag zzhh;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzah(zzag zzagVar, zzbc zzbcVar) {
        super(zzbcVar);
        this.zzhh = zzagVar;
    }

    @Override // com.google.android.gms.common.api.internal.zzbe
    public final void zzaq() {
        this.zzhh.onConnectionSuspended(1);
    }
}
