package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: loaded from: classes.dex */
final class zzbl implements BaseGmsClient.SignOutCallbacks {
    final /* synthetic */ GoogleApiManager.zza zzkk;

    zzbl(GoogleApiManager.zza zzaVar) {
        this.zzkk = zzaVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.SignOutCallbacks
    public final void onSignOutComplete() {
        GoogleApiManager.this.handler.post(new zzbm(this));
    }
}
