package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
final class zzwk implements zzws {
    private zzws[] zzcaq;

    zzwk(zzws... zzwsVarArr) {
        this.zzcaq = zzwsVarArr;
    }

    @Override // com.google.android.gms.internal.measurement.zzws
    public final boolean zze(Class<?> cls) {
        for (zzws zzwsVar : this.zzcaq) {
            if (zzwsVar.zze(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzws
    public final zzwr zzf(Class<?> cls) {
        for (zzws zzwsVar : this.zzcaq) {
            if (zzwsVar.zze(cls)) {
                return zzwsVar.zzf(cls);
            }
        }
        String strValueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(strValueOf.length() != 0 ? "No factory is available for message type: ".concat(strValueOf) : new String("No factory is available for message type: "));
    }
}
