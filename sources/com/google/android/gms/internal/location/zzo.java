package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "DeviceOrientationRequestUpdateDataCreator")
public final class zzo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzo> CREATOR = new zzp();

    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequestUpdateData.OPERATION_ADD", id = 1)
    private int zzcg;

    @SafeParcelable.Field(defaultValueUnchecked = "null", id = 2)
    private zzm zzch;

    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getDeviceOrientationListenerBinder", id = 3, type = "android.os.IBinder")
    private com.google.android.gms.location.zzr zzci;

    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getFusedLocationProviderCallbackBinder", id = 4, type = "android.os.IBinder")
    private zzaj zzcj;

    @SafeParcelable.Constructor
    zzo(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) zzm zzmVar, @SafeParcelable.Param(id = 3) IBinder iBinder, @SafeParcelable.Param(id = 4) IBinder iBinder2) {
        this.zzcg = i;
        this.zzch = zzmVar;
        zzaj zzalVar = null;
        this.zzci = iBinder == null ? null : com.google.android.gms.location.zzs.zza(iBinder);
        if (iBinder2 != null && iBinder2 != null) {
            IInterface iInterfaceQueryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            zzalVar = iInterfaceQueryLocalInterface instanceof zzaj ? (zzaj) iInterfaceQueryLocalInterface : new zzal(iBinder2);
        }
        this.zzcj = zzalVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzcg);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzch, i, false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zzci == null ? null : this.zzci.asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 4, this.zzcj != null ? this.zzcj.asBinder() : null, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
