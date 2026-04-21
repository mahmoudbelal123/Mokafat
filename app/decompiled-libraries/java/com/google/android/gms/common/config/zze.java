package com.google.android.gms.common.config;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
final class zze extends GservicesValue<Float> {
    zze(String str, Float f) {
        super(str, f);
    }

    private static Float zza(Context context, String str, Float f) {
        String string = context.getSharedPreferences("gservices-direboot-cache", 0).getString(str, null);
        if (string != null) {
            try {
                return Float.valueOf(Float.parseFloat(string));
            } catch (NumberFormatException e) {
            }
        }
        return f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.config.GservicesValue
    protected final /* synthetic */ Float retrieve(String str) {
        return GservicesValue.zzmu.zza(this.mKey, (Float) this.mDefaultValue);
    }

    @Override // com.google.android.gms.common.config.GservicesValue
    protected final /* synthetic */ Float retrieveFromDirectBootCache(Context context, String str, Float f) {
        return zza(context, str, f);
    }
}
