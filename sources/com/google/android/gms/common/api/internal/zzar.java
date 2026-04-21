package com.google.android.gms.common.api.internal;

import com.google.android.gms.signin.internal.SignInResponse;

/* JADX INFO: loaded from: classes.dex */
final class zzar extends zzbe {
    private final /* synthetic */ zzaj zzic;
    private final /* synthetic */ SignInResponse zzid;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzar(zzaq zzaqVar, zzbc zzbcVar, zzaj zzajVar, SignInResponse signInResponse) {
        super(zzbcVar);
        this.zzic = zzajVar;
        this.zzid = signInResponse;
    }

    @Override // com.google.android.gms.common.api.internal.zzbe
    public final void zzaq() {
        this.zzic.zza(this.zzid);
    }
}
