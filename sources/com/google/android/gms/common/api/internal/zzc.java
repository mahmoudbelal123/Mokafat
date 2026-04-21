package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: classes.dex */
abstract class zzc<T> extends zzb {
    protected final TaskCompletionSource<T> zzdu;

    public zzc(int i, TaskCompletionSource<T> taskCompletionSource) {
        super(i);
        this.zzdu = taskCompletionSource;
    }

    @Override // com.google.android.gms.common.api.internal.zzb
    public void zza(@NonNull Status status) {
        this.zzdu.trySetException(new ApiException(status));
    }

    @Override // com.google.android.gms.common.api.internal.zzb
    public final void zza(GoogleApiManager.zza<?> zzaVar) throws DeadObjectException {
        try {
            zzb(zzaVar);
        } catch (DeadObjectException e) {
            zza(zzb.zza(e));
            throw e;
        } catch (RemoteException e2) {
            zza(zzb.zza(e2));
        } catch (RuntimeException e3) {
            zza(e3);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzb
    public void zza(@NonNull zzaa zzaaVar, boolean z) {
    }

    @Override // com.google.android.gms.common.api.internal.zzb
    public void zza(@NonNull RuntimeException runtimeException) {
        this.zzdu.trySetException(runtimeException);
    }

    protected abstract void zzb(GoogleApiManager.zza<?> zzaVar) throws RemoteException;
}
