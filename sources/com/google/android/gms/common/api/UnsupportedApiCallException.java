package com.google.android.gms.common.api;

import com.google.android.gms.common.Feature;

/* JADX INFO: loaded from: classes.dex */
public final class UnsupportedApiCallException extends UnsupportedOperationException {
    private final Feature zzdr;

    public UnsupportedApiCallException(Feature feature) {
        this.zzdr = feature;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        String strValueOf = String.valueOf(this.zzdr);
        StringBuilder sb = new StringBuilder(8 + String.valueOf(strValueOf).length());
        sb.append("Missing ");
        sb.append(strValueOf);
        return sb.toString();
    }
}
