package com.google.android.gms.internal.measurement;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
final class zzsy {
    private final ConcurrentHashMap<zzsz, List<Throwable>> zzbrw = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zzbrx = new ReferenceQueue<>();

    zzsy() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        Reference<? extends Throwable> referencePoll = this.zzbrx.poll();
        while (referencePoll != null) {
            this.zzbrw.remove(referencePoll);
            referencePoll = this.zzbrx.poll();
        }
        return this.zzbrw.get(new zzsz(th, null));
    }
}
