package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationListener;

/* JADX INFO: loaded from: classes.dex */
final class zzz extends zzab {
    private final /* synthetic */ LocationListener zzcl;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzz(zzq zzqVar, GoogleApiClient googleApiClient, LocationListener locationListener) {
        super(googleApiClient);
        this.zzcl = locationListener;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzaz) anyClient).zza(ListenerHolders.createListenerKey(this.zzcl, LocationListener.class.getSimpleName()), new zzac(this));
    }
}
