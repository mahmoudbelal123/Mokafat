package com.google.android.gms.common;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.ICertData;
import com.google.android.gms.common.internal.IGoogleCertificatesApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.CheckReturnValue;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
@CheckReturnValue
final class GoogleCertificates {
    private static volatile IGoogleCertificatesApi zzax;
    private static final Object zzay = new Object();
    private static Context zzaz;

    @GuardedBy("GoogleCertificates.class")
    private static Set<ICertData> zzba;

    @GuardedBy("GoogleCertificates.class")
    private static Set<ICertData> zzbb;

    static abstract class CertData extends ICertData.Stub {
        private int zzbc;

        protected CertData(byte[] bArr) {
            Preconditions.checkArgument(bArr.length == 25);
            this.zzbc = Arrays.hashCode(bArr);
        }

        protected static byte[] zzd(String str) {
            try {
                return str.getBytes("ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }

        public boolean equals(Object obj) {
            IObjectWrapper bytesWrapped;
            if (obj == null || !(obj instanceof ICertData)) {
                return false;
            }
            try {
                ICertData iCertData = (ICertData) obj;
                if (iCertData.getHashCode() == hashCode() && (bytesWrapped = iCertData.getBytesWrapped()) != null) {
                    return Arrays.equals(getBytes(), (byte[]) ObjectWrapper.unwrap(bytesWrapped));
                }
                return false;
            } catch (RemoteException e) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
                return false;
            }
        }

        abstract byte[] getBytes();

        @Override // com.google.android.gms.common.internal.ICertData
        public IObjectWrapper getBytesWrapped() {
            return ObjectWrapper.wrap(getBytes());
        }

        @Override // com.google.android.gms.common.internal.ICertData
        public int getHashCode() {
            return hashCode();
        }

        public int hashCode() {
            return this.zzbc;
        }
    }

    static synchronized void init(Context context) {
        if (zzaz != null) {
            Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
        } else if (context != null) {
            zzaz = context.getApplicationContext();
        }
    }

    static zzg zza(String str, CertData certData, boolean z) {
        String str2;
        try {
            zzc();
            Preconditions.checkNotNull(zzaz);
        } catch (DynamiteModule.LoadingException e) {
            e = e;
            str2 = "module init";
        }
        try {
            if (zzax.isGoogleOrPlatformSigned(new GoogleCertificatesQuery(str, certData, z), ObjectWrapper.wrap(zzaz.getPackageManager()))) {
                return zzg.zzg();
            }
            return zzg.zza(str, certData, z, !z && zza(str, certData, true).zzbl);
        } catch (RemoteException e2) {
            e = e2;
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
            str2 = "module call";
            return zzg.zza(str2, e);
        }
    }

    private static Set<ICertData> zza(IBinder[] iBinderArr) throws RemoteException {
        HashSet hashSet = new HashSet(iBinderArr.length);
        for (IBinder iBinder : iBinderArr) {
            ICertData iCertDataAsInterface = ICertData.Stub.asInterface(iBinder);
            if (iCertDataAsInterface != null) {
                hashSet.add(iCertDataAsInterface);
            }
        }
        return hashSet;
    }

    private static void zzc() throws DynamiteModule.LoadingException {
        if (zzax != null) {
            return;
        }
        Preconditions.checkNotNull(zzaz);
        synchronized (zzay) {
            if (zzax == null) {
                zzax = IGoogleCertificatesApi.Stub.asInterface(DynamiteModule.load(zzaz, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.googlecertificates").instantiate("com.google.android.gms.common.GoogleCertificatesImpl"));
            }
        }
    }

    static synchronized Set<ICertData> zzd() {
        if (zzba != null) {
            return zzba;
        }
        try {
            zzc();
            try {
                IObjectWrapper googleCertificates = zzax.getGoogleCertificates();
                if (googleCertificates == null) {
                    Log.e("GoogleCertificates", "Failed to get Google certificates from remote");
                    return Collections.emptySet();
                }
                zzba = zza((IBinder[]) ObjectWrapper.unwrap(googleCertificates));
                return zzba;
            } catch (RemoteException e) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
                return Collections.emptySet();
            }
        } catch (DynamiteModule.LoadingException e2) {
            Log.e("GoogleCertificates", "Failed to load com.google.android.gms.googlecertificates", e2);
            return Collections.emptySet();
        }
    }

    static synchronized Set<ICertData> zze() {
        if (zzbb != null) {
            return zzbb;
        }
        try {
            zzc();
            try {
                IObjectWrapper googleReleaseCertificates = zzax.getGoogleReleaseCertificates();
                if (googleReleaseCertificates == null) {
                    Log.e("GoogleCertificates", "Failed to get Google certificates from remote");
                    return Collections.emptySet();
                }
                zzbb = zza((IBinder[]) ObjectWrapper.unwrap(googleReleaseCertificates));
                return zzbb;
            } catch (RemoteException e) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
                return Collections.emptySet();
            }
        } catch (DynamiteModule.LoadingException e2) {
            Log.e("GoogleCertificates", "Failed to load com.google.android.gms.googlecertificates", e2);
            return Collections.emptySet();
        }
    }
}
