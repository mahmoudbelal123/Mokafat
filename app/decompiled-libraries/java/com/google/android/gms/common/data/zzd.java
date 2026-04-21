package com.google.android.gms.common.data;

import com.google.android.gms.common.data.TextFilterable;

/* JADX INFO: loaded from: classes.dex */
final class zzd implements TextFilterable.StringFilter {
    zzd() {
    }

    @Override // com.google.android.gms.common.data.TextFilterable.StringFilter
    public final boolean matches(String str, String str2) {
        return str.startsWith(str2);
    }
}
