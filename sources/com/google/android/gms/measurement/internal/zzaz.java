package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzaz implements Runnable {
    private final /* synthetic */ boolean zzana;
    private final /* synthetic */ zzay zzanb;

    zzaz(zzay zzayVar, boolean z) {
        this.zzanb = zzayVar;
        this.zzana = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzanb.zzamz.zzo(this.zzana);
    }
}
