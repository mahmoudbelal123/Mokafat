package com.google.firebase.events;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.firebase:firebase-common@@16.0.1 */
/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public class Event<T> {
    private final Class<T> zza;
    private final T zzb;

    @KeepForSdk
    public Event(Class<T> cls, T t) {
        this.zza = (Class) Preconditions.checkNotNull(cls);
        this.zzb = (T) Preconditions.checkNotNull(t);
    }

    @KeepForSdk
    public Class<T> getType() {
        return this.zza;
    }

    @KeepForSdk
    public T getPayload() {
        return this.zzb;
    }

    public String toString() {
        return String.format("Event{type: %s, payload: %s}", this.zza, this.zzb);
    }
}
