package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class zzcx implements Runnable {
    private final /* synthetic */ boolean zzaev;
    private final /* synthetic */ AtomicReference zzarb;
    private final /* synthetic */ zzcs zzarc;

    zzcx(zzcs zzcsVar, AtomicReference atomicReference, boolean z) {
        this.zzarc = zzcsVar;
        this.zzarb = atomicReference;
        this.zzaev = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzarc.zzgg().zza(this.zzarb, this.zzaev);
    }
}
