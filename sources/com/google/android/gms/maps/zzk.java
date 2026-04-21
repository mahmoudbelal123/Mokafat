package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzam;

/* JADX INFO: loaded from: classes.dex */
final class zzk extends zzam {
    private final /* synthetic */ GoogleMap.OnMapLoadedCallback zzs;

    zzk(GoogleMap googleMap, GoogleMap.OnMapLoadedCallback onMapLoadedCallback) {
        this.zzs = onMapLoadedCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzal
    public final void onMapLoaded() throws RemoteException {
        this.zzs.onMapLoaded();
    }
}
