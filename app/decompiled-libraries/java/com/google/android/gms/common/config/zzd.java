package com.google.android.gms.common.config;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
final class zzd extends GservicesValue<Double> {
    zzd(String str, Double d) {
        super(str, d);
    }

    private static Double zza(Context context, String str, Double d) {
        String string = context.getSharedPreferences("gservices-direboot-cache", 0).getString(str, null);
        if (string != null) {
            try {
                return Double.valueOf(Double.parseDouble(string));
            } catch (NumberFormatException e) {
            }
        }
        return d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.config.GservicesValue
    protected final /* synthetic */ Double retrieve(String str) {
        return GservicesValue.zzmu.zza(this.mKey, (Double) this.mDefaultValue);
    }

    @Override // com.google.android.gms.common.config.GservicesValue
    protected final /* synthetic */ Double retrieveFromDirectBootCache(Context context, String str, Double d) {
        return zza(context, str, d);
    }
}
