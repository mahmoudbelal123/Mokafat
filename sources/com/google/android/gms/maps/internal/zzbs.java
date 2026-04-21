package com.google.android.gms.maps.internal;

import android.graphics.Bitmap;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes.dex */
public interface zzbs extends IInterface {
    void onSnapshotReady(Bitmap bitmap) throws RemoteException;

    void zzb(IObjectWrapper iObjectWrapper) throws RemoteException;
}
