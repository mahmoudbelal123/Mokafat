package com.google.android.gms.common.config;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
final class zzb extends GservicesValue<Long> {
    zzb(String str, Long l) {
        super(str, l);
    }

    private static Long zza(Context context, String str, Long l) {
        String string = context.getSharedPreferences("gservices-direboot-cache", 0).getString(str, null);
        if (string != null) {
            try {
                return Long.valueOf(Long.parseLong(string));
            } catch (NumberFormatException e) {
            }
        }
        return l;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.config.GservicesValue
    protected final /* synthetic */ Long retrieve(String str) {
        return GservicesValue.zzmu.getLong(this.mKey, (Long) this.mDefaultValue);
    }

    @Override // com.google.android.gms.common.config.GservicesValue
    protected final /* synthetic */ Long retrieveFromDirectBootCache(Context context, String str, Long l) {
        return zza(context, str, l);
    }
}
