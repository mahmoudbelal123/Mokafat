package com.google.firebase.iid;

import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.VisibleForTesting;
import java.security.KeyPair;

/* JADX INFO: loaded from: classes.dex */
final class zzy {
    private final KeyPair zzbo;
    private final long zzbp;

    @VisibleForTesting
    zzy(KeyPair keyPair, long j) {
        this.zzbo = keyPair;
        this.zzbp = j;
    }

    final KeyPair getKeyPair() {
        return this.zzbo;
    }

    final long getCreationTime() {
        return this.zzbp;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzy)) {
            return false;
        }
        zzy zzyVar = (zzy) obj;
        return this.zzbp == zzyVar.zzbp && this.zzbo.getPublic().equals(zzyVar.zzbo.getPublic()) && this.zzbo.getPrivate().equals(zzyVar.zzbo.getPrivate());
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzbo.getPublic(), this.zzbo.getPrivate(), Long.valueOf(this.zzbp));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zzu() {
        return Base64.encodeToString(this.zzbo.getPublic().getEncoded(), 11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zzv() {
        return Base64.encodeToString(this.zzbo.getPrivate().getEncoded(), 11);
    }
}
