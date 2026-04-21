package com.google.android.gms.internal.measurement;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
final class zzzi {
    final int tag;
    final byte[] zzbug;

    zzzi(int i, byte[] bArr) {
        this.tag = i;
        this.zzbug = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzzi)) {
            return false;
        }
        zzzi zzziVar = (zzzi) obj;
        return this.tag == zzziVar.tag && Arrays.equals(this.zzbug, zzziVar.zzbug);
    }

    public final int hashCode() {
        return ((527 + this.tag) * 31) + Arrays.hashCode(this.zzbug);
    }
}
