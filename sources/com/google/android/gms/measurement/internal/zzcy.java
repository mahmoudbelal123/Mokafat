package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class zzcy implements Runnable {
    private final /* synthetic */ AtomicReference zzarb;
    private final /* synthetic */ zzcs zzarc;

    zzcy(zzcs zzcsVar, AtomicReference atomicReference) {
        this.zzarc = zzcsVar;
        this.zzarb = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzarc.zzgg().zza(this.zzarb);
    }
}
