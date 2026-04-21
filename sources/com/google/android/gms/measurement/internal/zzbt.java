package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzsl;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public class zzbt implements zzcq {
    private static volatile zzbt zzapl;
    private final boolean zzadv;
    private final String zzadx;
    private final long zzagx;
    private final zzk zzaiq;
    private final String zzapm;
    private final String zzapn;
    private final zzn zzapo;
    private final zzba zzapp;
    private final zzap zzapq;
    private final zzbo zzapr;
    private final zzeq zzaps;
    private final AppMeasurement zzapt;
    private final zzfk zzapu;
    private final zzan zzapv;
    private final zzdo zzapw;
    private final zzcs zzapx;
    private final zza zzapy;
    private zzal zzapz;
    private zzdr zzaqa;
    private zzx zzaqb;
    private zzaj zzaqc;
    private zzbg zzaqd;
    private Boolean zzaqe;
    private long zzaqf;
    private volatile Boolean zzaqg;
    private int zzaqh;
    private int zzaqi;
    private final Context zzri;
    private final Clock zzrz;
    private boolean zzvz = false;

    private zzbt(zzcr zzcrVar) {
        Preconditions.checkNotNull(zzcrVar);
        this.zzaiq = new zzk(zzcrVar.zzri);
        zzaf.zza(this.zzaiq);
        this.zzri = zzcrVar.zzri;
        this.zzadx = zzcrVar.zzadx;
        this.zzapm = zzcrVar.zzapm;
        this.zzapn = zzcrVar.zzapn;
        this.zzadv = zzcrVar.zzadv;
        this.zzaqg = zzcrVar.zzaqg;
        zzsl.init(this.zzri);
        this.zzrz = DefaultClock.getInstance();
        this.zzagx = this.zzrz.currentTimeMillis();
        this.zzapo = new zzn(this);
        zzba zzbaVar = new zzba(this);
        zzbaVar.zzq();
        this.zzapp = zzbaVar;
        zzap zzapVar = new zzap(this);
        zzapVar.zzq();
        this.zzapq = zzapVar;
        zzfk zzfkVar = new zzfk(this);
        zzfkVar.zzq();
        this.zzapu = zzfkVar;
        zzan zzanVar = new zzan(this);
        zzanVar.zzq();
        this.zzapv = zzanVar;
        this.zzapy = new zza(this);
        zzdo zzdoVar = new zzdo(this);
        zzdoVar.zzq();
        this.zzapw = zzdoVar;
        zzcs zzcsVar = new zzcs(this);
        zzcsVar.zzq();
        this.zzapx = zzcsVar;
        this.zzapt = new AppMeasurement(this);
        zzeq zzeqVar = new zzeq(this);
        zzeqVar.zzq();
        this.zzaps = zzeqVar;
        zzbo zzboVar = new zzbo(this);
        zzboVar.zzq();
        this.zzapr = zzboVar;
        zzk zzkVar = this.zzaiq;
        if (this.zzri.getApplicationContext() instanceof Application) {
            zzcs zzcsVarZzge = zzge();
            if (zzcsVarZzge.getContext().getApplicationContext() instanceof Application) {
                Application application = (Application) zzcsVarZzge.getContext().getApplicationContext();
                if (zzcsVarZzge.zzaqv == null) {
                    zzcsVarZzge.zzaqv = new zzdm(zzcsVarZzge, null);
                }
                application.unregisterActivityLifecycleCallbacks(zzcsVarZzge.zzaqv);
                application.registerActivityLifecycleCallbacks(zzcsVarZzge.zzaqv);
                zzcsVarZzge.zzgo().zzjl().zzbx("Registered activity lifecycle callback");
            }
        } else {
            zzgo().zzjg().zzbx("Application context is not an Application");
        }
        this.zzapr.zzc(new zzbu(this, zzcrVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zza(zzcr zzcrVar) {
        zzar zzarVarZzjj;
        String strConcat;
        zzgn().zzaf();
        zzn.zzht();
        zzx zzxVar = new zzx(this);
        zzxVar.zzq();
        this.zzaqb = zzxVar;
        zzaj zzajVar = new zzaj(this);
        zzajVar.zzq();
        this.zzaqc = zzajVar;
        zzal zzalVar = new zzal(this);
        zzalVar.zzq();
        this.zzapz = zzalVar;
        zzdr zzdrVar = new zzdr(this);
        zzdrVar.zzq();
        this.zzaqa = zzdrVar;
        this.zzapu.zzgs();
        this.zzapp.zzgs();
        this.zzaqd = new zzbg(this);
        this.zzaqc.zzgs();
        zzgo().zzjj().zzg("App measurement is starting up, version", Long.valueOf(this.zzapo.zzhc()));
        zzk zzkVar = this.zzaiq;
        zzgo().zzjj().zzbx("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        zzk zzkVar2 = this.zzaiq;
        String strZzal = zzajVar.zzal();
        if (TextUtils.isEmpty(this.zzadx)) {
            if (zzgm().zzcw(strZzal)) {
                zzarVarZzjj = zzgo().zzjj();
                strConcat = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
            } else {
                zzarVarZzjj = zzgo().zzjj();
                String strValueOf = String.valueOf(strZzal);
                strConcat = strValueOf.length() != 0 ? "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(strValueOf) : new String("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ");
            }
            zzarVarZzjj.zzbx(strConcat);
        }
        zzgo().zzjk().zzbx("Debug-level message logging enabled");
        if (this.zzaqh != this.zzaqi) {
            zzgo().zzjd().zze("Not all components initialized", Integer.valueOf(this.zzaqh), Integer.valueOf(this.zzaqi));
        }
        this.zzvz = true;
    }

    @WorkerThread
    protected final void start() {
        zzgn().zzaf();
        if (zzgp().zzane.get() == 0) {
            zzgp().zzane.set(this.zzrz.currentTimeMillis());
        }
        if (Long.valueOf(zzgp().zzanj.get()).longValue() == 0) {
            zzgo().zzjl().zzg("Persisting first open", Long.valueOf(this.zzagx));
            zzgp().zzanj.set(this.zzagx);
        }
        if (!zzkr()) {
            if (isEnabled()) {
                if (!zzgm().zzx("android.permission.INTERNET")) {
                    zzgo().zzjd().zzbx("App is missing INTERNET permission");
                }
                if (!zzgm().zzx("android.permission.ACCESS_NETWORK_STATE")) {
                    zzgo().zzjd().zzbx("App is missing ACCESS_NETWORK_STATE permission");
                }
                zzk zzkVar = this.zzaiq;
                if (!Wrappers.packageManager(this.zzri).isCallerInstantApp() && !this.zzapo.zzib()) {
                    if (!zzbj.zza(this.zzri)) {
                        zzgo().zzjd().zzbx("AppMeasurementReceiver not registered/enabled");
                    }
                    if (!zzfk.zza(this.zzri, false)) {
                        zzgo().zzjd().zzbx("AppMeasurementService not registered/enabled");
                    }
                }
                zzgo().zzjd().zzbx("Uploading is not possible. App measurement disabled");
                return;
            }
            return;
        }
        zzk zzkVar2 = this.zzaiq;
        if (!TextUtils.isEmpty(zzgf().getGmpAppId()) || !TextUtils.isEmpty(zzgf().zzgw())) {
            zzgm();
            if (zzfk.zza(zzgf().getGmpAppId(), zzgp().zzjs(), zzgf().zzgw(), zzgp().zzjt())) {
                zzgo().zzjj().zzbx("Rechecking which service to use due to a GMP App Id change");
                zzgp().zzjv();
                if (this.zzapo.zza(zzaf.zzalc)) {
                    zzgi().resetAnalyticsData();
                }
                this.zzaqa.disconnect();
                this.zzaqa.zzdj();
                zzgp().zzanj.set(this.zzagx);
                zzgp().zzanl.zzcc(null);
            }
            zzgp().zzca(zzgf().getGmpAppId());
            zzgp().zzcb(zzgf().zzgw());
            if (this.zzapo.zzbj(zzgf().zzal())) {
                this.zzaps.zzam(this.zzagx);
            }
        }
        zzge().zzcm(zzgp().zzanl.zzjz());
        zzk zzkVar3 = this.zzaiq;
        if (!TextUtils.isEmpty(zzgf().getGmpAppId()) || !TextUtils.isEmpty(zzgf().zzgw())) {
            boolean zIsEnabled = isEnabled();
            if (!zzgp().zzjy() && !this.zzapo.zzhu()) {
                zzgp().zzi(!zIsEnabled);
            }
            if (this.zzapo.zze(zzgf().zzal(), zzaf.zzalj)) {
                zzj(false);
            }
            if (!this.zzapo.zzbd(zzgf().zzal()) || zIsEnabled) {
                zzge().zzkz();
            }
            zzgg().zza(new AtomicReference<>());
        }
    }

    final void zzj(boolean z) {
        boolean z2;
        zzgn().zzaf();
        String strZzjz = zzgp().zzans.zzjz();
        if (!z && strZzjz != null) {
            if ("unset".equals(strZzjz)) {
                zzge().zza("app", "_ap", (Object) null, this.zzrz.currentTimeMillis());
                z2 = true;
            } else {
                zzge().zza("app", "_ap", strZzjz, this.zzrz.currentTimeMillis());
                z2 = false;
            }
        } else {
            z2 = true;
        }
        if (z2) {
            Boolean boolZzau = this.zzapo.zzau("google_analytics_default_allow_ad_personalization_signals");
            if (boolZzau != null) {
                zzge().zza("auto", "_ap", Long.valueOf(boolZzau.booleanValue() ? 1L : 0L), this.zzrz.currentTimeMillis());
            } else {
                zzge().zza("auto", "_ap", (Object) null, this.zzrz.currentTimeMillis());
            }
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final zzk zzgr() {
        return this.zzaiq;
    }

    public final zzn zzgq() {
        return this.zzapo;
    }

    public final zzba zzgp() {
        zza((zzco) this.zzapp);
        return this.zzapp;
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final zzap zzgo() {
        zza((zzcp) this.zzapq);
        return this.zzapq;
    }

    public final zzap zzkf() {
        if (this.zzapq == null || !this.zzapq.isInitialized()) {
            return null;
        }
        return this.zzapq;
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final zzbo zzgn() {
        zza((zzcp) this.zzapr);
        return this.zzapr;
    }

    public final zzeq zzgj() {
        zza((zzf) this.zzaps);
        return this.zzaps;
    }

    public final zzbg zzkg() {
        return this.zzaqd;
    }

    final zzbo zzkh() {
        return this.zzapr;
    }

    public final zzcs zzge() {
        zza((zzf) this.zzapx);
        return this.zzapx;
    }

    public final AppMeasurement zzki() {
        return this.zzapt;
    }

    public final zzfk zzgm() {
        zza((zzco) this.zzapu);
        return this.zzapu;
    }

    public final zzan zzgl() {
        zza((zzco) this.zzapv);
        return this.zzapv;
    }

    public final zzal zzgi() {
        zza((zzf) this.zzapz);
        return this.zzapz;
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final Context getContext() {
        return this.zzri;
    }

    public final boolean zzkj() {
        return TextUtils.isEmpty(this.zzadx);
    }

    public final String zzkk() {
        return this.zzadx;
    }

    public final String zzkl() {
        return this.zzapm;
    }

    public final String zzkm() {
        return this.zzapn;
    }

    public final boolean zzkn() {
        return this.zzadv;
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final Clock zzbx() {
        return this.zzrz;
    }

    public final zzdo zzgh() {
        zza((zzf) this.zzapw);
        return this.zzapw;
    }

    public final zzdr zzgg() {
        zza((zzf) this.zzaqa);
        return this.zzaqa;
    }

    public final zzx zzgk() {
        zza((zzcp) this.zzaqb);
        return this.zzaqb;
    }

    public final zzaj zzgf() {
        zza((zzf) this.zzaqc);
        return this.zzaqc;
    }

    public final zza zzgd() {
        if (this.zzapy == null) {
            throw new IllegalStateException("Component not created");
        }
        return this.zzapy;
    }

    public static zzbt zza(Context context, zzak zzakVar) {
        if (zzakVar != null && (zzakVar.origin == null || zzakVar.zzadx == null)) {
            zzakVar = new zzak(zzakVar.zzadt, zzakVar.zzadu, zzakVar.zzadv, zzakVar.zzadw, null, null, zzakVar.zzady);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzapl == null) {
            synchronized (zzbt.class) {
                if (zzapl == null) {
                    zzapl = new zzbt(new zzcr(context, zzakVar));
                }
            }
        } else if (zzakVar != null && zzakVar.zzady != null && zzakVar.zzady.containsKey("dataCollectionDefaultEnabled")) {
            zzapl.zzd(zzakVar.zzady.getBoolean("dataCollectionDefaultEnabled"));
        }
        return zzapl;
    }

    private final void zzcl() {
        if (!this.zzvz) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }

    private static void zza(zzcp zzcpVar) {
        if (zzcpVar == null) {
            throw new IllegalStateException("Component not created");
        }
        if (!zzcpVar.isInitialized()) {
            String strValueOf = String.valueOf(zzcpVar.getClass());
            StringBuilder sb = new StringBuilder(27 + String.valueOf(strValueOf).length());
            sb.append("Component not initialized: ");
            sb.append(strValueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    private static void zza(zzf zzfVar) {
        if (zzfVar == null) {
            throw new IllegalStateException("Component not created");
        }
        if (!zzfVar.isInitialized()) {
            String strValueOf = String.valueOf(zzfVar.getClass());
            StringBuilder sb = new StringBuilder(27 + String.valueOf(strValueOf).length());
            sb.append("Component not initialized: ");
            sb.append(strValueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    private static void zza(zzco zzcoVar) {
        if (zzcoVar == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    @WorkerThread
    final void zzd(boolean z) {
        this.zzaqg = Boolean.valueOf(z);
    }

    @WorkerThread
    public final boolean zzko() {
        return this.zzaqg != null && this.zzaqg.booleanValue();
    }

    @WorkerThread
    public final boolean isEnabled() {
        boolean zBooleanValue;
        zzgn().zzaf();
        zzcl();
        if (this.zzapo.zzhu()) {
            return false;
        }
        Boolean boolZzhv = this.zzapo.zzhv();
        if (boolZzhv != null) {
            zBooleanValue = boolZzhv.booleanValue();
        } else {
            zBooleanValue = !GoogleServices.isMeasurementExplicitlyDisabled();
            if (zBooleanValue && this.zzaqg != null && zzaf.zzalh.get().booleanValue()) {
                zBooleanValue = this.zzaqg.booleanValue();
            }
        }
        return zzgp().zzh(zBooleanValue);
    }

    final long zzkp() {
        Long lValueOf = Long.valueOf(zzgp().zzanj.get());
        if (lValueOf.longValue() == 0) {
            return this.zzagx;
        }
        return Math.min(this.zzagx, lValueOf.longValue());
    }

    final void zzgb() {
        zzk zzkVar = this.zzaiq;
    }

    final void zzga() {
        zzk zzkVar = this.zzaiq;
        throw new IllegalStateException("Unexpected call on client side");
    }

    final void zzb(zzcp zzcpVar) {
        this.zzaqh++;
    }

    final void zzb(zzf zzfVar) {
        this.zzaqh++;
    }

    final void zzkq() {
        this.zzaqi++;
    }

    @WorkerThread
    protected final boolean zzkr() {
        zzcl();
        zzgn().zzaf();
        if (this.zzaqe == null || this.zzaqf == 0 || (this.zzaqe != null && !this.zzaqe.booleanValue() && Math.abs(this.zzrz.elapsedRealtime() - this.zzaqf) > 1000)) {
            this.zzaqf = this.zzrz.elapsedRealtime();
            zzk zzkVar = this.zzaiq;
            boolean z = true;
            this.zzaqe = Boolean.valueOf(zzgm().zzx("android.permission.INTERNET") && zzgm().zzx("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zzri).isCallerInstantApp() || this.zzapo.zzib() || (zzbj.zza(this.zzri) && zzfk.zza(this.zzri, false))));
            if (this.zzaqe.booleanValue()) {
                if (!zzgm().zzt(zzgf().getGmpAppId(), zzgf().zzgw()) && TextUtils.isEmpty(zzgf().zzgw())) {
                    z = false;
                }
                this.zzaqe = Boolean.valueOf(z);
            }
        }
        return this.zzaqe.booleanValue();
    }
}
