package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
abstract class zzez extends zzey {
    private boolean zzvz;

    zzez(zzfa zzfaVar) {
        super(zzfaVar);
        this.zzamz.zzb(this);
    }

    protected abstract boolean zzgt();

    final boolean isInitialized() {
        return this.zzvz;
    }

    protected final void zzcl() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzq() {
        if (this.zzvz) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzgt();
        this.zzamz.zzma();
        this.zzvz = true;
    }
}
