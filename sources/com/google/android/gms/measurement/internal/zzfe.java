package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
final class zzfe implements Callable<String> {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzfa zzaty;

    zzfe(zzfa zzfaVar, zzh zzhVar) {
        this.zzaty = zzfaVar;
        this.zzaqn = zzhVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        zzg zzgVarZzg = this.zzaty.zzgq().zzbd(this.zzaqn.packageName) ? this.zzaty.zzg(this.zzaqn) : this.zzaty.zzjq().zzbl(this.zzaqn.packageName);
        if (zzgVarZzg != null) {
            return zzgVarZzg.getAppInstanceId();
        }
        this.zzaty.zzgo().zzjg().zzbx("App info was null when attempting to get app instance id");
        return null;
    }
}
