package com.google.android.gms.common.config;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
final class zza extends GservicesValue<Boolean> {
    zza(String str, Boolean bool) {
        super(str, bool);
    }

    private static Boolean zza(Context context, String str, Boolean bool) {
        String string = context.getSharedPreferences("gservices-direboot-cache", 0).getString(str, null);
        if (string != null) {
            try {
                return Boolean.valueOf(Boolean.parseBoolean(string));
            } catch (NumberFormatException e) {
            }
        }
        return bool;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.config.GservicesValue
    protected final /* synthetic */ Boolean retrieve(String str) {
        return GservicesValue.zzmu.zza(this.mKey, (Boolean) this.mDefaultValue);
    }

    @Override // com.google.android.gms.common.config.GservicesValue
    protected final /* synthetic */ Boolean retrieveFromDirectBootCache(Context context, String str, Boolean bool) {
        return zza(context, str, bool);
    }
}
