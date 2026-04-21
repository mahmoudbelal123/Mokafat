package com.google.firebase.iid;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class zzaw {
    private static final long zzdc = TimeUnit.DAYS.toMillis(7);
    private final long timestamp;
    final String zzbn;
    private final String zzdd;

    private zzaw(String str, String str2, long j) {
        this.zzbn = str;
        this.zzdd = str2;
        this.timestamp = j;
    }

    static zzaw zzi(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith("{")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new zzaw(jSONObject.getString("token"), jSONObject.getString("appVersion"), jSONObject.getLong(AppMeasurement.Param.TIMESTAMP));
            } catch (JSONException e) {
                String strValueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(23 + String.valueOf(strValueOf).length());
                sb.append("Failed to parse token: ");
                sb.append(strValueOf);
                Log.w("FirebaseInstanceId", sb.toString());
                return null;
            }
        }
        return new zzaw(str, null, 0L);
    }

    static String zza(String str, String str2, long j) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("token", str);
            jSONObject.put("appVersion", str2);
            jSONObject.put(AppMeasurement.Param.TIMESTAMP, j);
            return jSONObject.toString();
        } catch (JSONException e) {
            String strValueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(24 + String.valueOf(strValueOf).length());
            sb.append("Failed to encode token: ");
            sb.append(strValueOf);
            Log.w("FirebaseInstanceId", sb.toString());
            return null;
        }
    }

    static String zza(@Nullable zzaw zzawVar) {
        if (zzawVar == null) {
            return null;
        }
        return zzawVar.zzbn;
    }

    final boolean zzj(String str) {
        return System.currentTimeMillis() > this.timestamp + zzdc || !str.equals(this.zzdd);
    }
}
