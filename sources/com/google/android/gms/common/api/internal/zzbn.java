package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
final class zzbn implements Runnable {
    private final /* synthetic */ ConnectionResult zzkl;
    private final /* synthetic */ GoogleApiManager.zzc zzkr;

    zzbn(GoogleApiManager.zzc zzcVar, ConnectionResult connectionResult) {
        this.zzkr = zzcVar;
        this.zzkl = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!this.zzkl.isSuccess()) {
            ((GoogleApiManager.zza) GoogleApiManager.this.zzju.get(this.zzkr.zzhc)).onConnectionFailed(this.zzkl);
            return;
        }
        GoogleApiManager.zzc.zza(this.zzkr, true);
        if (this.zzkr.zzka.requiresSignIn()) {
            this.zzkr.zzbu();
        } else {
            this.zzkr.zzka.getRemoteService(null, Collections.emptySet());
        }
    }
}
