package com.google.firebase.iid;

import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
final class zzap {

    @GuardedBy("this")
    private final Map<Pair<String, String>, Task<String>> zzcl = new ArrayMap();

    zzap() {
    }

    final synchronized Task<String> zza(String str, String str2, zzar zzarVar) {
        final Pair<String, String> pair = new Pair<>(str, str2);
        Task<String> task = this.zzcl.get(pair);
        if (task != null) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String strValueOf = String.valueOf(pair);
                StringBuilder sb = new StringBuilder(29 + String.valueOf(strValueOf).length());
                sb.append("Joining ongoing request for: ");
                sb.append(strValueOf);
                Log.d("FirebaseInstanceId", sb.toString());
            }
            return task;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String strValueOf2 = String.valueOf(pair);
            StringBuilder sb2 = new StringBuilder(24 + String.valueOf(strValueOf2).length());
            sb2.append("Making new request for: ");
            sb2.append(strValueOf2);
            Log.d("FirebaseInstanceId", sb2.toString());
        }
        Task taskContinueWithTask = zzarVar.zzr().continueWithTask(zzi.zzd(), new Continuation(this, pair) { // from class: com.google.firebase.iid.zzaq
            private final zzap zzcm;
            private final Pair zzcn;

            {
                this.zzcm = this;
                this.zzcn = pair;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task2) {
                return this.zzcm.zza(this.zzcn, task2);
            }
        });
        this.zzcl.put(pair, (Task<String>) taskContinueWithTask);
        return taskContinueWithTask;
    }

    final /* synthetic */ Task zza(Pair pair, Task task) throws Exception {
        synchronized (this) {
            this.zzcl.remove(pair);
        }
        return task;
    }
}
