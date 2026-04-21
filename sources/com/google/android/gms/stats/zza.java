package com.google.android.gms.stats;

import com.google.android.gms.stats.WakeLock;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
final class zza implements WakeLock.Configuration {
    zza() {
    }

    @Override // com.google.android.gms.stats.WakeLock.Configuration
    public final long getMaximumTimeout(String str, String str2) {
        return LongCompanionObject.MAX_VALUE;
    }

    @Override // com.google.android.gms.stats.WakeLock.Configuration
    public final boolean isWorkChainsEnabled() {
        return false;
    }
}
