package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.internal.location.zzbk;

/* JADX INFO: loaded from: classes.dex */
public class LocationServices {
    private static final Api.ClientKey<zzaz> CLIENT_KEY = new Api.ClientKey<>();
    private static final Api.AbstractClientBuilder<zzaz, Api.ApiOptions.NoOptions> CLIENT_BUILDER = new zzad();
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>("LocationServices.API", CLIENT_BUILDER, CLIENT_KEY);

    @Deprecated
    public static final FusedLocationProviderApi FusedLocationApi = new com.google.android.gms.internal.location.zzq();

    @Deprecated
    public static final GeofencingApi GeofencingApi = new com.google.android.gms.internal.location.zzaf();

    @Deprecated
    public static final SettingsApi SettingsApi = new zzbk();

    public static abstract class zza<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzaz> {
        public zza(GoogleApiClient googleApiClient) {
            super(LocationServices.API, googleApiClient);
        }
    }

    private LocationServices() {
    }

    public static FusedLocationProviderClient getFusedLocationProviderClient(@NonNull Activity activity) {
        return new FusedLocationProviderClient(activity);
    }

    public static FusedLocationProviderClient getFusedLocationProviderClient(@NonNull Context context) {
        return new FusedLocationProviderClient(context);
    }

    public static GeofencingClient getGeofencingClient(@NonNull Activity activity) {
        return new GeofencingClient(activity);
    }

    public static GeofencingClient getGeofencingClient(@NonNull Context context) {
        return new GeofencingClient(context);
    }

    public static SettingsClient getSettingsClient(@NonNull Activity activity) {
        return new SettingsClient(activity);
    }

    public static SettingsClient getSettingsClient(@NonNull Context context) {
        return new SettingsClient(context);
    }

    public static zzaz zza(GoogleApiClient googleApiClient) {
        Preconditions.checkArgument(googleApiClient != null, "GoogleApiClient parameter is required.");
        zzaz zzazVar = (zzaz) googleApiClient.getClient(CLIENT_KEY);
        Preconditions.checkState(zzazVar != null, "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        return zzazVar;
    }
}
