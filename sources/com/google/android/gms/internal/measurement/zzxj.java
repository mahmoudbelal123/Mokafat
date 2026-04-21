package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
interface zzxj<T> {
    boolean equals(T t, T t2);

    int hashCode(T t);

    T newInstance();

    void zza(T t, zzxi zzxiVar, zzuz zzuzVar) throws IOException;

    void zza(T t, zzyw zzywVar) throws IOException;

    int zzae(T t);

    boolean zzaf(T t);

    void zzd(T t, T t2);

    void zzu(T t);
}
