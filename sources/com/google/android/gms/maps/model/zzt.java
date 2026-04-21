package com.google.android.gms.maps.model;

import com.google.android.gms.internal.maps.zzag;

/* JADX INFO: loaded from: classes.dex */
final class zzt extends zzag {
    private final /* synthetic */ TileProvider zzem;

    zzt(TileOverlayOptions tileOverlayOptions, TileProvider tileProvider) {
        this.zzem = tileProvider;
    }

    @Override // com.google.android.gms.internal.maps.zzaf
    public final Tile getTile(int i, int i2, int i3) {
        return this.zzem.getTile(i, i2, i3);
    }
}
