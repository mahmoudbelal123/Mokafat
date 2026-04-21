package com.google.android.gms.internal.firebase_messaging;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
final class zzc {
    private final ConcurrentHashMap<zzd, List<Throwable>> zzd = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zze = new ReferenceQueue<>();

    zzc() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        Reference<? extends Throwable> referencePoll = this.zze.poll();
        while (referencePoll != null) {
            this.zzd.remove(referencePoll);
            referencePoll = this.zze.poll();
        }
        List<Throwable> list = this.zzd.get(new zzd(th, null));
        if (list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        List<Throwable> listPutIfAbsent = this.zzd.putIfAbsent(new zzd(th, this.zze), vector);
        return listPutIfAbsent == null ? vector : listPutIfAbsent;
    }
}
