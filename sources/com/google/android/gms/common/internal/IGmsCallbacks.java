package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IGmsCallbacks extends IInterface {

    public static abstract class Stub extends com.google.android.gms.internal.stable.zzb implements IGmsCallbacks {

        public static class Proxy extends com.google.android.gms.internal.stable.zza implements IGmsCallbacks {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.IGmsCallbacks");
            }

            @Override // com.google.android.gms.common.internal.IGmsCallbacks
            public void onAccountValidationComplete(int i, Bundle bundle) throws RemoteException {
                Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                parcelObtainAndWriteInterfaceToken.writeInt(i);
                com.google.android.gms.internal.stable.zzc.zza(parcelObtainAndWriteInterfaceToken, bundle);
                transactAndReadExceptionReturnVoid(2, parcelObtainAndWriteInterfaceToken);
            }

            @Override // com.google.android.gms.common.internal.IGmsCallbacks
            public void onPostInitComplete(int i, IBinder iBinder, Bundle bundle) throws RemoteException {
                Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                parcelObtainAndWriteInterfaceToken.writeInt(i);
                parcelObtainAndWriteInterfaceToken.writeStrongBinder(iBinder);
                com.google.android.gms.internal.stable.zzc.zza(parcelObtainAndWriteInterfaceToken, bundle);
                transactAndReadExceptionReturnVoid(1, parcelObtainAndWriteInterfaceToken);
            }

            @Override // com.google.android.gms.common.internal.IGmsCallbacks
            public void onPostInitCompleteWithConnectionInfo(int i, IBinder iBinder, ConnectionInfo connectionInfo) throws RemoteException {
                Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                parcelObtainAndWriteInterfaceToken.writeInt(i);
                parcelObtainAndWriteInterfaceToken.writeStrongBinder(iBinder);
                com.google.android.gms.internal.stable.zzc.zza(parcelObtainAndWriteInterfaceToken, connectionInfo);
                transactAndReadExceptionReturnVoid(3, parcelObtainAndWriteInterfaceToken);
            }
        }

        public Stub() {
            super("com.google.android.gms.common.internal.IGmsCallbacks");
        }

        public static IGmsCallbacks asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsCallbacks");
            return iInterfaceQueryLocalInterface instanceof IGmsCallbacks ? (IGmsCallbacks) iInterfaceQueryLocalInterface : new Proxy(iBinder);
        }

        @Override // com.google.android.gms.internal.stable.zzb
        protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    onPostInitComplete(parcel.readInt(), parcel.readStrongBinder(), (Bundle) com.google.android.gms.internal.stable.zzc.zza(parcel, Bundle.CREATOR));
                    break;
                case 2:
                    onAccountValidationComplete(parcel.readInt(), (Bundle) com.google.android.gms.internal.stable.zzc.zza(parcel, Bundle.CREATOR));
                    break;
                case 3:
                    onPostInitCompleteWithConnectionInfo(parcel.readInt(), parcel.readStrongBinder(), (ConnectionInfo) com.google.android.gms.internal.stable.zzc.zza(parcel, ConnectionInfo.CREATOR));
                    break;
                default:
                    return false;
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void onAccountValidationComplete(int i, Bundle bundle) throws RemoteException;

    void onPostInitComplete(int i, IBinder iBinder, Bundle bundle) throws RemoteException;

    void onPostInitCompleteWithConnectionInfo(int i, IBinder iBinder, ConnectionInfo connectionInfo) throws RemoteException;
}
