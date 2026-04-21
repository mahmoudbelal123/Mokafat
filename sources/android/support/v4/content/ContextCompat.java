package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class ContextCompat {
    private static final String TAG = "ContextCompat";
    private static final Object sLock = new Object();
    private static TypedValue sTempValue;

    protected ContextCompat() {
    }

    public static boolean startActivities(@NonNull Context context, @NonNull Intent[] intents) {
        return startActivities(context, intents, null);
    }

    public static boolean startActivities(@NonNull Context context, @NonNull Intent[] intents, @Nullable Bundle options) {
        if (Build.VERSION.SDK_INT >= 16) {
            context.startActivities(intents, options);
            return true;
        }
        context.startActivities(intents);
        return true;
    }

    public static void startActivity(@NonNull Context context, @NonNull Intent intent, @Nullable Bundle options) {
        if (Build.VERSION.SDK_INT >= 16) {
            context.startActivity(intent, options);
        } else {
            context.startActivity(intent);
        }
    }

    @Nullable
    public static File getDataDir(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return context.getDataDir();
        }
        String dataDir = context.getApplicationInfo().dataDir;
        if (dataDir != null) {
            return new File(dataDir);
        }
        return null;
    }

    @NonNull
    public static File[] getObbDirs(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            return context.getObbDirs();
        }
        return new File[]{context.getObbDir()};
    }

    @NonNull
    public static File[] getExternalFilesDirs(@NonNull Context context, @Nullable String type) {
        if (Build.VERSION.SDK_INT >= 19) {
            return context.getExternalFilesDirs(type);
        }
        return new File[]{context.getExternalFilesDir(type)};
    }

    @NonNull
    public static File[] getExternalCacheDirs(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            return context.getExternalCacheDirs();
        }
        return new File[]{context.getExternalCacheDir()};
    }

    private static File buildPath(File base, String... segments) {
        File cur = base;
        for (String segment : segments) {
            if (cur == null) {
                cur = new File(segment);
            } else if (segment != null) {
                cur = new File(cur, segment);
            }
        }
        return cur;
    }

    @Nullable
    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int id) {
        int resolvedId;
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getDrawable(id);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return context.getResources().getDrawable(id);
        }
        synchronized (sLock) {
            if (sTempValue == null) {
                sTempValue = new TypedValue();
            }
            context.getResources().getValue(id, sTempValue, true);
            resolvedId = sTempValue.resourceId;
        }
        return context.getResources().getDrawable(resolvedId);
    }

    @Nullable
    public static ColorStateList getColorStateList(@NonNull Context context, @ColorRes int id) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(id);
        }
        return context.getResources().getColorStateList(id);
    }

    @ColorInt
    public static int getColor(@NonNull Context context, @ColorRes int id) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColor(id);
        }
        return context.getResources().getColor(id);
    }

    public static int checkSelfPermission(@NonNull Context context, @NonNull String permission) {
        if (permission == null) {
            throw new IllegalArgumentException("permission is null");
        }
        return context.checkPermission(permission, Process.myPid(), Process.myUid());
    }

    @Nullable
    public static File getNoBackupFilesDir(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getNoBackupFilesDir();
        }
        ApplicationInfo appInfo = context.getApplicationInfo();
        return createFilesDir(new File(appInfo.dataDir, "no_backup"));
    }

    public static File getCodeCacheDir(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getCodeCacheDir();
        }
        ApplicationInfo appInfo = context.getApplicationInfo();
        return createFilesDir(new File(appInfo.dataDir, "code_cache"));
    }

    private static synchronized File createFilesDir(File file) {
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        if (file.exists()) {
            return file;
        }
        Log.w(TAG, "Unable to create files subdir " + file.getPath());
        return null;
    }

    @Nullable
    public static Context createDeviceProtectedStorageContext(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return context.createDeviceProtectedStorageContext();
        }
        return null;
    }

    public static boolean isDeviceProtectedStorage(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return context.isDeviceProtectedStorage();
        }
        return false;
    }

    public static void startForegroundService(@NonNull Context context, @NonNull Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }
}
