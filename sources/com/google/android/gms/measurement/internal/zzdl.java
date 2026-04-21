package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzdl implements Runnable {
    private final /* synthetic */ zzcs zzarc;
    private final /* synthetic */ long zzark;

    zzdl(zzcs zzcsVar, long j) {
        this.zzarc = zzcsVar;
        this.zzark = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzarc.zzgp().zzanq.set(this.zzark);
        this.zzarc.zzgo().zzjk().zzg("Session timeout duration set", Long.valueOf(this.zzark));
    }
}
