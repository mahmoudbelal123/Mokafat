package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class zzee implements Runnable {
    private final /* synthetic */ boolean zzaev;
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzdr zzasg;
    private final /* synthetic */ AtomicReference zzash;

    zzee(zzdr zzdrVar, AtomicReference atomicReference, zzh zzhVar, boolean z) {
        this.zzasg = zzdrVar;
        this.zzash = atomicReference;
        this.zzaqn = zzhVar;
        this.zzaev = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzag zzagVar;
        synchronized (this.zzash) {
            try {
                try {
                    zzagVar = this.zzasg.zzasa;
                } catch (RemoteException e) {
                    this.zzasg.zzgo().zzjd().zzg("Failed to get user properties", e);
                }
                if (zzagVar == null) {
                    this.zzasg.zzgo().zzjd().zzbx("Failed to get user properties");
                    return;
                }
                this.zzash.set(zzagVar.zza(this.zzaqn, this.zzaev));
                this.zzasg.zzcy();
                this.zzash.notify();
            } finally {
                this.zzash.notify();
            }
        }
    }
}
