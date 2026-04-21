package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Add missing generic type declarations: [A, ResultT] */
/* JADX INFO: loaded from: classes.dex */
final class zzcf<A, ResultT> extends TaskApiCall<A, ResultT> {
    private final /* synthetic */ TaskApiCall.Builder zzmc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcf(TaskApiCall.Builder builder, Feature[] featureArr, boolean z) {
        super(featureArr, z);
        this.zzmc = builder;
    }

    /* JADX WARN: Incorrect types in method signature: (TA;Lcom/google/android/gms/tasks/TaskCompletionSource<TResultT;>;)V */
    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzmc.zzmb.accept(anyClient, taskCompletionSource);
    }
}
