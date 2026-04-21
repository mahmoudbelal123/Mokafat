package com.google.android.gms.common.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.WorkSource;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WorkSourceUtil {
    public static final String TAG = "WorkSourceUtil";
    private static final int zzaam = Process.myUid();
    private static final Method zzaan = zzdf();
    private static final Method zzaao = zzdg();
    private static final Method zzaap = zzdh();
    private static final Method zzaaq = zzdi();
    private static final Method zzaar = zzdj();
    private static final Method zzaas = zzdk();
    private static final Method zzaat = zzdl();

    private WorkSourceUtil() {
    }

    public static void add(WorkSource workSource, int i, @Nullable String str) {
        if (zzaao != null) {
            if (str == null) {
                str = "";
            }
            try {
                zzaao.invoke(workSource, Integer.valueOf(i), str);
                return;
            } catch (Exception e) {
                Log.wtf(TAG, "Unable to assign blame through WorkSource", e);
                return;
            }
        }
        if (zzaan != null) {
            try {
                zzaan.invoke(workSource, Integer.valueOf(i));
            } catch (Exception e2) {
                Log.wtf(TAG, "Unable to assign blame through WorkSource", e2);
            }
        }
    }

    @Nullable
    public static WorkSource fromPackage(Context context, @Nullable String str) {
        if (context == null || context.getPackageManager() == null || str == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return fromUidAndPackage(applicationInfo.uid, str);
            }
            String strValueOf = String.valueOf(str);
            Log.e(TAG, strValueOf.length() != 0 ? "Could not get applicationInfo from package: ".concat(strValueOf) : new String("Could not get applicationInfo from package: "));
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            String strValueOf2 = String.valueOf(str);
            Log.e(TAG, strValueOf2.length() != 0 ? "Could not find package: ".concat(strValueOf2) : new String("Could not find package: "));
            return null;
        }
    }

    public static WorkSource fromPackageAndModuleExperimentalPi(Context context, String str, String str2) {
        if (context == null || context.getPackageManager() == null || str2 == null || str == null) {
            Log.w(TAG, "Unexpected null arguments");
            return null;
        }
        int iZzc = zzc(context, str);
        if (iZzc < 0) {
            return null;
        }
        WorkSource workSource = new WorkSource();
        if (zzaas == null || zzaat == null) {
            add(workSource, iZzc, str);
            return workSource;
        }
        try {
            Object objInvoke = zzaas.invoke(workSource, new Object[0]);
            if (iZzc != zzaam) {
                zzaat.invoke(objInvoke, Integer.valueOf(iZzc), str);
            }
            zzaat.invoke(objInvoke, Integer.valueOf(zzaam), str2);
            return workSource;
        } catch (Exception e) {
            Log.w(TAG, "Unable to assign chained blame through WorkSource", e);
            return workSource;
        }
    }

    public static WorkSource fromUidAndPackage(int i, String str) {
        WorkSource workSource = new WorkSource();
        add(workSource, i, str);
        return workSource;
    }

    public static int get(WorkSource workSource, int i) {
        if (zzaaq != null) {
            try {
                return ((Integer) zzaaq.invoke(workSource, Integer.valueOf(i))).intValue();
            } catch (Exception e) {
                Log.wtf(TAG, "Unable to assign blame through WorkSource", e);
            }
        }
        return 0;
    }

    @Nullable
    public static String getName(WorkSource workSource, int i) {
        if (zzaar == null) {
            return null;
        }
        try {
            return (String) zzaar.invoke(workSource, Integer.valueOf(i));
        } catch (Exception e) {
            Log.wtf(TAG, "Unable to assign blame through WorkSource", e);
            return null;
        }
    }

    public static List<String> getNames(@Nullable WorkSource workSource) {
        int size = workSource == null ? 0 : size(workSource);
        if (size == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            String name = getName(workSource, i);
            if (!Strings.isEmptyOrWhitespace(name)) {
                arrayList.add(name);
            }
        }
        return arrayList;
    }

    public static boolean hasWorkSourcePermission(Context context) {
        return (context == null || context.getPackageManager() == null || Wrappers.packageManager(context).checkPermission("android.permission.UPDATE_DEVICE_STATS", context.getPackageName()) != 0) ? false : true;
    }

    public static int size(WorkSource workSource) {
        if (zzaap != null) {
            try {
                return ((Integer) zzaap.invoke(workSource, new Object[0])).intValue();
            } catch (Exception e) {
                Log.wtf(TAG, "Unable to assign blame through WorkSource", e);
            }
        }
        return 0;
    }

    private static int zzc(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
            String strValueOf = String.valueOf(str);
            Log.e(TAG, strValueOf.length() != 0 ? "Could not get applicationInfo from package: ".concat(strValueOf) : new String("Could not get applicationInfo from package: "));
            return -1;
        } catch (PackageManager.NameNotFoundException e) {
            String strValueOf2 = String.valueOf(str);
            Log.e(TAG, strValueOf2.length() != 0 ? "Could not find package: ".concat(strValueOf2) : new String("Could not find package: "));
            return -1;
        }
    }

    private static Method zzdf() {
        try {
            return WorkSource.class.getMethod("add", Integer.TYPE);
        } catch (Exception e) {
            return null;
        }
    }

    private static Method zzdg() {
        if (!PlatformVersion.isAtLeastJellyBeanMR2()) {
            return null;
        }
        try {
            return WorkSource.class.getMethod("add", Integer.TYPE, String.class);
        } catch (Exception e) {
            return null;
        }
    }

    private static Method zzdh() {
        try {
            return WorkSource.class.getMethod("size", new Class[0]);
        } catch (Exception e) {
            return null;
        }
    }

    private static Method zzdi() {
        try {
            return WorkSource.class.getMethod("get", Integer.TYPE);
        } catch (Exception e) {
            return null;
        }
    }

    private static Method zzdj() {
        if (!PlatformVersion.isAtLeastJellyBeanMR2()) {
            return null;
        }
        try {
            return WorkSource.class.getMethod("getName", Integer.TYPE);
        } catch (Exception e) {
            return null;
        }
    }

    private static final Method zzdk() {
        if (!PlatformVersion.isAtLeastP()) {
            return null;
        }
        try {
            return WorkSource.class.getMethod("createWorkChain", new Class[0]);
        } catch (Exception e) {
            Log.w(TAG, "Missing WorkChain API createWorkChain", e);
            return null;
        }
    }

    @SuppressLint({"PrivateApi"})
    private static final Method zzdl() {
        if (!PlatformVersion.isAtLeastP()) {
            return null;
        }
        try {
            return Class.forName("android.os.WorkSource$WorkChain").getMethod("addNode", Integer.TYPE, String.class);
        } catch (Exception e) {
            Log.w(TAG, "Missing WorkChain class", e);
            return null;
        }
    }
}
