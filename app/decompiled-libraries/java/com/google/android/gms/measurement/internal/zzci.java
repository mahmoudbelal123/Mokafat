package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
final class zzci implements Callable<byte[]> {
    private final /* synthetic */ zzbv zzaqo;
    private final /* synthetic */ String zzaqq;
    private final /* synthetic */ zzad zzaqr;

    zzci(zzbv zzbvVar, zzad zzadVar, String str) {
        this.zzaqo = zzbvVar;
        this.zzaqr = zzadVar;
        this.zzaqq = str;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ byte[] call() throws Exception {
        this.zzaqo.zzamz.zzly();
        return this.zzaqo.zzamz.zza(this.zzaqr, this.zzaqq);
    }
}
