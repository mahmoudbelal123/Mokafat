package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.location.zzbh;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zzq implements Parcelable.Creator<GeofencingRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GeofencingRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = "";
        ArrayList arrayListCreateTypedList = null;
        int i = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, zzbh.CREATOR);
                    break;
                case 2:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 3:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new GeofencingRequest(arrayListCreateTypedList, i, strCreateString);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GeofencingRequest[] newArray(int i) {
        return new GeofencingRequest[i];
    }
}
