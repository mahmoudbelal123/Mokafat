package com.google.firebase.components;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.firebase:firebase-common@@16.0.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzd implements ComponentContainer {
    private final List<Component<?>> zza;
    private final Map<Class<?>, zzh<?>> zzb = new HashMap();
    private final zzf zzc;

    @Override // com.google.firebase.components.ComponentContainer
    public final Object get(Class cls) {
        return ComponentContainer$$CC.get(this, cls);
    }

    public zzd(Executor executor, Iterable<ComponentRegistrar> iterable, Component<?>... componentArr) {
        this.zzc = new zzf(executor);
        ArrayList arrayList = new ArrayList();
        arrayList.add(Component.of(this.zzc, zzf.class, Subscriber.class, Publisher.class));
        Iterator<ComponentRegistrar> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next().getComponents());
        }
        Collections.addAll(arrayList, componentArr);
        this.zza = Collections.unmodifiableList(zze.zza(arrayList));
        Iterator<Component<?>> it2 = this.zza.iterator();
        while (it2.hasNext()) {
            zza(it2.next());
        }
        zza();
    }

    @Override // com.google.firebase.components.ComponentContainer
    public final <T> Provider<T> getProvider(Class<T> anInterface) {
        Preconditions.checkNotNull(anInterface, "Null interface requested.");
        return this.zzb.get(anInterface);
    }

    public final void zza(boolean z) {
        for (Component<?> component : this.zza) {
            if (component.zze() || (component.zzf() && z)) {
                get(component.zza().iterator().next());
            }
        }
        this.zzc.zza();
    }

    private <T> void zza(Component<T> component) {
        zzh<?> zzhVar = new zzh<>(component.zzc(), new zzj(component, this));
        Iterator<Class<? super T>> it = component.zza().iterator();
        while (it.hasNext()) {
            this.zzb.put(it.next(), zzhVar);
        }
    }

    private void zza() {
        for (Component<?> component : this.zza) {
            for (Dependency dependency : component.zzb()) {
                if (dependency.zzb() && !this.zzb.containsKey(dependency.zza())) {
                    throw new MissingDependencyException(String.format("Unsatisfied dependency for component %s: %s", component, dependency.zza()));
                }
            }
        }
    }
}
