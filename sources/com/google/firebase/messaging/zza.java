package com.google.firebase.messaging;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import java.util.Arrays;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
final class zza {
    private static final AtomicInteger zzdk = new AtomicInteger((int) SystemClock.elapsedRealtime());
    private Bundle zzdl;
    private final Context zzv;

    public zza(Context context) {
        this.zzv = context.getApplicationContext();
    }

    static boolean zzf(Bundle bundle) {
        return "1".equals(zza(bundle, "gcm.n.e")) || zza(bundle, "gcm.n.icon") != null;
    }

    static String zza(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return bundle.getString(str.replace("gcm.n.", "gcm.notification."));
        }
        return string;
    }

    static String zzb(Bundle bundle, String str) {
        String strValueOf = String.valueOf(str);
        String strValueOf2 = String.valueOf("_loc_key");
        return zza(bundle, strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
    }

    /* JADX WARN: Multi-variable type inference failed */
    static Object[] zzc(Bundle bundle, String str) {
        String strValueOf = String.valueOf(str);
        String strValueOf2 = String.valueOf("_loc_args");
        String strZza = zza(bundle, strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
        if (TextUtils.isEmpty(strZza)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(strZza);
            String[] strArr = new String[jSONArray.length()];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = jSONArray.opt(i);
            }
            return strArr;
        } catch (JSONException e) {
            String strValueOf3 = String.valueOf(str);
            String strValueOf4 = String.valueOf("_loc_args");
            String strSubstring = (strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3)).substring(6);
            StringBuilder sb = new StringBuilder(41 + String.valueOf(strSubstring).length() + String.valueOf(strZza).length());
            sb.append("Malformed ");
            sb.append(strSubstring);
            sb.append(": ");
            sb.append(strZza);
            sb.append("  Default value will be used.");
            Log.w("FirebaseMessaging", sb.toString());
            return null;
        }
    }

    @Nullable
    static Uri zzg(@NonNull Bundle bundle) {
        String strZza = zza(bundle, "gcm.n.link_android");
        if (TextUtils.isEmpty(strZza)) {
            strZza = zza(bundle, "gcm.n.link");
        }
        if (!TextUtils.isEmpty(strZza)) {
            return Uri.parse(strZza);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0064 A[EDGE_INSN: B:158:0x0064->B:23:0x0064 BREAK  A[LOOP:0: B:13:0x0049->B:159:?]] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0112  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final boolean zzh(android.os.Bundle r18) {
        /*
            Method dump skipped, instruction units count: 1020
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.zza.zzh(android.os.Bundle):boolean");
    }

    private final String zzd(Bundle bundle, String str) {
        String strZza = zza(bundle, str);
        if (!TextUtils.isEmpty(strZza)) {
            return strZza;
        }
        String strZzb = zzb(bundle, str);
        if (TextUtils.isEmpty(strZzb)) {
            return null;
        }
        Resources resources = this.zzv.getResources();
        int identifier = resources.getIdentifier(strZzb, "string", this.zzv.getPackageName());
        if (identifier == 0) {
            String strValueOf = String.valueOf(str);
            String strValueOf2 = String.valueOf("_loc_key");
            String strSubstring = (strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf)).substring(6);
            StringBuilder sb = new StringBuilder(49 + String.valueOf(strSubstring).length() + String.valueOf(strZzb).length());
            sb.append(strSubstring);
            sb.append(" resource not found: ");
            sb.append(strZzb);
            sb.append(" Default value will be used.");
            Log.w("FirebaseMessaging", sb.toString());
            return null;
        }
        Object[] objArrZzc = zzc(bundle, str);
        if (objArrZzc == null) {
            return resources.getString(identifier);
        }
        try {
            return resources.getString(identifier, objArrZzc);
        } catch (MissingFormatArgumentException e) {
            String string = Arrays.toString(objArrZzc);
            StringBuilder sb2 = new StringBuilder(58 + String.valueOf(strZzb).length() + String.valueOf(string).length());
            sb2.append("Missing format argument for ");
            sb2.append(strZzb);
            sb2.append(": ");
            sb2.append(string);
            sb2.append(" Default value will be used.");
            Log.w("FirebaseMessaging", sb2.toString(), e);
            return null;
        }
    }

    @TargetApi(26)
    private final boolean zzb(int i) {
        if (Build.VERSION.SDK_INT != 26) {
            return true;
        }
        try {
            if (!(this.zzv.getResources().getDrawable(i, null) instanceof AdaptiveIconDrawable)) {
                return true;
            }
            StringBuilder sb = new StringBuilder(77);
            sb.append("Adaptive icons cannot be used in notifications. Ignoring icon id: ");
            sb.append(i);
            Log.e("FirebaseMessaging", sb.toString());
            return false;
        } catch (Resources.NotFoundException e) {
            return false;
        }
    }

    private final Integer zzl(String str) {
        if (Build.VERSION.SDK_INT < 21) {
            return null;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.valueOf(Color.parseColor(str));
            } catch (IllegalArgumentException e) {
                StringBuilder sb = new StringBuilder(54 + String.valueOf(str).length());
                sb.append("Color ");
                sb.append(str);
                sb.append(" not valid. Notification will use default color.");
                Log.w("FirebaseMessaging", sb.toString());
            }
        }
        int i = zzar().getInt("com.google.firebase.messaging.default_notification_color", 0);
        if (i != 0) {
            try {
                return Integer.valueOf(ContextCompat.getColor(this.zzv, i));
            } catch (Resources.NotFoundException e2) {
                Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
            }
        }
        return null;
    }

    static String zzi(Bundle bundle) {
        String strZza = zza(bundle, "gcm.n.sound2");
        if (TextUtils.isEmpty(strZza)) {
            return zza(bundle, "gcm.n.sound");
        }
        return strZza;
    }

    private static void zza(Intent intent, Bundle bundle) {
        for (String str : bundle.keySet()) {
            if (str.startsWith("google.c.a.") || str.equals("from")) {
                intent.putExtra(str, bundle.getString(str));
            }
        }
    }

    private final Bundle zzar() throws PackageManager.NameNotFoundException {
        if (this.zzdl != null) {
            return this.zzdl;
        }
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = this.zzv.getPackageManager().getApplicationInfo(this.zzv.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
        }
        if (applicationInfo != null && applicationInfo.metaData != null) {
            this.zzdl = applicationInfo.metaData;
            return this.zzdl;
        }
        return Bundle.EMPTY;
    }
}
