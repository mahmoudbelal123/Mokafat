package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "LocationRequestUpdateDataCreator")
@SafeParcelable.Reserved({1000})
public final class zzbf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbf> CREATOR = new zzbg();

    @SafeParcelable.Field(defaultValueUnchecked = "null", id = 4)
    private PendingIntent zzbv;

    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequestUpdateData.OPERATION_ADD", id = 1)
    private int zzcg;

    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getFusedLocationProviderCallbackBinder", id = 6, type = "android.os.IBinder")
    private zzaj zzcj;

    @SafeParcelable.Field(defaultValueUnchecked = "null", id = 2)
    private zzbd zzdl;

    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getLocationListenerBinder", id = 3, type = "android.os.IBinder")
    private com.google.android.gms.location.zzx zzdm;

    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getLocationCallbackBinder", id = 5, type = "android.os.IBinder")
    private com.google.android.gms.location.zzu zzdn;

    @SafeParcelable.Constructor
    zzbf(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) zzbd zzbdVar, @SafeParcelable.Param(id = 3) IBinder iBinder, @SafeParcelable.Param(id = 4) PendingIntent pendingIntent, @SafeParcelable.Param(id = 5) IBinder iBinder2, @SafeParcelable.Param(id = 6) IBinder iBinder3) {
        this.zzcg = i;
        this.zzdl = zzbdVar;
        zzaj zzalVar = null;
        this.zzdm = iBinder == null ? null : com.google.android.gms.location.zzy.zzc(iBinder);
        this.zzbv = pendingIntent;
        this.zzdn = iBinder2 == null ? null : com.google.android.gms.location.zzv.zzb(iBinder2);
        if (iBinder3 != null && iBinder3 != null) {
            IInterface iInterfaceQueryLocalInterface = iBinder3.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            zzalVar = iInterfaceQueryLocalInterface instanceof zzaj ? (zzaj) iInterfaceQueryLocalInterface : new zzal(iBinder3);
        }
        this.zzcj = zzalVar;
    }

    public static zzbf zza(com.google.android.gms.location.zzu zzuVar, @Nullable zzaj zzajVar) {
        return new zzbf(2, null, null, null, zzuVar.asBinder(), zzajVar != null ? zzajVar.asBinder() : null);
    }

    public static zzbf zza(com.google.android.gms.location.zzx zzxVar, @Nullable zzaj zzajVar) {
        return new zzbf(2, null, zzxVar.asBinder(), null, null, zzajVar != null ? zzajVar.asBinder() : null);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzcg);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdl, i, false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zzdm == null ? null : this.zzdm.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzbv, i, false);
        SafeParcelWriter.writeIBinder(parcel, 5, this.zzdn == null ? null : this.zzdn.asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 6, this.zzcj != null ? this.zzcj.asBinder() : null, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
