package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzcw implements Runnable {
    private final /* synthetic */ zzcs zzarc;

    zzcw(zzcs zzcsVar) {
        this.zzarc = zzcsVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzarc.zzadj.zzj(true);
    }
}
