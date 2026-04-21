package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class zzdd implements Runnable {
    private final /* synthetic */ AtomicReference zzarb;
    private final /* synthetic */ zzcs zzarc;

    zzdd(zzcs zzcsVar, AtomicReference atomicReference) {
        this.zzarc = zzcsVar;
        this.zzarb = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzarb) {
            try {
                this.zzarb.set(this.zzarc.zzgq().zzbb(this.zzarc.zzgf().zzal()));
            } finally {
                this.zzarb.notify();
            }
        }
    }
}
