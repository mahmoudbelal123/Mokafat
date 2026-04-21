package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    private static final Object zzsj = new Object();
    private static ClassLoader zzsk = null;
    private static Integer zzsl = null;
    private boolean zzsm = false;

    public static final class DowngradeableSafeParcelHelper {
        public static Bundle getExtras(Intent intent, Context context, Integer num) {
            Bundle extras;
            synchronized (DowngradeableSafeParcel.zzsj) {
                extras = DowngradeableSafeParcel.getExtras(intent, context, num);
            }
            return extras;
        }

        public static <T extends Parcelable> T getParcelable(Intent intent, String str, Context context, Integer num) {
            T t;
            synchronized (DowngradeableSafeParcel.zzsj) {
                t = (T) DowngradeableSafeParcel.getParcelable(intent, str, context, num);
            }
            return t;
        }

        public static <T extends Parcelable> T getParcelable(Bundle bundle, String str, Context context, Integer num) {
            T t;
            synchronized (DowngradeableSafeParcel.zzsj) {
                t = (T) DowngradeableSafeParcel.getParcelable(bundle, str, context, num);
            }
            return t;
        }

        public static boolean putParcelable(Bundle bundle, String str, DowngradeableSafeParcel downgradeableSafeParcel, Context context, Integer num) {
            return DowngradeableSafeParcel.putParcelable(bundle, str, downgradeableSafeParcel, context, num);
        }
    }

    protected static boolean canUnparcelSafely(String str) {
        ClassLoader unparcelClassLoader = getUnparcelClassLoader();
        if (unparcelClassLoader == null) {
            return true;
        }
        try {
            return zza(unparcelClassLoader.loadClass(str));
        } catch (Exception e) {
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static android.os.Bundle getExtras(android.content.Intent r1, android.content.Context r2, java.lang.Integer r3) {
        /*
            r0 = 0
            if (r2 == 0) goto L22
            java.lang.ClassLoader r2 = r2.getClassLoader()     // Catch: java.lang.Throwable -> L1d
            zza(r2, r3)     // Catch: java.lang.Throwable -> L1d
            android.os.Bundle r2 = r1.getExtras()     // Catch: java.lang.Throwable -> L1d
            if (r2 == 0) goto L22
            android.os.Bundle r2 = new android.os.Bundle     // Catch: java.lang.Throwable -> L1d
            r2.<init>()     // Catch: java.lang.Throwable -> L1d
            android.os.Bundle r1 = r1.getExtras()     // Catch: java.lang.Throwable -> L1d
            r2.putAll(r1)     // Catch: java.lang.Throwable -> L1d
            goto L23
        L1d:
            r1 = move-exception
            zza(r0, r0)
            throw r1
        L22:
            r2 = r0
        L23:
            zza(r0, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.DowngradeableSafeParcel.getExtras(android.content.Intent, android.content.Context, java.lang.Integer):android.os.Bundle");
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <T extends Parcelable> T getParcelable(Intent intent, String str, Context context, Integer num) {
        T t;
        if (context != null) {
            try {
                zza(context.getClassLoader(), num);
                t = (T) intent.getParcelableExtra(str);
            } finally {
                zza((ClassLoader) null, (Integer) null);
            }
        } else {
            t = null;
        }
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <T extends Parcelable> T getParcelable(Bundle bundle, String str, Context context, Integer num) {
        T t;
        if (context != null) {
            try {
                zza(context.getClassLoader(), num);
                t = (T) bundle.getParcelable(str);
            } finally {
                zza((ClassLoader) null, (Integer) null);
            }
        } else {
            t = null;
        }
        return t;
    }

    protected static ClassLoader getUnparcelClassLoader() {
        ClassLoader classLoader;
        synchronized (zzsj) {
            classLoader = zzsk;
        }
        return classLoader;
    }

    protected static Integer getUnparcelClientVersion() {
        Integer num;
        synchronized (zzsj) {
            num = zzsl;
        }
        return num;
    }

    static boolean putParcelable(Bundle bundle, String str, DowngradeableSafeParcel downgradeableSafeParcel, Context context, Integer num) {
        if ((context == null && num == null) || !downgradeableSafeParcel.zza(context, num)) {
            return false;
        }
        bundle.putParcelable(str, downgradeableSafeParcel);
        return true;
    }

    private static void zza(ClassLoader classLoader, Integer num) {
        synchronized (zzsj) {
            zzsk = classLoader;
            zzsl = num;
        }
    }

    private final boolean zza(Context context, Integer num) {
        if (num != null) {
            return prepareForClientVersion(num.intValue());
        }
        try {
            setShouldDowngrade(!zza(context.getClassLoader().loadClass(getClass().getCanonicalName())));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean zza(Class<?> cls) {
        try {
            return SafeParcelable.NULL.equals(cls.getField("NULL").get(null));
        } catch (IllegalAccessException e) {
            return false;
        } catch (NoSuchFieldException e2) {
            return false;
        }
    }

    protected abstract boolean prepareForClientVersion(int i);

    public void setShouldDowngrade(boolean z) {
        this.zzsm = z;
    }

    protected boolean shouldDowngrade() {
        return this.zzsm;
    }
}
