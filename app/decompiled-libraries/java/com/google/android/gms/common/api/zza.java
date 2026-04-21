package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult;

/* JADX INFO: loaded from: classes.dex */
final class zza implements PendingResult.StatusListener {
    private final /* synthetic */ Batch zzch;

    zza(Batch batch) {
        this.zzch = batch;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        synchronized (this.zzch.mLock) {
            if (this.zzch.isCanceled()) {
                return;
            }
            if (status.isCanceled()) {
                Batch.zza(this.zzch, true);
            } else if (!status.isSuccess()) {
                Batch.zzb(this.zzch, true);
            }
            Batch.zzb(this.zzch);
            if (this.zzch.zzcd == 0) {
                if (this.zzch.zzcf) {
                    super/*com.google.android.gms.common.api.internal.BasePendingResult*/.cancel();
                } else {
                    this.zzch.setResult(new BatchResult(this.zzch.zzce ? new Status(13) : Status.RESULT_SUCCESS, this.zzch.zzcg));
                }
            }
        }
    }
}
