package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.GoogleApiManager;

/* JADX INFO: loaded from: classes.dex */
final class zzbk implements Runnable {
    private final /* synthetic */ GoogleApiManager.zza zzkk;
    private final /* synthetic */ ConnectionResult zzkl;

    zzbk(GoogleApiManager.zza zzaVar, ConnectionResult connectionResult) {
        this.zzkk = zzaVar;
        this.zzkl = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzkk.onConnectionFailed(this.zzkl);
    }
}
