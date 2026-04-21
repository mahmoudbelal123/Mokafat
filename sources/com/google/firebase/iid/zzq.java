package com.google.firebase.iid;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import java.io.IOException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
final class zzq implements MessagingChannel {
    private final FirebaseApp zzaj;
    private final zzam zzak;
    private final zzas zzbf;
    private final Executor zzbg;

    zzq(FirebaseApp firebaseApp, zzam zzamVar, Executor executor) {
        this(firebaseApp, zzamVar, executor, new zzas(firebaseApp.getApplicationContext(), zzamVar));
    }

    @VisibleForTesting
    private zzq(FirebaseApp firebaseApp, zzam zzamVar, Executor executor, zzas zzasVar) {
        this.zzaj = firebaseApp;
        this.zzak = zzamVar;
        this.zzbf = zzasVar;
        this.zzbg = executor;
    }

    @Override // com.google.firebase.iid.MessagingChannel
    public final boolean isAvailable() {
        return this.zzak.zzab() != 0;
    }

    @Override // com.google.firebase.iid.MessagingChannel
    public final boolean isChannelBuilt() {
        return true;
    }

    @Override // com.google.firebase.iid.MessagingChannel
    public final Task<Void> buildChannel(String str, String str2) {
        return Tasks.forResult(null);
    }

    @Override // com.google.firebase.iid.MessagingChannel
    public final Task<String> getToken(String str, String str2, String str3, String str4) {
        return zzc(zza(str, str3, str4, new Bundle()));
    }

    @Override // com.google.firebase.iid.MessagingChannel
    public final Task<Void> deleteToken(String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putString("delete", "1");
        return zzb(zzc(zza(str, str3, str4, bundle)));
    }

    @Override // com.google.firebase.iid.MessagingChannel
    public final Task<Void> deleteInstanceId(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("iid-operation", "delete");
        bundle.putString("delete", "1");
        return zzb(zzc(zza(str, "*", "*", bundle)));
    }

    @Override // com.google.firebase.iid.MessagingChannel
    public final Task<Void> ackMessage(String str) {
        return null;
    }

    @Override // com.google.firebase.iid.MessagingChannel
    public final Task<Void> subscribeToTopic(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        String strValueOf = String.valueOf("/topics/");
        String strValueOf2 = String.valueOf(str3);
        bundle.putString("gcm.topic", strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
        String strValueOf3 = String.valueOf("/topics/");
        String strValueOf4 = String.valueOf(str3);
        return zzb(zzc(zza(str, str2, strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3), bundle)));
    }

    @Override // com.google.firebase.iid.MessagingChannel
    public final Task<Void> unsubscribeFromTopic(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        String strValueOf = String.valueOf("/topics/");
        String strValueOf2 = String.valueOf(str3);
        bundle.putString("gcm.topic", strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
        bundle.putString("delete", "1");
        String strValueOf3 = String.valueOf("/topics/");
        String strValueOf4 = String.valueOf(str3);
        return zzb(zzc(zza(str, str2, strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3), bundle)));
    }

    private final Task<Bundle> zza(String str, String str2, String str3, final Bundle bundle) {
        bundle.putString("scope", str3);
        bundle.putString("sender", str2);
        bundle.putString("subtype", str2);
        bundle.putString("appid", str);
        bundle.putString("gmp_app_id", this.zzaj.getOptions().getApplicationId());
        bundle.putString("gmsv", Integer.toString(this.zzak.zzae()));
        bundle.putString("osv", Integer.toString(Build.VERSION.SDK_INT));
        bundle.putString("app_ver", this.zzak.zzac());
        bundle.putString("app_ver_name", this.zzak.zzad());
        bundle.putString("cliv", "fiid-12451000");
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzbg.execute(new Runnable(this, bundle, taskCompletionSource) { // from class: com.google.firebase.iid.zzr
            private final zzq zzbh;
            private final Bundle zzbi;
            private final TaskCompletionSource zzbj;

            {
                this.zzbh = this;
                this.zzbi = bundle;
                this.zzbj = taskCompletionSource;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzbh.zza(this.zzbi, this.zzbj);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String zza(Bundle bundle) throws IOException {
        if (bundle == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String string = bundle.getString("registration_id");
        if (string != null) {
            return string;
        }
        String string2 = bundle.getString("unregistered");
        if (string2 != null) {
            return string2;
        }
        String string3 = bundle.getString("error");
        if ("RST".equals(string3)) {
            throw new IOException("INSTANCE_ID_RESET");
        }
        if (string3 != null) {
            throw new IOException(string3);
        }
        String strValueOf = String.valueOf(bundle);
        StringBuilder sb = new StringBuilder(21 + String.valueOf(strValueOf).length());
        sb.append("Unexpected response: ");
        sb.append(strValueOf);
        Log.w("FirebaseInstanceId", sb.toString(), new Throwable());
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    private final <T> Task<Void> zzb(Task<T> task) {
        return task.continueWith(zzi.zzd(), new zzs(this));
    }

    private final Task<String> zzc(Task<Bundle> task) {
        return task.continueWith(this.zzbg, new zzt(this));
    }

    final /* synthetic */ void zza(Bundle bundle, TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(this.zzbf.zzc(bundle));
        } catch (IOException e) {
            taskCompletionSource.setException(e);
        }
    }
}
