package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: classes.dex */
final class zzas implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private final /* synthetic */ zzaj zzhv;

    private zzas(zzaj zzajVar) {
        this.zzhv = zzajVar;
    }

    /* synthetic */ zzas(zzaj zzajVar, zzak zzakVar) {
        this(zzajVar);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        this.zzhv.zzhn.signIn(new zzaq(this.zzhv));
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zzhv.zzga.lock();
        try {
            if (this.zzhv.zzd(connectionResult)) {
                this.zzhv.zzau();
                this.zzhv.zzas();
            } else {
                this.zzhv.zze(connectionResult);
            }
        } finally {
            this.zzhv.zzga.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
    }
}
