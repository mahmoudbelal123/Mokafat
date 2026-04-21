package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzdi implements Runnable {
    private final /* synthetic */ boolean zzaes;
    private final /* synthetic */ zzcs zzarc;

    zzdi(zzcs zzcsVar, boolean z) {
        this.zzarc = zzcsVar;
        this.zzaes = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzarc.zzk(this.zzaes);
    }
}
