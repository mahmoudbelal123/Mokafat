package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class zzdf implements Runnable {
    private final /* synthetic */ AtomicReference zzarb;
    private final /* synthetic */ zzcs zzarc;

    zzdf(zzcs zzcsVar, AtomicReference atomicReference) {
        this.zzarc = zzcsVar;
        this.zzarb = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzarb) {
            try {
                this.zzarb.set(Long.valueOf(this.zzarc.zzgq().zza(this.zzarc.zzgf().zzal(), zzaf.zzakm)));
            } finally {
                this.zzarb.notify();
            }
        }
    }
}
