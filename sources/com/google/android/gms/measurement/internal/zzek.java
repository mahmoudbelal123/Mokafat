package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzek implements Runnable {
    private final /* synthetic */ zzef zzasp;

    zzek(zzef zzefVar) {
        this.zzasp = zzefVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzdr.zza(this.zzasp.zzasg, (zzag) null);
        this.zzasp.zzasg.zzlf();
    }
}
