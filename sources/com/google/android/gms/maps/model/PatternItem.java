package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "PatternItemCreator")
@SafeParcelable.Reserved({1})
public class PatternItem extends AbstractSafeParcelable {

    @SafeParcelable.Field(getter = "getType", id = 2)
    private final int type;

    @Nullable
    @SafeParcelable.Field(getter = "getLength", id = 3)
    private final Float zzdu;
    private static final String TAG = PatternItem.class.getSimpleName();
    public static final Parcelable.Creator<PatternItem> CREATOR = new zzi();

    @SafeParcelable.Constructor
    public PatternItem(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) @Nullable Float f) {
        boolean z = true;
        if (i != 1 && (f == null || f.floatValue() < 0.0f)) {
            z = false;
        }
        String strValueOf = String.valueOf(f);
        StringBuilder sb = new StringBuilder(45 + String.valueOf(strValueOf).length());
        sb.append("Invalid PatternItem: type=");
        sb.append(i);
        sb.append(" length=");
        sb.append(strValueOf);
        Preconditions.checkArgument(z, sb.toString());
        this.type = i;
        this.zzdu = f;
    }

    @Nullable
    static List<PatternItem> zza(@Nullable List<PatternItem> list) {
        PatternItem dash;
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (PatternItem dot : list) {
            if (dot == null) {
                dot = null;
            } else {
                switch (dot.type) {
                    case 0:
                        dash = new Dash(dot.zzdu.floatValue());
                        break;
                    case 1:
                        dot = new Dot();
                        continue;
                    case 2:
                        dash = new Gap(dot.zzdu.floatValue());
                        break;
                    default:
                        String str = TAG;
                        int i = dot.type;
                        StringBuilder sb = new StringBuilder(37);
                        sb.append("Unknown PatternItem type: ");
                        sb.append(i);
                        Log.w(str, sb.toString());
                        continue;
                }
                dot = dash;
            }
            arrayList.add(dot);
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PatternItem)) {
            return false;
        }
        PatternItem patternItem = (PatternItem) obj;
        return this.type == patternItem.type && Objects.equal(this.zzdu, patternItem.zzdu);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.type), this.zzdu);
    }

    public String toString() {
        int i = this.type;
        String strValueOf = String.valueOf(this.zzdu);
        StringBuilder sb = new StringBuilder(39 + String.valueOf(strValueOf).length());
        sb.append("[PatternItem: type=");
        sb.append(i);
        sb.append(" length=");
        sb.append(strValueOf);
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.type);
        SafeParcelWriter.writeFloatObject(parcel, 3, this.zzdu, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
