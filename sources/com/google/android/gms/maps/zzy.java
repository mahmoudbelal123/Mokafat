package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
final class zzy extends com.google.android.gms.maps.internal.zzak {
    private final /* synthetic */ GoogleMap.OnMapClickListener zzag;

    zzy(GoogleMap googleMap, GoogleMap.OnMapClickListener onMapClickListener) {
        this.zzag = onMapClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzaj
    public final void onMapClick(LatLng latLng) {
        this.zzag.onMapClick(latLng);
    }
}
