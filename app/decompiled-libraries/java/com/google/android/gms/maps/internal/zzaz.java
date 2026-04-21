package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.IInterface;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface zzaz extends IInterface {
    void onMyLocationClick(Location location) throws RemoteException;
}
