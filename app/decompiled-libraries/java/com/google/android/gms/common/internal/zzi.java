package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
final class zzi implements ServiceConnection {
    private ComponentName mComponentName;
    private IBinder zzry;
    private boolean zztw;
    private final GmsClientSupervisor.ConnectionStatusConfig zztx;
    private final /* synthetic */ zzh zzty;
    private final Set<ServiceConnection> zztv = new HashSet();
    private int mState = 2;

    public zzi(zzh zzhVar, GmsClientSupervisor.ConnectionStatusConfig connectionStatusConfig) {
        this.zzty = zzhVar;
        this.zztx = connectionStatusConfig;
    }

    public final IBinder getBinder() {
        return this.zzry;
    }

    public final ComponentName getComponentName() {
        return this.mComponentName;
    }

    public final int getState() {
        return this.mState;
    }

    public final boolean isBound() {
        return this.zztw;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.zzty.zztr) {
            this.zzty.mHandler.removeMessages(1, this.zztx);
            this.zzry = iBinder;
            this.mComponentName = componentName;
            Iterator<ServiceConnection> it = this.zztv.iterator();
            while (it.hasNext()) {
                it.next().onServiceConnected(componentName, iBinder);
            }
            this.mState = 1;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.zzty.zztr) {
            this.zzty.mHandler.removeMessages(1, this.zztx);
            this.zzry = null;
            this.mComponentName = componentName;
            Iterator<ServiceConnection> it = this.zztv.iterator();
            while (it.hasNext()) {
                it.next().onServiceDisconnected(componentName);
            }
            this.mState = 2;
        }
    }

    public final void zza(ServiceConnection serviceConnection, String str) {
        this.zzty.zzts.logConnectService(this.zzty.zzau, serviceConnection, str, this.zztx.getStartServiceIntent(this.zzty.zzau));
        this.zztv.add(serviceConnection);
    }

    public final boolean zza(ServiceConnection serviceConnection) {
        return this.zztv.contains(serviceConnection);
    }

    public final void zzb(ServiceConnection serviceConnection, String str) {
        this.zzty.zzts.logDisconnectService(this.zzty.zzau, serviceConnection);
        this.zztv.remove(serviceConnection);
    }

    public final boolean zzcv() {
        return this.zztv.isEmpty();
    }

    public final void zzj(String str) {
        this.mState = 3;
        this.zztw = this.zzty.zzts.bindService(this.zzty.zzau, str, this.zztx.getStartServiceIntent(this.zzty.zzau), this, this.zztx.getBindFlags());
        if (this.zztw) {
            this.zzty.mHandler.sendMessageDelayed(this.zzty.mHandler.obtainMessage(1, this.zztx), this.zzty.zztu);
        } else {
            this.mState = 2;
            try {
                this.zzty.zzts.unbindService(this.zzty.zzau, this);
            } catch (IllegalArgumentException e) {
            }
        }
    }

    public final void zzk(String str) {
        this.zzty.mHandler.removeMessages(1, this.zztx);
        this.zzty.zzts.unbindService(this.zzty.zzau, this);
        this.zztw = false;
        this.mState = 2;
    }
}
