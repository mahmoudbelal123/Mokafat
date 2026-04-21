package com.google.android.gms.common.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.SparseIntArray;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;

/* JADX INFO: loaded from: classes.dex */
public class GoogleApiAvailabilityCache {
    private final SparseIntArray zzug;
    private GoogleApiAvailabilityLight zzuh;

    public GoogleApiAvailabilityCache() {
        this(GoogleApiAvailability.getInstance());
    }

    public GoogleApiAvailabilityCache(@NonNull GoogleApiAvailabilityLight googleApiAvailabilityLight) {
        this.zzug = new SparseIntArray();
        Preconditions.checkNotNull(googleApiAvailabilityLight);
        this.zzuh = googleApiAvailabilityLight;
    }

    public void flush() {
        this.zzug.clear();
    }

    public int getClientAvailability(@NonNull Context context, @NonNull Api.Client client) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(client);
        if (!client.requiresGooglePlayServices()) {
            return 0;
        }
        int minApkVersion = client.getMinApkVersion();
        int iIsGooglePlayServicesAvailable = this.zzug.get(minApkVersion, -1);
        if (iIsGooglePlayServicesAvailable != -1) {
            return iIsGooglePlayServicesAvailable;
        }
        int i = 0;
        while (true) {
            if (i < this.zzug.size()) {
                int iKeyAt = this.zzug.keyAt(i);
                if (iKeyAt > minApkVersion && this.zzug.get(iKeyAt) == 0) {
                    iIsGooglePlayServicesAvailable = 0;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        if (iIsGooglePlayServicesAvailable == -1) {
            iIsGooglePlayServicesAvailable = this.zzuh.isGooglePlayServicesAvailable(context, minApkVersion);
        }
        this.zzug.put(minApkVersion, iIsGooglePlayServicesAvailable);
        return iIsGooglePlayServicesAvailable;
    }
}
