package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "EventParamsCreator")
@SafeParcelable.Reserved({1})
public final class zzaa extends AbstractSafeParcelable implements Iterable<String> {
    public static final Parcelable.Creator<zzaa> CREATOR = new zzac();

    @SafeParcelable.Field(getter = "z", id = 2)
    private final Bundle zzaim;

    @SafeParcelable.Constructor
    zzaa(@SafeParcelable.Param(id = 2) Bundle bundle) {
        this.zzaim = bundle;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, zziv(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    final Object get(String str) {
        return this.zzaim.get(str);
    }

    final Long getLong(String str) {
        return Long.valueOf(this.zzaim.getLong(str));
    }

    final Double zzbq(String str) {
        return Double.valueOf(this.zzaim.getDouble(str));
    }

    final String getString(String str) {
        return this.zzaim.getString(str);
    }

    public final int size() {
        return this.zzaim.size();
    }

    public final String toString() {
        return this.zzaim.toString();
    }

    public final Bundle zziv() {
        return new Bundle(this.zzaim);
    }

    @Override // java.lang.Iterable
    public final Iterator<String> iterator() {
        return new zzab(this);
    }
}
