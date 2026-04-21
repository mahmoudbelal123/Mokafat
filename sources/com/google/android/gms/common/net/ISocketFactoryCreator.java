package com.google.android.gms.common.net;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

/* JADX INFO: loaded from: classes.dex */
public interface ISocketFactoryCreator extends IInterface {

    public static abstract class Stub extends zzb implements ISocketFactoryCreator {

        public static class Proxy extends com.google.android.gms.internal.stable.zza implements ISocketFactoryCreator {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.net.ISocketFactoryCreator");
            }

            @Override // com.google.android.gms.common.net.ISocketFactoryCreator
            public IObjectWrapper newSocketFactory(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3, boolean z) throws RemoteException {
                Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                zzc.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper);
                zzc.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper2);
                zzc.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper3);
                zzc.zza(parcelObtainAndWriteInterfaceToken, z);
                Parcel parcelTransactAndReadException = transactAndReadException(1, parcelObtainAndWriteInterfaceToken);
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelTransactAndReadException.readStrongBinder());
                parcelTransactAndReadException.recycle();
                return iObjectWrapperAsInterface;
            }

            @Override // com.google.android.gms.common.net.ISocketFactoryCreator
            public IObjectWrapper newSocketFactoryWithCacheDir(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3, String str) throws RemoteException {
                Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                zzc.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper);
                zzc.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper2);
                zzc.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper3);
                parcelObtainAndWriteInterfaceToken.writeString(str);
                Parcel parcelTransactAndReadException = transactAndReadException(2, parcelObtainAndWriteInterfaceToken);
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelTransactAndReadException.readStrongBinder());
                parcelTransactAndReadException.recycle();
                return iObjectWrapperAsInterface;
            }
        }

        public Stub() {
            super("com.google.android.gms.common.net.ISocketFactoryCreator");
        }

        public static ISocketFactoryCreator asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.net.ISocketFactoryCreator");
            return iInterfaceQueryLocalInterface instanceof ISocketFactoryCreator ? (ISocketFactoryCreator) iInterfaceQueryLocalInterface : new Proxy(iBinder);
        }

        @Override // com.google.android.gms.internal.stable.zzb
        protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            IObjectWrapper iObjectWrapperNewSocketFactory;
            switch (i) {
                case 1:
                    iObjectWrapperNewSocketFactory = newSocketFactory(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), zzc.zza(parcel));
                    break;
                case 2:
                    iObjectWrapperNewSocketFactory = newSocketFactoryWithCacheDir(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    break;
                default:
                    return false;
            }
            parcel2.writeNoException();
            zzc.zza(parcel2, iObjectWrapperNewSocketFactory);
            return true;
        }
    }

    IObjectWrapper newSocketFactory(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3, boolean z) throws RemoteException;

    IObjectWrapper newSocketFactoryWithCacheDir(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3, String str) throws RemoteException;
}
