package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zzi implements Parcelable.Creator<zzh> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzh[] newArray(int i) {
        return new zzh[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzh createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        int i = 0;
        boolean z2 = false;
        boolean z3 = true;
        boolean z4 = true;
        boolean z5 = true;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        String strCreateString = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        String strCreateString4 = null;
        String strCreateString5 = null;
        String strCreateString6 = null;
        String strCreateString7 = null;
        long j5 = -2147483648L;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 3:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    break;
                case 5:
                    strCreateString4 = SafeParcelReader.createString(parcel, header);
                    break;
                case 6:
                    j = SafeParcelReader.readLong(parcel, header);
                    break;
                case 7:
                    j2 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 8:
                    strCreateString5 = SafeParcelReader.createString(parcel, header);
                    break;
                case 9:
                    z3 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 10:
                    z = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 11:
                    j5 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 12:
                    strCreateString6 = SafeParcelReader.createString(parcel, header);
                    break;
                case 13:
                    j3 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 14:
                    j4 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 15:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 16:
                    z4 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 17:
                    z5 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 18:
                    z2 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 19:
                    strCreateString7 = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzh(strCreateString, strCreateString2, strCreateString3, strCreateString4, j, j2, strCreateString5, z3, z, j5, strCreateString6, j3, j4, i, z4, z5, z2, strCreateString7);
    }
}
