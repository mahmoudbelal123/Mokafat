package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class zzdh implements Runnable {
    private final /* synthetic */ AtomicReference zzarb;
    private final /* synthetic */ zzcs zzarc;

    zzdh(zzcs zzcsVar, AtomicReference atomicReference) {
        this.zzarc = zzcsVar;
        this.zzarb = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzarb) {
            try {
                this.zzarb.set(Double.valueOf(this.zzarc.zzgq().zzc(this.zzarc.zzgf().zzal(), zzaf.zzako)));
            } finally {
                this.zzarb.notify();
            }
        }
    }
}
