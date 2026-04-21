package com.google.android.gms.internal.measurement;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class zzwg extends zzwd {
    private zzwg() {
        super();
    }

    @Override // com.google.android.gms.internal.measurement.zzwd
    final <L> List<L> zza(Object obj, long j) {
        zzvs zzvsVarZzd = zzd(obj, j);
        if (zzvsVarZzd.zztw()) {
            return zzvsVarZzd;
        }
        int size = zzvsVarZzd.size();
        zzvs zzvsVarZzak = zzvsVarZzd.zzak(size == 0 ? 10 : size << 1);
        zzyh.zza(obj, j, zzvsVarZzak);
        return zzvsVarZzak;
    }

    @Override // com.google.android.gms.internal.measurement.zzwd
    final void zzb(Object obj, long j) {
        zzd(obj, j).zzsm();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.android.gms.internal.measurement.zzvs] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r6v1, types: [com.google.android.gms.internal.measurement.zzvs, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v3 */
    @Override // com.google.android.gms.internal.measurement.zzwd
    final <E> void zza(Object obj, Object obj2, long j) {
        zzvs zzvsVarZzd = zzd(obj, j);
        ?? Zzd = zzd(obj2, j);
        int size = zzvsVarZzd.size();
        int size2 = Zzd.size();
        ?? r0 = zzvsVarZzd;
        r0 = zzvsVarZzd;
        if (size > 0 && size2 > 0) {
            boolean zZztw = zzvsVarZzd.zztw();
            ?? Zzak = zzvsVarZzd;
            if (!zZztw) {
                Zzak = zzvsVarZzd.zzak(size2 + size);
            }
            Zzak.addAll(Zzd);
            r0 = Zzak;
        }
        if (size > 0) {
            Zzd = r0;
        }
        zzyh.zza(obj, j, (Object) Zzd);
    }

    private static <E> zzvs<E> zzd(Object obj, long j) {
        return (zzvs) zzyh.zzp(obj, j);
    }
}
