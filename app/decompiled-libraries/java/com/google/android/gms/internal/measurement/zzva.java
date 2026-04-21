package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzvf;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
abstract class zzva<T extends zzvf<T>> {
    zzva() {
    }

    abstract Object zza(zzuz zzuzVar, zzwt zzwtVar, int i);

    abstract <UT, UB> UB zza(zzxi zzxiVar, Object obj, zzuz zzuzVar, zzvd<T> zzvdVar, UB ub, zzyb<UT, UB> zzybVar) throws IOException;

    abstract void zza(zzud zzudVar, Object obj, zzuz zzuzVar, zzvd<T> zzvdVar) throws IOException;

    abstract void zza(zzxi zzxiVar, Object obj, zzuz zzuzVar, zzvd<T> zzvdVar) throws IOException;

    abstract void zza(zzyw zzywVar, Map.Entry<?, ?> entry) throws IOException;

    abstract void zza(Object obj, zzvd<T> zzvdVar);

    abstract int zzb(Map.Entry<?, ?> entry);

    abstract boolean zze(zzwt zzwtVar);

    abstract zzvd<T> zzs(Object obj);

    abstract zzvd<T> zzt(Object obj);

    abstract void zzu(Object obj);
}
