package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzei implements Runnable {
    private final /* synthetic */ zzef zzasp;
    private final /* synthetic */ zzag zzasq;

    zzei(zzef zzefVar, zzag zzagVar) {
        this.zzasp = zzefVar;
        this.zzasq = zzagVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzasp) {
            zzef.zza(this.zzasp, false);
            if (!this.zzasp.zzasg.isConnected()) {
                this.zzasp.zzasg.zzgo().zzjk().zzbx("Connected to remote service");
                this.zzasp.zzasg.zza(this.zzasq);
            }
        }
    }
}
