package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class zzi extends zzk {
    private final SparseArray<zza> zzed;

    private class zza implements GoogleApiClient.OnConnectionFailedListener {
        public final int zzee;
        public final GoogleApiClient zzef;
        public final GoogleApiClient.OnConnectionFailedListener zzeg;

        public zza(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            this.zzee = i;
            this.zzef = googleApiClient;
            this.zzeg = onConnectionFailedListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
        public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            String strValueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(27 + String.valueOf(strValueOf).length());
            sb.append("beginFailureResolution for ");
            sb.append(strValueOf);
            Log.d("AutoManageHelper", sb.toString());
            zzi.this.zzb(connectionResult, this.zzee);
        }
    }

    private zzi(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.zzed = new SparseArray<>();
        this.mLifecycleFragment.addCallback("AutoManageHelper", this);
    }

    public static zzi zza(LifecycleActivity lifecycleActivity) {
        LifecycleFragment fragment = getFragment(lifecycleActivity);
        zzi zziVar = (zzi) fragment.getCallbackOrNull("AutoManageHelper", zzi.class);
        return zziVar != null ? zziVar : new zzi(fragment);
    }

    @Nullable
    private final zza zzd(int i) {
        if (this.zzed.size() <= i) {
            return null;
        }
        return this.zzed.get(this.zzed.keyAt(i));
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i = 0; i < this.zzed.size(); i++) {
            zza zzaVarZzd = zzd(i);
            if (zzaVarZzd != null) {
                printWriter.append((CharSequence) str).append("GoogleApiClient #").print(zzaVarZzd.zzee);
                printWriter.println(":");
                zzaVarZzd.zzef.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzk, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStart() {
        super.onStart();
        boolean z = this.mStarted;
        String strValueOf = String.valueOf(this.zzed);
        StringBuilder sb = new StringBuilder(14 + String.valueOf(strValueOf).length());
        sb.append("onStart ");
        sb.append(z);
        sb.append(" ");
        sb.append(strValueOf);
        Log.d("AutoManageHelper", sb.toString());
        if (this.zzer.get() == null) {
            for (int i = 0; i < this.zzed.size(); i++) {
                zza zzaVarZzd = zzd(i);
                if (zzaVarZzd != null) {
                    zzaVarZzd.zzef.connect();
                }
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzk, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        for (int i = 0; i < this.zzed.size(); i++) {
            zza zzaVarZzd = zzd(i);
            if (zzaVarZzd != null) {
                zzaVarZzd.zzef.disconnect();
            }
        }
    }

    public final void zza(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(googleApiClient, "GoogleApiClient instance cannot be null");
        boolean z = this.zzed.indexOfKey(i) < 0;
        StringBuilder sb = new StringBuilder(54);
        sb.append("Already managing a GoogleApiClient with id ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzl zzlVar = this.zzer.get();
        boolean z2 = this.mStarted;
        String strValueOf = String.valueOf(zzlVar);
        StringBuilder sb2 = new StringBuilder(49 + String.valueOf(strValueOf).length());
        sb2.append("starting AutoManage for client ");
        sb2.append(i);
        sb2.append(" ");
        sb2.append(z2);
        sb2.append(" ");
        sb2.append(strValueOf);
        Log.d("AutoManageHelper", sb2.toString());
        this.zzed.put(i, new zza(i, googleApiClient, onConnectionFailedListener));
        if (this.mStarted && zzlVar == null) {
            String strValueOf2 = String.valueOf(googleApiClient);
            StringBuilder sb3 = new StringBuilder(11 + String.valueOf(strValueOf2).length());
            sb3.append("connecting ");
            sb3.append(strValueOf2);
            Log.d("AutoManageHelper", sb3.toString());
            googleApiClient.connect();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzk
    protected final void zza(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zza zzaVar = this.zzed.get(i);
        if (zzaVar != null) {
            zzc(i);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zzaVar.zzeg;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    public final void zzc(int i) {
        zza zzaVar = this.zzed.get(i);
        this.zzed.remove(i);
        if (zzaVar != null) {
            zzaVar.zzef.unregisterConnectionFailedListener(zzaVar);
            zzaVar.zzef.disconnect();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzk
    protected final void zzr() {
        for (int i = 0; i < this.zzed.size(); i++) {
            zza zzaVarZzd = zzd(i);
            if (zzaVarZzd != null) {
                zzaVarZzd.zzef.connect();
            }
        }
    }
}
