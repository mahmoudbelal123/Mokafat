package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.IDynamiteLoader;
import com.google.android.gms.dynamite.IDynamiteLoaderV2;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
public final class DynamiteModule {

    @GuardedBy("DynamiteModule.class")
    private static Boolean zzabr;

    @GuardedBy("DynamiteModule.class")
    private static IDynamiteLoader zzabs;

    @GuardedBy("DynamiteModule.class")
    private static IDynamiteLoaderV2 zzabt;

    @GuardedBy("DynamiteModule.class")
    private static String zzabu;
    private final Context zzabx;
    private static final ThreadLocal<zza> zzabv = new ThreadLocal<>();
    private static final VersionPolicy.IVersions zzabw = new com.google.android.gms.dynamite.zza();
    public static final VersionPolicy PREFER_REMOTE = new com.google.android.gms.dynamite.zzb();
    public static final VersionPolicy PREFER_LOCAL = new zzc();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzd();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zze();
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzf();
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION_NO_FORCE_STAGING = new zzg();

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {

        @GuardedBy("DynamiteLoaderClassLoader.class")
        public static ClassLoader sClassLoader;
    }

    public static class LoadingException extends Exception {
        private LoadingException(String str) {
            super(str);
        }

        /* synthetic */ LoadingException(String str, com.google.android.gms.dynamite.zza zzaVar) {
            this(str);
        }

        private LoadingException(String str, Throwable th) {
            super(str, th);
        }

        /* synthetic */ LoadingException(String str, Throwable th, com.google.android.gms.dynamite.zza zzaVar) {
            this(str, th);
        }
    }

    public interface VersionPolicy {

        public interface IVersions {
            int getLocalVersion(Context context, String str);

            int getRemoteVersion(Context context, String str, boolean z) throws LoadingException;
        }

        public static class SelectionResult {
            public int localVersion = 0;
            public int remoteVersion = 0;
            public int selection = 0;
        }

        SelectionResult selectModule(Context context, String str, IVersions iVersions) throws LoadingException;
    }

    private static class zza {
        public Cursor zzaby;

        private zza() {
        }

        /* synthetic */ zza(com.google.android.gms.dynamite.zza zzaVar) {
            this();
        }
    }

    private static class zzb implements VersionPolicy.IVersions {
        private final int zzabz;
        private final int zzaca = 0;

        public zzb(int i, int i2) {
            this.zzabz = i;
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
        public final int getLocalVersion(Context context, String str) {
            return this.zzabz;
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
        public final int getRemoteVersion(Context context, String str, boolean z) {
            return 0;
        }
    }

    private DynamiteModule(Context context) {
        this.zzabx = (Context) Preconditions.checkNotNull(context);
    }

    public static int getLocalVersion(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            StringBuilder sb = new StringBuilder(61 + String.valueOf(str).length());
            sb.append("com.google.android.gms.dynamite.descriptors.");
            sb.append(str);
            sb.append(".ModuleDescriptor");
            Class<?> clsLoadClass = classLoader.loadClass(sb.toString());
            Field declaredField = clsLoadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = clsLoadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                return declaredField2.getInt(null);
            }
            String strValueOf = String.valueOf(declaredField.get(null));
            StringBuilder sb2 = new StringBuilder(51 + String.valueOf(strValueOf).length() + String.valueOf(str).length());
            sb2.append("Module descriptor id '");
            sb2.append(strValueOf);
            sb2.append("' didn't match expected id '");
            sb2.append(str);
            sb2.append("'");
            Log.e("DynamiteModule", sb2.toString());
            return 0;
        } catch (ClassNotFoundException e) {
            StringBuilder sb3 = new StringBuilder(45 + String.valueOf(str).length());
            sb3.append("Local module descriptor class for ");
            sb3.append(str);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e2) {
            String strValueOf2 = String.valueOf(e2.getMessage());
            Log.e("DynamiteModule", strValueOf2.length() != 0 ? "Failed to load module descriptor class: ".concat(strValueOf2) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    public static Uri getQueryUri(String str, boolean z) {
        String str2 = z ? ProviderConstants.API_PATH_FORCE_STAGING : ProviderConstants.API_PATH;
        StringBuilder sb = new StringBuilder(42 + String.valueOf(str2).length() + String.valueOf(str).length());
        sb.append("content://com.google.android.gms.chimera/");
        sb.append(str2);
        sb.append("/");
        sb.append(str);
        return Uri.parse(sb.toString());
    }

    public static int getRemoteVersion(Context context, String str) {
        return getRemoteVersion(context, str, false);
    }

    public static int getRemoteVersion(Context context, String str, boolean z) {
        Class<?> clsLoadClass;
        Field declaredField;
        Boolean bool;
        synchronized (DynamiteModule.class) {
            Boolean bool2 = zzabr;
            if (bool2 == null) {
                try {
                    clsLoadClass = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName());
                    declaredField = clsLoadClass.getDeclaredField("sClassLoader");
                } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                    String strValueOf = String.valueOf(e);
                    StringBuilder sb = new StringBuilder(30 + String.valueOf(strValueOf).length());
                    sb.append("Failed to load module via V2: ");
                    sb.append(strValueOf);
                    Log.w("DynamiteModule", sb.toString());
                    bool2 = Boolean.FALSE;
                }
                synchronized (clsLoadClass) {
                    ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                    if (classLoader != null) {
                        if (classLoader != ClassLoader.getSystemClassLoader()) {
                            try {
                                zza(classLoader);
                            } catch (LoadingException e2) {
                            }
                            bool = Boolean.TRUE;
                        }
                        bool2 = bool;
                        zzabr = bool2;
                    } else if ("com.google.android.gms".equals(context.getApplicationContext().getPackageName())) {
                        declaredField.set(null, ClassLoader.getSystemClassLoader());
                    } else {
                        try {
                            int iZzb = zzb(context, str, z);
                            if (zzabu != null && !zzabu.isEmpty()) {
                                zzh zzhVar = new zzh(zzabu, ClassLoader.getSystemClassLoader());
                                zza(zzhVar);
                                declaredField.set(null, zzhVar);
                                zzabr = Boolean.TRUE;
                                return iZzb;
                            }
                            return iZzb;
                        } catch (LoadingException e3) {
                            declaredField.set(null, ClassLoader.getSystemClassLoader());
                        }
                    }
                    bool = Boolean.FALSE;
                    bool2 = bool;
                    zzabr = bool2;
                }
            }
            if (!bool2.booleanValue()) {
                return zza(context, str, z);
            }
            try {
                return zzb(context, str, z);
            } catch (LoadingException e4) {
                String strValueOf2 = String.valueOf(e4.getMessage());
                Log.w("DynamiteModule", strValueOf2.length() != 0 ? "Failed to retrieve remote module version: ".concat(strValueOf2) : new String("Failed to retrieve remote module version: "));
                return 0;
            }
        }
    }

    @VisibleForTesting
    public static synchronized Boolean getUseV2ForTesting() {
        return zzabr;
    }

    public static DynamiteModule load(Context context, VersionPolicy versionPolicy, String str) throws LoadingException {
        DynamiteModule dynamiteModuleZzd;
        zza zzaVar = zzabv.get();
        com.google.android.gms.dynamite.zza zzaVar2 = null;
        zza zzaVar3 = new zza(zzaVar2);
        zzabv.set(zzaVar3);
        try {
            VersionPolicy.SelectionResult selectionResultSelectModule = versionPolicy.selectModule(context, str, zzabw);
            int i = selectionResultSelectModule.localVersion;
            int i2 = selectionResultSelectModule.remoteVersion;
            StringBuilder sb = new StringBuilder(68 + String.valueOf(str).length() + String.valueOf(str).length());
            sb.append("Considering local module ");
            sb.append(str);
            sb.append(":");
            sb.append(i);
            sb.append(" and remote module ");
            sb.append(str);
            sb.append(":");
            sb.append(i2);
            Log.i("DynamiteModule", sb.toString());
            if (selectionResultSelectModule.selection != 0 && ((selectionResultSelectModule.selection != -1 || selectionResultSelectModule.localVersion != 0) && (selectionResultSelectModule.selection != 1 || selectionResultSelectModule.remoteVersion != 0))) {
                if (selectionResultSelectModule.selection == -1) {
                    dynamiteModuleZzd = zzd(context, str);
                } else {
                    if (selectionResultSelectModule.selection != 1) {
                        int i3 = selectionResultSelectModule.selection;
                        StringBuilder sb2 = new StringBuilder(47);
                        sb2.append("VersionPolicy returned invalid code:");
                        sb2.append(i3);
                        throw new LoadingException(sb2.toString(), zzaVar2);
                    }
                    try {
                        DynamiteModule dynamiteModuleZza = zza(context, str, selectionResultSelectModule.remoteVersion);
                        if (zzaVar3.zzaby != null) {
                            zzaVar3.zzaby.close();
                        }
                        zzabv.set(zzaVar);
                        return dynamiteModuleZza;
                    } catch (LoadingException e) {
                        String strValueOf = String.valueOf(e.getMessage());
                        Log.w("DynamiteModule", strValueOf.length() != 0 ? "Failed to load remote module: ".concat(strValueOf) : new String("Failed to load remote module: "));
                        if (selectionResultSelectModule.localVersion == 0 || versionPolicy.selectModule(context, str, new zzb(selectionResultSelectModule.localVersion, 0)).selection != -1) {
                            throw new LoadingException("Remote load failed. No local fallback found.", e, zzaVar2);
                        }
                        dynamiteModuleZzd = zzd(context, str);
                        if (zzaVar3.zzaby != null) {
                        }
                    }
                }
                return dynamiteModuleZzd;
            }
            int i4 = selectionResultSelectModule.localVersion;
            int i5 = selectionResultSelectModule.remoteVersion;
            StringBuilder sb3 = new StringBuilder(91);
            sb3.append("No acceptable module found. Local version is ");
            sb3.append(i4);
            sb3.append(" and remote version is ");
            sb3.append(i5);
            sb3.append(".");
            throw new LoadingException(sb3.toString(), zzaVar2);
        } finally {
            if (zzaVar3.zzaby != null) {
                zzaVar3.zzaby.close();
            }
            zzabv.set(zzaVar);
        }
    }

    public static Cursor queryForDynamiteModule(Context context, String str, boolean z) {
        return context.getContentResolver().query(getQueryUri(str, z), null, null, null, null);
    }

    @VisibleForTesting
    public static synchronized void resetInternalStateForTesting() {
        zzabs = null;
        zzabt = null;
        zzabu = null;
        zzabr = null;
        synchronized (DynamiteLoaderClassLoader.class) {
            DynamiteLoaderClassLoader.sClassLoader = null;
        }
    }

    @VisibleForTesting
    public static synchronized void setUseV2ForTesting(Boolean bool) {
        zzabr = bool;
    }

    private static int zza(Context context, String str, boolean z) {
        IDynamiteLoader iDynamiteLoaderZzg = zzg(context);
        if (iDynamiteLoaderZzg == null) {
            return 0;
        }
        try {
            return iDynamiteLoaderZzg.getModuleVersion2(ObjectWrapper.wrap(context), str, z);
        } catch (RemoteException e) {
            String strValueOf = String.valueOf(e.getMessage());
            Log.w("DynamiteModule", strValueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(strValueOf) : new String("Failed to retrieve remote module version: "));
            return 0;
        }
    }

    private static Context zza(Context context, String str, int i, Cursor cursor, IDynamiteLoaderV2 iDynamiteLoaderV2) {
        try {
            return (Context) ObjectWrapper.unwrap(iDynamiteLoaderV2.loadModule2(ObjectWrapper.wrap(context), str, i, ObjectWrapper.wrap(cursor)));
        } catch (Exception e) {
            String strValueOf = String.valueOf(e.toString());
            Log.e("DynamiteModule", strValueOf.length() != 0 ? "Failed to load DynamiteLoader: ".concat(strValueOf) : new String("Failed to load DynamiteLoader: "));
            return null;
        }
    }

    private static DynamiteModule zza(Context context, String str, int i) throws LoadingException {
        Boolean bool;
        synchronized (DynamiteModule.class) {
            bool = zzabr;
        }
        if (bool == null) {
            throw new LoadingException("Failed to determine which loading route to use.", (com.google.android.gms.dynamite.zza) null);
        }
        return bool.booleanValue() ? zzc(context, str, i) : zzb(context, str, i);
    }

    @GuardedBy("DynamiteModule.class")
    private static void zza(ClassLoader classLoader) throws LoadingException {
        try {
            zzabt = IDynamiteLoaderV2.Stub.asInterface((IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0061  */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v3, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int zzb(android.content.Context r2, java.lang.String r3, boolean r4) throws java.lang.Throwable {
        /*
            r0 = 0
            android.database.Cursor r2 = queryForDynamiteModule(r2, r3, r4)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4f
            if (r2 == 0) goto L3d
            boolean r3 = r2.moveToFirst()     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5e
            if (r3 != 0) goto Le
            goto L3d
        Le:
            r3 = 0
            int r3 = r2.getInt(r3)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5e
            if (r3 <= 0) goto L35
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r4 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r4)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5e
            r1 = 2
            java.lang.String r1 = r2.getString(r1)     // Catch: java.lang.Throwable -> L32
            com.google.android.gms.dynamite.DynamiteModule.zzabu = r1     // Catch: java.lang.Throwable -> L32
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L32
            java.lang.ThreadLocal<com.google.android.gms.dynamite.DynamiteModule$zza> r4 = com.google.android.gms.dynamite.DynamiteModule.zzabv     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5e
            java.lang.Object r4 = r4.get()     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5e
            com.google.android.gms.dynamite.DynamiteModule$zza r4 = (com.google.android.gms.dynamite.DynamiteModule.zza) r4     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5e
            if (r4 == 0) goto L35
            android.database.Cursor r1 = r4.zzaby     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5e
            if (r1 != 0) goto L35
            r4.zzaby = r2     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5e
            r2 = r0
            goto L35
        L32:
            r3 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L32
            throw r3     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5e
        L35:
            if (r2 == 0) goto L3a
            r2.close()
        L3a:
            return r3
        L3b:
            r3 = move-exception
            goto L51
        L3d:
            java.lang.String r3 = "DynamiteModule"
            java.lang.String r4 = "Failed to retrieve remote module version."
            android.util.Log.w(r3, r4)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5e
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r3 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5e
            java.lang.String r4 = "Failed to connect to dynamite module ContentResolver."
            r3.<init>(r4, r0)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5e
            throw r3     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L5e
        L4c:
            r3 = move-exception
            r2 = r0
            goto L5f
        L4f:
            r3 = move-exception
            r2 = r0
        L51:
            boolean r4 = r3 instanceof com.google.android.gms.dynamite.DynamiteModule.LoadingException     // Catch: java.lang.Throwable -> L5e
            if (r4 == 0) goto L56
            throw r3     // Catch: java.lang.Throwable -> L5e
        L56:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r4 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch: java.lang.Throwable -> L5e
            java.lang.String r1 = "V2 version check failed"
            r4.<init>(r1, r3, r0)     // Catch: java.lang.Throwable -> L5e
            throw r4     // Catch: java.lang.Throwable -> L5e
        L5e:
            r3 = move-exception
        L5f:
            if (r2 == 0) goto L64
            r2.close()
        L64:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzb(android.content.Context, java.lang.String, boolean):int");
    }

    private static DynamiteModule zzb(Context context, String str, int i) throws LoadingException {
        StringBuilder sb = new StringBuilder(51 + String.valueOf(str).length());
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i);
        Log.i("DynamiteModule", sb.toString());
        IDynamiteLoader iDynamiteLoaderZzg = zzg(context);
        com.google.android.gms.dynamite.zza zzaVar = null;
        if (iDynamiteLoaderZzg == null) {
            throw new LoadingException("Failed to create IDynamiteLoader.", zzaVar);
        }
        try {
            IObjectWrapper iObjectWrapperCreateModuleContext = iDynamiteLoaderZzg.createModuleContext(ObjectWrapper.wrap(context), str, i);
            if (ObjectWrapper.unwrap(iObjectWrapperCreateModuleContext) == null) {
                throw new LoadingException("Failed to load remote module.", zzaVar);
            }
            return new DynamiteModule((Context) ObjectWrapper.unwrap(iObjectWrapperCreateModuleContext));
        } catch (RemoteException e) {
            throw new LoadingException("Failed to load remote module.", e, zzaVar);
        }
    }

    private static DynamiteModule zzc(Context context, String str, int i) throws LoadingException {
        IDynamiteLoaderV2 iDynamiteLoaderV2;
        StringBuilder sb = new StringBuilder(51 + String.valueOf(str).length());
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i);
        Log.i("DynamiteModule", sb.toString());
        synchronized (DynamiteModule.class) {
            iDynamiteLoaderV2 = zzabt;
        }
        com.google.android.gms.dynamite.zza zzaVar = null;
        if (iDynamiteLoaderV2 == null) {
            throw new LoadingException("DynamiteLoaderV2 was not cached.", zzaVar);
        }
        zza zzaVar2 = zzabv.get();
        if (zzaVar2 == null || zzaVar2.zzaby == null) {
            throw new LoadingException("No result cursor", zzaVar);
        }
        Context contextZza = zza(context.getApplicationContext(), str, i, zzaVar2.zzaby, iDynamiteLoaderV2);
        if (contextZza == null) {
            throw new LoadingException("Failed to get module context", zzaVar);
        }
        return new DynamiteModule(contextZza);
    }

    private static DynamiteModule zzd(Context context, String str) {
        String strValueOf = String.valueOf(str);
        Log.i("DynamiteModule", strValueOf.length() != 0 ? "Selected local version of ".concat(strValueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static IDynamiteLoader zzg(Context context) {
        synchronized (DynamiteModule.class) {
            if (zzabs != null) {
                return zzabs;
            }
            if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context) != 0) {
                return null;
            }
            try {
                IDynamiteLoader iDynamiteLoaderAsInterface = IDynamiteLoader.Stub.asInterface((IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance());
                if (iDynamiteLoaderAsInterface != null) {
                    zzabs = iDynamiteLoaderAsInterface;
                    return iDynamiteLoaderAsInterface;
                }
            } catch (Exception e) {
                String strValueOf = String.valueOf(e.getMessage());
                Log.e("DynamiteModule", strValueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(strValueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
            }
            return null;
        }
    }

    public final Context getModuleContext() {
        return this.zzabx;
    }

    public final IBinder instantiate(String str) throws LoadingException {
        try {
            return (IBinder) this.zzabx.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String strValueOf = String.valueOf(str);
            throw new LoadingException(strValueOf.length() != 0 ? "Failed to instantiate module class: ".concat(strValueOf) : new String("Failed to instantiate module class: "), e, null);
        }
    }
}
