package com.google.android.gms.maps.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.maps.model.RuntimeRemoteException;

/* JADX INFO: loaded from: classes.dex */
public class zzbz {
    private static final String TAG = zzbz.class.getSimpleName();

    @SuppressLint({"StaticFieldLeak"})
    @Nullable
    private static Context zzcj = null;
    private static zze zzck;

    public static zze zza(Context context) throws GooglePlayServicesNotAvailableException {
        zze zzfVar;
        Preconditions.checkNotNull(context);
        if (zzck != null) {
            return zzck;
        }
        int iIsGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context, 12451000);
        if (iIsGooglePlayServicesAvailable != 0) {
            throw new GooglePlayServicesNotAvailableException(iIsGooglePlayServicesAvailable);
        }
        Log.i(TAG, "Making Creator dynamically");
        IBinder iBinder = (IBinder) zza(zzb(context).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl");
        if (iBinder == null) {
            zzfVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
            zzfVar = iInterfaceQueryLocalInterface instanceof zze ? (zze) iInterfaceQueryLocalInterface : new zzf(iBinder);
        }
        zzck = zzfVar;
        try {
            zzck.zza(ObjectWrapper.wrap(zzb(context).getResources()), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            return zzck;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    private static <T> T zza(Class<?> cls) {
        try {
            return (T) cls.newInstance();
        } catch (IllegalAccessException e) {
            String strValueOf = String.valueOf(cls.getName());
            throw new IllegalStateException(strValueOf.length() != 0 ? "Unable to call the default constructor of ".concat(strValueOf) : new String("Unable to call the default constructor of "));
        } catch (InstantiationException e2) {
            String strValueOf2 = String.valueOf(cls.getName());
            throw new IllegalStateException(strValueOf2.length() != 0 ? "Unable to instantiate the dynamic class ".concat(strValueOf2) : new String("Unable to instantiate the dynamic class "));
        }
    }

    private static <T> T zza(ClassLoader classLoader, String str) {
        try {
            return (T) zza(((ClassLoader) Preconditions.checkNotNull(classLoader)).loadClass(str));
        } catch (ClassNotFoundException e) {
            String strValueOf = String.valueOf(str);
            throw new IllegalStateException(strValueOf.length() != 0 ? "Unable to find dynamic class ".concat(strValueOf) : new String("Unable to find dynamic class "));
        }
    }

    @Nullable
    private static Context zzb(Context context) {
        if (zzcj != null) {
            return zzcj;
        }
        Context contextZzc = zzc(context);
        zzcj = contextZzc;
        return contextZzc;
    }

    @Nullable
    private static Context zzc(Context context) {
        try {
            return DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.maps_dynamite").getModuleContext();
        } catch (Throwable th) {
            Log.e(TAG, "Failed to load maps module, use legacy", th);
            return GooglePlayServicesUtil.getRemoteContext(context);
        }
    }
}
