package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzau extends com.google.android.gms.internal.maps.zzb implements zzat {
    public zzau() {
        super("com.google.android.gms.maps.internal.IOnMarkerDragListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzb(com.google.android.gms.internal.maps.zzu.zzg(parcel.readStrongBinder()));
                break;
            case 2:
                zzd(com.google.android.gms.internal.maps.zzu.zzg(parcel.readStrongBinder()));
                break;
            case 3:
                zzc(com.google.android.gms.internal.maps.zzu.zzg(parcel.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
