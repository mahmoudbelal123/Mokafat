package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
final class zzr<TResult> {
    private final Object mLock = new Object();

    @GuardedBy("mLock")
    private Queue<zzq<TResult>> zzaga;

    @GuardedBy("mLock")
    private boolean zzagb;

    zzr() {
    }

    public final void zza(@NonNull Task<TResult> task) {
        zzq<TResult> zzqVarPoll;
        synchronized (this.mLock) {
            if (this.zzaga != null && !this.zzagb) {
                this.zzagb = true;
                while (true) {
                    synchronized (this.mLock) {
                        zzqVarPoll = this.zzaga.poll();
                        if (zzqVarPoll == null) {
                            this.zzagb = false;
                            return;
                        }
                    }
                    zzqVarPoll.onComplete(task);
                }
            }
        }
    }

    public final void zza(@NonNull zzq<TResult> zzqVar) {
        synchronized (this.mLock) {
            if (this.zzaga == null) {
                this.zzaga = new ArrayDeque();
            }
            this.zzaga.add(zzqVar);
        }
    }
}
