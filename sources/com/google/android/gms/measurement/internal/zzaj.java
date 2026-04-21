package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.WorkerThread;
import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.InstantApps;

/* JADX INFO: loaded from: classes.dex */
public final class zzaj extends zzf {
    private String zzafx;
    private String zzage;
    private long zzagh;
    private String zzagk;
    private int zzagy;
    private int zzalo;
    private long zzalp;
    private String zztr;
    private String zzts;
    private String zztt;

    zzaj(zzbt zzbtVar) {
        super(zzbtVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzgt() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.measurement.internal.zzf
    protected final void zzgu() {
        char c;
        String installerPackageName = EnvironmentCompat.MEDIA_UNKNOWN;
        String str = "Unknown";
        String string = "Unknown";
        String packageName = getContext().getPackageName();
        PackageManager packageManager = getContext().getPackageManager();
        int i = Integer.MIN_VALUE;
        if (packageManager == null) {
            zzgo().zzjd().zzg("PackageManager is null, app identity information might be inaccurate. appId", zzap.zzbv(packageName));
        } else {
            try {
                installerPackageName = packageManager.getInstallerPackageName(packageName);
            } catch (IllegalArgumentException e) {
                zzgo().zzjd().zzg("Error retrieving app installer package name. appId", zzap.zzbv(packageName));
            }
            if (installerPackageName == null) {
                installerPackageName = "manual_install";
            } else if ("com.android.vending".equals(installerPackageName)) {
                installerPackageName = "";
            }
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
                if (packageInfo != null) {
                    CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                    if (!TextUtils.isEmpty(applicationLabel)) {
                        string = applicationLabel.toString();
                    }
                    String str2 = packageInfo.versionName;
                    try {
                        i = packageInfo.versionCode;
                        str = str2;
                    } catch (PackageManager.NameNotFoundException e2) {
                        str = str2;
                        zzgo().zzjd().zze("Error retrieving package info. appId, appName", zzap.zzbv(packageName), string);
                    }
                }
            } catch (PackageManager.NameNotFoundException e3) {
            }
        }
        this.zztt = packageName;
        this.zzage = installerPackageName;
        this.zzts = str;
        this.zzalo = i;
        this.zztr = string;
        this.zzalp = 0L;
        zzgr();
        Status statusInitialize = GoogleServices.initialize(getContext());
        boolean z = true;
        char c2 = statusInitialize != null && statusInitialize.isSuccess();
        if (TextUtils.isEmpty(this.zzadj.zzkk()) || !"am".equals(this.zzadj.zzkl())) {
            c = false;
        } else {
            c = true;
        }
        boolean z2 = c2 | c;
        if (!z2) {
            if (statusInitialize == null) {
                zzgo().zzjd().zzbx("GoogleService failed to initialize (no status)");
            } else {
                zzgo().zzjd().zze("GoogleService failed to initialize, status", Integer.valueOf(statusInitialize.getStatusCode()), statusInitialize.getStatusMessage());
            }
        }
        if (z2) {
            Boolean boolZzhv = zzgq().zzhv();
            if (zzgq().zzhu()) {
                if (this.zzadj.zzkj()) {
                    zzgo().zzjj().zzbx("Collection disabled with firebase_analytics_collection_deactivated=1");
                }
            } else if (boolZzhv != null && !boolZzhv.booleanValue()) {
                if (this.zzadj.zzkj()) {
                    zzgo().zzjj().zzbx("Collection disabled with firebase_analytics_collection_enabled=0");
                }
            } else if (boolZzhv == null && GoogleServices.isMeasurementExplicitlyDisabled()) {
                zzgo().zzjj().zzbx("Collection disabled with google_app_measurement_enable=0");
            } else {
                zzgo().zzjl().zzbx("Collection enabled");
            }
            z = false;
        } else {
            z = false;
        }
        this.zzafx = "";
        this.zzagk = "";
        this.zzagh = 0L;
        zzgr();
        if (!TextUtils.isEmpty(this.zzadj.zzkk()) && "am".equals(this.zzadj.zzkl())) {
            this.zzagk = this.zzadj.zzkk();
        }
        try {
            String googleAppId = GoogleServices.getGoogleAppId();
            this.zzafx = TextUtils.isEmpty(googleAppId) ? "" : googleAppId;
            if (!TextUtils.isEmpty(googleAppId)) {
                this.zzagk = new StringResourceValueReader(getContext()).getString("gma_app_id");
            }
            if (z) {
                zzgo().zzjl().zze("App package, google app id", this.zztt, this.zzafx);
            }
        } catch (IllegalStateException e4) {
            zzgo().zzjd().zze("getGoogleAppId or isMeasurementEnabled failed with exception. appId", zzap.zzbv(packageName), e4);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            this.zzagy = InstantApps.isInstantApp(getContext()) ? 1 : 0;
        } else {
            this.zzagy = 0;
        }
    }

    @WorkerThread
    final zzh zzbr(String str) {
        String strZziz;
        zzaf();
        zzgb();
        String strZzal = zzal();
        String gmpAppId = getGmpAppId();
        zzcl();
        String str2 = this.zzts;
        long jZzja = zzja();
        zzcl();
        String str3 = this.zzage;
        long jZzhc = zzgq().zzhc();
        zzcl();
        zzaf();
        if (this.zzalp == 0) {
            this.zzalp = this.zzadj.zzgm().zzd(getContext(), getContext().getPackageName());
        }
        long j = this.zzalp;
        boolean zIsEnabled = this.zzadj.isEnabled();
        boolean z = !zzgp().zzanv;
        zzaf();
        zzgb();
        if (zzgq().zzbc(this.zztt) && !this.zzadj.isEnabled()) {
            strZziz = null;
        } else {
            strZziz = zziz();
        }
        String str4 = strZziz;
        zzcl();
        long j2 = this.zzagh;
        long jZzkp = this.zzadj.zzkp();
        int iZzjb = zzjb();
        zzn zznVarZzgq = zzgq();
        zznVarZzgq.zzgb();
        Boolean boolZzau = zznVarZzgq.zzau("google_analytics_adid_collection_enabled");
        boolean zBooleanValue = Boolean.valueOf(boolZzau == null || boolZzau.booleanValue()).booleanValue();
        zzn zznVarZzgq2 = zzgq();
        zznVarZzgq2.zzgb();
        Boolean boolZzau2 = zznVarZzgq2.zzau("google_analytics_ssaid_collection_enabled");
        return new zzh(strZzal, gmpAppId, str2, jZzja, str3, jZzhc, j, str, zIsEnabled, z, str4, j2, jZzkp, iZzjb, zBooleanValue, Boolean.valueOf(boolZzau2 == null || boolZzau2.booleanValue()).booleanValue(), zzgp().zzjx(), zzgw());
    }

    @WorkerThread
    @VisibleForTesting
    private final String zziz() {
        try {
            Class<?> clsLoadClass = getContext().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
            if (clsLoadClass == null) {
                return null;
            }
            try {
                Object objInvoke = clsLoadClass.getDeclaredMethod("getInstance", Context.class).invoke(null, getContext());
                if (objInvoke == null) {
                    return null;
                }
                try {
                    return (String) clsLoadClass.getDeclaredMethod("getFirebaseInstanceId", new Class[0]).invoke(objInvoke, new Object[0]);
                } catch (Exception e) {
                    zzgo().zzji().zzbx("Failed to retrieve Firebase Instance Id");
                    return null;
                }
            } catch (Exception e2) {
                zzgo().zzjh().zzbx("Failed to obtain Firebase Analytics instance");
                return null;
            }
        } catch (ClassNotFoundException e3) {
            return null;
        }
    }

    final String zzal() {
        zzcl();
        return this.zztt;
    }

    final String getGmpAppId() {
        zzcl();
        return this.zzafx;
    }

    final String zzgw() {
        zzcl();
        return this.zzagk;
    }

    final int zzja() {
        zzcl();
        return this.zzalo;
    }

    final int zzjb() {
        zzcl();
        return this.zzagy;
    }

    @Override // com.google.android.gms.measurement.internal.zze, com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ void zzga() {
        super.zzga();
    }

    @Override // com.google.android.gms.measurement.internal.zze, com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ void zzgb() {
        super.zzgb();
    }

    @Override // com.google.android.gms.measurement.internal.zze, com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ void zzgc() {
        super.zzgc();
    }

    @Override // com.google.android.gms.measurement.internal.zze, com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ void zzaf() {
        super.zzaf();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zza zzgd() {
        return super.zzgd();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzcs zzge() {
        return super.zzge();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzaj zzgf() {
        return super.zzgf();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzdr zzgg() {
        return super.zzgg();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzdo zzgh() {
        return super.zzgh();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzal zzgi() {
        return super.zzgi();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzeq zzgj() {
        return super.zzgj();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ zzx zzgk() {
        return super.zzgk();
    }

    @Override // com.google.android.gms.measurement.internal.zzco, com.google.android.gms.measurement.internal.zzcq
    public final /* bridge */ /* synthetic */ Clock zzbx() {
        return super.zzbx();
    }

    @Override // com.google.android.gms.measurement.internal.zzco, com.google.android.gms.measurement.internal.zzcq
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ zzan zzgl() {
        return super.zzgl();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ zzfk zzgm() {
        return super.zzgm();
    }

    @Override // com.google.android.gms.measurement.internal.zzco, com.google.android.gms.measurement.internal.zzcq
    public final /* bridge */ /* synthetic */ zzbo zzgn() {
        return super.zzgn();
    }

    @Override // com.google.android.gms.measurement.internal.zzco, com.google.android.gms.measurement.internal.zzcq
    public final /* bridge */ /* synthetic */ zzap zzgo() {
        return super.zzgo();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ zzba zzgp() {
        return super.zzgp();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ zzn zzgq() {
        return super.zzgq();
    }

    @Override // com.google.android.gms.measurement.internal.zzco, com.google.android.gms.measurement.internal.zzcq
    public final /* bridge */ /* synthetic */ zzk zzgr() {
        return super.zzgr();
    }
}
