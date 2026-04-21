package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
final class zzce implements Callable<List<zzl>> {
    private final /* synthetic */ String zzaeh;
    private final /* synthetic */ String zzaeo;
    private final /* synthetic */ zzbv zzaqo;
    private final /* synthetic */ String zzaqq;

    zzce(zzbv zzbvVar, String str, String str2, String str3) {
        this.zzaqo = zzbvVar;
        this.zzaqq = str;
        this.zzaeh = str2;
        this.zzaeo = str3;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzl> call() throws Exception {
        this.zzaqo.zzamz.zzly();
        return this.zzaqo.zzamz.zzjq().zzc(this.zzaqq, this.zzaeh, this.zzaeo);
    }
}
