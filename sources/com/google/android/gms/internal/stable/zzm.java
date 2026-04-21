package com.google.android.gms.internal.stable;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
final class zzm {
    private final ConcurrentHashMap<zzn, List<Throwable>> zzahj = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zzahk = new ReferenceQueue<>();

    zzm() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        while (true) {
            Reference<? extends Throwable> referencePoll = this.zzahk.poll();
            if (referencePoll == null) {
                break;
            }
            this.zzahj.remove(referencePoll);
        }
        List<Throwable> list = this.zzahj.get(new zzn(th, null));
        if (list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        List<Throwable> listPutIfAbsent = this.zzahj.putIfAbsent(new zzn(th, this.zzahk), vector);
        return listPutIfAbsent == null ? vector : listPutIfAbsent;
    }
}
