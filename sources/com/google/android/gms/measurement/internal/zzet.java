package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzet implements Runnable {
    private final /* synthetic */ long zzafv;
    private final /* synthetic */ zzeq zzasz;

    zzet(zzeq zzeqVar, long j) {
        this.zzasz = zzeqVar;
        this.zzafv = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzasz.zzal(this.zzafv);
    }
}
