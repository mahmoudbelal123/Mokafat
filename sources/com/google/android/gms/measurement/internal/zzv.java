package com.google.android.gms.measurement.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
abstract class zzv {
    private static volatile Handler handler;
    private final zzcq zzahw;
    private final Runnable zzyo;
    private volatile long zzyp;

    zzv(zzcq zzcqVar) {
        Preconditions.checkNotNull(zzcqVar);
        this.zzahw = zzcqVar;
        this.zzyo = new zzw(this, zzcqVar);
    }

    public abstract void run();

    public final void zzh(long j) {
        cancel();
        if (j >= 0) {
            this.zzyp = this.zzahw.zzbx().currentTimeMillis();
            if (!getHandler().postDelayed(this.zzyo, j)) {
                this.zzahw.zzgo().zzjd().zzg("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }

    public final boolean zzej() {
        return this.zzyp != 0;
    }

    final void cancel() {
        this.zzyp = 0L;
        getHandler().removeCallbacks(this.zzyo);
    }

    private final Handler getHandler() {
        Handler handler2;
        if (handler != null) {
            return handler;
        }
        synchronized (zzv.class) {
            if (handler == null) {
                handler = new com.google.android.gms.internal.measurement.zzdx(this.zzahw.getContext().getMainLooper());
            }
            handler2 = handler;
        }
        return handler2;
    }

    static /* synthetic */ long zza(zzv zzvVar, long j) {
        zzvVar.zzyp = 0L;
        return 0L;
    }
}
