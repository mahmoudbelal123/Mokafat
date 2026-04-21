package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class zzcc extends Fragment implements LifecycleFragment {
    private static WeakHashMap<FragmentActivity, WeakReference<zzcc>> zzla = new WeakHashMap<>();
    private Map<String, LifecycleCallback> zzlb = new ArrayMap();
    private int zzlc = 0;
    private Bundle zzld;

    public static zzcc zza(FragmentActivity fragmentActivity) {
        zzcc zzccVar;
        WeakReference<zzcc> weakReference = zzla.get(fragmentActivity);
        if (weakReference != null && (zzccVar = weakReference.get()) != null) {
            return zzccVar;
        }
        try {
            zzcc zzccVar2 = (zzcc) fragmentActivity.getSupportFragmentManager().findFragmentByTag("SupportLifecycleFragmentImpl");
            if (zzccVar2 == null || zzccVar2.isRemoving()) {
                zzccVar2 = new zzcc();
                fragmentActivity.getSupportFragmentManager().beginTransaction().add(zzccVar2, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
            }
            zzla.put(fragmentActivity, new WeakReference<>(zzccVar2));
            return zzccVar2;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", e);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final void addCallback(String str, @NonNull LifecycleCallback lifecycleCallback) {
        if (this.zzlb.containsKey(str)) {
            StringBuilder sb = new StringBuilder(59 + String.valueOf(str).length());
            sb.append("LifecycleCallback with tag ");
            sb.append(str);
            sb.append(" already added to this fragment.");
            throw new IllegalArgumentException(sb.toString());
        }
        this.zzlb.put(str, lifecycleCallback);
        if (this.zzlc > 0) {
            new Handler(Looper.getMainLooper()).post(new zzcd(this, lifecycleCallback, str));
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        Iterator<LifecycleCallback> it = this.zzlb.values().iterator();
        while (it.hasNext()) {
            it.next().dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final <T extends LifecycleCallback> T getCallbackOrNull(String str, Class<T> cls) {
        return cls.cast(this.zzlb.get(str));
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final /* synthetic */ Activity getLifecycleActivity() {
        return getActivity();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final boolean isCreated() {
        return this.zzlc > 0;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final boolean isStarted() {
        return this.zzlc >= 2;
    }

    @Override // android.support.v4.app.Fragment
    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Iterator<LifecycleCallback> it = this.zzlb.values().iterator();
        while (it.hasNext()) {
            it.next().onActivityResult(i, i2, intent);
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzlc = 1;
        this.zzld = bundle;
        for (Map.Entry<String, LifecycleCallback> entry : this.zzlb.entrySet()) {
            entry.getValue().onCreate(bundle != null ? bundle.getBundle(entry.getKey()) : null);
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        this.zzlc = 5;
        Iterator<LifecycleCallback> it = this.zzlb.values().iterator();
        while (it.hasNext()) {
            it.next().onDestroy();
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void onResume() {
        super.onResume();
        this.zzlc = 3;
        Iterator<LifecycleCallback> it = this.zzlb.values().iterator();
        while (it.hasNext()) {
            it.next().onResume();
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle == null) {
            return;
        }
        for (Map.Entry<String, LifecycleCallback> entry : this.zzlb.entrySet()) {
            Bundle bundle2 = new Bundle();
            entry.getValue().onSaveInstanceState(bundle2);
            bundle.putBundle(entry.getKey(), bundle2);
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void onStart() {
        super.onStart();
        this.zzlc = 2;
        Iterator<LifecycleCallback> it = this.zzlb.values().iterator();
        while (it.hasNext()) {
            it.next().onStart();
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void onStop() {
        super.onStop();
        this.zzlc = 4;
        Iterator<LifecycleCallback> it = this.zzlb.values().iterator();
        while (it.hasNext()) {
            it.next().onStop();
        }
    }
}
