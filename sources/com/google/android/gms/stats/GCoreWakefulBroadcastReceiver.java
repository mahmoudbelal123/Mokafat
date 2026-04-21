package com.google.android.gms.stats;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.google.android.gms.common.stats.LoggingConstants;
import com.google.android.gms.common.stats.StatsUtils;
import com.google.android.gms.common.stats.WakeLockTracker;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes.dex */
public abstract class GCoreWakefulBroadcastReceiver extends WakefulBroadcastReceiver {
    private static String TAG = "GCoreWakefulBroadcastReceiver";

    @SuppressLint({"UnwrappedWakefulBroadcastReceiver"})
    public static boolean completeWakefulIntent(Context context, Intent intent) {
        if (intent == null) {
            return false;
        }
        if (context != null) {
            WakeLockTracker.getInstance().registerReleaseEvent(context, intent);
        } else {
            String str = TAG;
            String strValueOf = String.valueOf(intent.toUri(0));
            Log.w(str, strValueOf.length() != 0 ? "context shouldn't be null. intent: ".concat(strValueOf) : new String("context shouldn't be null. intent: "));
        }
        return WakefulBroadcastReceiver.completeWakefulIntent(intent);
    }

    public static ComponentName startWakefulService(Context context, Intent intent) {
        ComponentName componentNameZza = zza(context, intent);
        if (componentNameZza == null) {
            return null;
        }
        WakeLockTracker wakeLockTracker = WakeLockTracker.getInstance();
        String strValueOf = String.valueOf(componentNameZza.flattenToShortString());
        wakeLockTracker.registerAcquireEvent(context, intent, strValueOf.length() != 0 ? "wake:".concat(strValueOf) : new String("wake:"), TAG, (String) null, 1, "com.google.android.gms");
        return componentNameZza;
    }

    public static ComponentName startWakefulService(Context context, Intent intent, @Nonnull String str) {
        return startWakefulService(context, intent, str, context.getPackageName());
    }

    public static ComponentName startWakefulService(Context context, Intent intent, @Nonnull String str, String str2) {
        ComponentName componentNameZza = zza(context, intent);
        if (componentNameZza == null) {
            return null;
        }
        WakeLockTracker.getInstance().registerAcquireEvent(context, intent, str, TAG, (String) null, 1, str2);
        return componentNameZza;
    }

    @SuppressLint({"UnwrappedWakefulBroadcastReceiver"})
    private static ComponentName zza(Context context, Intent intent) {
        intent.putExtra(LoggingConstants.EXTRA_WAKE_LOCK_KEY, StatsUtils.getEventKey(context, intent));
        return WakefulBroadcastReceiver.startWakefulService(context, intent);
    }
}
