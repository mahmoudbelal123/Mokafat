package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
final class zzcc implements Callable<List<zzfj>> {
    private final /* synthetic */ String zzaeh;
    private final /* synthetic */ String zzaeo;
    private final /* synthetic */ zzbv zzaqo;
    private final /* synthetic */ String zzaqq;

    zzcc(zzbv zzbvVar, String str, String str2, String str3) {
        this.zzaqo = zzbvVar;
        this.zzaqq = str;
        this.zzaeh = str2;
        this.zzaeo = str3;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzfj> call() throws Exception {
        this.zzaqo.zzamz.zzly();
        return this.zzaqo.zzamz.zzjq().zzb(this.zzaqq, this.zzaeh, this.zzaeo);
    }
}
