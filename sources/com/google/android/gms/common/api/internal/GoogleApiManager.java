package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public class GoogleApiManager implements Handler.Callback {

    @GuardedBy("lock")
    private static GoogleApiManager zzjo;
    private final Handler handler;
    private final Context zzjp;
    private final GoogleApiAvailability zzjq;
    private final GoogleApiAvailabilityCache zzjr;
    public static final Status zzjj = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status zzjk = new Status(4, "The user must be signed in to make this API call.");
    private static final Object lock = new Object();
    private long zzjl = 5000;
    private long zzjm = 120000;
    private long zzjn = 10000;
    private final AtomicInteger zzjs = new AtomicInteger(1);
    private final AtomicInteger zzjt = new AtomicInteger(0);
    private final Map<zzh<?>, zza<?>> zzju = new ConcurrentHashMap(5, 0.75f, 1);

    @GuardedBy("lock")
    private zzad zzjv = null;

    @GuardedBy("lock")
    private final Set<zzh<?>> zzjw = new ArraySet();
    private final Set<zzh<?>> zzjx = new ArraySet();

    public class zza<O extends Api.ApiOptions> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zzq {
        private final zzh<O> zzhc;
        private final Api.Client zzka;
        private final Api.AnyClient zzkb;
        private final zzaa zzkc;
        private final int zzkf;
        private final zzby zzkg;
        private boolean zzkh;
        private final Queue<com.google.android.gms.common.api.internal.zzb> zzjz = new LinkedList();
        private final Set<zzj> zzkd = new HashSet();
        private final Map<ListenerHolder.ListenerKey<?>, zzbv> zzke = new HashMap();
        private final List<zzb> zzki = new ArrayList();
        private ConnectionResult zzkj = null;

        @WorkerThread
        public zza(GoogleApi<O> googleApi) {
            this.zzka = googleApi.zza(GoogleApiManager.this.handler.getLooper(), this);
            this.zzkb = this.zzka instanceof SimpleClientAdapter ? ((SimpleClientAdapter) this.zzka).getClient() : this.zzka;
            this.zzhc = googleApi.zzm();
            this.zzkc = new zzaa();
            this.zzkf = googleApi.getInstanceId();
            if (this.zzka.requiresSignIn()) {
                this.zzkg = googleApi.zza(GoogleApiManager.this.zzjp, GoogleApiManager.this.handler);
            } else {
                this.zzkg = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @WorkerThread
        public final void zza(zzb zzbVar) {
            if (this.zzki.contains(zzbVar) && !this.zzkh) {
                if (this.zzka.isConnected()) {
                    zzbl();
                } else {
                    connect();
                }
            }
        }

        static /* synthetic */ boolean zza(zza zzaVar, boolean z) {
            return zzaVar.zzb(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @WorkerThread
        public final void zzb(zzb zzbVar) {
            Feature[] requiredFeatures;
            if (this.zzki.remove(zzbVar)) {
                GoogleApiManager.this.handler.removeMessages(15, zzbVar);
                GoogleApiManager.this.handler.removeMessages(16, zzbVar);
                Feature feature = zzbVar.zzdr;
                ArrayList arrayList = new ArrayList(this.zzjz.size());
                for (com.google.android.gms.common.api.internal.zzb zzbVar2 : this.zzjz) {
                    if ((zzbVar2 instanceof zzf) && (requiredFeatures = ((zzf) zzbVar2).getRequiredFeatures()) != null && ArrayUtils.contains(requiredFeatures, feature)) {
                        arrayList.add(zzbVar2);
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList2.get(i);
                    i++;
                    com.google.android.gms.common.api.internal.zzb zzbVar3 = (com.google.android.gms.common.api.internal.zzb) obj;
                    this.zzjz.remove(zzbVar3);
                    zzbVar3.zza(new UnsupportedApiCallException(feature));
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @WorkerThread
        private final boolean zzb(com.google.android.gms.common.api.internal.zzb zzbVar) {
            if (!(zzbVar instanceof zzf)) {
                zzc(zzbVar);
                return true;
            }
            zzf zzfVar = (zzf) zzbVar;
            Feature[] requiredFeatures = zzfVar.getRequiredFeatures();
            if (requiredFeatures == null || requiredFeatures.length == 0) {
                zzc(zzbVar);
                return true;
            }
            Feature[] availableFeatures = this.zzka.getAvailableFeatures();
            if (availableFeatures == null) {
                availableFeatures = new Feature[0];
            }
            ArrayMap arrayMap = new ArrayMap(availableFeatures.length);
            for (Feature feature : availableFeatures) {
                arrayMap.put(feature.getName(), Long.valueOf(feature.getVersion()));
            }
            for (Feature feature2 : requiredFeatures) {
                zzbh zzbhVar = null;
                if (!arrayMap.containsKey(feature2.getName()) || ((Long) arrayMap.get(feature2.getName())).longValue() < feature2.getVersion()) {
                    if (zzfVar.shouldAutoResolveMissingFeatures()) {
                        zzb zzbVar2 = new zzb(this.zzhc, feature2, zzbhVar);
                        int iIndexOf = this.zzki.indexOf(zzbVar2);
                        if (iIndexOf >= 0) {
                            zzb zzbVar3 = this.zzki.get(iIndexOf);
                            GoogleApiManager.this.handler.removeMessages(15, zzbVar3);
                            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 15, zzbVar3), GoogleApiManager.this.zzjl);
                            return false;
                        }
                        this.zzki.add(zzbVar2);
                        GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 15, zzbVar2), GoogleApiManager.this.zzjl);
                        GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 16, zzbVar2), GoogleApiManager.this.zzjm);
                        ConnectionResult connectionResult = new ConnectionResult(2, null);
                        if (!zzh(connectionResult)) {
                            GoogleApiManager.this.zzc(connectionResult, this.zzkf);
                            return false;
                        }
                    } else {
                        zzfVar.zza(new UnsupportedApiCallException(feature2));
                    }
                    return false;
                }
                this.zzki.remove(new zzb(this.zzhc, feature2, zzbhVar));
            }
            zzc(zzbVar);
            return true;
        }

        @WorkerThread
        private final boolean zzb(boolean z) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (!this.zzka.isConnected() || this.zzke.size() != 0) {
                return false;
            }
            if (!this.zzkc.zzaj()) {
                this.zzka.disconnect();
                return true;
            }
            if (z) {
                zzbr();
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @WorkerThread
        public final void zzbj() {
            zzbo();
            zzi(ConnectionResult.RESULT_SUCCESS);
            zzbq();
            Iterator<zzbv> it = this.zzke.values().iterator();
            while (it.hasNext()) {
                try {
                    it.next().zzlt.registerListener(this.zzkb, new TaskCompletionSource<>());
                } catch (DeadObjectException e) {
                    onConnectionSuspended(1);
                    this.zzka.disconnect();
                } catch (RemoteException e2) {
                }
            }
            zzbl();
            zzbr();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @WorkerThread
        public final void zzbk() {
            zzbo();
            this.zzkh = true;
            this.zzkc.zzal();
            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 9, this.zzhc), GoogleApiManager.this.zzjl);
            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 11, this.zzhc), GoogleApiManager.this.zzjm);
            GoogleApiManager.this.zzjr.flush();
        }

        @WorkerThread
        private final void zzbl() {
            ArrayList arrayList = new ArrayList(this.zzjz);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                com.google.android.gms.common.api.internal.zzb zzbVar = (com.google.android.gms.common.api.internal.zzb) obj;
                if (!this.zzka.isConnected()) {
                    return;
                }
                if (zzb(zzbVar)) {
                    this.zzjz.remove(zzbVar);
                }
            }
        }

        @WorkerThread
        private final void zzbq() {
            if (this.zzkh) {
                GoogleApiManager.this.handler.removeMessages(11, this.zzhc);
                GoogleApiManager.this.handler.removeMessages(9, this.zzhc);
                this.zzkh = false;
            }
        }

        private final void zzbr() {
            GoogleApiManager.this.handler.removeMessages(12, this.zzhc);
            GoogleApiManager.this.handler.sendMessageDelayed(GoogleApiManager.this.handler.obtainMessage(12, this.zzhc), GoogleApiManager.this.zzjn);
        }

        @WorkerThread
        private final void zzc(com.google.android.gms.common.api.internal.zzb zzbVar) {
            zzbVar.zza(this.zzkc, requiresSignIn());
            try {
                zzbVar.zza((zza<?>) this);
            } catch (DeadObjectException e) {
                onConnectionSuspended(1);
                this.zzka.disconnect();
            }
        }

        @WorkerThread
        private final boolean zzh(@NonNull ConnectionResult connectionResult) {
            synchronized (GoogleApiManager.lock) {
                if (GoogleApiManager.this.zzjv == null || !GoogleApiManager.this.zzjw.contains(this.zzhc)) {
                    return false;
                }
                GoogleApiManager.this.zzjv.zzb(connectionResult, this.zzkf);
                return true;
            }
        }

        @WorkerThread
        private final void zzi(ConnectionResult connectionResult) {
            for (zzj zzjVar : this.zzkd) {
                String endpointPackageName = null;
                if (Objects.equal(connectionResult, ConnectionResult.RESULT_SUCCESS)) {
                    endpointPackageName = this.zzka.getEndpointPackageName();
                }
                zzjVar.zza(this.zzhc, connectionResult, endpointPackageName);
            }
            this.zzkd.clear();
        }

        @WorkerThread
        public final void connect() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zzka.isConnected() || this.zzka.isConnecting()) {
                return;
            }
            int clientAvailability = GoogleApiManager.this.zzjr.getClientAvailability(GoogleApiManager.this.zzjp, this.zzka);
            if (clientAvailability != 0) {
                onConnectionFailed(new ConnectionResult(clientAvailability, null));
                return;
            }
            zzc zzcVar = GoogleApiManager.this.new zzc(this.zzka, this.zzhc);
            if (this.zzka.requiresSignIn()) {
                this.zzkg.zza(zzcVar);
            }
            this.zzka.connect(zzcVar);
        }

        public final int getInstanceId() {
            return this.zzkf;
        }

        final boolean isConnected() {
            return this.zzka.isConnected();
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public final void onConnected(@Nullable Bundle bundle) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                zzbj();
            } else {
                GoogleApiManager.this.handler.post(new zzbi(this));
            }
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
        @WorkerThread
        public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zzkg != null) {
                this.zzkg.zzbz();
            }
            zzbo();
            GoogleApiManager.this.zzjr.flush();
            zzi(connectionResult);
            if (connectionResult.getErrorCode() == 4) {
                zzc(GoogleApiManager.zzjk);
                return;
            }
            if (this.zzjz.isEmpty()) {
                this.zzkj = connectionResult;
                return;
            }
            if (zzh(connectionResult) || GoogleApiManager.this.zzc(connectionResult, this.zzkf)) {
                return;
            }
            if (connectionResult.getErrorCode() == 18) {
                this.zzkh = true;
            }
            if (this.zzkh) {
                GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 9, this.zzhc), GoogleApiManager.this.zzjl);
                return;
            }
            String strZzq = this.zzhc.zzq();
            StringBuilder sb = new StringBuilder(38 + String.valueOf(strZzq).length());
            sb.append("API: ");
            sb.append(strZzq);
            sb.append(" is not available on this device.");
            zzc(new Status(17, sb.toString()));
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public final void onConnectionSuspended(int i) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                zzbk();
            } else {
                GoogleApiManager.this.handler.post(new zzbj(this));
            }
        }

        public final boolean requiresSignIn() {
            return this.zzka.requiresSignIn();
        }

        @WorkerThread
        public final void resume() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zzkh) {
                connect();
            }
        }

        @Override // com.google.android.gms.common.api.internal.zzq
        public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                onConnectionFailed(connectionResult);
            } else {
                GoogleApiManager.this.handler.post(new zzbk(this, connectionResult));
            }
        }

        @WorkerThread
        public final void zza(com.google.android.gms.common.api.internal.zzb zzbVar) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zzka.isConnected()) {
                if (zzb(zzbVar)) {
                    zzbr();
                    return;
                } else {
                    this.zzjz.add(zzbVar);
                    return;
                }
            }
            this.zzjz.add(zzbVar);
            if (this.zzkj == null || !this.zzkj.hasResolution()) {
                connect();
            } else {
                onConnectionFailed(this.zzkj);
            }
        }

        @WorkerThread
        public final void zza(zzj zzjVar) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zzkd.add(zzjVar);
        }

        public final Api.Client zzae() {
            return this.zzka;
        }

        @WorkerThread
        public final void zzay() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zzkh) {
                zzbq();
                zzc(GoogleApiManager.this.zzjq.isGooglePlayServicesAvailable(GoogleApiManager.this.zzjp) == 18 ? new Status(8, "Connection timed out while waiting for Google Play services update to complete.") : new Status(8, "API failed to connect while resuming due to an unknown error."));
                this.zzka.disconnect();
            }
        }

        @WorkerThread
        public final void zzbm() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            zzc(GoogleApiManager.zzjj);
            this.zzkc.zzak();
            for (ListenerHolder.ListenerKey listenerKey : (ListenerHolder.ListenerKey[]) this.zzke.keySet().toArray(new ListenerHolder.ListenerKey[this.zzke.size()])) {
                zza(new zzg(listenerKey, new TaskCompletionSource()));
            }
            zzi(new ConnectionResult(4));
            if (this.zzka.isConnected()) {
                this.zzka.onUserSignOut(new zzbl(this));
            }
        }

        public final Map<ListenerHolder.ListenerKey<?>, zzbv> zzbn() {
            return this.zzke;
        }

        @WorkerThread
        public final void zzbo() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zzkj = null;
        }

        @WorkerThread
        public final ConnectionResult zzbp() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            return this.zzkj;
        }

        @WorkerThread
        public final boolean zzbs() {
            return zzb(true);
        }

        final SignInClient zzbt() {
            if (this.zzkg == null) {
                return null;
            }
            return this.zzkg.zzbt();
        }

        @WorkerThread
        public final void zzc(Status status) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            Iterator<com.google.android.gms.common.api.internal.zzb> it = this.zzjz.iterator();
            while (it.hasNext()) {
                it.next().zza(status);
            }
            this.zzjz.clear();
        }

        @WorkerThread
        public final void zzg(@NonNull ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zzka.disconnect();
            onConnectionFailed(connectionResult);
        }
    }

    private static class zzb {
        private final Feature zzdr;
        private final zzh<?> zzkn;

        private zzb(zzh<?> zzhVar, Feature feature) {
            this.zzkn = zzhVar;
            this.zzdr = feature;
        }

        /* synthetic */ zzb(zzh zzhVar, Feature feature, zzbh zzbhVar) {
            this(zzhVar, feature);
        }

        public final boolean equals(Object obj) {
            if (obj != null && (obj instanceof zzb)) {
                zzb zzbVar = (zzb) obj;
                if (Objects.equal(this.zzkn, zzbVar.zzkn) && Objects.equal(this.zzdr, zzbVar.zzdr)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hashCode(this.zzkn, this.zzdr);
        }

        public final String toString() {
            return Objects.toStringHelper(this).add("key", this.zzkn).add("feature", this.zzdr).toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class zzc implements zzcb, BaseGmsClient.ConnectionProgressReportCallbacks {
        private final zzh<?> zzhc;
        private final Api.Client zzka;
        private IAccountAccessor zzko = null;
        private Set<Scope> zzkp = null;
        private boolean zzkq = false;

        public zzc(Api.Client client, zzh<?> zzhVar) {
            this.zzka = client;
            this.zzhc = zzhVar;
        }

        static /* synthetic */ boolean zza(zzc zzcVar, boolean z) {
            zzcVar.zzkq = true;
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @WorkerThread
        public final void zzbu() {
            if (!this.zzkq || this.zzko == null) {
                return;
            }
            this.zzka.getRemoteService(this.zzko, this.zzkp);
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
        public final void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
            GoogleApiManager.this.handler.post(new zzbn(this, connectionResult));
        }

        @Override // com.google.android.gms.common.api.internal.zzcb
        @WorkerThread
        public final void zza(IAccountAccessor iAccountAccessor, Set<Scope> set) {
            if (iAccountAccessor == null || set == null) {
                Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
                zzg(new ConnectionResult(4));
            } else {
                this.zzko = iAccountAccessor;
                this.zzkp = set;
                zzbu();
            }
        }

        @Override // com.google.android.gms.common.api.internal.zzcb
        @WorkerThread
        public final void zzg(ConnectionResult connectionResult) {
            ((zza) GoogleApiManager.this.zzju.get(this.zzhc)).zzg(connectionResult);
        }
    }

    @KeepForSdk
    private GoogleApiManager(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.zzjp = context;
        this.handler = new Handler(looper, this);
        this.zzjq = googleApiAvailability;
        this.zzjr = new GoogleApiAvailabilityCache(googleApiAvailability);
        this.handler.sendMessage(this.handler.obtainMessage(6));
    }

    @KeepForSdk
    public static void reportSignOut() {
        synchronized (lock) {
            if (zzjo != null) {
                GoogleApiManager googleApiManager = zzjo;
                googleApiManager.zzjt.incrementAndGet();
                googleApiManager.handler.sendMessageAtFrontOfQueue(googleApiManager.handler.obtainMessage(10));
            }
        }
    }

    public static GoogleApiManager zzb(Context context) {
        GoogleApiManager googleApiManager;
        synchronized (lock) {
            if (zzjo == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                zzjo = new GoogleApiManager(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.getInstance());
            }
            googleApiManager = zzjo;
        }
        return googleApiManager;
    }

    @WorkerThread
    private final void zzb(GoogleApi<?> googleApi) {
        Object objZzm = googleApi.zzm();
        zza<?> zzaVar = this.zzju.get(objZzm);
        if (zzaVar == null) {
            zzaVar = new zza<>(googleApi);
            this.zzju.put((zzh<?>) objZzm, zzaVar);
        }
        if (zzaVar.requiresSignIn()) {
            this.zzjx.add((zzh<?>) objZzm);
        }
        zzaVar.connect();
    }

    public static GoogleApiManager zzbf() {
        GoogleApiManager googleApiManager;
        synchronized (lock) {
            Preconditions.checkNotNull(zzjo, "Must guarantee manager is non-null before using getInstance");
            googleApiManager = zzjo;
        }
        return googleApiManager;
    }

    @Override // android.os.Handler.Callback
    @WorkerThread
    public boolean handleMessage(Message message) {
        zza<?> next;
        TaskCompletionSource<Boolean> taskCompletionSourceZzao;
        boolean zValueOf;
        switch (message.what) {
            case 1:
                this.zzjn = ((Boolean) message.obj).booleanValue() ? 10000L : 300000L;
                this.handler.removeMessages(12);
                Iterator<zzh<?>> it = this.zzju.keySet().iterator();
                while (it.hasNext()) {
                    this.handler.sendMessageDelayed(this.handler.obtainMessage(12, it.next()), this.zzjn);
                }
                return true;
            case 2:
                zzj zzjVar = (zzj) message.obj;
                for (zzh<?> zzhVar : zzjVar.zzs()) {
                    zza<?> zzaVar = this.zzju.get(zzhVar);
                    if (zzaVar == null) {
                        zzjVar.zza(zzhVar, new ConnectionResult(13), null);
                        return true;
                    }
                    if (zzaVar.isConnected()) {
                        zzjVar.zza(zzhVar, ConnectionResult.RESULT_SUCCESS, zzaVar.zzae().getEndpointPackageName());
                    } else if (zzaVar.zzbp() != null) {
                        zzjVar.zza(zzhVar, zzaVar.zzbp(), null);
                    } else {
                        zzaVar.zza(zzjVar);
                    }
                }
                return true;
            case 3:
                for (zza<?> zzaVar2 : this.zzju.values()) {
                    zzaVar2.zzbo();
                    zzaVar2.connect();
                }
                return true;
            case 4:
            case 8:
            case 13:
                zzbu zzbuVar = (zzbu) message.obj;
                zza<?> zzaVar3 = this.zzju.get(zzbuVar.zzlr.zzm());
                if (zzaVar3 == null) {
                    zzb(zzbuVar.zzlr);
                    zzaVar3 = this.zzju.get(zzbuVar.zzlr.zzm());
                }
                if (!zzaVar3.requiresSignIn() || this.zzjt.get() == zzbuVar.zzlq) {
                    zzaVar3.zza(zzbuVar.zzlp);
                    return true;
                }
                zzbuVar.zzlp.zza(zzjj);
                zzaVar3.zzbm();
                return true;
            case 5:
                int i = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                Iterator<zza<?>> it2 = this.zzju.values().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        next = it2.next();
                        if (next.getInstanceId() == i) {
                        }
                    } else {
                        next = null;
                    }
                }
                if (next == null) {
                    StringBuilder sb = new StringBuilder(76);
                    sb.append("Could not find API instance ");
                    sb.append(i);
                    sb.append(" while trying to fail enqueued calls.");
                    Log.wtf("GoogleApiManager", sb.toString(), new Exception());
                    return true;
                }
                String errorString = this.zzjq.getErrorString(connectionResult.getErrorCode());
                String errorMessage = connectionResult.getErrorMessage();
                StringBuilder sb2 = new StringBuilder(69 + String.valueOf(errorString).length() + String.valueOf(errorMessage).length());
                sb2.append("Error resolution was canceled by the user, original error message: ");
                sb2.append(errorString);
                sb2.append(": ");
                sb2.append(errorMessage);
                next.zzc(new Status(17, sb2.toString()));
                return true;
            case 6:
                if (PlatformVersion.isAtLeastIceCreamSandwich() && (this.zzjp.getApplicationContext() instanceof Application)) {
                    BackgroundDetector.initialize((Application) this.zzjp.getApplicationContext());
                    BackgroundDetector.getInstance().addListener(new zzbh(this));
                    if (!BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
                        this.zzjn = 300000L;
                        return true;
                    }
                }
                return true;
            case 7:
                zzb((GoogleApi<?>) message.obj);
                return true;
            case 9:
                if (this.zzju.containsKey(message.obj)) {
                    this.zzju.get(message.obj).resume();
                    return true;
                }
                return true;
            case 10:
                Iterator<zzh<?>> it3 = this.zzjx.iterator();
                while (it3.hasNext()) {
                    this.zzju.remove(it3.next()).zzbm();
                }
                this.zzjx.clear();
                return true;
            case 11:
                if (this.zzju.containsKey(message.obj)) {
                    this.zzju.get(message.obj).zzay();
                    return true;
                }
                return true;
            case 12:
                if (this.zzju.containsKey(message.obj)) {
                    this.zzju.get(message.obj).zzbs();
                    return true;
                }
                return true;
            case 14:
                zzae zzaeVar = (zzae) message.obj;
                zzh<?> zzhVarZzm = zzaeVar.zzm();
                if (this.zzju.containsKey(zzhVarZzm)) {
                    boolean zZza = zza.zza((zza) this.zzju.get(zzhVarZzm), false);
                    taskCompletionSourceZzao = zzaeVar.zzao();
                    zValueOf = Boolean.valueOf(zZza);
                } else {
                    taskCompletionSourceZzao = zzaeVar.zzao();
                    zValueOf = false;
                }
                taskCompletionSourceZzao.setResult(zValueOf);
                return true;
            case 15:
                zzb zzbVar = (zzb) message.obj;
                if (this.zzju.containsKey(zzbVar.zzkn)) {
                    this.zzju.get(zzbVar.zzkn).zza(zzbVar);
                    return true;
                }
                return true;
            case 16:
                zzb zzbVar2 = (zzb) message.obj;
                if (this.zzju.containsKey(zzbVar2.zzkn)) {
                    this.zzju.get(zzbVar2.zzkn).zzb(zzbVar2);
                    return true;
                }
                return true;
            default:
                int i2 = message.what;
                StringBuilder sb3 = new StringBuilder(31);
                sb3.append("Unknown message id: ");
                sb3.append(i2);
                Log.w("GoogleApiManager", sb3.toString());
                return false;
        }
    }

    final void maybeSignOut() {
        this.zzjt.incrementAndGet();
        this.handler.sendMessage(this.handler.obtainMessage(10));
    }

    final PendingIntent zza(zzh<?> zzhVar, int i) {
        SignInClient signInClientZzbt;
        zza<?> zzaVar = this.zzju.get(zzhVar);
        if (zzaVar == null || (signInClientZzbt = zzaVar.zzbt()) == null) {
            return null;
        }
        return PendingIntent.getActivity(this.zzjp, i, signInClientZzbt.getSignInIntent(), 134217728);
    }

    public final <O extends Api.ApiOptions> Task<Boolean> zza(@NonNull GoogleApi<O> googleApi, @NonNull ListenerHolder.ListenerKey<?> listenerKey) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.handler.sendMessage(this.handler.obtainMessage(13, new zzbu(new zzg(listenerKey, taskCompletionSource), this.zzjt.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final <O extends Api.ApiOptions> Task<Void> zza(@NonNull GoogleApi<O> googleApi, @NonNull RegisterListenerMethod<Api.AnyClient, ?> registerListenerMethod, @NonNull UnregisterListenerMethod<Api.AnyClient, ?> unregisterListenerMethod) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.handler.sendMessage(this.handler.obtainMessage(8, new zzbu(new zze(new zzbv(registerListenerMethod, unregisterListenerMethod), taskCompletionSource), this.zzjt.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final Task<Map<zzh<?>, String>> zza(Iterable<? extends GoogleApi<?>> iterable) {
        zzj zzjVar = new zzj(iterable);
        this.handler.sendMessage(this.handler.obtainMessage(2, zzjVar));
        return zzjVar.getTask();
    }

    public final void zza(ConnectionResult connectionResult, int i) {
        if (zzc(connectionResult, i)) {
            return;
        }
        this.handler.sendMessage(this.handler.obtainMessage(5, i, 0, connectionResult));
    }

    public final void zza(GoogleApi<?> googleApi) {
        this.handler.sendMessage(this.handler.obtainMessage(7, googleApi));
    }

    public final <O extends Api.ApiOptions> void zza(GoogleApi<O> googleApi, int i, BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient> apiMethodImpl) {
        this.handler.sendMessage(this.handler.obtainMessage(4, new zzbu(new zzd(i, apiMethodImpl), this.zzjt.get(), googleApi)));
    }

    public final <O extends Api.ApiOptions, ResultT> void zza(GoogleApi<O> googleApi, int i, TaskApiCall<Api.AnyClient, ResultT> taskApiCall, TaskCompletionSource<ResultT> taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        this.handler.sendMessage(this.handler.obtainMessage(4, new zzbu(new zzf(i, taskApiCall, taskCompletionSource, statusExceptionMapper), this.zzjt.get(), googleApi)));
    }

    public final void zza(@NonNull zzad zzadVar) {
        synchronized (lock) {
            if (this.zzjv != zzadVar) {
                this.zzjv = zzadVar;
                this.zzjw.clear();
            }
            this.zzjw.addAll(zzadVar.zzam());
        }
    }

    final void zzb(@NonNull zzad zzadVar) {
        synchronized (lock) {
            if (this.zzjv == zzadVar) {
                this.zzjv = null;
                this.zzjw.clear();
            }
        }
    }

    public final int zzbg() {
        return this.zzjs.getAndIncrement();
    }

    public final Task<Boolean> zzc(GoogleApi<?> googleApi) {
        zzae zzaeVar = new zzae(googleApi.zzm());
        this.handler.sendMessage(this.handler.obtainMessage(14, zzaeVar));
        return zzaeVar.zzao().getTask();
    }

    final boolean zzc(ConnectionResult connectionResult, int i) {
        return this.zzjq.showWrappedErrorNotification(this.zzjp, connectionResult, i);
    }

    public final void zzr() {
        this.handler.sendMessage(this.handler.obtainMessage(3));
    }
}
