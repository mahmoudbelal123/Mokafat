package com.google.android.gms.internal.measurement;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzya extends RuntimeException {
    private final List<String> zzccn;

    public zzya(zzwt zzwtVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.zzccn = null;
    }
}
