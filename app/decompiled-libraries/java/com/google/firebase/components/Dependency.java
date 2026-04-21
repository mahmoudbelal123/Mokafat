package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.firebase:firebase-common@@16.0.1 */
/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public final class Dependency {
    private final Class<?> zza;
    private final int zzb;
    private final int zzc;

    private Dependency(Class<?> anInterface, int type, int injection) {
        this.zza = (Class) Preconditions.checkNotNull(anInterface, "Null dependency anInterface.");
        this.zzb = type;
        this.zzc = injection;
    }

    @KeepForSdk
    public static Dependency optional(Class<?> anInterface) {
        return new Dependency(anInterface, 0, 0);
    }

    @KeepForSdk
    public static Dependency required(Class<?> anInterface) {
        return new Dependency(anInterface, 1, 0);
    }

    @KeepForSdk
    public static Dependency optionalProvider(Class<?> anInterface) {
        return new Dependency(anInterface, 0, 1);
    }

    @KeepForSdk
    public static Dependency requiredProvider(Class<?> anInterface) {
        return new Dependency(anInterface, 1, 1);
    }

    public final Class<?> zza() {
        return this.zza;
    }

    public final boolean zzb() {
        return this.zzb == 1;
    }

    public final boolean zzc() {
        return this.zzc == 0;
    }

    public final boolean equals(Object o) {
        if (!(o instanceof Dependency)) {
            return false;
        }
        Dependency dependency = (Dependency) o;
        return this.zza == dependency.zza && this.zzb == dependency.zzb && this.zzc == dependency.zzc;
    }

    public final int hashCode() {
        return ((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb) * 1000003) ^ this.zzc;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Dependency{anInterface=");
        sb.append(this.zza);
        sb.append(", required=");
        sb.append(this.zzb == 1);
        sb.append(", direct=");
        sb.append(this.zzc == 0);
        sb.append("}");
        return sb.toString();
    }
}
