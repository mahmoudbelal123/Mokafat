package com.google.firebase.components;

import com.google.firebase.inject.Provider;

/* JADX INFO: compiled from: com.google.firebase:firebase-common@@16.0.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzh<T> implements Provider<T> {
    private static final Object zza = new Object();
    private volatile Object zzb = zza;
    private volatile Provider<T> zzc;

    zzh(final ComponentFactory<T> componentFactory, final ComponentContainer componentContainer) {
        this.zzc = new Provider(componentFactory, componentContainer) { // from class: com.google.firebase.components.zzi
            private final ComponentFactory zza;
            private final ComponentContainer zzb;

            {
                this.zza = componentFactory;
                this.zzb = componentContainer;
            }

            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return this.zza.create(this.zzb);
            }
        };
    }

    @Override // com.google.firebase.inject.Provider
    public final T get() {
        T t = (T) this.zzb;
        if (t == zza) {
            synchronized (this) {
                t = (T) this.zzb;
                if (t == zza) {
                    t = this.zzc.get();
                    this.zzb = t;
                    this.zzc = null;
                }
            }
        }
        return t;
    }
}
