package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: classes.dex */
final class zzah extends zzai {
    private final /* synthetic */ com.google.android.gms.location.zzal zzct;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzah(zzaf zzafVar, GoogleApiClient googleApiClient, com.google.android.gms.location.zzal zzalVar) {
        super(googleApiClient);
        this.zzct = zzalVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzaz) anyClient).zza(this.zzct, this);
    }
}
