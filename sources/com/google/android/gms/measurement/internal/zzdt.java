package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
final class zzdt implements Runnable {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzdr zzasg;

    zzdt(zzdr zzdrVar, zzh zzhVar) {
        this.zzasg = zzdrVar;
        this.zzaqn = zzhVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzag zzagVar = this.zzasg.zzasa;
        if (zzagVar == null) {
            this.zzasg.zzgo().zzjd().zzbx("Failed to reset data on the service; null service");
            return;
        }
        try {
            zzagVar.zzd(this.zzaqn);
        } catch (RemoteException e) {
            this.zzasg.zzgo().zzjd().zzg("Failed to reset data on the service", e);
        }
        this.zzasg.zzcy();
    }
}
