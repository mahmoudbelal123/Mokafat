package com.google.android.gms.internal.measurement;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
final class zzsq extends zzsl<Integer> {
    zzsq(zzsv zzsvVar, String str, Integer num) {
        super(zzsvVar, str, num, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.measurement.zzsl
    /* JADX INFO: renamed from: zzfl, reason: merged with bridge method [inline-methods] */
    public final Integer zzfj(String str) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            String str2 = this.zzbrc;
            StringBuilder sb = new StringBuilder(28 + String.valueOf(str2).length() + String.valueOf(str).length());
            sb.append("Invalid integer value for ");
            sb.append(str2);
            sb.append(": ");
            sb.append(str);
            Log.e("PhenotypeFlag", sb.toString());
            return null;
        }
    }
}
