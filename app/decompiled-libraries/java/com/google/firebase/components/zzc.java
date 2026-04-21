package com.google.firebase.components;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-common@@16.0.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzc<T> {
    private final T zza;
    private final zzb<T> zzb;

    /* JADX INFO: compiled from: com.google.firebase:firebase-common@@16.0.1 */
    @VisibleForTesting
    interface zzb<T> {
        List<String> zza(T t);
    }

    public static zzc<Context> zza(Context context) {
        return new zzc<>(context, new zza((byte) 0));
    }

    @VisibleForTesting
    private zzc(T t, zzb<T> zzbVar) {
        this.zza = t;
        this.zzb = zzbVar;
    }

    public final List<ComponentRegistrar> zza() {
        return zza(this.zzb.zza(this.zza));
    }

    private static List<ComponentRegistrar> zza(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            try {
                Class<?> cls = Class.forName(str);
                if (!ComponentRegistrar.class.isAssignableFrom(cls)) {
                    Log.w("ComponentDiscovery", String.format("Class %s is not an instance of %s", str, "com.google.firebase.components.ComponentRegistrar"));
                } else {
                    arrayList.add((ComponentRegistrar) cls.newInstance());
                }
            } catch (ClassNotFoundException e) {
                Log.w("ComponentDiscovery", String.format("Class %s is not an found.", str), e);
            } catch (IllegalAccessException e2) {
                Log.w("ComponentDiscovery", String.format("Could not instantiate %s.", str), e2);
            } catch (InstantiationException e3) {
                Log.w("ComponentDiscovery", String.format("Could not instantiate %s.", str), e3);
            }
        }
        return arrayList;
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-common@@16.0.1 */
    static class zza implements zzb<Context> {
        private zza() {
        }

        /* synthetic */ zza(byte b) {
            this();
        }

        @Override // com.google.firebase.components.zzc.zzb
        public final /* synthetic */ List zza(Context context) {
            Bundle bundleZza2 = zza2(context);
            if (bundleZza2 == null) {
                Log.w("ComponentDiscovery", "Could not retrieve metadata, returning empty list of registrars.");
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            for (String str : bundleZza2.keySet()) {
                if ("com.google.firebase.components.ComponentRegistrar".equals(bundleZza2.get(str)) && str.startsWith("com.google.firebase.components:")) {
                    arrayList.add(str.substring(31));
                }
            }
            return arrayList;
        }

        /* JADX INFO: renamed from: zza, reason: avoid collision after fix types in other method */
        private static Bundle zza2(Context context) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager == null) {
                    Log.w("ComponentDiscovery", "Context has no PackageManager.");
                    return null;
                }
                ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, (Class<?>) ComponentDiscoveryService.class), 128);
                if (serviceInfo == null) {
                    Log.w("ComponentDiscovery", "ComponentDiscoveryService has no service info.");
                    return null;
                }
                return serviceInfo.metaData;
            } catch (PackageManager.NameNotFoundException e) {
                Log.w("ComponentDiscovery", "Application info not found.");
                return null;
            }
        }
    }
}
