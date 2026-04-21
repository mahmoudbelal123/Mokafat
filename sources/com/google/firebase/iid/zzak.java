package com.google.firebase.iid;

/* JADX INFO: loaded from: classes.dex */
public final class zzak extends Exception {
    private final int errorCode;

    public zzak(int i, String str) {
        super(str);
        this.errorCode = i;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }
}
