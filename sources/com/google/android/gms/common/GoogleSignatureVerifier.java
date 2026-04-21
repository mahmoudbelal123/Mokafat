package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GoogleCertificates;
import com.google.android.gms.common.internal.ICertData;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.ProviderConstants;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckReturnValue;

/* JADX INFO: loaded from: classes.dex */
@CheckReturnValue
public class GoogleSignatureVerifier {
    private static GoogleSignatureVerifier zzbv;
    private final Context mContext;

    private GoogleSignatureVerifier(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static GoogleSignatureVerifier getInstance(Context context) {
        Preconditions.checkNotNull(context);
        synchronized (GoogleSignatureVerifier.class) {
            if (zzbv == null) {
                GoogleCertificates.init(context);
                zzbv = new GoogleSignatureVerifier(context);
            }
        }
        return zzbv;
    }

    @VisibleForTesting
    public static synchronized void resetForTests() {
        zzbv = null;
    }

    private static GoogleCertificates.CertData zza(PackageInfo packageInfo, GoogleCertificates.CertData... certDataArr) {
        if (packageInfo.signatures == null) {
            return null;
        }
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zzb zzbVar = new zzb(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < certDataArr.length; i++) {
            if (certDataArr[i].equals(zzbVar)) {
                return certDataArr[i];
            }
        }
        return null;
    }

    private final zzg zza(PackageInfo packageInfo) {
        String str;
        boolean zHonorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
        if (packageInfo == null) {
            str = "null pkg";
        } else if (packageInfo.signatures.length != 1) {
            str = "single cert required";
        } else {
            zzb zzbVar = new zzb(packageInfo.signatures[0].toByteArray());
            String str2 = packageInfo.packageName;
            zzg zzgVarZza = GoogleCertificates.zza(str2, zzbVar, zHonorsDebugCertificates);
            if (!zzgVarZza.zzbl || packageInfo.applicationInfo == null || (packageInfo.applicationInfo.flags & 2) == 0 || (zHonorsDebugCertificates && !GoogleCertificates.zza(str2, zzbVar, false).zzbl)) {
                return zzgVarZza;
            }
            str = "debuggable release cert app rejected";
        }
        return zzg.zze(str);
    }

    private final zzg zzb(int i) {
        String[] packagesForUid = Wrappers.packageManager(this.mContext).getPackagesForUid(i);
        if (packagesForUid == null || packagesForUid.length == 0) {
            return zzg.zze("no pkgs");
        }
        zzg zzgVarZzf = null;
        for (String str : packagesForUid) {
            zzgVarZzf = zzf(str);
            if (zzgVarZzf.zzbl) {
                return zzgVarZzf;
            }
        }
        return zzgVarZzf;
    }

    private final zzg zzf(String str) {
        try {
            return zza(Wrappers.packageManager(this.mContext).getPackageInfo(str, 64));
        } catch (PackageManager.NameNotFoundException e) {
            String strValueOf = String.valueOf(str);
            return zzg.zze(strValueOf.length() != 0 ? "no pkg ".concat(strValueOf) : new String("no pkg "));
        }
    }

    @Deprecated
    public Set<byte[]> getAllGoogleSignatures(boolean z) {
        Set<ICertData> setZzd = z ? GoogleCertificates.zzd() : GoogleCertificates.zze();
        HashSet hashSet = new HashSet(setZzd.size());
        try {
            Iterator<ICertData> it = setZzd.iterator();
            while (it.hasNext()) {
                hashSet.add((byte[]) ObjectWrapper.unwrap(it.next().getBytesWrapped()));
            }
        } catch (RemoteException e) {
            Log.e("GoogleSignatureVerifier", "Failed to get Google certificates from remote", e);
        }
        return hashSet;
    }

    public boolean isChimeraSigned(PackageManager packageManager, PackageInfo packageInfo) {
        String str = packageInfo.packageName;
        packageInfo.packageName = ProviderConstants.API_PROVIDER_NAME;
        boolean zIsPackageGoogleSigned = isPackageGoogleSigned(packageInfo);
        packageInfo.packageName = str;
        return zIsPackageGoogleSigned;
    }

    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (isGooglePublicSignedPackage(packageInfo, false)) {
            return true;
        }
        if (isGooglePublicSignedPackage(packageInfo, true)) {
            if (GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext)) {
                return true;
            }
            Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        }
        return false;
    }

    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo, boolean z) {
        if (packageInfo != null && packageInfo.signatures != null) {
            if (zza(packageInfo, z ? zzd.zzbg : new GoogleCertificates.CertData[]{zzd.zzbg[0]}) != null) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public boolean isGooglePublicSignedPackage(PackageManager packageManager, PackageInfo packageInfo) {
        return isGooglePublicSignedPackage(packageInfo);
    }

    public boolean isPackageGoogleSigned(PackageInfo packageInfo) {
        zzg zzgVarZza = zza(packageInfo);
        zzgVarZza.zzi();
        return zzgVarZza.zzbl;
    }

    @Deprecated
    public boolean isPackageGoogleSigned(PackageManager packageManager, PackageInfo packageInfo) {
        return isPackageGoogleSigned(packageInfo);
    }

    @Deprecated
    public boolean isPackageGoogleSigned(PackageManager packageManager, String str) {
        return isPackageGoogleSigned(str);
    }

    public boolean isPackageGoogleSigned(String str) {
        zzg zzgVarZzf = zzf(str);
        zzgVarZzf.zzi();
        return zzgVarZzf.zzbl;
    }

    public boolean isUidGoogleSigned(int i) {
        zzg zzgVarZzb = zzb(i);
        zzgVarZzb.zzi();
        return zzgVarZzb.zzbl;
    }

    @Deprecated
    public boolean isUidGoogleSigned(PackageManager packageManager, int i) {
        return isUidGoogleSigned(i);
    }

    @Deprecated
    public void verifyPackageIsGoogleSigned(PackageManager packageManager, String str) throws SecurityException {
        verifyPackageIsGoogleSigned(str);
    }

    public void verifyPackageIsGoogleSigned(String str) throws SecurityException {
        zzf(str).zzh();
    }

    public void verifyUidIsGoogleSigned(int i) throws SecurityException {
        zzb(i).zzh();
    }

    @Deprecated
    public void verifyUidIsGoogleSigned(PackageManager packageManager, int i) throws SecurityException {
        verifyUidIsGoogleSigned(i);
    }
}
