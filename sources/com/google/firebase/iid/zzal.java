package com.google.firebase.iid;

import android.os.Bundle;
import com.google.android.gms.common.data.DataBufferSafeParcelable;

/* JADX INFO: loaded from: classes.dex */
final class zzal extends zzaj<Bundle> {
    zzal(int i, int i2, Bundle bundle) {
        super(i, 1, bundle);
    }

    @Override // com.google.firebase.iid.zzaj
    final boolean zzaa() {
        return false;
    }

    @Override // com.google.firebase.iid.zzaj
    final void zzb(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(DataBufferSafeParcelable.DATA_FIELD);
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        finish(bundle2);
    }
}
