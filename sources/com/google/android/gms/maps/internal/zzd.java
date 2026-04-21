package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzd extends com.google.android.gms.internal.maps.zzb implements zzc {
    public zzd() {
        super("com.google.android.gms.maps.internal.ICancelableCallback");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                onFinish();
                break;
            case 2:
                onCancel();
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
