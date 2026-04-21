package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class zzeb implements Runnable {
    private final /* synthetic */ String zzaeh;
    private final /* synthetic */ String zzaeo;
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ String zzaqq;
    private final /* synthetic */ zzdr zzasg;
    private final /* synthetic */ AtomicReference zzash;

    zzeb(zzdr zzdrVar, AtomicReference atomicReference, String str, String str2, String str3, zzh zzhVar) {
        this.zzasg = zzdrVar;
        this.zzash = atomicReference;
        this.zzaqq = str;
        this.zzaeh = str2;
        this.zzaeo = str3;
        this.zzaqn = zzhVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzag zzagVar;
        synchronized (this.zzash) {
            try {
                zzagVar = this.zzasg.zzasa;
            } catch (RemoteException e) {
                this.zzasg.zzgo().zzjd().zzd("Failed to get conditional properties", zzap.zzbv(this.zzaqq), this.zzaeh, e);
                this.zzash.set(Collections.emptyList());
            } finally {
                this.zzash.notify();
            }
            if (zzagVar == null) {
                this.zzasg.zzgo().zzjd().zzd("Failed to get conditional properties", zzap.zzbv(this.zzaqq), this.zzaeh, this.zzaeo);
                this.zzash.set(Collections.emptyList());
            } else {
                if (TextUtils.isEmpty(this.zzaqq)) {
                    this.zzash.set(zzagVar.zza(this.zzaeh, this.zzaeo, this.zzaqn));
                } else {
                    this.zzash.set(zzagVar.zze(this.zzaqq, this.zzaeh, this.zzaeo));
                }
                this.zzasg.zzcy();
            }
        }
    }
}
