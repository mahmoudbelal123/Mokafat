package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseMessaging {
    public static final String INSTANCE_ID_SCOPE = "FCM";
    private static final Pattern zzdm = Pattern.compile("[a-zA-Z0-9-_.~%]{1,900}");
    private static FirebaseMessaging zzdn;
    private final FirebaseInstanceId zzdg;

    public static synchronized FirebaseMessaging getInstance() {
        if (zzdn == null) {
            zzdn = new FirebaseMessaging(FirebaseInstanceId.getInstance());
        }
        return zzdn;
    }

    private FirebaseMessaging(FirebaseInstanceId firebaseInstanceId) {
        this.zzdg = firebaseInstanceId;
    }

    public boolean isAutoInitEnabled() {
        return this.zzdg.zzq();
    }

    public void setAutoInitEnabled(boolean z) {
        this.zzdg.zzb(z);
    }

    public Task<Void> subscribeToTopic(String str) {
        if (str != null && str.startsWith("/topics/")) {
            Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in subscribeToTopic.");
            str = str.substring(8);
        }
        if (str == null || !zzdm.matcher(str).matches()) {
            StringBuilder sb = new StringBuilder(78 + String.valueOf(str).length());
            sb.append("Invalid topic name: ");
            sb.append(str);
            sb.append(" does not match the allowed format [a-zA-Z0-9-_.~%]{1,900}");
            throw new IllegalArgumentException(sb.toString());
        }
        FirebaseInstanceId firebaseInstanceId = this.zzdg;
        String strValueOf = String.valueOf("S!");
        String strValueOf2 = String.valueOf(str);
        return firebaseInstanceId.zza(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
    }

    public Task<Void> unsubscribeFromTopic(String str) {
        if (str != null && str.startsWith("/topics/")) {
            Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in unsubscribeFromTopic.");
            str = str.substring(8);
        }
        if (str == null || !zzdm.matcher(str).matches()) {
            StringBuilder sb = new StringBuilder(78 + String.valueOf(str).length());
            sb.append("Invalid topic name: ");
            sb.append(str);
            sb.append(" does not match the allowed format [a-zA-Z0-9-_.~%]{1,900}");
            throw new IllegalArgumentException(sb.toString());
        }
        FirebaseInstanceId firebaseInstanceId = this.zzdg;
        String strValueOf = String.valueOf("U!");
        String strValueOf2 = String.valueOf(str);
        return firebaseInstanceId.zza(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
    }

    public void send(RemoteMessage remoteMessage) {
        if (TextUtils.isEmpty(remoteMessage.getTo())) {
            throw new IllegalArgumentException("Missing 'to'");
        }
        Context applicationContext = FirebaseApp.getInstance().getApplicationContext();
        Intent intent = new Intent("com.google.android.gcm.intent.SEND");
        Intent intent2 = new Intent();
        intent2.setPackage("com.google.example.invalidpackage");
        intent.putExtra("app", PendingIntent.getBroadcast(applicationContext, 0, intent2, 0));
        intent.setPackage("com.google.android.gms");
        intent.putExtras(remoteMessage.zzdp);
        applicationContext.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }
}
