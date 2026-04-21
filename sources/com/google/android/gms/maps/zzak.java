package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzaq;

/* JADX INFO: loaded from: classes.dex */
final class zzak extends zzaq {
    private final /* synthetic */ OnMapReadyCallback zzbb;

    zzak(SupportMapFragment.zza zzaVar, OnMapReadyCallback onMapReadyCallback) {
        this.zzbb = onMapReadyCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzap
    public final void zza(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException {
        this.zzbb.onMapReady(new GoogleMap(iGoogleMapDelegate));
    }
}
