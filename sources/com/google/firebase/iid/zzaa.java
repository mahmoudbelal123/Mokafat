package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
public final class zzaa {

    @GuardedBy("MessengerIpcClient.class")
    private static zzaa zzbq;
    private final ScheduledExecutorService zzbr;

    @GuardedBy("this")
    private zzac zzbs = new zzac(this);

    @GuardedBy("this")
    private int zzbt = 1;
    private final Context zzv;

    public static synchronized zzaa zzc(Context context) {
        if (zzbq == null) {
            zzbq = new zzaa(context, Executors.newSingleThreadScheduledExecutor());
        }
        return zzbq;
    }

    @VisibleForTesting
    private zzaa(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzbr = scheduledExecutorService;
        this.zzv = context.getApplicationContext();
    }

    public final Task<Void> zza(int i, Bundle bundle) {
        return zza(new zzai(zzw(), 2, bundle));
    }

    public final Task<Bundle> zzb(int i, Bundle bundle) {
        return zza(new zzal(zzw(), 1, bundle));
    }

    private final synchronized <T> Task<T> zza(zzaj<T> zzajVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String strValueOf = String.valueOf(zzajVar);
            StringBuilder sb = new StringBuilder(9 + String.valueOf(strValueOf).length());
            sb.append("Queueing ");
            sb.append(strValueOf);
            Log.d("MessengerIpcClient", sb.toString());
        }
        if (!this.zzbs.zzb(zzajVar)) {
            this.zzbs = new zzac(this);
            this.zzbs.zzb(zzajVar);
        }
        return zzajVar.zzcd.getTask();
    }

    private final synchronized int zzw() {
        int i;
        i = this.zzbt;
        this.zzbt = i + 1;
        return i;
    }
}
