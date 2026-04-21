package com.google.android.gms.common.providers;

import android.util.Log;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes.dex */
public class PooledExecutorsProvider {
    private static PooledExecutorFactory zzvs;

    public interface PooledExecutorFactory {
        ScheduledExecutorService newSingleThreadScheduledExecutor();
    }

    private PooledExecutorsProvider() {
    }

    public static PooledExecutorFactory createDefaultFactory() {
        return new zza();
    }

    public static synchronized PooledExecutorFactory getInstance() {
        if (zzvs == null) {
            zzvs = createDefaultFactory();
        }
        return zzvs;
    }

    public static synchronized void setInstance(PooledExecutorFactory pooledExecutorFactory) {
        if (zzvs != null) {
            Log.e("PooledExecutorsProvider", "setInstance called when instance was already set.");
        }
        zzvs = pooledExecutorFactory;
    }
}
