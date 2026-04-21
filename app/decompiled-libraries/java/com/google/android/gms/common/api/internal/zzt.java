package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: loaded from: classes.dex */
final class zzt implements zzbq {
    private final /* synthetic */ zzr zzgc;

    private zzt(zzr zzrVar) {
        this.zzgc = zzrVar;
    }

    /* synthetic */ zzt(zzr zzrVar, zzs zzsVar) {
        this(zzrVar);
    }

    @Override // com.google.android.gms.common.api.internal.zzbq
    public final void zzb(int i, boolean z) {
        this.zzgc.zzga.lock();
        try {
            if (this.zzgc.zzfz || this.zzgc.zzfy == null || !this.zzgc.zzfy.isSuccess()) {
                this.zzgc.zzfz = false;
                this.zzgc.zza(i, z);
            } else {
                this.zzgc.zzfz = true;
                this.zzgc.zzfs.onConnectionSuspended(i);
            }
        } finally {
            this.zzgc.zzga.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzbq
    public final void zzb(@Nullable Bundle bundle) {
        this.zzgc.zzga.lock();
        try {
            this.zzgc.zza(bundle);
            this.zzgc.zzfx = ConnectionResult.RESULT_SUCCESS;
            this.zzgc.zzaa();
        } finally {
            this.zzgc.zzga.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzbq
    public final void zzc(@NonNull ConnectionResult connectionResult) {
        this.zzgc.zzga.lock();
        try {
            this.zzgc.zzfx = connectionResult;
            this.zzgc.zzaa();
        } finally {
            this.zzgc.zzga.unlock();
        }
    }
}
