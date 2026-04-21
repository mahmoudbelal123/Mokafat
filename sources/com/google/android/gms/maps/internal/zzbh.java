package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

/* JADX INFO: loaded from: classes.dex */
public interface zzbh extends IInterface {
    void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera streetViewPanoramaCamera) throws RemoteException;
}
