package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ICommonCallbacks extends IInterface {

    public static abstract class Stub extends com.google.android.gms.internal.stable.zzb implements ICommonCallbacks {

        public static class Proxy extends com.google.android.gms.internal.stable.zza implements ICommonCallbacks {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.service.ICommonCallbacks");
            }

            @Override // com.google.android.gms.common.internal.service.ICommonCallbacks
            public void onDefaultAccountCleared(int i) throws RemoteException {
                Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                parcelObtainAndWriteInterfaceToken.writeInt(i);
                transactAndReadExceptionReturnVoid(1, parcelObtainAndWriteInterfaceToken);
            }
        }

        public Stub() {
            super("com.google.android.gms.common.internal.service.ICommonCallbacks");
        }

        public static ICommonCallbacks asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.service.ICommonCallbacks");
            return iInterfaceQueryLocalInterface instanceof ICommonCallbacks ? (ICommonCallbacks) iInterfaceQueryLocalInterface : new Proxy(iBinder);
        }

        @Override // com.google.android.gms.internal.stable.zzb
        protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                return false;
            }
            onDefaultAccountCleared(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }
    }

    void onDefaultAccountCleared(int i) throws RemoteException;
}
