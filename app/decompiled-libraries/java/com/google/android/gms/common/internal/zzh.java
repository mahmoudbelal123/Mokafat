package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Message;
import android.support.v4.os.EnvironmentCompat;
import android.util.Log;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
final class zzh extends GmsClientSupervisor implements Handler.Callback {
    private final Handler mHandler;
    private final Context zzau;

    @GuardedBy("mConnectionStatus")
    private final HashMap<GmsClientSupervisor.ConnectionStatusConfig, zzi> zztr = new HashMap<>();
    private final ConnectionTracker zzts = ConnectionTracker.getInstance();
    private final long zztt = 5000;
    private final long zztu = 300000;

    zzh(Context context) {
        this.zzau = context.getApplicationContext();
        this.mHandler = new Handler(context.getMainLooper(), this);
    }

    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    protected final boolean bindService(GmsClientSupervisor.ConnectionStatusConfig connectionStatusConfig, ServiceConnection serviceConnection, String str) {
        boolean zIsBound;
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zztr) {
            zzi zziVar = this.zztr.get(connectionStatusConfig);
            if (zziVar != null) {
                this.mHandler.removeMessages(0, connectionStatusConfig);
                if (!zziVar.zza(serviceConnection)) {
                    zziVar.zza(serviceConnection, str);
                    switch (zziVar.getState()) {
                        case 1:
                            serviceConnection.onServiceConnected(zziVar.getComponentName(), zziVar.getBinder());
                            break;
                        case 2:
                            zziVar.zzj(str);
                            break;
                    }
                } else {
                    String strValueOf = String.valueOf(connectionStatusConfig);
                    StringBuilder sb = new StringBuilder(81 + String.valueOf(strValueOf).length());
                    sb.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");
                    sb.append(strValueOf);
                    throw new IllegalStateException(sb.toString());
                }
            } else {
                zziVar = new zzi(this, connectionStatusConfig);
                zziVar.zza(serviceConnection, str);
                zziVar.zzj(str);
                this.zztr.put(connectionStatusConfig, zziVar);
            }
            zIsBound = zziVar.isBound();
        }
        return zIsBound;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                synchronized (this.zztr) {
                    GmsClientSupervisor.ConnectionStatusConfig connectionStatusConfig = (GmsClientSupervisor.ConnectionStatusConfig) message.obj;
                    zzi zziVar = this.zztr.get(connectionStatusConfig);
                    if (zziVar != null && zziVar.zzcv()) {
                        if (zziVar.isBound()) {
                            zziVar.zzk("GmsClientSupervisor");
                        }
                        this.zztr.remove(connectionStatusConfig);
                    }
                    break;
                }
                return true;
            case 1:
                synchronized (this.zztr) {
                    GmsClientSupervisor.ConnectionStatusConfig connectionStatusConfig2 = (GmsClientSupervisor.ConnectionStatusConfig) message.obj;
                    zzi zziVar2 = this.zztr.get(connectionStatusConfig2);
                    if (zziVar2 != null && zziVar2.getState() == 3) {
                        String strValueOf = String.valueOf(connectionStatusConfig2);
                        StringBuilder sb = new StringBuilder(47 + String.valueOf(strValueOf).length());
                        sb.append("Timeout waiting for ServiceConnection callback ");
                        sb.append(strValueOf);
                        Log.wtf("GmsClientSupervisor", sb.toString(), new Exception());
                        ComponentName componentName = zziVar2.getComponentName();
                        if (componentName == null) {
                            componentName = connectionStatusConfig2.getComponentName();
                        }
                        if (componentName == null) {
                            componentName = new ComponentName(connectionStatusConfig2.getPackage(), EnvironmentCompat.MEDIA_UNKNOWN);
                        }
                        zziVar2.onServiceDisconnected(componentName);
                    }
                    break;
                }
                return true;
            default:
                return false;
        }
    }

    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    @VisibleForTesting
    public final void resetForTesting() {
        synchronized (this.zztr) {
            for (zzi zziVar : this.zztr.values()) {
                this.mHandler.removeMessages(0, zziVar.zztx);
                if (zziVar.isBound()) {
                    zziVar.zzk("GmsClientSupervisor");
                }
            }
            this.zztr.clear();
        }
    }

    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    protected final void unbindService(GmsClientSupervisor.ConnectionStatusConfig connectionStatusConfig, ServiceConnection serviceConnection, String str) {
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zztr) {
            zzi zziVar = this.zztr.get(connectionStatusConfig);
            if (zziVar == null) {
                String strValueOf = String.valueOf(connectionStatusConfig);
                StringBuilder sb = new StringBuilder(50 + String.valueOf(strValueOf).length());
                sb.append("Nonexistent connection status for service config: ");
                sb.append(strValueOf);
                throw new IllegalStateException(sb.toString());
            }
            if (!zziVar.zza(serviceConnection)) {
                String strValueOf2 = String.valueOf(connectionStatusConfig);
                StringBuilder sb2 = new StringBuilder(76 + String.valueOf(strValueOf2).length());
                sb2.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");
                sb2.append(strValueOf2);
                throw new IllegalStateException(sb2.toString());
            }
            zziVar.zzb(serviceConnection, str);
            if (zziVar.zzcv()) {
                this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, connectionStatusConfig), this.zztt);
            }
        }
    }
}
