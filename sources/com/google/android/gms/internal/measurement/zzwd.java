package com.google.android.gms.internal.measurement;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class zzwd {
    private static final zzwd zzcaj;
    private static final zzwd zzcak;

    private zzwd() {
    }

    abstract <L> List<L> zza(Object obj, long j);

    abstract <L> void zza(Object obj, Object obj2, long j);

    abstract void zzb(Object obj, long j);

    static zzwd zzwx() {
        return zzcaj;
    }

    static zzwd zzwy() {
        return zzcak;
    }

    static {
        zzwe zzweVar = null;
        zzcaj = new zzwf();
        zzcak = new zzwg();
    }
}
