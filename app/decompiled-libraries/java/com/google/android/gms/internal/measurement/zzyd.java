package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
final class zzyd extends zzyb<zzyc, zzyc> {
    zzyd() {
    }

    @Override // com.google.android.gms.internal.measurement.zzyb
    final boolean zza(zzxi zzxiVar) {
        return false;
    }

    private static void zza(Object obj, zzyc zzycVar) {
        ((zzvm) obj).zzbym = zzycVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzyb
    final void zzu(Object obj) {
        ((zzvm) obj).zzbym.zzsm();
    }

    @Override // com.google.android.gms.internal.measurement.zzyb
    final /* synthetic */ int zzae(zzyc zzycVar) {
        return zzycVar.zzvu();
    }

    @Override // com.google.android.gms.internal.measurement.zzyb
    final /* synthetic */ int zzaj(zzyc zzycVar) {
        return zzycVar.zzyh();
    }

    @Override // com.google.android.gms.internal.measurement.zzyb
    final /* synthetic */ zzyc zzh(zzyc zzycVar, zzyc zzycVar2) {
        zzyc zzycVar3 = zzycVar;
        zzyc zzycVar4 = zzycVar2;
        if (zzycVar4.equals(zzyc.zzyf())) {
            return zzycVar3;
        }
        return zzyc.zza(zzycVar3, zzycVar4);
    }

    @Override // com.google.android.gms.internal.measurement.zzyb
    final /* synthetic */ void zzc(zzyc zzycVar, zzyw zzywVar) throws IOException {
        zzycVar.zza(zzywVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzyb
    final /* synthetic */ void zza(zzyc zzycVar, zzyw zzywVar) throws IOException {
        zzycVar.zzb(zzywVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzyb
    final /* synthetic */ void zzg(Object obj, zzyc zzycVar) {
        zza(obj, zzycVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzyb
    final /* synthetic */ zzyc zzai(Object obj) {
        zzyc zzycVar = ((zzvm) obj).zzbym;
        if (zzycVar == zzyc.zzyf()) {
            zzyc zzycVarZzyg = zzyc.zzyg();
            zza(obj, zzycVarZzyg);
            return zzycVarZzyg;
        }
        return zzycVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzyb
    final /* synthetic */ zzyc zzah(Object obj) {
        return ((zzvm) obj).zzbym;
    }

    @Override // com.google.android.gms.internal.measurement.zzyb
    final /* synthetic */ void zzf(Object obj, zzyc zzycVar) {
        zza(obj, zzycVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzyb
    final /* synthetic */ zzyc zzab(zzyc zzycVar) {
        zzyc zzycVar2 = zzycVar;
        zzycVar2.zzsm();
        return zzycVar2;
    }

    @Override // com.google.android.gms.internal.measurement.zzyb
    final /* synthetic */ zzyc zzye() {
        return zzyc.zzyg();
    }

    @Override // com.google.android.gms.internal.measurement.zzyb
    final /* synthetic */ void zza(zzyc zzycVar, int i, zzyc zzycVar2) {
        zzycVar.zzb((i << 3) | 3, zzycVar2);
    }

    @Override // com.google.android.gms.internal.measurement.zzyb
    final /* synthetic */ void zza(zzyc zzycVar, int i, zzud zzudVar) {
        zzycVar.zzb((i << 3) | 2, zzudVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzyb
    final /* synthetic */ void zzb(zzyc zzycVar, int i, long j) {
        zzycVar.zzb((i << 3) | 1, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.measurement.zzyb
    final /* synthetic */ void zzc(zzyc zzycVar, int i, int i2) {
        zzycVar.zzb((i << 3) | 5, Integer.valueOf(i2));
    }

    @Override // com.google.android.gms.internal.measurement.zzyb
    final /* synthetic */ void zza(zzyc zzycVar, int i, long j) {
        zzycVar.zzb(i << 3, Long.valueOf(j));
    }
}
