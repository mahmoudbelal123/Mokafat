package com.google.android.gms.common.config;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
final class zzf extends GservicesValue<String> {
    zzf(String str, String str2) {
        super(str, str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.config.GservicesValue
    protected final /* synthetic */ String retrieve(String str) {
        return GservicesValue.zzmu.getString(this.mKey, (String) this.mDefaultValue);
    }

    @Override // com.google.android.gms.common.config.GservicesValue
    protected final /* synthetic */ String retrieveFromDirectBootCache(Context context, String str, String str2) {
        return context.getSharedPreferences("gservices-direboot-cache", 0).getString(str, str2);
    }
}
