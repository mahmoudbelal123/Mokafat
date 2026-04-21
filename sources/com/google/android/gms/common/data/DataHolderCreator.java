package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public class DataHolderCreator implements Parcelable.Creator<DataHolder> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public DataHolder createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        int i2 = 0;
        String[] strArrCreateStringArray = null;
        CursorWindow[] cursorWindowArr = null;
        Bundle bundleCreateBundle = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId != 1000) {
                switch (fieldId) {
                    case 1:
                        strArrCreateStringArray = SafeParcelReader.createStringArray(parcel, header);
                        break;
                    case 2:
                        cursorWindowArr = (CursorWindow[]) SafeParcelReader.createTypedArray(parcel, header, CursorWindow.CREATOR);
                        break;
                    case 3:
                        i2 = SafeParcelReader.readInt(parcel, header);
                        break;
                    case 4:
                        bundleCreateBundle = SafeParcelReader.createBundle(parcel, header);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, header);
                        break;
                }
            } else {
                i = SafeParcelReader.readInt(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        DataHolder dataHolder = new DataHolder(i, strArrCreateStringArray, cursorWindowArr, i2, bundleCreateBundle);
        dataHolder.validateContents();
        return dataHolder;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public DataHolder[] newArray(int i) {
        return new DataHolder[i];
    }
}
