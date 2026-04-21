package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes.dex */
public class zzbt extends zzk {
    private TaskCompletionSource<Void> zzln;

    private zzbt(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.zzln = new TaskCompletionSource<>();
        this.mLifecycleFragment.addCallback("GmsAvailabilityHelper", this);
    }

    public static zzbt zzd(Activity activity) {
        LifecycleFragment fragment = getFragment(activity);
        zzbt zzbtVar = (zzbt) fragment.getCallbackOrNull("GmsAvailabilityHelper", zzbt.class);
        if (zzbtVar == null) {
            return new zzbt(fragment);
        }
        if (zzbtVar.zzln.getTask().isComplete()) {
            zzbtVar.zzln = new TaskCompletionSource<>();
        }
        return zzbtVar;
    }

    public final Task<Void> getTask() {
        return this.zzln.getTask();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onDestroy() {
        super.onDestroy();
        this.zzln.trySetException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
    }

    @Override // com.google.android.gms.common.api.internal.zzk
    protected final void zza(ConnectionResult connectionResult, int i) {
        this.zzln.setException(ApiExceptionUtil.fromConnectionResult(connectionResult));
    }

    @Override // com.google.android.gms.common.api.internal.zzk
    protected final void zzr() {
        int iIsGooglePlayServicesAvailable = this.zzdg.isGooglePlayServicesAvailable(this.mLifecycleFragment.getLifecycleActivity());
        if (iIsGooglePlayServicesAvailable == 0) {
            this.zzln.setResult(null);
        } else {
            if (this.zzln.getTask().isComplete()) {
                return;
            }
            zzb(new ConnectionResult(iIsGooglePlayServicesAvailable, null), 0);
        }
    }
}
