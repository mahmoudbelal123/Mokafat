package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.internal.zzfk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class zzc {
    private static final Set<String> zzbsm = new HashSet(Arrays.asList("_in", "_xa", "_xu", "_aq", "_aa", "_ai", "_ac", FirebaseAnalytics.Event.CAMPAIGN_DETAILS, "_ug", "_iapx", "_exp_set", "_exp_clear", "_exp_activate", "_exp_timeout", "_exp_expire"));
    private static final List<String> zzbsn = Arrays.asList("_e", "_f", "_iap", "_s", "_au", "_ui", "_cd", FirebaseAnalytics.Event.APP_OPEN);
    private static final List<String> zzbso = Arrays.asList("auto", "app", "am");
    private static final List<String> zzbsp = Arrays.asList("_r", "_dbg");
    private static final List<String> zzbsq = Arrays.asList((String[]) ArrayUtils.concat(AppMeasurement.UserProperty.zzado, AppMeasurement.UserProperty.zzadp));
    private static final List<String> zzbsr = Arrays.asList("^_ltv_[A-Z]{3}$", "^_cc[1-5]{1}$");

    public static boolean zzfo(@NonNull String str) {
        return !zzbso.contains(str);
    }

    public static boolean zza(@NonNull String str, @Nullable Bundle bundle) {
        if (zzbsn.contains(str)) {
            return false;
        }
        if (bundle == null) {
            return true;
        }
        Iterator<String> it = zzbsp.iterator();
        while (it.hasNext()) {
            if (bundle.containsKey(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static boolean zzfp(@NonNull String str) {
        return !zzbsm.contains(str);
    }

    public static boolean zzy(@NonNull String str, @NonNull String str2) {
        if ("_ce1".equals(str2) || "_ce2".equals(str2)) {
            return str.equals(AppMeasurement.FCM_ORIGIN) || str.equals("frc");
        }
        if (AppMeasurement.UserProperty.FIREBASE_LAST_NOTIFICATION.equals(str2)) {
            return str.equals(AppMeasurement.FCM_ORIGIN) || str.equals(AppMeasurement.FIAM_ORIGIN);
        }
        if (zzbsq.contains(str2)) {
            return false;
        }
        Iterator<String> it = zzbsr.iterator();
        while (it.hasNext()) {
            if (str2.matches(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static boolean zza(AnalyticsConnector.ConditionalUserProperty conditionalUserProperty) {
        String str;
        if (conditionalUserProperty == null || (str = conditionalUserProperty.origin) == null || str.isEmpty()) {
            return false;
        }
        if ((conditionalUserProperty.value != null && zzfk.zzf(conditionalUserProperty.value) == null) || !zzfo(str) || !zzy(str, conditionalUserProperty.name)) {
            return false;
        }
        if (conditionalUserProperty.expiredEventName != null && (!zza(conditionalUserProperty.expiredEventName, conditionalUserProperty.expiredEventParams) || !zzb(str, conditionalUserProperty.expiredEventName, conditionalUserProperty.expiredEventParams))) {
            return false;
        }
        if (conditionalUserProperty.triggeredEventName != null && (!zza(conditionalUserProperty.triggeredEventName, conditionalUserProperty.triggeredEventParams) || !zzb(str, conditionalUserProperty.triggeredEventName, conditionalUserProperty.triggeredEventParams))) {
            return false;
        }
        if (conditionalUserProperty.timedOutEventName != null) {
            if (!zza(conditionalUserProperty.timedOutEventName, conditionalUserProperty.timedOutEventParams) || !zzb(str, conditionalUserProperty.timedOutEventName, conditionalUserProperty.timedOutEventParams)) {
                return false;
            }
            return true;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean zzb(@android.support.annotation.NonNull java.lang.String r4, @android.support.annotation.NonNull java.lang.String r5, @android.support.annotation.Nullable android.os.Bundle r6) {
        /*
            java.lang.String r0 = "_cmp"
            boolean r5 = r0.equals(r5)
            r0 = 1
            if (r5 != 0) goto La
            return r0
        La:
            boolean r5 = zzfo(r4)
            r1 = 0
            if (r5 != 0) goto L12
            return r1
        L12:
            if (r6 != 0) goto L15
            return r1
        L15:
            java.util.List<java.lang.String> r5 = com.google.firebase.analytics.connector.internal.zzc.zzbsp
            java.util.Iterator r5 = r5.iterator()
        L1b:
            boolean r2 = r5.hasNext()
            if (r2 == 0) goto L2f
            java.lang.Object r2 = r5.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r2 = r6.containsKey(r2)
            if (r2 == 0) goto L2e
            return r1
        L2e:
            goto L1b
        L2f:
            r5 = -1
            int r2 = r4.hashCode()
            r3 = 101200(0x18b50, float:1.41811E-40)
            if (r2 == r3) goto L58
            r3 = 101230(0x18b6e, float:1.41853E-40)
            if (r2 == r3) goto L4e
            r3 = 3142703(0x2ff42f, float:4.403865E-39)
            if (r2 == r3) goto L44
            goto L62
        L44:
            java.lang.String r2 = "fiam"
            boolean r4 = r4.equals(r2)
            if (r4 == 0) goto L62
            r4 = 2
            goto L63
        L4e:
            java.lang.String r2 = "fdl"
            boolean r4 = r4.equals(r2)
            if (r4 == 0) goto L62
            r4 = r0
            goto L63
        L58:
            java.lang.String r2 = "fcm"
            boolean r4 = r4.equals(r2)
            if (r4 == 0) goto L62
            r4 = r1
            goto L63
        L62:
            r4 = r5
        L63:
            switch(r4) {
                case 0: goto L77;
                case 1: goto L6f;
                case 2: goto L67;
                default: goto L66;
            }
        L66:
            return r1
        L67:
            java.lang.String r4 = "_cis"
            java.lang.String r5 = "fiam_integration"
            r6.putString(r4, r5)
            return r0
        L6f:
            java.lang.String r4 = "_cis"
            java.lang.String r5 = "fdl_integration"
            r6.putString(r4, r5)
            return r0
        L77:
            java.lang.String r4 = "_cis"
            java.lang.String r5 = "fcm_integration"
            r6.putString(r4, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.analytics.connector.internal.zzc.zzb(java.lang.String, java.lang.String, android.os.Bundle):boolean");
    }

    public static AppMeasurement.ConditionalUserProperty zzb(AnalyticsConnector.ConditionalUserProperty conditionalUserProperty) {
        AppMeasurement.ConditionalUserProperty conditionalUserProperty2 = new AppMeasurement.ConditionalUserProperty();
        conditionalUserProperty2.mOrigin = conditionalUserProperty.origin;
        conditionalUserProperty2.mActive = conditionalUserProperty.active;
        conditionalUserProperty2.mCreationTimestamp = conditionalUserProperty.creationTimestamp;
        conditionalUserProperty2.mExpiredEventName = conditionalUserProperty.expiredEventName;
        if (conditionalUserProperty.expiredEventParams != null) {
            conditionalUserProperty2.mExpiredEventParams = new Bundle(conditionalUserProperty.expiredEventParams);
        }
        conditionalUserProperty2.mName = conditionalUserProperty.name;
        conditionalUserProperty2.mTimedOutEventName = conditionalUserProperty.timedOutEventName;
        if (conditionalUserProperty.timedOutEventParams != null) {
            conditionalUserProperty2.mTimedOutEventParams = new Bundle(conditionalUserProperty.timedOutEventParams);
        }
        conditionalUserProperty2.mTimeToLive = conditionalUserProperty.timeToLive;
        conditionalUserProperty2.mTriggeredEventName = conditionalUserProperty.triggeredEventName;
        if (conditionalUserProperty.triggeredEventParams != null) {
            conditionalUserProperty2.mTriggeredEventParams = new Bundle(conditionalUserProperty.triggeredEventParams);
        }
        conditionalUserProperty2.mTriggeredTimestamp = conditionalUserProperty.triggeredTimestamp;
        conditionalUserProperty2.mTriggerEventName = conditionalUserProperty.triggerEventName;
        conditionalUserProperty2.mTriggerTimeout = conditionalUserProperty.triggerTimeout;
        if (conditionalUserProperty.value != null) {
            conditionalUserProperty2.mValue = zzfk.zzf(conditionalUserProperty.value);
        }
        return conditionalUserProperty2;
    }

    public static AnalyticsConnector.ConditionalUserProperty zzd(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        AnalyticsConnector.ConditionalUserProperty conditionalUserProperty2 = new AnalyticsConnector.ConditionalUserProperty();
        conditionalUserProperty2.origin = conditionalUserProperty.mOrigin;
        conditionalUserProperty2.active = conditionalUserProperty.mActive;
        conditionalUserProperty2.creationTimestamp = conditionalUserProperty.mCreationTimestamp;
        conditionalUserProperty2.expiredEventName = conditionalUserProperty.mExpiredEventName;
        if (conditionalUserProperty.mExpiredEventParams != null) {
            conditionalUserProperty2.expiredEventParams = new Bundle(conditionalUserProperty.mExpiredEventParams);
        }
        conditionalUserProperty2.name = conditionalUserProperty.mName;
        conditionalUserProperty2.timedOutEventName = conditionalUserProperty.mTimedOutEventName;
        if (conditionalUserProperty.mTimedOutEventParams != null) {
            conditionalUserProperty2.timedOutEventParams = new Bundle(conditionalUserProperty.mTimedOutEventParams);
        }
        conditionalUserProperty2.timeToLive = conditionalUserProperty.mTimeToLive;
        conditionalUserProperty2.triggeredEventName = conditionalUserProperty.mTriggeredEventName;
        if (conditionalUserProperty.mTriggeredEventParams != null) {
            conditionalUserProperty2.triggeredEventParams = new Bundle(conditionalUserProperty.mTriggeredEventParams);
        }
        conditionalUserProperty2.triggeredTimestamp = conditionalUserProperty.mTriggeredTimestamp;
        conditionalUserProperty2.triggerEventName = conditionalUserProperty.mTriggerEventName;
        conditionalUserProperty2.triggerTimeout = conditionalUserProperty.mTriggerTimeout;
        if (conditionalUserProperty.mValue != null) {
            conditionalUserProperty2.value = zzfk.zzf(conditionalUserProperty.mValue);
        }
        return conditionalUserProperty2;
    }

    public static boolean zzfq(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int iCodePointAt = str.codePointAt(0);
        if (!Character.isLetter(iCodePointAt)) {
            return false;
        }
        int length = str.length();
        int iCharCount = Character.charCount(iCodePointAt);
        while (iCharCount < length) {
            int iCodePointAt2 = str.codePointAt(iCharCount);
            if (iCodePointAt2 != 95 && !Character.isLetterOrDigit(iCodePointAt2)) {
                return false;
            }
            iCharCount += Character.charCount(iCodePointAt2);
        }
        return true;
    }

    public static boolean zzfr(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int iCodePointAt = str.codePointAt(0);
        if (!Character.isLetter(iCodePointAt) && iCodePointAt != 95) {
            return false;
        }
        int length = str.length();
        int iCharCount = Character.charCount(iCodePointAt);
        while (iCharCount < length) {
            int iCodePointAt2 = str.codePointAt(iCharCount);
            if (iCodePointAt2 != 95 && !Character.isLetterOrDigit(iCodePointAt2)) {
                return false;
            }
            iCharCount += Character.charCount(iCodePointAt2);
        }
        return true;
    }

    public static String zzfs(String str) {
        String strZzak = AppMeasurement.Event.zzak(str);
        if (strZzak != null) {
            return strZzak;
        }
        return str;
    }

    public static String zzft(String str) {
        String strZzal = AppMeasurement.Event.zzal(str);
        if (strZzal != null) {
            return strZzal;
        }
        return str;
    }
}
