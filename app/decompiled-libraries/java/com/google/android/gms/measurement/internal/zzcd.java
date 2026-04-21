package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
final class zzcd implements Callable<List<zzl>> {
    private final /* synthetic */ String zzaeh;
    private final /* synthetic */ String zzaeo;
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzbv zzaqo;

    zzcd(zzbv zzbvVar, zzh zzhVar, String str, String str2) {
        this.zzaqo = zzbvVar;
        this.zzaqn = zzhVar;
        this.zzaeh = str;
        this.zzaeo = str2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzl> call() throws Exception {
        this.zzaqo.zzamz.zzly();
        return this.zzaqo.zzamz.zzjq().zzc(this.zzaqn.packageName, this.zzaeh, this.zzaeo);
    }
}
