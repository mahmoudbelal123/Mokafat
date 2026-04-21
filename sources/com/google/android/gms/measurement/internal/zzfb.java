package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzfb implements Runnable {
    private final /* synthetic */ zzff zzatx;
    private final /* synthetic */ zzfa zzaty;

    zzfb(zzfa zzfaVar, zzff zzffVar) {
        this.zzaty = zzfaVar;
        this.zzatx = zzffVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzaty.zza(this.zzatx);
        this.zzaty.start();
    }
}
