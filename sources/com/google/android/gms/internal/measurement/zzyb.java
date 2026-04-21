package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
abstract class zzyb<T, B> {
    zzyb() {
    }

    abstract void zza(B b, int i, long j);

    abstract void zza(B b, int i, zzud zzudVar);

    abstract void zza(B b, int i, T t);

    abstract void zza(T t, zzyw zzywVar) throws IOException;

    abstract boolean zza(zzxi zzxiVar);

    abstract T zzab(B b);

    abstract int zzae(T t);

    abstract T zzah(Object obj);

    abstract B zzai(Object obj);

    abstract int zzaj(T t);

    abstract void zzb(B b, int i, long j);

    abstract void zzc(B b, int i, int i2);

    abstract void zzc(T t, zzyw zzywVar) throws IOException;

    abstract void zzf(Object obj, T t);

    abstract void zzg(Object obj, B b);

    abstract T zzh(T t, T t2);

    abstract void zzu(Object obj);

    abstract B zzye();

    final boolean zza(B b, zzxi zzxiVar) throws IOException {
        int tag = zzxiVar.getTag();
        int i = tag >>> 3;
        switch (tag & 7) {
            case 0:
                zza(b, i, zzxiVar.zzui());
                return true;
            case 1:
                zzb(b, i, zzxiVar.zzuk());
                return true;
            case 2:
                zza((Object) b, i, zzxiVar.zzuo());
                return true;
            case 3:
                B bZzye = zzye();
                int i2 = (i << 3) | 4;
                while (zzxiVar.zzve() != Integer.MAX_VALUE && zza(bZzye, zzxiVar)) {
                }
                if (i2 != zzxiVar.getTag()) {
                    throw zzvt.zzwn();
                }
                zza(b, i, zzab(bZzye));
                return true;
            case 4:
                return false;
            case 5:
                zzc(b, i, zzxiVar.zzul());
                return true;
            default:
                throw zzvt.zzwo();
        }
    }
}
