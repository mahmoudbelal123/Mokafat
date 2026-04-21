package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

/* JADX INFO: loaded from: classes.dex */
public interface zzbl extends IInterface {
    void onStreetViewPanoramaClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation) throws RemoteException;
}
