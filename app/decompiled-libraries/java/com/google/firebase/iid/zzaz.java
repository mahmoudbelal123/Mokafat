package com.google.firebase.iid;

import android.support.annotation.GuardedBy;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class zzaz {

    @GuardedBy("itself")
    private final zzav zzag;

    @GuardedBy("this")
    private int zzdi = 0;

    @GuardedBy("this")
    private final Map<Integer, TaskCompletionSource<Void>> zzdj = new ArrayMap();

    zzaz(zzav zzavVar) {
        this.zzag = zzavVar;
    }

    final synchronized Task<Void> zza(String str) {
        String strZzaj;
        TaskCompletionSource<Void> taskCompletionSource;
        synchronized (this.zzag) {
            strZzaj = this.zzag.zzaj();
            zzav zzavVar = this.zzag;
            StringBuilder sb = new StringBuilder(String.valueOf(strZzaj).length() + 1 + String.valueOf(str).length());
            sb.append(strZzaj);
            sb.append(",");
            sb.append(str);
            zzavVar.zzf(sb.toString());
        }
        taskCompletionSource = new TaskCompletionSource<>();
        this.zzdj.put(Integer.valueOf(this.zzdi + (TextUtils.isEmpty(strZzaj) ? 0 : strZzaj.split(",").length - 1)), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    final synchronized boolean zzap() {
        return zzaq() != null;
    }

    @WorkerThread
    final boolean zzc(FirebaseInstanceId firebaseInstanceId) {
        TaskCompletionSource<Void> taskCompletionSourceRemove;
        while (true) {
            synchronized (this) {
                String strZzaq = zzaq();
                if (strZzaq == null) {
                    Log.d("FirebaseInstanceId", "topic sync succeeded");
                    return true;
                }
                if (!zza(firebaseInstanceId, strZzaq)) {
                    return false;
                }
                synchronized (this) {
                    taskCompletionSourceRemove = this.zzdj.remove(Integer.valueOf(this.zzdi));
                    zzk(strZzaq);
                    this.zzdi++;
                }
                if (taskCompletionSourceRemove != null) {
                    taskCompletionSourceRemove.setResult(null);
                }
            }
        }
    }

    @GuardedBy("this")
    @Nullable
    private final String zzaq() {
        String strZzaj;
        synchronized (this.zzag) {
            strZzaj = this.zzag.zzaj();
        }
        if (!TextUtils.isEmpty(strZzaj)) {
            String[] strArrSplit = strZzaj.split(",");
            if (strArrSplit.length > 1 && !TextUtils.isEmpty(strArrSplit[1])) {
                return strArrSplit[1];
            }
            return null;
        }
        return null;
    }

    private final synchronized boolean zzk(String str) {
        synchronized (this.zzag) {
            String strZzaj = this.zzag.zzaj();
            String strValueOf = String.valueOf(",");
            String strValueOf2 = String.valueOf(str);
            if (strZzaj.startsWith(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf))) {
                String strValueOf3 = String.valueOf(",");
                String strValueOf4 = String.valueOf(str);
                this.zzag.zzf(strZzaj.substring((strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3)).length()));
                return true;
            }
            return false;
        }
    }

    @WorkerThread
    private static boolean zza(FirebaseInstanceId firebaseInstanceId, String str) {
        String[] strArrSplit = str.split("!");
        if (strArrSplit.length == 2) {
            String str2 = strArrSplit[0];
            String str3 = strArrSplit[1];
            byte b = -1;
            try {
                int iHashCode = str2.hashCode();
                if (iHashCode != 83) {
                    if (iHashCode == 85 && str2.equals("U")) {
                        b = 1;
                    }
                } else if (str2.equals("S")) {
                    b = 0;
                }
                switch (b) {
                    case 0:
                        firebaseInstanceId.zzb(str3);
                        if (FirebaseInstanceId.zzk()) {
                            Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                        }
                        break;
                    case 1:
                        firebaseInstanceId.zzc(str3);
                        if (FirebaseInstanceId.zzk()) {
                            Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
                        }
                        break;
                }
            } catch (IOException e) {
                String strValueOf = String.valueOf(e.getMessage());
                Log.e("FirebaseInstanceId", strValueOf.length() != 0 ? "Topic sync failed: ".concat(strValueOf) : new String("Topic sync failed: "));
                return false;
            }
        }
        return true;
    }
}
