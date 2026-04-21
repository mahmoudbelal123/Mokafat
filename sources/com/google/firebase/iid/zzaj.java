package com.google.firebase.iid;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: classes.dex */
abstract class zzaj<T> {
    final int what;
    final int zzcc;
    final TaskCompletionSource<T> zzcd = new TaskCompletionSource<>();
    final Bundle zzce;

    zzaj(int i, int i2, Bundle bundle) {
        this.zzcc = i;
        this.what = i2;
        this.zzce = bundle;
    }

    abstract boolean zzaa();

    abstract void zzb(Bundle bundle);

    final void finish(T t) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String strValueOf = String.valueOf(this);
            String strValueOf2 = String.valueOf(t);
            StringBuilder sb = new StringBuilder(16 + String.valueOf(strValueOf).length() + String.valueOf(strValueOf2).length());
            sb.append("Finishing ");
            sb.append(strValueOf);
            sb.append(" with ");
            sb.append(strValueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.zzcd.setResult(t);
    }

    final void zza(zzak zzakVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String strValueOf = String.valueOf(this);
            String strValueOf2 = String.valueOf(zzakVar);
            StringBuilder sb = new StringBuilder(14 + String.valueOf(strValueOf).length() + String.valueOf(strValueOf2).length());
            sb.append("Failing ");
            sb.append(strValueOf);
            sb.append(" with ");
            sb.append(strValueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.zzcd.setException(zzakVar);
    }

    public String toString() {
        int i = this.what;
        int i2 = this.zzcc;
        boolean zZzaa = zzaa();
        StringBuilder sb = new StringBuilder(55);
        sb.append("Request { what=");
        sb.append(i);
        sb.append(" id=");
        sb.append(i2);
        sb.append(" oneWay=");
        sb.append(zZzaa);
        sb.append("}");
        return sb.toString();
    }
}
