package com.google.android.gms.measurement.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzai extends com.google.android.gms.internal.measurement.zzq implements zzag {
    zzai(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    public final void zza(zzad zzadVar, zzh zzhVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.measurement.zzs.zza(parcelObtainAndWriteInterfaceToken, zzadVar);
        com.google.android.gms.internal.measurement.zzs.zza(parcelObtainAndWriteInterfaceToken, zzhVar);
        transactAndReadExceptionReturnVoid(1, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    public final void zza(zzfh zzfhVar, zzh zzhVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.measurement.zzs.zza(parcelObtainAndWriteInterfaceToken, zzfhVar);
        com.google.android.gms.internal.measurement.zzs.zza(parcelObtainAndWriteInterfaceToken, zzhVar);
        transactAndReadExceptionReturnVoid(2, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    public final void zza(zzh zzhVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.measurement.zzs.zza(parcelObtainAndWriteInterfaceToken, zzhVar);
        transactAndReadExceptionReturnVoid(4, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    public final void zza(zzad zzadVar, String str, String str2) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.measurement.zzs.zza(parcelObtainAndWriteInterfaceToken, zzadVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeString(str2);
        transactAndReadExceptionReturnVoid(5, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    public final void zzb(zzh zzhVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.measurement.zzs.zza(parcelObtainAndWriteInterfaceToken, zzhVar);
        transactAndReadExceptionReturnVoid(6, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    public final List<zzfh> zza(zzh zzhVar, boolean z) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.measurement.zzs.zza(parcelObtainAndWriteInterfaceToken, zzhVar);
        com.google.android.gms.internal.measurement.zzs.writeBoolean(parcelObtainAndWriteInterfaceToken, z);
        Parcel parcelTransactAndReadException = transactAndReadException(7, parcelObtainAndWriteInterfaceToken);
        ArrayList arrayListCreateTypedArrayList = parcelTransactAndReadException.createTypedArrayList(zzfh.CREATOR);
        parcelTransactAndReadException.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    public final byte[] zza(zzad zzadVar, String str) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.measurement.zzs.zza(parcelObtainAndWriteInterfaceToken, zzadVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        Parcel parcelTransactAndReadException = transactAndReadException(9, parcelObtainAndWriteInterfaceToken);
        byte[] bArrCreateByteArray = parcelTransactAndReadException.createByteArray();
        parcelTransactAndReadException.recycle();
        return bArrCreateByteArray;
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    public final void zza(long j, String str, String str2, String str3) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeLong(j);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeString(str2);
        parcelObtainAndWriteInterfaceToken.writeString(str3);
        transactAndReadExceptionReturnVoid(10, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    public final String zzc(zzh zzhVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.measurement.zzs.zza(parcelObtainAndWriteInterfaceToken, zzhVar);
        Parcel parcelTransactAndReadException = transactAndReadException(11, parcelObtainAndWriteInterfaceToken);
        String string = parcelTransactAndReadException.readString();
        parcelTransactAndReadException.recycle();
        return string;
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    public final void zza(zzl zzlVar, zzh zzhVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.measurement.zzs.zza(parcelObtainAndWriteInterfaceToken, zzlVar);
        com.google.android.gms.internal.measurement.zzs.zza(parcelObtainAndWriteInterfaceToken, zzhVar);
        transactAndReadExceptionReturnVoid(12, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    public final void zzb(zzl zzlVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.measurement.zzs.zza(parcelObtainAndWriteInterfaceToken, zzlVar);
        transactAndReadExceptionReturnVoid(13, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    public final List<zzfh> zza(String str, String str2, boolean z, zzh zzhVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeString(str2);
        com.google.android.gms.internal.measurement.zzs.writeBoolean(parcelObtainAndWriteInterfaceToken, z);
        com.google.android.gms.internal.measurement.zzs.zza(parcelObtainAndWriteInterfaceToken, zzhVar);
        Parcel parcelTransactAndReadException = transactAndReadException(14, parcelObtainAndWriteInterfaceToken);
        ArrayList arrayListCreateTypedArrayList = parcelTransactAndReadException.createTypedArrayList(zzfh.CREATOR);
        parcelTransactAndReadException.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    public final List<zzfh> zza(String str, String str2, String str3, boolean z) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeString(str2);
        parcelObtainAndWriteInterfaceToken.writeString(str3);
        com.google.android.gms.internal.measurement.zzs.writeBoolean(parcelObtainAndWriteInterfaceToken, z);
        Parcel parcelTransactAndReadException = transactAndReadException(15, parcelObtainAndWriteInterfaceToken);
        ArrayList arrayListCreateTypedArrayList = parcelTransactAndReadException.createTypedArrayList(zzfh.CREATOR);
        parcelTransactAndReadException.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    public final List<zzl> zza(String str, String str2, zzh zzhVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeString(str2);
        com.google.android.gms.internal.measurement.zzs.zza(parcelObtainAndWriteInterfaceToken, zzhVar);
        Parcel parcelTransactAndReadException = transactAndReadException(16, parcelObtainAndWriteInterfaceToken);
        ArrayList arrayListCreateTypedArrayList = parcelTransactAndReadException.createTypedArrayList(zzl.CREATOR);
        parcelTransactAndReadException.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    public final List<zzl> zze(String str, String str2, String str3) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeString(str2);
        parcelObtainAndWriteInterfaceToken.writeString(str3);
        Parcel parcelTransactAndReadException = transactAndReadException(17, parcelObtainAndWriteInterfaceToken);
        ArrayList arrayListCreateTypedArrayList = parcelTransactAndReadException.createTypedArrayList(zzl.CREATOR);
        parcelTransactAndReadException.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    public final void zzd(zzh zzhVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.measurement.zzs.zza(parcelObtainAndWriteInterfaceToken, zzhVar);
        transactAndReadExceptionReturnVoid(18, parcelObtainAndWriteInterfaceToken);
    }
}
