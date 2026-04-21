package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zzbj {
    private final zzbm zzaoi;

    public zzbj(zzbm zzbmVar) {
        Preconditions.checkNotNull(zzbmVar);
        this.zzaoi = zzbmVar;
    }

    public static boolean zza(Context context) {
        ActivityInfo receiverInfo;
        Preconditions.checkNotNull(context);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && (receiverInfo = packageManager.getReceiverInfo(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementReceiver"), 0)) != null) {
                if (receiverInfo.enabled) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }

    @MainThread
    public final void onReceive(Context context, Intent intent) {
        zzbt zzbtVarZza = zzbt.zza(context, (zzak) null);
        zzap zzapVarZzgo = zzbtVarZza.zzgo();
        if (intent == null) {
            zzapVarZzgo.zzjg().zzbx("Receiver called with null intent");
            return;
        }
        zzbtVarZza.zzgr();
        String action = intent.getAction();
        zzapVarZzgo.zzjl().zzg("Local receiver got", action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            Intent className = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
            className.setAction("com.google.android.gms.measurement.UPLOAD");
            zzapVarZzgo.zzjl().zzbx("Starting wakeful intent.");
            this.zzaoi.doStartService(context, className);
            return;
        }
        if ("com.android.vending.INSTALL_REFERRER".equals(action)) {
            try {
                zzbtVarZza.zzgn().zzc(new zzbk(this, zzbtVarZza, zzapVarZzgo));
            } catch (Exception e) {
                zzapVarZzgo.zzjg().zzg("Install Referrer Reporter encountered a problem", e);
            }
            BroadcastReceiver.PendingResult pendingResultDoGoAsync = this.zzaoi.doGoAsync();
            String stringExtra = intent.getStringExtra("referrer");
            if (stringExtra == null) {
                zzapVarZzgo.zzjl().zzbx("Install referrer extras are null");
                if (pendingResultDoGoAsync != null) {
                    pendingResultDoGoAsync.finish();
                    return;
                }
                return;
            }
            zzapVarZzgo.zzjj().zzg("Install referrer extras are", stringExtra);
            if (!stringExtra.contains("?")) {
                String strValueOf = String.valueOf(stringExtra);
                stringExtra = strValueOf.length() != 0 ? "?".concat(strValueOf) : new String("?");
            }
            Bundle bundleZza = zzbtVarZza.zzgm().zza(Uri.parse(stringExtra));
            if (bundleZza == null) {
                zzapVarZzgo.zzjl().zzbx("No campaign defined in install referrer broadcast");
                if (pendingResultDoGoAsync != null) {
                    pendingResultDoGoAsync.finish();
                    return;
                }
                return;
            }
            long longExtra = intent.getLongExtra("referrer_timestamp_seconds", 0L) * 1000;
            if (longExtra == 0) {
                zzapVarZzgo.zzjg().zzbx("Install referrer is missing timestamp");
            }
            zzbtVarZza.zzgn().zzc(new zzbl(this, zzbtVarZza, longExtra, bundleZza, context, zzapVarZzgo, pendingResultDoGoAsync));
        }
    }
}
