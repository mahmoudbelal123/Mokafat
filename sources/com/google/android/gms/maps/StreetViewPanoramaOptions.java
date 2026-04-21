package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewSource;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "StreetViewPanoramaOptionsCreator")
@SafeParcelable.Reserved({1})
public final class StreetViewPanoramaOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<StreetViewPanoramaOptions> CREATOR = new zzai();

    @SafeParcelable.Field(getter = "getPanoramaId", id = 3)
    private String panoId;

    @SafeParcelable.Field(getter = "getPosition", id = 4)
    private LatLng position;

    @SafeParcelable.Field(getter = "getUseViewLifecycleInFragmentForParcel", id = 10, type = "byte")
    private Boolean zzak;

    @SafeParcelable.Field(getter = "getZoomGesturesEnabledForParcel", id = 7, type = "byte")
    private Boolean zzap;

    @SafeParcelable.Field(getter = "getStreetViewPanoramaCamera", id = 2)
    private StreetViewPanoramaCamera zzbw;

    @SafeParcelable.Field(getter = "getRadius", id = 5)
    private Integer zzbx;

    @SafeParcelable.Field(getter = "getUserNavigationEnabledForParcel", id = 6, type = "byte")
    private Boolean zzby;

    @SafeParcelable.Field(getter = "getPanningGesturesEnabledForParcel", id = 8, type = "byte")
    private Boolean zzbz;

    @SafeParcelable.Field(getter = "getStreetNamesEnabledForParcel", id = 9, type = "byte")
    private Boolean zzca;

    @SafeParcelable.Field(getter = "getSource", id = 11)
    private StreetViewSource zzcb;

    public StreetViewPanoramaOptions() {
        this.zzby = true;
        this.zzap = true;
        this.zzbz = true;
        this.zzca = true;
        this.zzcb = StreetViewSource.DEFAULT;
    }

    @SafeParcelable.Constructor
    StreetViewPanoramaOptions(@SafeParcelable.Param(id = 2) StreetViewPanoramaCamera streetViewPanoramaCamera, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) LatLng latLng, @SafeParcelable.Param(id = 5) Integer num, @SafeParcelable.Param(id = 6) byte b, @SafeParcelable.Param(id = 7) byte b2, @SafeParcelable.Param(id = 8) byte b3, @SafeParcelable.Param(id = 9) byte b4, @SafeParcelable.Param(id = 10) byte b5, @SafeParcelable.Param(id = 11) StreetViewSource streetViewSource) {
        this.zzby = true;
        this.zzap = true;
        this.zzbz = true;
        this.zzca = true;
        this.zzcb = StreetViewSource.DEFAULT;
        this.zzbw = streetViewPanoramaCamera;
        this.position = latLng;
        this.zzbx = num;
        this.panoId = str;
        this.zzby = com.google.android.gms.maps.internal.zza.zza(b);
        this.zzap = com.google.android.gms.maps.internal.zza.zza(b2);
        this.zzbz = com.google.android.gms.maps.internal.zza.zza(b3);
        this.zzca = com.google.android.gms.maps.internal.zza.zza(b4);
        this.zzak = com.google.android.gms.maps.internal.zza.zza(b5);
        this.zzcb = streetViewSource;
    }

    public final Boolean getPanningGesturesEnabled() {
        return this.zzbz;
    }

    public final String getPanoramaId() {
        return this.panoId;
    }

    public final LatLng getPosition() {
        return this.position;
    }

    public final Integer getRadius() {
        return this.zzbx;
    }

    public final StreetViewSource getSource() {
        return this.zzcb;
    }

    public final Boolean getStreetNamesEnabled() {
        return this.zzca;
    }

    public final StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
        return this.zzbw;
    }

    public final Boolean getUseViewLifecycleInFragment() {
        return this.zzak;
    }

    public final Boolean getUserNavigationEnabled() {
        return this.zzby;
    }

    public final Boolean getZoomGesturesEnabled() {
        return this.zzap;
    }

    public final StreetViewPanoramaOptions panningGesturesEnabled(boolean z) {
        this.zzbz = Boolean.valueOf(z);
        return this;
    }

    public final StreetViewPanoramaOptions panoramaCamera(StreetViewPanoramaCamera streetViewPanoramaCamera) {
        this.zzbw = streetViewPanoramaCamera;
        return this;
    }

    public final StreetViewPanoramaOptions panoramaId(String str) {
        this.panoId = str;
        return this;
    }

    public final StreetViewPanoramaOptions position(LatLng latLng) {
        this.position = latLng;
        return this;
    }

    public final StreetViewPanoramaOptions position(LatLng latLng, StreetViewSource streetViewSource) {
        this.position = latLng;
        this.zzcb = streetViewSource;
        return this;
    }

    public final StreetViewPanoramaOptions position(LatLng latLng, Integer num) {
        this.position = latLng;
        this.zzbx = num;
        return this;
    }

    public final StreetViewPanoramaOptions position(LatLng latLng, Integer num, StreetViewSource streetViewSource) {
        this.position = latLng;
        this.zzbx = num;
        this.zzcb = streetViewSource;
        return this;
    }

    public final StreetViewPanoramaOptions streetNamesEnabled(boolean z) {
        this.zzca = Boolean.valueOf(z);
        return this;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("PanoramaId", this.panoId).add("Position", this.position).add("Radius", this.zzbx).add("Source", this.zzcb).add("StreetViewPanoramaCamera", this.zzbw).add("UserNavigationEnabled", this.zzby).add("ZoomGesturesEnabled", this.zzap).add("PanningGesturesEnabled", this.zzbz).add("StreetNamesEnabled", this.zzca).add("UseViewLifecycleInFragment", this.zzak).toString();
    }

    public final StreetViewPanoramaOptions useViewLifecycleInFragment(boolean z) {
        this.zzak = Boolean.valueOf(z);
        return this;
    }

    public final StreetViewPanoramaOptions userNavigationEnabled(boolean z) {
        this.zzby = Boolean.valueOf(z);
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getStreetViewPanoramaCamera(), i, false);
        SafeParcelWriter.writeString(parcel, 3, getPanoramaId(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, getPosition(), i, false);
        SafeParcelWriter.writeIntegerObject(parcel, 5, getRadius(), false);
        SafeParcelWriter.writeByte(parcel, 6, com.google.android.gms.maps.internal.zza.zza(this.zzby));
        SafeParcelWriter.writeByte(parcel, 7, com.google.android.gms.maps.internal.zza.zza(this.zzap));
        SafeParcelWriter.writeByte(parcel, 8, com.google.android.gms.maps.internal.zza.zza(this.zzbz));
        SafeParcelWriter.writeByte(parcel, 9, com.google.android.gms.maps.internal.zza.zza(this.zzca));
        SafeParcelWriter.writeByte(parcel, 10, com.google.android.gms.maps.internal.zza.zza(this.zzak));
        SafeParcelWriter.writeParcelable(parcel, 11, getSource(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final StreetViewPanoramaOptions zoomGesturesEnabled(boolean z) {
        this.zzap = Boolean.valueOf(z);
        return this;
    }
}
