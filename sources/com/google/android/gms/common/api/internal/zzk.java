package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzk extends LifecycleCallback implements DialogInterface.OnCancelListener {
    protected volatile boolean mStarted;
    protected final GoogleApiAvailability zzdg;
    protected final AtomicReference<zzl> zzer;
    private final Handler zzes;

    protected zzk(LifecycleFragment lifecycleFragment) {
        this(lifecycleFragment, GoogleApiAvailability.getInstance());
    }

    @VisibleForTesting
    private zzk(LifecycleFragment lifecycleFragment, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment);
        this.zzer = new AtomicReference<>(null);
        this.zzes = new Handler(Looper.getMainLooper());
        this.zzdg = googleApiAvailability;
    }

    private static int zza(@Nullable zzl zzlVar) {
        if (zzlVar == null) {
            return -1;
        }
        return zzlVar.zzu();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005a  */
    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onActivityResult(int r4, int r5, android.content.Intent r6) {
        /*
            r3 = this;
            java.util.concurrent.atomic.AtomicReference<com.google.android.gms.common.api.internal.zzl> r0 = r3.zzer
            java.lang.Object r0 = r0.get()
            com.google.android.gms.common.api.internal.zzl r0 = (com.google.android.gms.common.api.internal.zzl) r0
            r1 = 1
            r2 = 0
            switch(r4) {
                case 1: goto L2e;
                case 2: goto Le;
                default: goto Ld;
            }
        Ld:
            goto L53
        Le:
            com.google.android.gms.common.GoogleApiAvailability r4 = r3.zzdg
            android.app.Activity r5 = r3.getActivity()
            int r4 = r4.isGooglePlayServicesAvailable(r5)
            if (r4 != 0) goto L1b
            goto L1c
        L1b:
            r1 = r2
        L1c:
            if (r0 != 0) goto L1f
            return
        L1f:
            com.google.android.gms.common.ConnectionResult r5 = r0.getConnectionResult()
            int r5 = r5.getErrorCode()
            r6 = 18
            if (r5 != r6) goto L54
            if (r4 != r6) goto L54
            return
        L2e:
            r4 = -1
            if (r5 != r4) goto L32
            goto L54
        L32:
            if (r5 != 0) goto L53
            r4 = 13
            if (r6 == 0) goto L3e
            java.lang.String r5 = "<<ResolutionFailureErrorDetail>>"
            int r4 = r6.getIntExtra(r5, r4)
        L3e:
            com.google.android.gms.common.api.internal.zzl r5 = new com.google.android.gms.common.api.internal.zzl
            com.google.android.gms.common.ConnectionResult r6 = new com.google.android.gms.common.ConnectionResult
            r1 = 0
            r6.<init>(r4, r1)
            int r4 = zza(r0)
            r5.<init>(r6, r4)
            java.util.concurrent.atomic.AtomicReference<com.google.android.gms.common.api.internal.zzl> r4 = r3.zzer
            r4.set(r5)
            r0 = r5
        L53:
            r1 = r2
        L54:
            if (r1 == 0) goto L5a
            r3.zzt()
            return
        L5a:
            if (r0 == 0) goto L67
            com.google.android.gms.common.ConnectionResult r4 = r0.getConnectionResult()
            int r5 = r0.zzu()
            r3.zza(r4, r5)
        L67:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzk.onActivityResult(int, int, android.content.Intent):void");
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        zza(new ConnectionResult(13, null), zza(this.zzer.get()));
        zzt();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zzer.set(bundle.getBoolean("resolving_error", false) ? new zzl(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1)) : null);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        zzl zzlVar = this.zzer.get();
        if (zzlVar != null) {
            bundle.putBoolean("resolving_error", true);
            bundle.putInt("failed_client_id", zzlVar.zzu());
            bundle.putInt("failed_status", zzlVar.getConnectionResult().getErrorCode());
            bundle.putParcelable("failed_resolution", zzlVar.getConnectionResult().getResolution());
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }

    protected abstract void zza(ConnectionResult connectionResult, int i);

    public final void zzb(ConnectionResult connectionResult, int i) {
        zzl zzlVar = new zzl(connectionResult, i);
        if (this.zzer.compareAndSet(null, zzlVar)) {
            this.zzes.post(new zzm(this, zzlVar));
        }
    }

    protected abstract void zzr();

    protected final void zzt() {
        this.zzer.set(null);
        zzr();
    }
}
