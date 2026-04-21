package com.google.android.gms.measurement.internal;

import android.support.annotation.WorkerThread;

/* JADX INFO: loaded from: classes.dex */
final class zzes extends zzv {
    private final /* synthetic */ zzeq zzasz;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzes(zzeq zzeqVar, zzcq zzcqVar) {
        super(zzcqVar);
        this.zzasz = zzeqVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzv
    @WorkerThread
    public final void run() {
        this.zzasz.zzll();
    }
}
