package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.ILocationSourceDelegate;

/* JADX INFO: loaded from: classes.dex */
final class zzl extends ILocationSourceDelegate.zza {
    private final /* synthetic */ LocationSource zzt;

    zzl(GoogleMap googleMap, LocationSource locationSource) {
        this.zzt = locationSource;
    }

    @Override // com.google.android.gms.maps.internal.ILocationSourceDelegate
    public final void activate(com.google.android.gms.maps.internal.zzah zzahVar) {
        this.zzt.activate(new zzm(this, zzahVar));
    }

    @Override // com.google.android.gms.maps.internal.ILocationSourceDelegate
    public final void deactivate() {
        this.zzt.deactivate();
    }
}
