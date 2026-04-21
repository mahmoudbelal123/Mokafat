package com.google.firebase.iid;

import android.support.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public interface InstanceIdResult {
    @NonNull
    String getId();

    @NonNull
    String getToken();
}
