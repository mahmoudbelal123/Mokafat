package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
final class zzal implements BaseGmsClient.ConnectionProgressReportCallbacks {
    private final Api<?> mApi;
    private final boolean zzfo;
    private final WeakReference<zzaj> zzhw;

    public zzal(zzaj zzajVar, Api<?> api, boolean z) {
        this.zzhw = new WeakReference<>(zzajVar);
        this.mApi = api;
        this.zzfo = z;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
    public final void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
        zzaj zzajVar = this.zzhw.get();
        if (zzajVar == null) {
            return;
        }
        Preconditions.checkState(Looper.myLooper() == zzajVar.zzhf.zzfq.getLooper(), "onReportServiceBinding must be called on the GoogleApiClient handler thread");
        zzajVar.zzga.lock();
        try {
            if (zzajVar.zze(0)) {
                if (!connectionResult.isSuccess()) {
                    zzajVar.zzb(connectionResult, this.mApi, this.zzfo);
                }
                if (zzajVar.zzar()) {
                    zzajVar.zzas();
                }
            }
        } finally {
            zzajVar.zzga.unlock();
        }
    }
}
