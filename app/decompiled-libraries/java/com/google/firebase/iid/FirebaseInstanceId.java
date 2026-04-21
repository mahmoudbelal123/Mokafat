package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Subscriber;
import com.google.firebase.iid.FirebaseInstanceId;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseInstanceId {
    private static final long zzaf = TimeUnit.HOURS.toSeconds(8);
    private static zzav zzag;

    @VisibleForTesting
    @GuardedBy("FirebaseInstanceId.class")
    private static ScheduledThreadPoolExecutor zzah;
    private final Executor zzai;
    private final FirebaseApp zzaj;
    private final zzam zzak;
    private MessagingChannel zzal;
    private final zzap zzam;
    private final zzaz zzan;

    @GuardedBy("this")
    private boolean zzao;
    private final zza zzap;

    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    @Keep
    public static synchronized FirebaseInstanceId getInstance(@NonNull FirebaseApp firebaseApp) {
        return (FirebaseInstanceId) firebaseApp.get(FirebaseInstanceId.class);
    }

    FirebaseInstanceId(FirebaseApp firebaseApp, Subscriber subscriber) {
        this(firebaseApp, new zzam(firebaseApp.getApplicationContext()), zzi.zze(), zzi.zze(), subscriber);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class zza {
        private final Subscriber zzax;

        @GuardedBy("this")
        @Nullable
        private EventHandler<DataCollectionDefaultChange> zzay;
        private final boolean zzaw = zzt();

        @GuardedBy("this")
        @Nullable
        private Boolean zzaz = zzs();

        zza(Subscriber subscriber) {
            this.zzax = subscriber;
            if (this.zzaz == null && this.zzaw) {
                this.zzay = new EventHandler(this) { // from class: com.google.firebase.iid.zzp
                    private final FirebaseInstanceId.zza zzbb;

                    {
                        this.zzbb = this;
                    }

                    @Override // com.google.firebase.events.EventHandler
                    public final void handle(Event event) {
                        FirebaseInstanceId.zza zzaVar = this.zzbb;
                        synchronized (zzaVar) {
                            if (zzaVar.isEnabled()) {
                                FirebaseInstanceId.this.zzf();
                            }
                        }
                    }
                };
                subscriber.subscribe(DataCollectionDefaultChange.class, this.zzay);
            }
        }

        final synchronized boolean isEnabled() {
            if (this.zzaz != null) {
                return this.zzaz.booleanValue();
            }
            return this.zzaw && FirebaseInstanceId.this.zzaj.isDataCollectionDefaultEnabled();
        }

        final synchronized void setEnabled(boolean z) {
            if (this.zzay != null) {
                this.zzax.unsubscribe(DataCollectionDefaultChange.class, this.zzay);
                this.zzay = null;
            }
            SharedPreferences.Editor editorEdit = FirebaseInstanceId.this.zzaj.getApplicationContext().getSharedPreferences("com.google.firebase.messaging", 0).edit();
            editorEdit.putBoolean("auto_init", z);
            editorEdit.apply();
            if (z) {
                FirebaseInstanceId.this.zzf();
            }
            this.zzaz = Boolean.valueOf(z);
        }

        @Nullable
        private final Boolean zzs() {
            ApplicationInfo applicationInfo;
            Context applicationContext = FirebaseInstanceId.this.zzaj.getApplicationContext();
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.google.firebase.messaging", 0);
            if (sharedPreferences.contains("auto_init")) {
                return Boolean.valueOf(sharedPreferences.getBoolean("auto_init", false));
            }
            try {
                PackageManager packageManager = applicationContext.getPackageManager();
                if (packageManager != null && (applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128)) != null && applicationInfo.metaData != null && applicationInfo.metaData.containsKey("firebase_messaging_auto_init_enabled")) {
                    return Boolean.valueOf(applicationInfo.metaData.getBoolean("firebase_messaging_auto_init_enabled"));
                }
                return null;
            } catch (PackageManager.NameNotFoundException e) {
                return null;
            }
        }

        private final boolean zzt() {
            try {
                Class.forName("com.google.firebase.messaging.FirebaseMessaging");
                return true;
            } catch (ClassNotFoundException e) {
                Context applicationContext = FirebaseInstanceId.this.zzaj.getApplicationContext();
                Intent intent = new Intent("com.google.firebase.MESSAGING_EVENT");
                intent.setPackage(applicationContext.getPackageName());
                ResolveInfo resolveInfoResolveService = applicationContext.getPackageManager().resolveService(intent, 0);
                return (resolveInfoResolveService == null || resolveInfoResolveService.serviceInfo == null) ? false : true;
            }
        }
    }

    private FirebaseInstanceId(FirebaseApp firebaseApp, zzam zzamVar, Executor executor, Executor executor2, Subscriber subscriber) {
        this.zzam = new zzap();
        this.zzao = false;
        if (zzam.zza(firebaseApp) == null) {
            throw new IllegalStateException("FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID");
        }
        synchronized (FirebaseInstanceId.class) {
            if (zzag == null) {
                zzag = new zzav(firebaseApp.getApplicationContext());
            }
        }
        this.zzaj = firebaseApp;
        this.zzak = zzamVar;
        if (this.zzal == null) {
            MessagingChannel messagingChannel = (MessagingChannel) firebaseApp.get(MessagingChannel.class);
            if (messagingChannel != null && messagingChannel.isAvailable()) {
                this.zzal = messagingChannel;
            } else {
                this.zzal = new zzq(firebaseApp, zzamVar, executor);
            }
        }
        this.zzal = this.zzal;
        this.zzai = executor2;
        this.zzan = new zzaz(zzag);
        this.zzap = new zza(subscriber);
        if (this.zzap.isEnabled()) {
            zzf();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzf() {
        zzaw zzawVarZzi = zzi();
        if (!zzn() || zzawVarZzi == null || zzawVarZzi.zzj(this.zzak.zzac()) || this.zzan.zzap()) {
            startSync();
        }
    }

    final FirebaseApp zzg() {
        return this.zzaj;
    }

    final synchronized void zza(boolean z) {
        this.zzao = z;
    }

    private final synchronized void startSync() {
        if (!this.zzao) {
            zza(0L);
        }
    }

    final synchronized void zza(long j) {
        zza(new zzax(this, this.zzak, this.zzan, Math.min(Math.max(30L, j << 1), zzaf)), j);
        this.zzao = true;
    }

    static void zza(Runnable runnable, long j) {
        synchronized (FirebaseInstanceId.class) {
            if (zzah == null) {
                zzah = new ScheduledThreadPoolExecutor(1);
            }
            zzah.schedule(runnable, j, TimeUnit.SECONDS);
        }
    }

    @WorkerThread
    public String getId() {
        zzf();
        return zzh();
    }

    private static String zzh() {
        return zzam.zza(zzag.zzg("").getKeyPair());
    }

    public long getCreationTime() {
        return zzag.zzg("").getCreationTime();
    }

    @NonNull
    public Task<InstanceIdResult> getInstanceId() {
        return zza(zzam.zza(this.zzaj), "*");
    }

    private final Task<InstanceIdResult> zza(final String str, final String str2) {
        final String strZzd = zzd(str2);
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzai.execute(new Runnable(this, str, str2, taskCompletionSource, strZzd) { // from class: com.google.firebase.iid.zzm
            private final FirebaseInstanceId zzaq;
            private final String zzar;
            private final String zzas;
            private final TaskCompletionSource zzat;
            private final String zzau;

            {
                this.zzaq = this;
                this.zzar = str;
                this.zzas = str2;
                this.zzat = taskCompletionSource;
                this.zzau = strZzd;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzaq.zza(this.zzar, this.zzas, this.zzat, this.zzau);
            }
        });
        return taskCompletionSource.getTask();
    }

    @WorkerThread
    public void deleteInstanceId() throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        zza(this.zzal.deleteInstanceId(zzh()));
        zzl();
    }

    @Nullable
    @Deprecated
    public String getToken() {
        zzaw zzawVarZzi = zzi();
        if (zzawVarZzi == null || zzawVarZzi.zzj(this.zzak.zzac())) {
            startSync();
        }
        if (zzawVarZzi != null) {
            return zzawVarZzi.zzbn;
        }
        return null;
    }

    @Nullable
    final zzaw zzi() {
        return zzb(zzam.zza(this.zzaj), "*");
    }

    @VisibleForTesting
    @Nullable
    private static zzaw zzb(String str, String str2) {
        return zzag.zzb("", str, str2);
    }

    final String zzj() throws IOException {
        return getToken(zzam.zza(this.zzaj), "*");
    }

    @WorkerThread
    public String getToken(String str, String str2) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        return ((InstanceIdResult) zza(zza(str, str2))).getToken();
    }

    private final <T> T zza(Task<T> task) throws IOException {
        try {
            return (T) Tasks.await(task, 30000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException e) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof IOException) {
                if ("INSTANCE_ID_RESET".equals(cause.getMessage())) {
                    zzl();
                }
                throw ((IOException) cause);
            }
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new IOException(e2);
        }
    }

    @WorkerThread
    public void deleteToken(String str, String str2) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        String strZzd = zzd(str2);
        zza(this.zzal.deleteToken(zzh(), zzaw.zza(zzb(str, strZzd)), str, strZzd));
        zzag.zzc("", str, strZzd);
    }

    public final synchronized Task<Void> zza(String str) {
        Task<Void> taskZza;
        taskZza = this.zzan.zza(str);
        startSync();
        return taskZza;
    }

    final void zzb(String str) throws IOException {
        zzaw zzawVarZzi = zzi();
        if (zzawVarZzi == null || zzawVarZzi.zzj(this.zzak.zzac())) {
            throw new IOException("token not available");
        }
        zza(this.zzal.subscribeToTopic(zzh(), zzawVarZzi.zzbn, str));
    }

    final void zzc(String str) throws IOException {
        zzaw zzawVarZzi = zzi();
        if (zzawVarZzi == null || zzawVarZzi.zzj(this.zzak.zzac())) {
            throw new IOException("token not available");
        }
        zza(this.zzal.unsubscribeFromTopic(zzh(), zzawVarZzi.zzbn, str));
    }

    static boolean zzk() {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            return true;
        }
        return Build.VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseInstanceId", 3);
    }

    final synchronized void zzl() {
        zzag.zzak();
        if (this.zzap.isEnabled()) {
            startSync();
        }
    }

    final boolean zzm() {
        return this.zzal.isAvailable();
    }

    final boolean zzn() {
        return this.zzal.isChannelBuilt();
    }

    final void zzo() throws IOException {
        zza(this.zzal.buildChannel(zzh(), zzaw.zza(zzi())));
    }

    final void zzp() {
        zzag.zzh("");
        startSync();
    }

    @VisibleForTesting
    public final boolean zzq() {
        return this.zzap.isEnabled();
    }

    @VisibleForTesting
    public final void zzb(boolean z) {
        this.zzap.setEnabled(z);
    }

    private static String zzd(String str) {
        if (str.isEmpty() || str.equalsIgnoreCase(AppMeasurement.FCM_ORIGIN) || str.equalsIgnoreCase("gcm")) {
            return "*";
        }
        return str;
    }

    final /* synthetic */ void zza(final String str, String str2, final TaskCompletionSource taskCompletionSource, final String str3) {
        final String strZzh = zzh();
        zzaw zzawVarZzb = zzb(str, str2);
        if (zzawVarZzb != null && !zzawVarZzb.zzj(this.zzak.zzac())) {
            taskCompletionSource.setResult(new zzw(strZzh, zzawVarZzb.zzbn));
        } else {
            final String strZza = zzaw.zza(zzawVarZzb);
            this.zzam.zza(str, str3, new zzar(this, strZzh, strZza, str, str3) { // from class: com.google.firebase.iid.zzn
                private final FirebaseInstanceId zzaq;
                private final String zzar;
                private final String zzas;
                private final String zzau;
                private final String zzav;

                {
                    this.zzaq = this;
                    this.zzar = strZzh;
                    this.zzas = strZza;
                    this.zzav = str;
                    this.zzau = str3;
                }

                @Override // com.google.firebase.iid.zzar
                public final Task zzr() {
                    return this.zzaq.zza(this.zzar, this.zzas, this.zzav, this.zzau);
                }
            }).addOnCompleteListener(this.zzai, new OnCompleteListener(this, str, str3, taskCompletionSource, strZzh) { // from class: com.google.firebase.iid.zzo
                private final FirebaseInstanceId zzaq;
                private final String zzar;
                private final String zzas;
                private final TaskCompletionSource zzat;
                private final String zzau;

                {
                    this.zzaq = this;
                    this.zzar = str;
                    this.zzas = str3;
                    this.zzat = taskCompletionSource;
                    this.zzau = strZzh;
                }

                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    this.zzaq.zza(this.zzar, this.zzas, this.zzat, this.zzau, task);
                }
            });
        }
    }

    final /* synthetic */ void zza(String str, String str2, TaskCompletionSource taskCompletionSource, String str3, Task task) {
        if (task.isSuccessful()) {
            String str4 = (String) task.getResult();
            zzag.zza("", str, str2, str4, this.zzak.zzac());
            taskCompletionSource.setResult(new zzw(str3, str4));
            return;
        }
        taskCompletionSource.setException(task.getException());
    }

    final /* synthetic */ Task zza(String str, String str2, String str3, String str4) {
        return this.zzal.getToken(str, str2, str3, str4);
    }
}
