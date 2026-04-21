package com.google.android.gms.common;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class zza implements Continuation<Map<com.google.android.gms.common.api.internal.zzh<?>, String>, Void> {
    zza(GoogleApiAvailability googleApiAvailability) {
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Void then(@NonNull Task<Map<com.google.android.gms.common.api.internal.zzh<?>, String>> task) throws Exception {
        task.getResult();
        return null;
    }
}
