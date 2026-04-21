package com.google.android.gms.common.data;

import com.google.android.gms.common.data.TextFilterable;

/* JADX INFO: loaded from: classes.dex */
final class zzc implements TextFilterable.StringFilter {
    zzc() {
    }

    @Override // com.google.android.gms.common.data.TextFilterable.StringFilter
    public final boolean matches(String str, String str2) {
        return str.contains(str2);
    }
}
