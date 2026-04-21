package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "ConditionalUserPropertyParcelCreator")
public final class zzl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzl> CREATOR = new zzm();

    @SafeParcelable.Field(id = 6)
    public boolean active;

    @SafeParcelable.Field(id = 5)
    public long creationTimestamp;

    @SafeParcelable.Field(id = 3)
    public String origin;

    @SafeParcelable.Field(id = 2)
    public String packageName;

    @SafeParcelable.Field(id = 11)
    public long timeToLive;

    @SafeParcelable.Field(id = 7)
    public String triggerEventName;

    @SafeParcelable.Field(id = 9)
    public long triggerTimeout;

    @SafeParcelable.Field(id = 4)
    public zzfh zzahb;

    @SafeParcelable.Field(id = 8)
    public zzad zzahc;

    @SafeParcelable.Field(id = 10)
    public zzad zzahd;

    @SafeParcelable.Field(id = 12)
    public zzad zzahe;

    zzl(zzl zzlVar) {
        Preconditions.checkNotNull(zzlVar);
        this.packageName = zzlVar.packageName;
        this.origin = zzlVar.origin;
        this.zzahb = zzlVar.zzahb;
        this.creationTimestamp = zzlVar.creationTimestamp;
        this.active = zzlVar.active;
        this.triggerEventName = zzlVar.triggerEventName;
        this.zzahc = zzlVar.zzahc;
        this.triggerTimeout = zzlVar.triggerTimeout;
        this.zzahd = zzlVar.zzahd;
        this.timeToLive = zzlVar.timeToLive;
        this.zzahe = zzlVar.zzahe;
    }

    @SafeParcelable.Constructor
    zzl(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) zzfh zzfhVar, @SafeParcelable.Param(id = 5) long j, @SafeParcelable.Param(id = 6) boolean z, @SafeParcelable.Param(id = 7) String str3, @SafeParcelable.Param(id = 8) zzad zzadVar, @SafeParcelable.Param(id = 9) long j2, @SafeParcelable.Param(id = 10) zzad zzadVar2, @SafeParcelable.Param(id = 11) long j3, @SafeParcelable.Param(id = 12) zzad zzadVar3) {
        this.packageName = str;
        this.origin = str2;
        this.zzahb = zzfhVar;
        this.creationTimestamp = j;
        this.active = z;
        this.triggerEventName = str3;
        this.zzahc = zzadVar;
        this.triggerTimeout = j2;
        this.zzahd = zzadVar2;
        this.timeToLive = j3;
        this.zzahe = zzadVar3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.packageName, false);
        SafeParcelWriter.writeString(parcel, 3, this.origin, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzahb, i, false);
        SafeParcelWriter.writeLong(parcel, 5, this.creationTimestamp);
        SafeParcelWriter.writeBoolean(parcel, 6, this.active);
        SafeParcelWriter.writeString(parcel, 7, this.triggerEventName, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzahc, i, false);
        SafeParcelWriter.writeLong(parcel, 9, this.triggerTimeout);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzahd, i, false);
        SafeParcelWriter.writeLong(parcel, 11, this.timeToLive);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzahe, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
