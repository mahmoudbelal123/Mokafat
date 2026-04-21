package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zzag implements Parcelable.Creator<LocationSettingsRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ LocationSettingsRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        ArrayList arrayListCreateTypedList = null;
        boolean z2 = false;
        zzae zzaeVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId != 5) {
                switch (fieldId) {
                    case 1:
                        arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, LocationRequest.CREATOR);
                        break;
                    case 2:
                        z = SafeParcelReader.readBoolean(parcel, header);
                        break;
                    case 3:
                        z2 = SafeParcelReader.readBoolean(parcel, header);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, header);
                        break;
                }
            } else {
                zzaeVar = (zzae) SafeParcelReader.createParcelable(parcel, header, zzae.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new LocationSettingsRequest(arrayListCreateTypedList, z, z2, zzaeVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ LocationSettingsRequest[] newArray(int i) {
        return new LocationSettingsRequest[i];
    }
}
