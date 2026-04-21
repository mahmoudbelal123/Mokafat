package com.google.firebase.iid;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzb extends Service {
    private final Object lock;

    @VisibleForTesting
    final ExecutorService zzh;
    private Binder zzi;
    private int zzj;
    private int zzk;

    public zzb() {
        String strValueOf = String.valueOf(getClass().getSimpleName());
        this.zzh = Executors.newSingleThreadExecutor(new NamedThreadFactory(strValueOf.length() != 0 ? "Firebase-".concat(strValueOf) : new String("Firebase-")));
        this.lock = new Object();
        this.zzk = 0;
    }

    public abstract void zzd(Intent intent);

    @Override // android.app.Service
    public final synchronized IBinder onBind(Intent intent) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "Service received bind request");
        }
        if (this.zzi == null) {
            this.zzi = new zzf(this);
        }
        return this.zzi;
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        synchronized (this.lock) {
            this.zzj = i2;
            this.zzk++;
        }
        Intent intentZzb = zzb(intent);
        if (intentZzb == null) {
            zza(intent);
            return 2;
        }
        if (zzc(intentZzb)) {
            zza(intent);
            return 2;
        }
        this.zzh.execute(new zzc(this, intentZzb, intent));
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(Intent intent) {
        if (intent != null) {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
        synchronized (this.lock) {
            this.zzk--;
            if (this.zzk == 0) {
                stopSelfResult(this.zzj);
            }
        }
    }

    protected Intent zzb(Intent intent) {
        return intent;
    }

    public boolean zzc(Intent intent) {
        return false;
    }
}
