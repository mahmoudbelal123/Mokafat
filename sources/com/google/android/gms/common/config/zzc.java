package com.google.android.gms.common.config;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
final class zzc extends GservicesValue<Integer> {
    zzc(String str, Integer num) {
        super(str, num);
    }

    private static Integer zza(Context context, String str, Integer num) {
        String string = context.getSharedPreferences("gservices-direboot-cache", 0).getString(str, null);
        if (string != null) {
            try {
                return Integer.valueOf(Integer.parseInt(string));
            } catch (NumberFormatException e) {
            }
        }
        return num;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.config.GservicesValue
    protected final /* synthetic */ Integer retrieve(String str) {
        return GservicesValue.zzmu.zza(this.mKey, (Integer) this.mDefaultValue);
    }

    @Override // com.google.android.gms.common.config.GservicesValue
    protected final /* synthetic */ Integer retrieveFromDirectBootCache(Context context, String str, Integer num) {
        return zza(context, str, num);
    }
}
