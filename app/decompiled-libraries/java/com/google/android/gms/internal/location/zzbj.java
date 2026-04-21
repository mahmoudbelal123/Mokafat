package com.google.android.gms.internal.location;

import android.os.DeadObjectException;
import android.os.IInterface;

/* JADX INFO: loaded from: classes.dex */
public interface zzbj<T extends IInterface> {
    void checkConnected();

    T getService() throws DeadObjectException;
}
