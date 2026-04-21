package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
class zzey extends zzco implements zzcq {
    protected final zzfa zzamz;

    zzey(zzfa zzfaVar) {
        super(zzfaVar.zzmb());
        Preconditions.checkNotNull(zzfaVar);
        this.zzamz = zzfaVar;
    }

    public zzq zzjq() {
        return this.zzamz.zzjq();
    }

    public zzj zzjp() {
        return this.zzamz.zzjp();
    }

    public zzfg zzjo() {
        return this.zzamz.zzjo();
    }
}
