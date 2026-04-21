package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public class zzq implements IInterface {
    private final IBinder zzqt;
    private final String zzqu;

    protected zzq(IBinder iBinder, String str) {
        this.zzqt = iBinder;
        this.zzqu = str;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.zzqt;
    }

    protected final Parcel obtainAndWriteInterfaceToken() {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInterfaceToken(this.zzqu);
        return parcelObtain;
    }

    protected final Parcel transactAndReadException(int i, Parcel parcel) throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        try {
            try {
                this.zzqt.transact(i, parcel, parcelObtain, 0);
                parcelObtain.readException();
                return parcelObtain;
            } catch (RuntimeException e) {
                parcelObtain.recycle();
                throw e;
            }
        } finally {
            parcel.recycle();
        }
    }

    protected final void transactAndReadExceptionReturnVoid(int i, Parcel parcel) throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        try {
            this.zzqt.transact(i, parcel, parcelObtain, 0);
            parcelObtain.readException();
        } finally {
            parcel.recycle();
            parcelObtain.recycle();
        }
    }

    protected final void transactOneway(int i, Parcel parcel) throws RemoteException {
        try {
            this.zzqt.transact(1, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }
}
