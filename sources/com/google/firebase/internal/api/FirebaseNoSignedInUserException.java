package com.google.firebase.internal.api;

import android.support.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.FirebaseException;

/* JADX INFO: compiled from: com.google.firebase:firebase-common@@16.0.1 */
/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public class FirebaseNoSignedInUserException extends FirebaseException {
    @KeepForSdk
    public FirebaseNoSignedInUserException(@NonNull String message) {
        super(message);
    }
}
