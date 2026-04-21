package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.MainThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzep;

/* JADX INFO: loaded from: classes.dex */
public final class zzel<T extends Context & zzep> {
    private final T zzaby;

    public zzel(T t) {
        Preconditions.checkNotNull(t);
        this.zzaby = t;
    }

    @MainThread
    public final void onCreate() {
        zzbt zzbtVarZza = zzbt.zza(this.zzaby, (zzak) null);
        zzap zzapVarZzgo = zzbtVarZza.zzgo();
        zzbtVarZza.zzgr();
        zzapVarZzgo.zzjl().zzbx("Local AppMeasurementService is starting up");
    }

    @MainThread
    public final void onDestroy() {
        zzbt zzbtVarZza = zzbt.zza(this.zzaby, (zzak) null);
        zzap zzapVarZzgo = zzbtVarZza.zzgo();
        zzbtVarZza.zzgr();
        zzapVarZzgo.zzjl().zzbx("Local AppMeasurementService is shutting down");
    }

    @MainThread
    public final int onStartCommand(final Intent intent, int i, final int i2) {
        zzbt zzbtVarZza = zzbt.zza(this.zzaby, (zzak) null);
        final zzap zzapVarZzgo = zzbtVarZza.zzgo();
        if (intent == null) {
            zzapVarZzgo.zzjg().zzbx("AppMeasurementService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zzbtVarZza.zzgr();
        zzapVarZzgo.zzjl().zze("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zzb(new Runnable(this, i2, zzapVarZzgo, intent) { // from class: com.google.android.gms.measurement.internal.zzem
                private final int zzacb;
                private final zzel zzasr;
                private final zzap zzass;
                private final Intent zzast;

                {
                    this.zzasr = this;
                    this.zzacb = i2;
                    this.zzass = zzapVarZzgo;
                    this.zzast = intent;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzasr.zza(this.zzacb, this.zzass, this.zzast);
                }
            });
        }
        return 2;
    }

    private final void zzb(Runnable runnable) {
        zzfa zzfaVarZzm = zzfa.zzm(this.zzaby);
        zzfaVarZzm.zzgn().zzc(new zzeo(this, zzfaVarZzm, runnable));
    }

    @MainThread
    public final IBinder onBind(Intent intent) {
        if (intent == null) {
            zzgo().zzjd().zzbx("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzbv(zzfa.zzm(this.zzaby));
        }
        zzgo().zzjg().zzg("onBind received unknown action", action);
        return null;
    }

    @MainThread
    public final boolean onUnbind(Intent intent) {
        if (intent == null) {
            zzgo().zzjd().zzbx("onUnbind called with null intent");
            return true;
        }
        zzgo().zzjl().zzg("onUnbind called for intent. action", intent.getAction());
        return true;
    }

    @TargetApi(24)
    @MainThread
    public final boolean onStartJob(final JobParameters jobParameters) {
        zzbt zzbtVarZza = zzbt.zza(this.zzaby, (zzak) null);
        final zzap zzapVarZzgo = zzbtVarZza.zzgo();
        String string = jobParameters.getExtras().getString("action");
        zzbtVarZza.zzgr();
        zzapVarZzgo.zzjl().zzg("Local AppMeasurementJobService called. action", string);
        if ("com.google.android.gms.measurement.UPLOAD".equals(string)) {
            zzb(new Runnable(this, zzapVarZzgo, jobParameters) { // from class: com.google.android.gms.measurement.internal.zzen
                private final JobParameters zzace;
                private final zzel zzasr;
                private final zzap zzasu;

                {
                    this.zzasr = this;
                    this.zzasu = zzapVarZzgo;
                    this.zzace = jobParameters;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzasr.zza(this.zzasu, this.zzace);
                }
            });
            return true;
        }
        return true;
    }

    @MainThread
    public final void onRebind(Intent intent) {
        if (intent == null) {
            zzgo().zzjd().zzbx("onRebind called with null intent");
        } else {
            zzgo().zzjl().zzg("onRebind called. action", intent.getAction());
        }
    }

    private final zzap zzgo() {
        return zzbt.zza(this.zzaby, (zzak) null).zzgo();
    }

    final /* synthetic */ void zza(zzap zzapVar, JobParameters jobParameters) {
        zzapVar.zzjl().zzbx("AppMeasurementJobService processed last upload request.");
        this.zzaby.zza(jobParameters, false);
    }

    final /* synthetic */ void zza(int i, zzap zzapVar, Intent intent) {
        if (this.zzaby.callServiceStopSelfResult(i)) {
            zzapVar.zzjl().zzg("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i));
            zzgo().zzjl().zzbx("Completed wakeful intent.");
            this.zzaby.zzb(intent);
        }
    }
}
