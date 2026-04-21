package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class zzct implements Runnable {
    private final /* synthetic */ AtomicReference zzarb;
    private final /* synthetic */ zzcs zzarc;

    zzct(zzcs zzcsVar, AtomicReference atomicReference) {
        this.zzarc = zzcsVar;
        this.zzarb = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzarb) {
            try {
                this.zzarb.set(Boolean.valueOf(this.zzarc.zzgq().zzba(this.zzarc.zzgf().zzal())));
            } finally {
                this.zzarb.notify();
            }
        }
    }
}
