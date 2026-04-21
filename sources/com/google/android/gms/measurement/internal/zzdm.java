package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(14)
@MainThread
final class zzdm implements Application.ActivityLifecycleCallbacks {
    private final /* synthetic */ zzcs zzarc;

    private zzdm(zzcs zzcsVar) {
        this.zzarc = zzcsVar;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        Uri data;
        boolean z;
        String str;
        try {
            this.zzarc.zzgo().zzjl().zzbx("onActivityCreated");
            Intent intent = activity.getIntent();
            if (intent != null && (data = intent.getData()) != null && data.isHierarchical()) {
                if (bundle == null) {
                    Bundle bundleZza = this.zzarc.zzgm().zza(data);
                    this.zzarc.zzgm();
                    if (zzfk.zzd(intent)) {
                        str = "gs";
                    } else {
                        str = "auto";
                    }
                    if (bundleZza != null) {
                        this.zzarc.logEvent(str, "_cmp", bundleZza);
                    }
                }
                String queryParameter = data.getQueryParameter("referrer");
                if (TextUtils.isEmpty(queryParameter)) {
                    return;
                }
                if (!queryParameter.contains("gclid") || (!queryParameter.contains("utm_campaign") && !queryParameter.contains("utm_source") && !queryParameter.contains("utm_medium") && !queryParameter.contains("utm_term") && !queryParameter.contains("utm_content"))) {
                    z = false;
                } else {
                    z = true;
                }
                if (!z) {
                    this.zzarc.zzgo().zzjk().zzbx("Activity created with data 'referrer' param without gclid and at least one utm field");
                    return;
                } else {
                    this.zzarc.zzgo().zzjk().zzg("Activity created with referrer", queryParameter);
                    if (!TextUtils.isEmpty(queryParameter)) {
                        this.zzarc.zzb("auto", "_ldl", (Object) queryParameter, true);
                    }
                }
            }
        } catch (Exception e) {
            this.zzarc.zzgo().zzjd().zzg("Throwable caught in onActivityCreated", e);
        }
        this.zzarc.zzgh().onActivityCreated(activity, bundle);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        this.zzarc.zzgh().onActivityDestroyed(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    @MainThread
    public final void onActivityPaused(Activity activity) {
        this.zzarc.zzgh().onActivityPaused(activity);
        zzeq zzeqVarZzgj = this.zzarc.zzgj();
        zzeqVarZzgj.zzgn().zzc(new zzeu(zzeqVarZzgj, zzeqVarZzgj.zzbx().elapsedRealtime()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    @MainThread
    public final void onActivityResumed(Activity activity) {
        this.zzarc.zzgh().onActivityResumed(activity);
        zzeq zzeqVarZzgj = this.zzarc.zzgj();
        zzeqVarZzgj.zzgn().zzc(new zzet(zzeqVarZzgj, zzeqVarZzgj.zzbx().elapsedRealtime()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zzarc.zzgh().onActivitySaveInstanceState(activity, bundle);
    }

    /* synthetic */ zzdm(zzcs zzcsVar, zzct zzctVar) {
        this(zzcsVar);
    }
}
