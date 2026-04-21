package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class zzwf extends zzwd {
    private static final Class<?> zzcal = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzwf() {
        super();
    }

    @Override // com.google.android.gms.internal.measurement.zzwd
    final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    @Override // com.google.android.gms.internal.measurement.zzwd
    final void zzb(Object obj, long j) {
        Object objUnmodifiableList;
        List list = (List) zzyh.zzp(obj, j);
        if (list instanceof zzwc) {
            objUnmodifiableList = ((zzwc) list).zzww();
        } else {
            if (zzcal.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzxe) && (list instanceof zzvs)) {
                zzvs zzvsVar = (zzvs) list;
                if (zzvsVar.zztw()) {
                    zzvsVar.zzsm();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zzyh.zza(obj, j, objUnmodifiableList);
    }

    private static <L> List<L> zza(Object obj, long j, int i) {
        Object obj2;
        List<L> arrayList;
        List<L> listZzc = zzc(obj, j);
        if (listZzc.isEmpty()) {
            if (listZzc instanceof zzwc) {
                arrayList = new zzwb(i);
            } else if ((listZzc instanceof zzxe) && (listZzc instanceof zzvs)) {
                arrayList = ((zzvs) listZzc).zzak(i);
            } else {
                arrayList = new ArrayList<>(i);
            }
            zzyh.zza(obj, j, arrayList);
            return arrayList;
        }
        if (zzcal.isAssignableFrom(listZzc.getClass())) {
            ArrayList arrayList2 = new ArrayList(listZzc.size() + i);
            arrayList2.addAll(listZzc);
            zzyh.zza(obj, j, arrayList2);
            obj2 = arrayList2;
        } else {
            if (!(listZzc instanceof zzye)) {
                if ((listZzc instanceof zzxe) && (listZzc instanceof zzvs)) {
                    zzvs zzvsVar = (zzvs) listZzc;
                    if (!zzvsVar.zztw()) {
                        zzvs zzvsVarZzak = zzvsVar.zzak(listZzc.size() + i);
                        zzyh.zza(obj, j, zzvsVarZzak);
                        return zzvsVarZzak;
                    }
                    return listZzc;
                }
                return listZzc;
            }
            zzwb zzwbVar = new zzwb(listZzc.size() + i);
            zzwbVar.addAll((zzye) listZzc);
            zzyh.zza(obj, j, zzwbVar);
            obj2 = zzwbVar;
        }
        return (List<L>) obj2;
    }

    @Override // com.google.android.gms.internal.measurement.zzwd
    final <E> void zza(Object obj, Object obj2, long j) {
        List listZzc = zzc(obj2, j);
        List listZza = zza(obj, j, listZzc.size());
        int size = listZza.size();
        int size2 = listZzc.size();
        if (size > 0 && size2 > 0) {
            listZza.addAll(listZzc);
        }
        if (size > 0) {
            listZzc = listZza;
        }
        zzyh.zza(obj, j, listZzc);
    }

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzyh.zzp(obj, j);
    }
}
