package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
abstract class zzf extends zze {
    private boolean zzvz;

    zzf(zzbt zzbtVar) {
        super(zzbtVar);
        this.zzadj.zzb(this);
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
        if (!zzgt()) {
            this.zzadj.zzkq();
            this.zzvz = true;
        }
    }

    public final void zzgs() {
        if (this.zzvz) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzgu();
        this.zzadj.zzkq();
        this.zzvz = true;
    }

    protected void zzgu() {
    }
}
