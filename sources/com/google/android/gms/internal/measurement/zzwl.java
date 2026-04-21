package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zzwl<K, V> {
    static <K, V> void zza(zzut zzutVar, zzwm<K, V> zzwmVar, K k, V v) throws IOException {
        zzvd.zza(zzutVar, zzwmVar.zzcar, 1, k);
        zzvd.zza(zzutVar, zzwmVar.zzcat, 2, v);
    }

    static <K, V> int zza(zzwm<K, V> zzwmVar, K k, V v) {
        return zzvd.zza(zzwmVar.zzcar, 1, k) + zzvd.zza(zzwmVar.zzcat, 2, v);
    }
}
