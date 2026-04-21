package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzvm;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class zzvb extends zzva<Object> {
    zzvb() {
    }

    @Override // com.google.android.gms.internal.measurement.zzva
    final boolean zze(zzwt zzwtVar) {
        return zzwtVar instanceof zzvm.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzva
    final zzvd<Object> zzs(Object obj) {
        return ((zzvm.zzc) obj).zzbys;
    }

    @Override // com.google.android.gms.internal.measurement.zzva
    final void zza(Object obj, zzvd<Object> zzvdVar) {
        ((zzvm.zzc) obj).zzbys = zzvdVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzva
    final zzvd<Object> zzt(Object obj) {
        zzvd<Object> zzvdVarZzs = zzs(obj);
        if (zzvdVarZzs.isImmutable()) {
            zzvd<Object> zzvdVar = (zzvd) zzvdVarZzs.clone();
            zza(obj, zzvdVar);
            return zzvdVar;
        }
        return zzvdVarZzs;
    }

    @Override // com.google.android.gms.internal.measurement.zzva
    final void zzu(Object obj) {
        zzs(obj).zzsm();
    }

    @Override // com.google.android.gms.internal.measurement.zzva
    final <UT, UB> UB zza(zzxi zzxiVar, Object obj, zzuz zzuzVar, zzvd<Object> zzvdVar, UB ub, zzyb<UT, UB> zzybVar) throws IOException {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.measurement.zzva
    final int zzb(Map.Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.measurement.zzva
    final void zza(zzyw zzywVar, Map.Entry<?, ?> entry) throws IOException {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.measurement.zzva
    final Object zza(zzuz zzuzVar, zzwt zzwtVar, int i) {
        return zzuzVar.zza(zzwtVar, i);
    }

    @Override // com.google.android.gms.internal.measurement.zzva
    final void zza(zzxi zzxiVar, Object obj, zzuz zzuzVar, zzvd<Object> zzvdVar) throws IOException {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.measurement.zzva
    final void zza(zzud zzudVar, Object obj, zzuz zzuzVar, zzvd<Object> zzvdVar) throws IOException {
        throw new NoSuchMethodError();
    }
}
