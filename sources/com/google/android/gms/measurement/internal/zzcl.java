package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
final class zzcl implements Callable<List<zzfj>> {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzbv zzaqo;

    zzcl(zzbv zzbvVar, zzh zzhVar) {
        this.zzaqo = zzbvVar;
        this.zzaqn = zzhVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzfj> call() throws Exception {
        this.zzaqo.zzamz.zzly();
        return this.zzaqo.zzamz.zzjq().zzbk(this.zzaqn.packageName);
    }
}
