package com.google.firebase.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: com.google.firebase:firebase-common@@16.0.1 */
/* JADX INFO: loaded from: classes.dex */
final class zze {

    /* JADX INFO: compiled from: com.google.firebase:firebase-common@@16.0.1 */
    static class zza {
        private final Component<?> zza;
        private final Set<zza> zzb = new HashSet();
        private final Set<zza> zzc = new HashSet();

        zza(Component<?> component) {
            this.zza = component;
        }

        final void zza(zza zzaVar) {
            this.zzb.add(zzaVar);
        }

        final void zzb(zza zzaVar) {
            this.zzc.add(zzaVar);
        }

        final Set<zza> zza() {
            return this.zzb;
        }

        final void zzc(zza zzaVar) {
            this.zzc.remove(zzaVar);
        }

        final Component<?> zzb() {
            return this.zza;
        }

        final boolean zzc() {
            return this.zzc.isEmpty();
        }

        final boolean zzd() {
            return this.zzb.isEmpty();
        }
    }

    static List<Component<?>> zza(List<Component<?>> list) {
        zza zzaVar;
        HashMap map = new HashMap(list.size());
        for (Component<?> component : list) {
            zza zzaVar2 = new zza(component);
            for (Class<? super Object> cls : component.zza()) {
                if (map.put(cls, zzaVar2) != null) {
                    throw new IllegalArgumentException(String.format("Multiple components provide %s.", cls));
                }
            }
        }
        for (zza zzaVar3 : map.values()) {
            for (Dependency dependency : zzaVar3.zzb().zzb()) {
                if (dependency.zzc() && (zzaVar = (zza) map.get(dependency.zza())) != null) {
                    zzaVar3.zza(zzaVar);
                    zzaVar.zzb(zzaVar3);
                }
            }
        }
        HashSet<zza> hashSet = new HashSet(map.values());
        Set<zza> setZza = zza(hashSet);
        ArrayList arrayList = new ArrayList();
        while (!setZza.isEmpty()) {
            zza next = setZza.iterator().next();
            setZza.remove(next);
            arrayList.add(next.zzb());
            for (zza zzaVar4 : next.zza()) {
                zzaVar4.zzc(next);
                if (zzaVar4.zzc()) {
                    setZza.add(zzaVar4);
                }
            }
        }
        if (arrayList.size() == list.size()) {
            Collections.reverse(arrayList);
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        for (zza zzaVar5 : hashSet) {
            if (!zzaVar5.zzc() && !zzaVar5.zzd()) {
                arrayList2.add(zzaVar5.zzb());
            }
        }
        throw new DependencyCycleException(arrayList2);
    }

    private static Set<zza> zza(Set<zza> set) {
        HashSet hashSet = new HashSet();
        for (zza zzaVar : set) {
            if (zzaVar.zzc()) {
                hashSet.add(zzaVar);
            }
        }
        return hashSet;
    }
}
