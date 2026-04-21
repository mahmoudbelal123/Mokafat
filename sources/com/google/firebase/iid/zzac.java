package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
final class zzac implements ServiceConnection {

    @GuardedBy("this")
    int state;
    final Messenger zzbu;
    zzah zzbv;

    @GuardedBy("this")
    final Queue<zzaj<?>> zzbw;

    @GuardedBy("this")
    final SparseArray<zzaj<?>> zzbx;
    final /* synthetic */ zzaa zzby;

    private zzac(zzaa zzaaVar) {
        this.zzby = zzaaVar;
        this.state = 0;
        this.zzbu = new Messenger(new Handler(Looper.getMainLooper(), new Handler.Callback(this) { // from class: com.google.firebase.iid.zzad
            private final zzac zzbz;

            {
                this.zzbz = this;
            }

            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.zzbz.zza(message);
            }
        }));
        this.zzbw = new ArrayDeque();
        this.zzbx = new SparseArray<>();
    }

    final synchronized boolean zzb(zzaj zzajVar) {
        switch (this.state) {
            case 0:
                this.zzbw.add(zzajVar);
                Preconditions.checkState(this.state == 0);
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Starting bind to GmsCore");
                }
                this.state = 1;
                Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                intent.setPackage("com.google.android.gms");
                if (!ConnectionTracker.getInstance().bindService(this.zzby.zzv, intent, this, 1)) {
                    zza(0, "Unable to bind to service");
                } else {
                    this.zzby.zzbr.schedule(new Runnable(this) { // from class: com.google.firebase.iid.zzae
                        private final zzac zzbz;

                        {
                            this.zzbz = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            this.zzbz.zzz();
                        }
                    }, 30L, TimeUnit.SECONDS);
                }
                return true;
            case 1:
                this.zzbw.add(zzajVar);
                return true;
            case 2:
                this.zzbw.add(zzajVar);
                zzx();
                return true;
            case 3:
            case 4:
                return false;
            default:
                int i = this.state;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i);
                throw new IllegalStateException(sb.toString());
        }
    }

    final boolean zza(Message message) {
        int i = message.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Received response to request: ");
            sb.append(i);
            Log.d("MessengerIpcClient", sb.toString());
        }
        synchronized (this) {
            zzaj<?> zzajVar = this.zzbx.get(i);
            if (zzajVar == null) {
                StringBuilder sb2 = new StringBuilder(50);
                sb2.append("Received response for unknown request: ");
                sb2.append(i);
                Log.w("MessengerIpcClient", sb2.toString());
                return true;
            }
            this.zzbx.remove(i);
            zzy();
            Bundle data = message.getData();
            if (data.getBoolean("unsupported", false)) {
                zzajVar.zza(new zzak(4, "Not supported by GmsCore"));
            } else {
                zzajVar.zzb(data);
            }
            return true;
        }
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        if (iBinder == null) {
            zza(0, "Null service connection");
            return;
        }
        try {
            this.zzbv = new zzah(iBinder);
            this.state = 2;
            zzx();
        } catch (RemoteException e) {
            zza(0, e.getMessage());
        }
    }

    private final void zzx() {
        this.zzby.zzbr.execute(new Runnable(this) { // from class: com.google.firebase.iid.zzaf
            private final zzac zzbz;

            {
                this.zzbz = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                final zzaj<?> zzajVarPoll;
                final zzac zzacVar = this.zzbz;
                while (true) {
                    synchronized (zzacVar) {
                        if (zzacVar.state != 2) {
                            return;
                        }
                        if (zzacVar.zzbw.isEmpty()) {
                            zzacVar.zzy();
                            return;
                        } else {
                            zzajVarPoll = zzacVar.zzbw.poll();
                            zzacVar.zzbx.put(zzajVarPoll.zzcc, zzajVarPoll);
                            zzacVar.zzby.zzbr.schedule(new Runnable(zzacVar, zzajVarPoll) { // from class: com.google.firebase.iid.zzag
                                private final zzac zzbz;
                                private final zzaj zzca;

                                {
                                    this.zzbz = zzacVar;
                                    this.zzca = zzajVarPoll;
                                }

                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.zzbz.zza(this.zzca.zzcc);
                                }
                            }, 30L, TimeUnit.SECONDS);
                        }
                    }
                    if (Log.isLoggable("MessengerIpcClient", 3)) {
                        String strValueOf = String.valueOf(zzajVarPoll);
                        StringBuilder sb = new StringBuilder(8 + String.valueOf(strValueOf).length());
                        sb.append("Sending ");
                        sb.append(strValueOf);
                        Log.d("MessengerIpcClient", sb.toString());
                    }
                    Context context = zzacVar.zzby.zzv;
                    Messenger messenger = zzacVar.zzbu;
                    Message messageObtain = Message.obtain();
                    messageObtain.what = zzajVarPoll.what;
                    messageObtain.arg1 = zzajVarPoll.zzcc;
                    messageObtain.replyTo = messenger;
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("oneWay", zzajVarPoll.zzaa());
                    bundle.putString("pkg", context.getPackageName());
                    bundle.putBundle(DataBufferSafeParcelable.DATA_FIELD, zzajVarPoll.zzce);
                    messageObtain.setData(bundle);
                    try {
                        zzacVar.zzbv.send(messageObtain);
                    } catch (RemoteException e) {
                        zzacVar.zza(2, e.getMessage());
                    }
                }
            }
        });
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        zza(2, "Service disconnected");
    }

    final synchronized void zza(int i, String str) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String strValueOf = String.valueOf(str);
            Log.d("MessengerIpcClient", strValueOf.length() != 0 ? "Disconnected: ".concat(strValueOf) : new String("Disconnected: "));
        }
        switch (this.state) {
            case 0:
                throw new IllegalStateException();
            case 1:
            case 2:
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Unbinding service");
                }
                this.state = 4;
                ConnectionTracker.getInstance().unbindService(this.zzby.zzv, this);
                zzak zzakVar = new zzak(i, str);
                Iterator<zzaj<?>> it = this.zzbw.iterator();
                while (it.hasNext()) {
                    it.next().zza(zzakVar);
                }
                this.zzbw.clear();
                for (int i2 = 0; i2 < this.zzbx.size(); i2++) {
                    this.zzbx.valueAt(i2).zza(zzakVar);
                }
                this.zzbx.clear();
                return;
            case 3:
                this.state = 4;
                return;
            case 4:
                return;
            default:
                int i3 = this.state;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i3);
                throw new IllegalStateException(sb.toString());
        }
    }

    final synchronized void zzy() {
        if (this.state == 2 && this.zzbw.isEmpty() && this.zzbx.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.state = 3;
            ConnectionTracker.getInstance().unbindService(this.zzby.zzv, this);
        }
    }

    final synchronized void zzz() {
        if (this.state == 1) {
            zza(1, "Timed out while binding");
        }
    }

    final synchronized void zza(int i) {
        zzaj<?> zzajVar = this.zzbx.get(i);
        if (zzajVar != null) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Timing out request: ");
            sb.append(i);
            Log.w("MessengerIpcClient", sb.toString());
            this.zzbx.remove(i);
            zzajVar.zza(new zzak(3, "Timed out waiting for response"));
            zzy();
        }
    }
}
