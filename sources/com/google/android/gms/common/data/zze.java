package com.google.android.gms.common.data;

import com.google.android.gms.common.data.TextFilterable;

/* JADX INFO: loaded from: classes.dex */
final class zze implements TextFilterable.StringFilter {
    zze() {
    }

    @Override // com.google.android.gms.common.data.TextFilterable.StringFilter
    public final boolean matches(String str, String str2) {
        if (str.startsWith(str2)) {
            return true;
        }
        String strValueOf = String.valueOf(str2);
        return str.contains(strValueOf.length() != 0 ? " ".concat(strValueOf) : new String(" "));
    }
}
