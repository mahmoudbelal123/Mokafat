package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: com.google.firebase:firebase-common@@16.0.1 */
/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public final class Component<T> {
    private final Set<Class<? super T>> zza;
    private final Set<Dependency> zzb;
    private final int zzc;
    private final ComponentFactory<T> zzd;
    private final Set<Class<?>> zze;

    /* synthetic */ Component(Set x0, Set x1, int x2, ComponentFactory x3, Set x4, byte b) {
        this(x0, x1, x2, x3, x4);
    }

    private Component(Set<Class<? super T>> providedInterfaces, Set<Dependency> dependencies, int instantiation, ComponentFactory<T> factory, Set<Class<?>> publishedEvents) {
        this.zza = Collections.unmodifiableSet(providedInterfaces);
        this.zzb = Collections.unmodifiableSet(dependencies);
        this.zzc = instantiation;
        this.zzd = factory;
        this.zze = Collections.unmodifiableSet(publishedEvents);
    }

    public final Set<Class<? super T>> zza() {
        return this.zza;
    }

    public final Set<Dependency> zzb() {
        return this.zzb;
    }

    public final ComponentFactory<T> zzc() {
        return this.zzd;
    }

    public final Set<Class<?>> zzd() {
        return this.zze;
    }

    public final boolean zze() {
        return this.zzc == 1;
    }

    public final boolean zzf() {
        return this.zzc == 2;
    }

    public final String toString() {
        return "Component<" + Arrays.toString(this.zza.toArray()) + ">{" + this.zzc + ", deps=" + Arrays.toString(this.zzb.toArray()) + "}";
    }

    @KeepForSdk
    public static <T> Builder<T> builder(Class<T> anInterface) {
        return new Builder<>(anInterface, new Class[0], (byte) 0);
    }

    @KeepForSdk
    public static <T> Builder<T> builder(Class<T> anInterface, Class<? super T>... additionalInterfaces) {
        return new Builder<>(anInterface, additionalInterfaces, (byte) 0);
    }

    @KeepForSdk
    @Deprecated
    public static <T> Component<T> of(Class<T> anInterface, final T value) {
        return builder(anInterface).factory(new ComponentFactory(value) { // from class: com.google.firebase.components.zza
            private final Object zza;

            {
                this.zza = value;
            }

            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return Component.zzb(this.zza);
            }
        }).build();
    }

    static final /* synthetic */ Object zzb(Object obj) {
        return obj;
    }

    @SafeVarargs
    @KeepForSdk
    public static <T> Component<T> of(final T value, Class<T> anInterface, Class<? super T>... additionalInterfaces) {
        return builder(anInterface, additionalInterfaces).factory(new ComponentFactory(value) { // from class: com.google.firebase.components.zzb
            private final Object zza;

            {
                this.zza = value;
            }

            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return Component.zza(this.zza);
            }
        }).build();
    }

    static final /* synthetic */ Object zza(Object obj) {
        return obj;
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-common@@16.0.1 */
    @KeepForSdk
    public static class Builder<T> {
        private final Set<Class<? super T>> zza;
        private final Set<Dependency> zzb;
        private int zzc;
        private ComponentFactory<T> zzd;
        private Set<Class<?>> zze;

        /* synthetic */ Builder(Class x0, Class[] x1, byte b) {
            this(x0, x1);
        }

        private Builder(Class<T> anInterface, Class<? super T>... additionalInterfaces) {
            this.zza = new HashSet();
            this.zzb = new HashSet();
            this.zzc = 0;
            this.zze = new HashSet();
            Preconditions.checkNotNull(anInterface, "Null interface");
            this.zza.add(anInterface);
            for (Class<? super T> cls : additionalInterfaces) {
                Preconditions.checkNotNull(cls, "Null interface");
            }
            Collections.addAll(this.zza, additionalInterfaces);
        }

        @KeepForSdk
        public Builder<T> add(Dependency dependency) {
            Preconditions.checkNotNull(dependency, "Null dependency");
            Preconditions.checkArgument(!this.zza.contains(dependency.zza()), "Components are not allowed to depend on interfaces they themselves provide.");
            this.zzb.add(dependency);
            return this;
        }

        @KeepForSdk
        public Builder<T> alwaysEager() {
            return zza(1);
        }

        @KeepForSdk
        public Builder<T> eagerInDefaultApp() {
            return zza(2);
        }

        @KeepForSdk
        public Builder<T> publishes(Class<?> eventType) {
            this.zze.add(eventType);
            return this;
        }

        private Builder<T> zza(int i) {
            Preconditions.checkState(this.zzc == 0, "Instantiation type has already been set.");
            this.zzc = i;
            return this;
        }

        @KeepForSdk
        public Builder<T> factory(ComponentFactory<T> value) {
            this.zzd = (ComponentFactory) Preconditions.checkNotNull(value, "Null factory");
            return this;
        }

        @KeepForSdk
        public Component<T> build() {
            Preconditions.checkState(this.zzd != null, "Missing required property: factory.");
            return new Component<>(new HashSet(this.zza), new HashSet(this.zzb), this.zzc, this.zzd, this.zze, (byte) 0);
        }
    }
}
