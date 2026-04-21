package com.google.android.gms.internal.measurement;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
final class zzuf implements Comparator<zzud> {
    zzuf() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzud zzudVar, zzud zzudVar2) {
        zzud zzudVar3 = zzudVar;
        zzud zzudVar4 = zzudVar2;
        zzuj zzujVar = (zzuj) zzudVar3.iterator();
        zzuj zzujVar2 = (zzuj) zzudVar4.iterator();
        while (zzujVar.hasNext() && zzujVar2.hasNext()) {
            int iCompare = Integer.compare(zzud.zza(zzujVar.nextByte()), zzud.zza(zzujVar2.nextByte()));
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return Integer.compare(zzudVar3.size(), zzudVar4.size());
    }
}
