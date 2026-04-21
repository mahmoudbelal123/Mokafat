package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IAccountAccessor extends IInterface {

    public static abstract class Stub extends com.google.android.gms.internal.stable.zzb implements IAccountAccessor {

        public static class Proxy extends com.google.android.gms.internal.stable.zza implements IAccountAccessor {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.IAccountAccessor");
            }

            @Override // com.google.android.gms.common.internal.IAccountAccessor
            public Account getAccount() throws RemoteException {
                Parcel parcelTransactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken());
                Account account = (Account) com.google.android.gms.internal.stable.zzc.zza(parcelTransactAndReadException, Account.CREATOR);
                parcelTransactAndReadException.recycle();
                return account;
            }
        }

        public Stub() {
            super("com.google.android.gms.common.internal.IAccountAccessor");
        }

        public static IAccountAccessor asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
            return iInterfaceQueryLocalInterface instanceof IAccountAccessor ? (IAccountAccessor) iInterfaceQueryLocalInterface : new Proxy(iBinder);
        }

        @Override // com.google.android.gms.internal.stable.zzb
        protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 2) {
                return false;
            }
            Account account = getAccount();
            parcel2.writeNoException();
            com.google.android.gms.internal.stable.zzc.zzb(parcel2, account);
            return true;
        }
    }

    Account getAccount() throws RemoteException;
}
