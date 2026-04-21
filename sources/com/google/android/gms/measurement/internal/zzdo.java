package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zzdo extends zzf {

    @VisibleForTesting
    protected zzdn zzaro;
    private volatile zzdn zzarp;
    private zzdn zzarq;
    private final Map<Activity, zzdn> zzarr;
    private zzdn zzars;
    private String zzart;

    public zzdo(zzbt zzbtVar) {
        super(zzbtVar);
        this.zzarr = new ArrayMap();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzgt() {
        return false;
    }

    @WorkerThread
    public final zzdn zzla() {
        zzcl();
        zzaf();
        return this.zzaro;
    }

    public final void setCurrentScreen(@NonNull Activity activity, @Size(max = 36, min = 1) @Nullable String str, @Size(max = 36, min = 1) @Nullable String str2) {
        if (this.zzarp == null) {
            zzgo().zzjg().zzbx("setCurrentScreen cannot be called while no activity active");
            return;
        }
        if (this.zzarr.get(activity) == null) {
            zzgo().zzjg().zzbx("setCurrentScreen must be called with an activity in the activity lifecycle");
            return;
        }
        if (str2 == null) {
            str2 = zzcn(activity.getClass().getCanonicalName());
        }
        boolean zEquals = this.zzarp.zzarl.equals(str2);
        boolean zZzu = zzfk.zzu(this.zzarp.zzuw, str);
        if (zEquals && zZzu) {
            zzgo().zzji().zzbx("setCurrentScreen cannot be called with the same class and name");
            return;
        }
        if (str != null && (str.length() <= 0 || str.length() > 100)) {
            zzgo().zzjg().zzg("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            return;
        }
        if (str2 != null && (str2.length() <= 0 || str2.length() > 100)) {
            zzgo().zzjg().zzg("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            return;
        }
        zzgo().zzjl().zze("Setting current screen to name, class", str == null ? "null" : str, str2);
        zzdn zzdnVar = new zzdn(str, str2, zzgm().zzmc());
        this.zzarr.put(activity, zzdnVar);
        zza(activity, zzdnVar, true);
    }

    public final zzdn zzlb() {
        zzgb();
        return this.zzarp;
    }

    @MainThread
    private final void zza(Activity activity, zzdn zzdnVar, boolean z) {
        zzdn zzdnVar2 = this.zzarp == null ? this.zzarq : this.zzarp;
        if (zzdnVar.zzarl == null) {
            zzdnVar = new zzdn(zzdnVar.zzuw, zzcn(activity.getClass().getCanonicalName()), zzdnVar.zzarm);
        }
        this.zzarq = this.zzarp;
        this.zzarp = zzdnVar;
        zzgn().zzc(new zzdp(this, z, zzdnVar2, zzdnVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zza(@NonNull zzdn zzdnVar) {
        zzgd().zzq(zzbx().elapsedRealtime());
        if (zzgj().zzn(zzdnVar.zzarn)) {
            zzdnVar.zzarn = false;
        }
    }

    public static void zza(zzdn zzdnVar, Bundle bundle, boolean z) {
        if (bundle != null && zzdnVar != null && (!bundle.containsKey("_sc") || z)) {
            if (zzdnVar.zzuw != null) {
                bundle.putString("_sn", zzdnVar.zzuw);
            } else {
                bundle.remove("_sn");
            }
            bundle.putString("_sc", zzdnVar.zzarl);
            bundle.putLong("_si", zzdnVar.zzarm);
            return;
        }
        if (bundle != null && zzdnVar == null && z) {
            bundle.remove("_sn");
            bundle.remove("_sc");
            bundle.remove("_si");
        }
    }

    @WorkerThread
    public final void zza(String str, zzdn zzdnVar) {
        zzaf();
        synchronized (this) {
            if (this.zzart == null || this.zzart.equals(str) || zzdnVar != null) {
                this.zzart = str;
                this.zzars = zzdnVar;
            }
        }
    }

    @VisibleForTesting
    private static String zzcn(String str) {
        String str2;
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length > 0) {
            str2 = strArrSplit[strArrSplit.length - 1];
        } else {
            str2 = "";
        }
        if (str2.length() > 100) {
            return str2.substring(0, 100);
        }
        return str2;
    }

    @MainThread
    private final zzdn zze(@NonNull Activity activity) {
        Preconditions.checkNotNull(activity);
        zzdn zzdnVar = this.zzarr.get(activity);
        if (zzdnVar != null) {
            return zzdnVar;
        }
        zzdn zzdnVar2 = new zzdn(null, zzcn(activity.getClass().getCanonicalName()), zzgm().zzmc());
        this.zzarr.put(activity, zzdnVar2);
        return zzdnVar2;
    }

    @MainThread
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        Bundle bundle2;
        if (bundle == null || (bundle2 = bundle.getBundle("com.google.app_measurement.screen_service")) == null) {
            return;
        }
        this.zzarr.put(activity, new zzdn(bundle2.getString("name"), bundle2.getString("referrer_name"), bundle2.getLong("id")));
    }

    @MainThread
    public final void onActivityResumed(Activity activity) {
        zza(activity, zze(activity), false);
        zza zzaVarZzgd = zzgd();
        zzaVarZzgd.zzgn().zzc(new zzd(zzaVarZzgd, zzaVarZzgd.zzbx().elapsedRealtime()));
    }

    @MainThread
    public final void onActivityPaused(Activity activity) {
        zzdn zzdnVarZze = zze(activity);
        this.zzarq = this.zzarp;
        this.zzarp = null;
        zzgn().zzc(new zzdq(this, zzdnVarZze));
    }

    @MainThread
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzdn zzdnVar;
        if (bundle == null || (zzdnVar = this.zzarr.get(activity)) == null) {
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putLong("id", zzdnVar.zzarm);
        bundle2.putString("name", zzdnVar.zzuw);
        bundle2.putString("referrer_name", zzdnVar.zzarl);
        bundle.putBundle("com.google.app_measurement.screen_service", bundle2);
    }

    @MainThread
    public final void onActivityDestroyed(Activity activity) {
        this.zzarr.remove(activity);
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
