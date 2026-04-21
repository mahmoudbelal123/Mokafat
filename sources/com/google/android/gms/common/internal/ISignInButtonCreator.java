package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes.dex */
public interface ISignInButtonCreator extends IInterface {

    public static abstract class Stub extends com.google.android.gms.internal.stable.zzb implements ISignInButtonCreator {

        public static class Proxy extends com.google.android.gms.internal.stable.zza implements ISignInButtonCreator {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.ISignInButtonCreator");
            }

            @Override // com.google.android.gms.common.internal.ISignInButtonCreator
            public IObjectWrapper newSignInButton(IObjectWrapper iObjectWrapper, int i, int i2) throws RemoteException {
                Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                com.google.android.gms.internal.stable.zzc.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper);
                parcelObtainAndWriteInterfaceToken.writeInt(i);
                parcelObtainAndWriteInterfaceToken.writeInt(i2);
                Parcel parcelTransactAndReadException = transactAndReadException(1, parcelObtainAndWriteInterfaceToken);
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelTransactAndReadException.readStrongBinder());
                parcelTransactAndReadException.recycle();
                return iObjectWrapperAsInterface;
            }

            @Override // com.google.android.gms.common.internal.ISignInButtonCreator
            public IObjectWrapper newSignInButtonFromConfig(IObjectWrapper iObjectWrapper, SignInButtonConfig signInButtonConfig) throws RemoteException {
                Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                com.google.android.gms.internal.stable.zzc.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper);
                com.google.android.gms.internal.stable.zzc.zza(parcelObtainAndWriteInterfaceToken, signInButtonConfig);
                Parcel parcelTransactAndReadException = transactAndReadException(2, parcelObtainAndWriteInterfaceToken);
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelTransactAndReadException.readStrongBinder());
                parcelTransactAndReadException.recycle();
                return iObjectWrapperAsInterface;
            }
        }

        public Stub() {
            super("com.google.android.gms.common.internal.ISignInButtonCreator");
        }

        public static ISignInButtonCreator asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
            return iInterfaceQueryLocalInterface instanceof ISignInButtonCreator ? (ISignInButtonCreator) iInterfaceQueryLocalInterface : new Proxy(iBinder);
        }

        @Override // com.google.android.gms.internal.stable.zzb
        protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            IObjectWrapper iObjectWrapperNewSignInButton;
            switch (i) {
                case 1:
                    iObjectWrapperNewSignInButton = newSignInButton(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    break;
                case 2:
                    iObjectWrapperNewSignInButton = newSignInButtonFromConfig(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (SignInButtonConfig) com.google.android.gms.internal.stable.zzc.zza(parcel, SignInButtonConfig.CREATOR));
                    break;
                default:
                    return false;
            }
            parcel2.writeNoException();
            com.google.android.gms.internal.stable.zzc.zza(parcel2, iObjectWrapperNewSignInButton);
            return true;
        }
    }

    IObjectWrapper newSignInButton(IObjectWrapper iObjectWrapper, int i, int i2) throws RemoteException;

    IObjectWrapper newSignInButtonFromConfig(IObjectWrapper iObjectWrapper, SignInButtonConfig signInButtonConfig) throws RemoteException;
}
