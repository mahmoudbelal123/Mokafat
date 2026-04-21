package com.google.android.gms.internal.measurement;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
final class zzsp extends zzsl<Long> {
    zzsp(zzsv zzsvVar, String str, Long l) {
        super(zzsvVar, str, l, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.measurement.zzsl
    /* JADX INFO: renamed from: zzfk, reason: merged with bridge method [inline-methods] */
    public final Long zzfj(String str) {
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (NumberFormatException e) {
            String str2 = this.zzbrc;
            StringBuilder sb = new StringBuilder(25 + String.valueOf(str2).length() + String.valueOf(str).length());
            sb.append("Invalid long value for ");
            sb.append(str2);
            sb.append(": ");
            sb.append(str);
            Log.e("PhenotypeFlag", sb.toString());
            return null;
        }
    }
}
