package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public final class zzef implements ServiceConnection, BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    final /* synthetic */ zzdr zzasg;
    private volatile boolean zzasm;
    private volatile zzao zzasn;

    protected zzef(zzdr zzdrVar) {
        this.zzasg = zzdrVar;
    }

    @WorkerThread
    public final void zzc(Intent intent) {
        this.zzasg.zzaf();
        Context context = this.zzasg.getContext();
        ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
        synchronized (this) {
            if (this.zzasm) {
                this.zzasg.zzgo().zzjl().zzbx("Connection attempt already in progress");
                return;
            }
            this.zzasg.zzgo().zzjl().zzbx("Using local app measurement service");
            this.zzasm = true;
            connectionTracker.bindService(context, intent, this.zzasg.zzarz, GmsClientSupervisor.DEFAULT_BIND_FLAGS);
        }
    }

    @WorkerThread
    public final void zzlg() {
        if (this.zzasn != null && (this.zzasn.isConnected() || this.zzasn.isConnecting())) {
            this.zzasn.disconnect();
        }
        this.zzasn = null;
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzag zzaiVar;
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceConnected");
        synchronized (this) {
            if (iBinder == null) {
                this.zzasm = false;
                this.zzasg.zzgo().zzjd().zzbx("Service connected with null binder");
                return;
            }
            zzag zzagVar = null;
            try {
                String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                if ("com.google.android.gms.measurement.internal.IMeasurementService".equals(interfaceDescriptor)) {
                    if (iBinder != null) {
                        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                        if (iInterfaceQueryLocalInterface instanceof zzag) {
                            zzaiVar = (zzag) iInterfaceQueryLocalInterface;
                        } else {
                            zzaiVar = new zzai(iBinder);
                        }
                        zzagVar = zzaiVar;
                    }
                    this.zzasg.zzgo().zzjl().zzbx("Bound to IMeasurementService interface");
                } else {
                    this.zzasg.zzgo().zzjd().zzg("Got binder with a wrong descriptor", interfaceDescriptor);
                }
            } catch (RemoteException e) {
                this.zzasg.zzgo().zzjd().zzbx("Service connect failed to get IMeasurementService");
            }
            if (zzagVar == null) {
                this.zzasm = false;
                try {
                    ConnectionTracker.getInstance().unbindService(this.zzasg.getContext(), this.zzasg.zzarz);
                } catch (IllegalArgumentException e2) {
                }
            } else {
                this.zzasg.zzgn().zzc(new zzeg(this, zzagVar));
            }
        }
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceDisconnected");
        this.zzasg.zzgo().zzjk().zzbx("Service disconnected");
        this.zzasg.zzgn().zzc(new zzeh(this, componentName));
    }

    @WorkerThread
    public final void zzlh() {
        this.zzasg.zzaf();
        Context context = this.zzasg.getContext();
        synchronized (this) {
            if (this.zzasm) {
                this.zzasg.zzgo().zzjl().zzbx("Connection attempt already in progress");
                return;
            }
            if (this.zzasn != null && (!zzn.zzia() || this.zzasn.isConnecting() || this.zzasn.isConnected())) {
                this.zzasg.zzgo().zzjl().zzbx("Already awaiting connection attempt");
                return;
            }
            this.zzasn = new zzao(context, Looper.getMainLooper(), this, this);
            this.zzasg.zzgo().zzjl().zzbx("Connecting to remote service");
            this.zzasm = true;
            this.zzasn.checkAvailabilityAndConnect();
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    @MainThread
    public final void onConnected(@Nullable Bundle bundle) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnected");
        synchronized (this) {
            try {
                zzag service = this.zzasn.getService();
                if (!zzn.zzia()) {
                    this.zzasn = null;
                }
                this.zzasg.zzgn().zzc(new zzei(this, service));
            } catch (DeadObjectException | IllegalStateException e) {
                this.zzasn = null;
                this.zzasm = false;
            }
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    @MainThread
    public final void onConnectionSuspended(int i) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionSuspended");
        this.zzasg.zzgo().zzjk().zzbx("Service connection suspended");
        this.zzasg.zzgn().zzc(new zzej(this));
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    @MainThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionFailed");
        zzap zzapVarZzkf = this.zzasg.zzadj.zzkf();
        if (zzapVarZzkf != null) {
            zzapVarZzkf.zzjg().zzg("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.zzasm = false;
            this.zzasn = null;
        }
        this.zzasg.zzgn().zzc(new zzek(this));
    }

    static /* synthetic */ boolean zza(zzef zzefVar, boolean z) {
        zzefVar.zzasm = false;
        return false;
    }
}
