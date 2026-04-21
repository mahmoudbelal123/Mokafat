package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzbg {
    final zzbt zzadj;

    zzbg(zzbt zzbtVar) {
        this.zzadj = zzbtVar;
    }

    @WorkerThread
    protected final void zzcd(String str) {
        if (str == null || str.isEmpty()) {
            this.zzadj.zzgo().zzjj().zzbx("Install Referrer Reporter was called with invalid app package name");
            return;
        }
        this.zzadj.zzgn().zzaf();
        if (!zzka()) {
            this.zzadj.zzgo().zzjj().zzbx("Install Referrer Reporter is not available");
            return;
        }
        this.zzadj.zzgo().zzjj().zzbx("Install Referrer Reporter is initializing");
        zzbh zzbhVar = new zzbh(this, str);
        this.zzadj.zzgn().zzaf();
        Intent intent = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");
        intent.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));
        PackageManager packageManager = this.zzadj.getContext().getPackageManager();
        if (packageManager == null) {
            this.zzadj.zzgo().zzjg().zzbx("Failed to obtain Package Manager to verify binding conditions");
            return;
        }
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (listQueryIntentServices != null && !listQueryIntentServices.isEmpty()) {
            ResolveInfo resolveInfo = listQueryIntentServices.get(0);
            if (resolveInfo.serviceInfo != null) {
                String str2 = resolveInfo.serviceInfo.packageName;
                if (resolveInfo.serviceInfo.name != null && "com.android.vending".equals(str2) && zzka()) {
                    try {
                        this.zzadj.zzgo().zzjj().zzg("Install Referrer Service is", ConnectionTracker.getInstance().bindService(this.zzadj.getContext(), new Intent(intent), zzbhVar, 1) ? "available" : "not available");
                        return;
                    } catch (Exception e) {
                        this.zzadj.zzgo().zzjd().zzg("Exception occurred while binding to Install Referrer Service", e.getMessage());
                        return;
                    }
                }
                this.zzadj.zzgo().zzjj().zzbx("Play Store missing or incompatible. Version 8.3.73 or later required");
                return;
            }
            return;
        }
        this.zzadj.zzgo().zzjj().zzbx("Play Service for fetching Install Referrer is unavailable on device");
    }

    @VisibleForTesting
    private final boolean zzka() {
        try {
            PackageManagerWrapper packageManagerWrapperPackageManager = Wrappers.packageManager(this.zzadj.getContext());
            if (packageManagerWrapperPackageManager != null) {
                return packageManagerWrapperPackageManager.getPackageInfo("com.android.vending", 128).versionCode >= 80837300;
            }
            this.zzadj.zzgo().zzjj().zzbx("Failed to retrieve Package Manager to check Play Store compatibility");
            return false;
        } catch (Exception e) {
            this.zzadj.zzgo().zzjj().zzg("Failed to retrieve Play Store version", e);
            return false;
        }
    }

    @WorkerThread
    @VisibleForTesting
    @Nullable
    final Bundle zza(String str, com.google.android.gms.internal.measurement.zzu zzuVar) {
        this.zzadj.zzgn().zzaf();
        if (zzuVar == null) {
            this.zzadj.zzgo().zzjg().zzbx("Attempting to use Install Referrer Service while it is not initialized");
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("package_name", str);
        try {
            Bundle bundleZza = zzuVar.zza(bundle);
            if (bundleZza == null) {
                this.zzadj.zzgo().zzjd().zzbx("Install Referrer Service returned a null response");
                return null;
            }
            return bundleZza;
        } catch (Exception e) {
            this.zzadj.zzgo().zzjd().zzg("Exception occurred while retrieving the Install Referrer", e.getMessage());
            return null;
        }
    }
}
