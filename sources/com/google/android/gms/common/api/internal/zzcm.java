package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import java.lang.ref.WeakReference;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
final class zzcm implements IBinder.DeathRecipient, zzcn {
    private final WeakReference<BasePendingResult<?>> zzmr;
    private final WeakReference<com.google.android.gms.common.api.zzc> zzms;
    private final WeakReference<IBinder> zzmt;

    private zzcm(BasePendingResult<?> basePendingResult, com.google.android.gms.common.api.zzc zzcVar, IBinder iBinder) {
        this.zzms = new WeakReference<>(zzcVar);
        this.zzmr = new WeakReference<>(basePendingResult);
        this.zzmt = new WeakReference<>(iBinder);
    }

    /* synthetic */ zzcm(BasePendingResult basePendingResult, com.google.android.gms.common.api.zzc zzcVar, IBinder iBinder, zzcl zzclVar) {
        this(basePendingResult, null, iBinder);
    }

    private final void zzcf() {
        BasePendingResult<?> basePendingResult = this.zzmr.get();
        com.google.android.gms.common.api.zzc zzcVar = this.zzms.get();
        if (zzcVar != null && basePendingResult != null) {
            zzcVar.remove(basePendingResult.zzo().intValue());
        }
        IBinder iBinder = this.zzmt.get();
        if (iBinder != null) {
            try {
                iBinder.unlinkToDeath(this, 0);
            } catch (NoSuchElementException e) {
            }
        }
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        zzcf();
    }

    @Override // com.google.android.gms.common.api.internal.zzcn
    public final void zzc(BasePendingResult<?> basePendingResult) {
        zzcf();
    }
}
