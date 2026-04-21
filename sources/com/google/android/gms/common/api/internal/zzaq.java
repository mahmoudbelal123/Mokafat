package com.google.android.gms.common.api.internal;

import android.support.annotation.BinderThread;
import com.google.android.gms.signin.internal.BaseSignInCallbacks;
import com.google.android.gms.signin.internal.SignInResponse;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
final class zzaq extends BaseSignInCallbacks {
    private final WeakReference<zzaj> zzhw;

    zzaq(zzaj zzajVar) {
        this.zzhw = new WeakReference<>(zzajVar);
    }

    @Override // com.google.android.gms.signin.internal.BaseSignInCallbacks, com.google.android.gms.signin.internal.ISignInCallbacks
    @BinderThread
    public final void onSignInComplete(SignInResponse signInResponse) {
        zzaj zzajVar = this.zzhw.get();
        if (zzajVar == null) {
            return;
        }
        zzajVar.zzhf.zza(new zzar(this, zzajVar, zzajVar, signInResponse));
    }
}
