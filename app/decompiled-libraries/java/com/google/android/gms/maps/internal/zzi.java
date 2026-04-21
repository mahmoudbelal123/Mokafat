package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzi extends com.google.android.gms.internal.maps.zzb implements zzh {
    public zzi() {
        super("com.google.android.gms.maps.internal.IInfoWindowAdapter");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IObjectWrapper iObjectWrapperZzh;
        switch (i) {
            case 1:
                iObjectWrapperZzh = zzh(com.google.android.gms.internal.maps.zzu.zzg(parcel.readStrongBinder()));
                break;
            case 2:
                iObjectWrapperZzh = zzi(com.google.android.gms.internal.maps.zzu.zzg(parcel.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        com.google.android.gms.internal.maps.zzc.zza(parcel2, iObjectWrapperZzh);
        return true;
    }
}
