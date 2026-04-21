package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.GoogleApiManager;

/* JADX INFO: loaded from: classes.dex */
final class zzbi implements Runnable {
    private final /* synthetic */ GoogleApiManager.zza zzkk;

    zzbi(GoogleApiManager.zza zzaVar) {
        this.zzkk = zzaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzkk.zzbj();
    }
}
