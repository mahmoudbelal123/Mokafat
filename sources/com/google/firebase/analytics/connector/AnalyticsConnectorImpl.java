package com.google.firebase.analytics.connector;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.internal.zzak;
import com.google.android.gms.measurement.internal.zzbt;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.analytics.connector.internal.zzc;
import com.google.firebase.analytics.connector.internal.zzd;
import com.google.firebase.analytics.connector.internal.zzf;
import com.google.firebase.events.Event;
import com.google.firebase.events.Subscriber;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class AnalyticsConnectorImpl implements AnalyticsConnector {
    private static volatile AnalyticsConnector zzbsf;

    @VisibleForTesting
    private final AppMeasurement zzbsg;

    @VisibleForTesting
    final Map<String, com.google.firebase.analytics.connector.internal.zza> zzbsh;

    private AnalyticsConnectorImpl(AppMeasurement appMeasurement) {
        Preconditions.checkNotNull(appMeasurement);
        this.zzbsg = appMeasurement;
        this.zzbsh = new ConcurrentHashMap();
    }

    @KeepForSdk
    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WAKE_LOCK"})
    public static AnalyticsConnector getInstance(FirebaseApp firebaseApp, Context context, Subscriber subscriber) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(subscriber);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzbsf == null) {
            synchronized (AnalyticsConnectorImpl.class) {
                if (zzbsf == null) {
                    Bundle bundle = new Bundle(1);
                    if (firebaseApp.isDefaultApp()) {
                        subscriber.subscribe(DataCollectionDefaultChange.class, zza.zzbsi, zzb.zzbsj);
                        bundle.putBoolean("dataCollectionDefaultEnabled", firebaseApp.isDataCollectionDefaultEnabled());
                    }
                    zzbsf = new AnalyticsConnectorImpl(zzbt.zza(context, zzak.zzc(bundle)).zzki());
                }
            }
        }
        return zzbsf;
    }

    @KeepForSdk
    public static AnalyticsConnector getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    @KeepForSdk
    public static AnalyticsConnector getInstance(FirebaseApp firebaseApp) {
        return (AnalyticsConnector) firebaseApp.get(AnalyticsConnector.class);
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    @KeepForSdk
    public void logEvent(@NonNull String str, @NonNull String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (!zzc.zzfo(str) || !zzc.zza(str2, bundle) || !zzc.zzb(str, str2, bundle)) {
            return;
        }
        this.zzbsg.logEventInternal(str, str2, bundle);
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    @KeepForSdk
    public void setUserProperty(@NonNull String str, @NonNull String str2, Object obj) {
        if (!zzc.zzfo(str) || !zzc.zzy(str, str2)) {
            return;
        }
        this.zzbsg.setUserPropertyInternal(str, str2, obj);
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    @WorkerThread
    @KeepForSdk
    public Map<String, Object> getUserProperties(boolean z) {
        return this.zzbsg.getUserProperties(z);
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    @WorkerThread
    @KeepForSdk
    public AnalyticsConnector.AnalyticsConnectorHandle registerAnalyticsConnectorListener(@NonNull final String str, AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener) {
        com.google.firebase.analytics.connector.internal.zza zzfVar;
        Preconditions.checkNotNull(analyticsConnectorListener);
        if (!zzc.zzfo(str) || zzfn(str)) {
            return null;
        }
        AppMeasurement appMeasurement = this.zzbsg;
        if (AppMeasurement.FIAM_ORIGIN.equals(str)) {
            zzfVar = new zzd(appMeasurement, analyticsConnectorListener);
        } else if (AppMeasurement.CRASH_ORIGIN.equals(str)) {
            zzfVar = new zzf(appMeasurement, analyticsConnectorListener);
        } else {
            zzfVar = null;
        }
        if (zzfVar == null) {
            return null;
        }
        this.zzbsh.put(str, zzfVar);
        return new AnalyticsConnector.AnalyticsConnectorHandle() { // from class: com.google.firebase.analytics.connector.AnalyticsConnectorImpl.1
            @Override // com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorHandle
            public void unregister() {
                if (AnalyticsConnectorImpl.this.zzfn(str)) {
                    AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListenerZztl = AnalyticsConnectorImpl.this.zzbsh.get(str).zztl();
                    if (analyticsConnectorListenerZztl != null) {
                        analyticsConnectorListenerZztl.onMessageTriggered(0, null);
                    }
                    AnalyticsConnectorImpl.this.zzbsh.remove(str);
                }
            }

            @Override // com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorHandle
            @KeepForSdk
            public void registerEventNames(Set<String> set) {
                if (!AnalyticsConnectorImpl.this.zzfn(str) || !str.equals(AppMeasurement.FIAM_ORIGIN) || set == null || set.isEmpty()) {
                    return;
                }
                AnalyticsConnectorImpl.this.zzbsh.get(str).registerEventNames(set);
            }

            @Override // com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorHandle
            @KeepForSdk
            public void unregisterEventNames() {
                if (!AnalyticsConnectorImpl.this.zzfn(str) || !str.equals(AppMeasurement.FIAM_ORIGIN)) {
                    return;
                }
                AnalyticsConnectorImpl.this.zzbsh.get(str).unregisterEventNames();
            }
        };
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    @KeepForSdk
    public void setConditionalUserProperty(@NonNull AnalyticsConnector.ConditionalUserProperty conditionalUserProperty) {
        if (!zzc.zza(conditionalUserProperty)) {
            return;
        }
        this.zzbsg.setConditionalUserProperty(zzc.zzb(conditionalUserProperty));
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    @KeepForSdk
    public void clearConditionalUserProperty(@Size(max = 24, min = 1) @NonNull String str, @Nullable String str2, @Nullable Bundle bundle) {
        if (str2 != null && !zzc.zza(str2, bundle)) {
            return;
        }
        this.zzbsg.clearConditionalUserProperty(str, str2, bundle);
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    @WorkerThread
    @KeepForSdk
    public List<AnalyticsConnector.ConditionalUserProperty> getConditionalUserProperties(@NonNull String str, @Size(max = 23, min = 1) @Nullable String str2) {
        ArrayList arrayList = new ArrayList();
        Iterator<AppMeasurement.ConditionalUserProperty> it = this.zzbsg.getConditionalUserProperties(str, str2).iterator();
        while (it.hasNext()) {
            arrayList.add(zzc.zzd(it.next()));
        }
        return arrayList;
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    @WorkerThread
    @KeepForSdk
    public int getMaxUserProperties(@Size(min = 1) @NonNull String str) {
        return this.zzbsg.getMaxUserProperties(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzfn(@NonNull String str) {
        return (str.isEmpty() || !this.zzbsh.containsKey(str) || this.zzbsh.get(str) == null) ? false : true;
    }

    static final /* synthetic */ void zza(Event event) {
        boolean z = ((DataCollectionDefaultChange) event.getPayload()).enabled;
        synchronized (AnalyticsConnectorImpl.class) {
            ((AnalyticsConnectorImpl) zzbsf).zzbsg.zzd(z);
        }
    }
}
