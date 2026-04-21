package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

/* JADX INFO: loaded from: classes.dex */
class zzco implements zzcq {
    protected final zzbt zzadj;

    zzco(zzbt zzbtVar) {
        Preconditions.checkNotNull(zzbtVar);
        this.zzadj = zzbtVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public zzk zzgr() {
        return this.zzadj.zzgr();
    }

    public zzn zzgq() {
        return this.zzadj.zzgq();
    }

    public zzba zzgp() {
        return this.zzadj.zzgp();
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public zzap zzgo() {
        return this.zzadj.zzgo();
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public zzbo zzgn() {
        return this.zzadj.zzgn();
    }

    public zzfk zzgm() {
        return this.zzadj.zzgm();
    }

    public zzan zzgl() {
        return this.zzadj.zzgl();
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public Context getContext() {
        return this.zzadj.getContext();
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public Clock zzbx() {
        return this.zzadj.zzbx();
    }

    public zzx zzgk() {
        return this.zzadj.zzgk();
    }

    public void zzaf() {
        this.zzadj.zzgn().zzaf();
    }

    public void zzgc() {
        this.zzadj.zzgn().zzgc();
    }

    public void zzgb() {
        this.zzadj.zzgb();
    }

    public void zzga() {
        this.zzadj.zzga();
    }
}
