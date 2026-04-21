package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.common.util.CrashUtils;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
public final class zzau {
    private static zzau zzcu;

    @GuardedBy("serviceClassNames")
    private final SimpleArrayMap<String, String> zzcv = new SimpleArrayMap<>();
    private Boolean zzcw = null;

    @VisibleForTesting
    final Queue<Intent> zzcx = new ArrayDeque();

    @VisibleForTesting
    private final Queue<Intent> zzcy = new ArrayDeque();

    public static synchronized zzau zzah() {
        if (zzcu == null) {
            zzcu = new zzau();
        }
        return zzcu;
    }

    private zzau() {
    }

    public static PendingIntent zza(Context context, int i, Intent intent, int i2) {
        return PendingIntent.getBroadcast(context, i, zza(context, "com.google.firebase.MESSAGING_EVENT", intent), CrashUtils.ErrorDialogData.SUPPRESSED);
    }

    public static void zzb(Context context, Intent intent) {
        context.sendBroadcast(zza(context, "com.google.firebase.INSTANCE_ID_EVENT", intent));
    }

    public static void zzc(Context context, Intent intent) {
        context.sendBroadcast(zza(context, "com.google.firebase.MESSAGING_EVENT", intent));
    }

    private static Intent zza(Context context, String str, Intent intent) {
        Intent intent2 = new Intent(context, (Class<?>) FirebaseInstanceIdReceiver.class);
        intent2.setAction(str);
        intent2.putExtra("wrapped_intent", intent);
        return intent2;
    }

    public final Intent zzai() {
        return this.zzcy.poll();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zzb(android.content.Context r3, java.lang.String r4, android.content.Intent r5) {
        /*
            r2 = this;
            int r0 = r4.hashCode()
            r1 = -842411455(0xffffffffcdc9d241, float:-4.2324995E8)
            if (r0 == r1) goto L19
            r1 = 41532704(0x279bd20, float:1.8347907E-37)
            if (r0 == r1) goto Lf
            goto L23
        Lf:
            java.lang.String r0 = "com.google.firebase.MESSAGING_EVENT"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L23
            r0 = 1
            goto L24
        L19:
            java.lang.String r0 = "com.google.firebase.INSTANCE_ID_EVENT"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L23
            r0 = 0
            goto L24
        L23:
            r0 = -1
        L24:
            switch(r0) {
                case 0: goto L40;
                case 1: goto L3a;
                default: goto L27;
            }
        L27:
            java.lang.String r3 = "FirebaseInstanceId"
            java.lang.String r5 = "Unknown service action: "
            java.lang.String r4 = java.lang.String.valueOf(r4)
            int r0 = r4.length()
            if (r0 == 0) goto L57
            java.lang.String r4 = r5.concat(r4)
            goto L5c
        L3a:
            java.util.Queue<android.content.Intent> r0 = r2.zzcy
            r0.offer(r5)
            goto L46
        L40:
            java.util.Queue<android.content.Intent> r0 = r2.zzcx
            r0.offer(r5)
        L46:
            android.content.Intent r5 = new android.content.Intent
            r5.<init>(r4)
            java.lang.String r4 = r3.getPackageName()
            r5.setPackage(r4)
            int r3 = r2.zzd(r3, r5)
            return r3
        L57:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r5)
        L5c:
            android.util.Log.w(r3, r4)
            r3 = 500(0x1f4, float:7.0E-43)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzau.zzb(android.content.Context, java.lang.String, android.content.Intent):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00b6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int zzd(android.content.Context r7, android.content.Intent r8) {
        /*
            Method dump skipped, instruction units count: 331
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzau.zzd(android.content.Context, android.content.Intent):int");
    }
}
