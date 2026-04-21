package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzeu implements Runnable {
    private final /* synthetic */ long zzafv;
    private final /* synthetic */ zzeq zzasz;

    zzeu(zzeq zzeqVar, long j) {
        this.zzasz = zzeqVar;
        this.zzafv = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzasz.zzan(this.zzafv);
    }
}
