package com.google.android.gms.common.api;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AvailabilityException extends Exception {
    private final ArrayMap<zzh<?>, ConnectionResult> zzcc;

    public AvailabilityException(ArrayMap<zzh<?>, ConnectionResult> arrayMap) {
        this.zzcc = arrayMap;
    }

    public ConnectionResult getConnectionResult(GoogleApi<? extends Api.ApiOptions> googleApi) {
        Object objZzm = googleApi.zzm();
        Preconditions.checkArgument(this.zzcc.get(objZzm) != null, "The given API was not part of the availability request.");
        return this.zzcc.get(objZzm);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (zzh<?> zzhVar : this.zzcc.keySet()) {
            ConnectionResult connectionResult = this.zzcc.get(zzhVar);
            if (connectionResult.isSuccess()) {
                z = false;
            }
            String strZzq = zzhVar.zzq();
            String strValueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(2 + String.valueOf(strZzq).length() + String.valueOf(strValueOf).length());
            sb.append(strZzq);
            sb.append(": ");
            sb.append(strValueOf);
            arrayList.add(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(z ? "None of the queried APIs are available. " : "Some of the queried APIs are unavailable. ");
        sb2.append(TextUtils.join("; ", arrayList));
        return sb2.toString();
    }

    public final ArrayMap<zzh<?>, ConnectionResult> zzl() {
        return this.zzcc;
    }
}
