package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class zzy implements OnCompleteListener<Map<zzh<?>, String>> {
    private final /* synthetic */ zzw zzgu;

    private zzy(zzw zzwVar) {
        this.zzgu = zzwVar;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(@NonNull Task<Map<zzh<?>, String>> task) {
        zzw zzwVar;
        ConnectionResult connectionResult;
        Map map;
        this.zzgu.zzga.lock();
        try {
            if (this.zzgu.zzgp) {
                if (task.isSuccessful()) {
                    this.zzgu.zzgq = new ArrayMap(this.zzgu.zzgg.size());
                    Iterator it = this.zzgu.zzgg.values().iterator();
                    while (it.hasNext()) {
                        this.zzgu.zzgq.put(((zzv) it.next()).zzm(), ConnectionResult.RESULT_SUCCESS);
                    }
                } else {
                    if (task.getException() instanceof AvailabilityException) {
                        AvailabilityException availabilityException = (AvailabilityException) task.getException();
                        if (this.zzgu.zzgn) {
                            this.zzgu.zzgq = new ArrayMap(this.zzgu.zzgg.size());
                            for (zzv zzvVar : this.zzgu.zzgg.values()) {
                                Object objZzm = zzvVar.zzm();
                                ConnectionResult connectionResult2 = availabilityException.getConnectionResult(zzvVar);
                                if (this.zzgu.zza((zzv<?>) zzvVar, connectionResult2)) {
                                    map = this.zzgu.zzgq;
                                    connectionResult2 = new ConnectionResult(16);
                                } else {
                                    map = this.zzgu.zzgq;
                                }
                                map.put(objZzm, connectionResult2);
                            }
                        } else {
                            this.zzgu.zzgq = availabilityException.zzl();
                        }
                        zzwVar = this.zzgu;
                        connectionResult = this.zzgu.zzai();
                    } else {
                        Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                        this.zzgu.zzgq = Collections.emptyMap();
                        zzwVar = this.zzgu;
                        connectionResult = new ConnectionResult(8);
                    }
                    zzwVar.zzgt = connectionResult;
                }
                if (this.zzgu.zzgr != null) {
                    this.zzgu.zzgq.putAll(this.zzgu.zzgr);
                    this.zzgu.zzgt = this.zzgu.zzai();
                }
                if (this.zzgu.zzgt == null) {
                    this.zzgu.zzag();
                    this.zzgu.zzah();
                } else {
                    zzw.zza(this.zzgu, false);
                    this.zzgu.zzgj.zzc(this.zzgu.zzgt);
                }
                this.zzgu.zzgl.signalAll();
            }
        } finally {
            this.zzgu.zzga.unlock();
        }
    }
}
