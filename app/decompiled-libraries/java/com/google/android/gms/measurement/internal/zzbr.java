package com.google.android.gms.measurement.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
final class zzbr<V> extends FutureTask<V> implements Comparable<zzbr> {
    private final String zzapf;
    private final /* synthetic */ zzbo zzapg;
    private final long zzaph;
    final boolean zzapi;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbr(zzbo zzboVar, Callable<V> callable, boolean z, String str) {
        super(callable);
        this.zzapg = zzboVar;
        Preconditions.checkNotNull(str);
        this.zzaph = zzbo.zzape.getAndIncrement();
        this.zzapf = str;
        this.zzapi = z;
        if (this.zzaph == LongCompanionObject.MAX_VALUE) {
            zzboVar.zzgo().zzjd().zzbx("Tasks index overflow");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbr(zzbo zzboVar, Runnable runnable, boolean z, String str) {
        super(runnable, null);
        this.zzapg = zzboVar;
        Preconditions.checkNotNull(str);
        this.zzaph = zzbo.zzape.getAndIncrement();
        this.zzapf = str;
        this.zzapi = false;
        if (this.zzaph == LongCompanionObject.MAX_VALUE) {
            zzboVar.zzgo().zzjd().zzbx("Tasks index overflow");
        }
    }

    @Override // java.util.concurrent.FutureTask
    protected final void setException(Throwable th) {
        this.zzapg.zzgo().zzjd().zzg(this.zzapf, th);
        if (th instanceof zzbp) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(@NonNull zzbr zzbrVar) {
        zzbr zzbrVar2 = zzbrVar;
        if (this.zzapi != zzbrVar2.zzapi) {
            return this.zzapi ? -1 : 1;
        }
        if (this.zzaph < zzbrVar2.zzaph) {
            return -1;
        }
        if (this.zzaph > zzbrVar2.zzaph) {
            return 1;
        }
        this.zzapg.zzgo().zzje().zzg("Two tasks share the same index. index", Long.valueOf(this.zzaph));
        return 0;
    }
}
