package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzvm;

/* JADX INFO: loaded from: classes.dex */
final class zzxh implements zzwr {
    private final int flags;
    private final String info;
    private final Object[] zzcba;
    private final zzwt zzcbd;

    zzxh(zzwt zzwtVar, String str, Object[] objArr) {
        this.zzcbd = zzwtVar;
        this.info = str;
        this.zzcba = objArr;
        char cCharAt = str.charAt(0);
        if (cCharAt < 55296) {
            this.flags = cCharAt;
            return;
        }
        int i = cCharAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char cCharAt2 = str.charAt(i3);
            if (cCharAt2 < 55296) {
                this.flags = i | (cCharAt2 << i2);
                return;
            } else {
                i |= (cCharAt2 & 8191) << i2;
                i2 += 13;
                i3 = i4;
            }
        }
    }

    final String zzxp() {
        return this.info;
    }

    final Object[] zzxq() {
        return this.zzcba;
    }

    @Override // com.google.android.gms.internal.measurement.zzwr
    public final zzwt zzxi() {
        return this.zzcbd;
    }

    @Override // com.google.android.gms.internal.measurement.zzwr
    public final int zzxg() {
        return (this.flags & 1) == 1 ? zzvm.zze.zzbzb : zzvm.zze.zzbzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzwr
    public final boolean zzxh() {
        return (this.flags & 2) == 2;
    }
}
