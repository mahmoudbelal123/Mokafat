package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
final class zzcb implements Callable<List<zzfj>> {
    private final /* synthetic */ String zzaeh;
    private final /* synthetic */ String zzaeo;
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzbv zzaqo;

    zzcb(zzbv zzbvVar, zzh zzhVar, String str, String str2) {
        this.zzaqo = zzbvVar;
        this.zzaqn = zzhVar;
        this.zzaeh = str;
        this.zzaeo = str2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzfj> call() throws Exception {
        this.zzaqo.zzamz.zzly();
        return this.zzaqo.zzamz.zzjq().zzb(this.zzaqn.packageName, this.zzaeh, this.zzaeo);
    }
}
