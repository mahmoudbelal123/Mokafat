package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class zzfc implements zzav {
    private final /* synthetic */ zzfa zzaty;
    private final /* synthetic */ String zzatz;

    zzfc(zzfa zzfaVar, String str) {
        this.zzaty = zzfaVar;
        this.zzatz = str;
    }

    @Override // com.google.android.gms.measurement.internal.zzav
    public final void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        this.zzaty.zza(i, th, bArr, this.zzatz);
    }
}
