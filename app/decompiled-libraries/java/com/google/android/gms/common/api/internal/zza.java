package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zza extends ActivityLifecycleObserver {
    private final WeakReference<C0004zza> zzds;

    /* JADX INFO: renamed from: com.google.android.gms.common.api.internal.zza$zza, reason: collision with other inner class name */
    @VisibleForTesting(otherwise = 2)
    static class C0004zza extends LifecycleCallback {
        private List<Runnable> zzdt;

        private C0004zza(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.zzdt = new ArrayList();
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static C0004zza zza(Activity activity) {
            C0004zza c0004zza;
            synchronized (activity) {
                LifecycleFragment fragment = getFragment(activity);
                c0004zza = (C0004zza) fragment.getCallbackOrNull("LifecycleObserverOnStop", C0004zza.class);
                if (c0004zza == null) {
                    c0004zza = new C0004zza(fragment);
                }
            }
            return c0004zza;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final synchronized void zza(Runnable runnable) {
            this.zzdt.add(runnable);
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        @MainThread
        public void onStop() {
            List<Runnable> list;
            synchronized (this) {
                list = this.zzdt;
                this.zzdt = new ArrayList();
            }
            Iterator<Runnable> it = list.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
        }
    }

    public zza(Activity activity) {
        this(C0004zza.zza(activity));
    }

    @VisibleForTesting(otherwise = 2)
    private zza(C0004zza c0004zza) {
        this.zzds = new WeakReference<>(c0004zza);
    }

    @Override // com.google.android.gms.common.api.internal.ActivityLifecycleObserver
    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        C0004zza c0004zza = this.zzds.get();
        if (c0004zza == null) {
            throw new IllegalStateException("The target activity has already been GC'd");
        }
        c0004zza.zza(runnable);
        return this;
    }
}
