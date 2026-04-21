package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;

/* JADX INFO: loaded from: classes.dex */
final class zzb implements DeferredLifecycleHelper.zza {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ DeferredLifecycleHelper zzabg;
    private final /* synthetic */ Bundle zzabh;
    private final /* synthetic */ Bundle zzabi;

    zzb(DeferredLifecycleHelper deferredLifecycleHelper, Activity activity, Bundle bundle, Bundle bundle2) {
        this.zzabg = deferredLifecycleHelper;
        this.val$activity = activity;
        this.zzabh = bundle;
        this.zzabi = bundle2;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zza
    public final int getState() {
        return 0;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zza
    public final void zza(LifecycleDelegate lifecycleDelegate) {
        this.zzabg.zzabc.onInflate(this.val$activity, this.zzabh, this.zzabi);
    }
}
