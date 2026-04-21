package com.google.android.gms.common.config;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Process;
import android.os.StrictMode;
import android.os.UserManager;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.stable.zzi;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
public abstract class GservicesValue<T> {
    private static Context zzmw;

    @GuardedBy("sLock")
    private static HashSet<String> zzmy;
    protected final T mDefaultValue;
    protected final String mKey;
    private T zzmz = null;
    private static final Object sLock = new Object();
    private static zza zzmu = null;
    private static int zzmv = 0;
    private static String zzmx = "com.google.android.providers.gsf.permission.READ_GSERVICES";

    /* JADX INFO: Access modifiers changed from: private */
    interface zza {
        Long getLong(String str, Long l);

        String getString(String str, String str2);

        Boolean zza(String str, Boolean bool);

        Double zza(String str, Double d);

        Float zza(String str, Float f);

        Integer zza(String str, Integer num);

        String zzb(String str, String str2);
    }

    private static class zzb implements zza {
        private static final Collection<GservicesValue<?>> zzna = new HashSet();

        private zzb() {
        }

        /* synthetic */ zzb(com.google.android.gms.common.config.zza zzaVar) {
            this();
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final Long getLong(String str, Long l) {
            return l;
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final String getString(String str, String str2) {
            return str2;
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final Boolean zza(String str, Boolean bool) {
            return bool;
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final Double zza(String str, Double d) {
            return d;
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final Float zza(String str, Float f) {
            return f;
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final Integer zza(String str, Integer num) {
            return num;
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final String zzb(String str, String str2) {
            return str2;
        }
    }

    @Deprecated
    private static class zzc implements zza {
        private final Map<String, ?> values;

        public zzc(Map<String, ?> map) {
            this.values = map;
        }

        private final <T> T zza(String str, T t) {
            return this.values.containsKey(str) ? (T) this.values.get(str) : t;
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final Long getLong(String str, Long l) {
            return (Long) zza(str, l);
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final String getString(String str, String str2) {
            return (String) zza(str, str2);
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final Boolean zza(String str, Boolean bool) {
            return (Boolean) zza(str, bool);
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final Double zza(String str, Double d) {
            return (Double) zza(str, d);
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final Float zza(String str, Float f) {
            return (Float) zza(str, f);
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final Integer zza(String str, Integer num) {
            return (Integer) zza(str, num);
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final String zzb(String str, String str2) {
            return (String) zza(str, str2);
        }
    }

    private static class zzd implements zza {
        private final ContentResolver mContentResolver;

        public zzd(ContentResolver contentResolver) {
            this.mContentResolver = contentResolver;
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final Long getLong(String str, Long l) {
            return Long.valueOf(zzi.getLong(this.mContentResolver, str, l.longValue()));
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final String getString(String str, String str2) {
            return zzi.zza(this.mContentResolver, str, str2);
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final Boolean zza(String str, Boolean bool) {
            return Boolean.valueOf(zzi.zza(this.mContentResolver, str, bool.booleanValue()));
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final Double zza(String str, Double d) {
            String strZza = zzi.zza(this.mContentResolver, str, (String) null);
            if (strZza != null) {
                try {
                    return Double.valueOf(Double.parseDouble(strZza));
                } catch (NumberFormatException e) {
                }
            }
            return d;
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final Float zza(String str, Float f) {
            String strZza = zzi.zza(this.mContentResolver, str, (String) null);
            if (strZza != null) {
                try {
                    return Float.valueOf(Float.parseFloat(strZza));
                } catch (NumberFormatException e) {
                }
            }
            return f;
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final Integer zza(String str, Integer num) {
            return Integer.valueOf(zzi.getInt(this.mContentResolver, str, num.intValue()));
        }

        @Override // com.google.android.gms.common.config.GservicesValue.zza
        public final String zzb(String str, String str2) {
            return com.google.android.gms.internal.stable.zzg.zza(this.mContentResolver, str, str2);
        }
    }

    protected GservicesValue(String str, T t) {
        this.mKey = str;
        this.mDefaultValue = t;
    }

    @VisibleForTesting
    @Deprecated
    public static void forceInit(Context context) {
        forceInit(context, new HashSet());
    }

    @VisibleForTesting
    public static void forceInit(Context context, @Nullable HashSet<String> hashSet) {
        zza(context, new zzd(context.getContentResolver()), hashSet);
    }

    @TargetApi(24)
    public static SharedPreferences getDirectBootCache(Context context) {
        return context.getApplicationContext().createDeviceProtectedStorageContext().getSharedPreferences("gservices-direboot-cache", 0);
    }

    public static int getSharedUserId() {
        return zzmv;
    }

    @Deprecated
    public static void init(Context context) {
        init(context, zzd(context) ? new HashSet() : null);
    }

    public static void init(Context context, @Nullable HashSet<String> hashSet) {
        synchronized (sLock) {
            if (zzmu == null) {
                zza(context, new zzd(context.getContentResolver()), hashSet);
            }
            if (zzmv == 0) {
                try {
                    zzmv = context.getPackageManager().getApplicationInfo("com.google.android.gms", 0).uid;
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e("GservicesValue", e.toString());
                }
            }
        }
    }

    @VisibleForTesting
    @Deprecated
    public static void initForTests() {
        zza(null, new zzb(null), new HashSet());
    }

    @VisibleForTesting
    public static void initForTests(Context context, @Nullable HashSet<String> hashSet) {
        zza(context, new zzb(null), hashSet);
    }

    @VisibleForTesting
    @Deprecated
    public static void initForTests(String str, Object obj) {
        HashMap map = new HashMap(1);
        map.put(str, obj);
        initForTests(map);
    }

    @VisibleForTesting
    @Deprecated
    public static void initForTests(Map<String, ?> map) {
        synchronized (sLock) {
            zzmu = new zzc(map);
        }
    }

    public static boolean isInitialized() {
        boolean z;
        synchronized (sLock) {
            z = zzmu != null;
        }
        return z;
    }

    public static GservicesValue<String> partnerSetting(String str, String str2) {
        return new zzg(str, str2);
    }

    @VisibleForTesting
    public static void resetAllOverrides() {
        synchronized (sLock) {
            if (zzcg()) {
                Iterator it = zzb.zzna.iterator();
                while (it.hasNext()) {
                    ((GservicesValue) it.next()).resetOverride();
                }
                zzb.zzna.clear();
            }
        }
    }

    public static GservicesValue<Double> value(String str, Double d) {
        return new com.google.android.gms.common.config.zzd(str, d);
    }

    public static GservicesValue<Float> value(String str, Float f) {
        return new zze(str, f);
    }

    public static GservicesValue<Integer> value(String str, Integer num) {
        return new com.google.android.gms.common.config.zzc(str, num);
    }

    public static GservicesValue<Long> value(String str, Long l) {
        return new com.google.android.gms.common.config.zzb(str, l);
    }

    public static GservicesValue<String> value(String str, String str2) {
        return new zzf(str, str2);
    }

    public static GservicesValue<Boolean> value(String str, boolean z) {
        return new com.google.android.gms.common.config.zza(str, Boolean.valueOf(z));
    }

    @TargetApi(24)
    private static void zza(@Nullable Context context, zza zzaVar, @Nullable HashSet<String> hashSet) {
        synchronized (sLock) {
            zzmu = zzaVar;
            zzmy = null;
            zzmw = null;
            if (context != null && zzd(context)) {
                zzmy = hashSet;
                zzmw = context.getApplicationContext().createDeviceProtectedStorageContext();
            }
        }
    }

    private static boolean zzcg() {
        boolean z;
        synchronized (sLock) {
            z = (zzmu instanceof zzb) || (zzmu instanceof zzc);
        }
        return z;
    }

    @TargetApi(24)
    private static boolean zzd(Context context) {
        if (!PlatformVersion.isAtLeastN()) {
            return false;
        }
        UserManager userManager = (UserManager) context.getSystemService(UserManager.class);
        return (userManager.isUserUnlocked() || userManager.isUserRunning(Process.myUserHandle())) ? false : true;
    }

    public final T get() {
        boolean z;
        HashSet<String> hashSet;
        Context context;
        if (this.zzmz != null) {
            return this.zzmz;
        }
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        synchronized (sLock) {
            z = zzmw != null && zzd(zzmw);
            hashSet = zzmy;
            context = zzmw;
        }
        if (z) {
            if (Log.isLoggable("GservicesValue", 3)) {
                String strValueOf = String.valueOf(this.mKey);
                Log.d("GservicesValue", strValueOf.length() != 0 ? "Gservice value accessed during directboot: ".concat(strValueOf) : new String("Gservice value accessed during directboot: "));
            }
            if (hashSet == null || hashSet.contains(this.mKey)) {
                return retrieveFromDirectBootCache(context, this.mKey, this.mDefaultValue);
            }
            String strValueOf2 = String.valueOf(this.mKey);
            Log.e("GservicesValue", strValueOf2.length() != 0 ? "Gservices key not whitelisted for directboot access: ".concat(strValueOf2) : new String("Gservices key not whitelisted for directboot access: "));
            return this.mDefaultValue;
        }
        synchronized (sLock) {
            zzmy = null;
            zzmw = null;
        }
        try {
            try {
                T tRetrieve = retrieve(this.mKey);
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                return tRetrieve;
            } catch (SecurityException e) {
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    T tRetrieve2 = retrieve(this.mKey);
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                    return tRetrieve2;
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            }
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            throw th;
        }
    }

    @Deprecated
    public final T getBinderSafe() {
        return get();
    }

    public String getKey() {
        return this.mKey;
    }

    @VisibleForTesting
    public void override(T t) {
        if (!(zzmu instanceof zzb)) {
            Log.w("GservicesValue", "GservicesValue.override(): test should probably call initForTests() first");
        }
        this.zzmz = t;
        synchronized (sLock) {
            if (zzcg()) {
                zzb.zzna.add(this);
            }
        }
    }

    @VisibleForTesting
    public void resetOverride() {
        this.zzmz = null;
    }

    protected abstract T retrieve(String str);

    @TargetApi(24)
    protected T retrieveFromDirectBootCache(Context context, String str, T t) {
        throw new UnsupportedOperationException("The Gservices classes used does not support direct-boot");
    }
}
