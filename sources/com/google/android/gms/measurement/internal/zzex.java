package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzex extends zzv {
    private final /* synthetic */ zzfa zzasv;
    private final /* synthetic */ zzew zzatb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzex(zzew zzewVar, zzcq zzcqVar, zzfa zzfaVar) {
        super(zzcqVar);
        this.zzatb = zzewVar;
        this.zzasv = zzfaVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzv
    public final void run() {
        this.zzatb.cancel();
        this.zzatb.zzgo().zzjl().zzbx("Starting upload from DelayedRunnable");
        this.zzasv.zzlt();
    }
}
