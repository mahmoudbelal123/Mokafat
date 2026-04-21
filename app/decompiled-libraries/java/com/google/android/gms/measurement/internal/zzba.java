package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
final class zzba extends zzcp {

    @VisibleForTesting
    static final Pair<String, Long> zzanc = new Pair<>("", 0L);
    private SharedPreferences zzabr;
    public zzbe zzand;
    public final zzbd zzane;
    public final zzbd zzanf;
    public final zzbd zzang;
    public final zzbd zzanh;
    public final zzbd zzani;
    public final zzbd zzanj;
    public final zzbd zzank;
    public final zzbf zzanl;
    private String zzanm;
    private boolean zzann;
    private long zzano;
    public final zzbd zzanp;
    public final zzbd zzanq;
    public final zzbc zzanr;
    public final zzbf zzans;
    public final zzbd zzant;
    public final zzbd zzanu;
    public boolean zzanv;

    @WorkerThread
    @NonNull
    final Pair<String, Boolean> zzby(String str) {
        zzaf();
        long jElapsedRealtime = zzbx().elapsedRealtime();
        if (this.zzanm != null && jElapsedRealtime < this.zzano) {
            return new Pair<>(this.zzanm, Boolean.valueOf(this.zzann));
        }
        this.zzano = jElapsedRealtime + zzgq().zza(str, zzaf.zzaje);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
            if (advertisingIdInfo != null) {
                this.zzanm = advertisingIdInfo.getId();
                this.zzann = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.zzanm == null) {
                this.zzanm = "";
            }
        } catch (Exception e) {
            zzgo().zzjk().zzg("Unable to get advertising id", e);
            this.zzanm = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzanm, Boolean.valueOf(this.zzann));
    }

    @WorkerThread
    final String zzbz(String str) {
        zzaf();
        String str2 = (String) zzby(str).first;
        MessageDigest messageDigest = zzfk.getMessageDigest();
        if (messageDigest == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, messageDigest.digest(str2.getBytes())));
    }

    zzba(zzbt zzbtVar) {
        super(zzbtVar);
        this.zzane = new zzbd(this, "last_upload", 0L);
        this.zzanf = new zzbd(this, "last_upload_attempt", 0L);
        this.zzang = new zzbd(this, "backoff", 0L);
        this.zzanh = new zzbd(this, "last_delete_stale", 0L);
        this.zzanp = new zzbd(this, "time_before_start", 10000L);
        this.zzanq = new zzbd(this, "session_timeout", 1800000L);
        this.zzanr = new zzbc(this, "start_new_session", true);
        this.zzans = new zzbf(this, "allow_ad_personalization", null);
        this.zzant = new zzbd(this, "last_pause_time", 0L);
        this.zzanu = new zzbd(this, "time_active", 0L);
        this.zzani = new zzbd(this, "midnight_offset", 0L);
        this.zzanj = new zzbd(this, "first_open_time", 0L);
        this.zzank = new zzbd(this, "app_install_time", 0L);
        this.zzanl = new zzbf(this, "app_instance_id", null);
    }

    @Override // com.google.android.gms.measurement.internal.zzcp
    protected final boolean zzgt() {
        return true;
    }

    @Override // com.google.android.gms.measurement.internal.zzcp
    @WorkerThread
    protected final void zzgu() {
        this.zzabr = getContext().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzanv = this.zzabr.getBoolean("has_been_opened", false);
        if (!this.zzanv) {
            SharedPreferences.Editor editorEdit = this.zzabr.edit();
            editorEdit.putBoolean("has_been_opened", true);
            editorEdit.apply();
        }
        this.zzand = new zzbe(this, "health_monitor", Math.max(0L, zzaf.zzajf.get().longValue()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final SharedPreferences zzjr() {
        zzaf();
        zzcl();
        return this.zzabr;
    }

    @WorkerThread
    final void zzca(String str) {
        zzaf();
        SharedPreferences.Editor editorEdit = zzjr().edit();
        editorEdit.putString("gmp_app_id", str);
        editorEdit.apply();
    }

    @WorkerThread
    final String zzjs() {
        zzaf();
        return zzjr().getString("gmp_app_id", null);
    }

    @WorkerThread
    final void zzcb(String str) {
        zzaf();
        SharedPreferences.Editor editorEdit = zzjr().edit();
        editorEdit.putString("admob_app_id", str);
        editorEdit.apply();
    }

    @WorkerThread
    final String zzjt() {
        zzaf();
        return zzjr().getString("admob_app_id", null);
    }

    @WorkerThread
    final Boolean zzju() {
        zzaf();
        if (!zzjr().contains("use_service")) {
            return null;
        }
        return Boolean.valueOf(zzjr().getBoolean("use_service", false));
    }

    @WorkerThread
    final void zzg(boolean z) {
        zzaf();
        zzgo().zzjl().zzg("Setting useService", Boolean.valueOf(z));
        SharedPreferences.Editor editorEdit = zzjr().edit();
        editorEdit.putBoolean("use_service", z);
        editorEdit.apply();
    }

    @WorkerThread
    final void zzjv() {
        zzaf();
        zzgo().zzjl().zzbx("Clearing collection preferences.");
        boolean zContains = zzjr().contains("measurement_enabled");
        boolean zZzh = true;
        if (zContains) {
            zZzh = zzh(true);
        }
        SharedPreferences.Editor editorEdit = zzjr().edit();
        editorEdit.clear();
        editorEdit.apply();
        if (zContains) {
            setMeasurementEnabled(zZzh);
        }
    }

    @WorkerThread
    final void setMeasurementEnabled(boolean z) {
        zzaf();
        zzgo().zzjl().zzg("Setting measurementEnabled", Boolean.valueOf(z));
        SharedPreferences.Editor editorEdit = zzjr().edit();
        editorEdit.putBoolean("measurement_enabled", z);
        editorEdit.apply();
    }

    @WorkerThread
    final boolean zzh(boolean z) {
        zzaf();
        return zzjr().getBoolean("measurement_enabled", z);
    }

    @WorkerThread
    protected final String zzjw() {
        zzaf();
        String string = zzjr().getString("previous_os_version", null);
        zzgk().zzcl();
        String str = Build.VERSION.RELEASE;
        if (!TextUtils.isEmpty(str) && !str.equals(string)) {
            SharedPreferences.Editor editorEdit = zzjr().edit();
            editorEdit.putString("previous_os_version", str);
            editorEdit.apply();
        }
        return string;
    }

    @WorkerThread
    final void zzi(boolean z) {
        zzaf();
        zzgo().zzjl().zzg("Updating deferred analytics collection", Boolean.valueOf(z));
        SharedPreferences.Editor editorEdit = zzjr().edit();
        editorEdit.putBoolean("deferred_analytics_collection", z);
        editorEdit.apply();
    }

    @WorkerThread
    final boolean zzjx() {
        zzaf();
        return zzjr().getBoolean("deferred_analytics_collection", false);
    }

    @WorkerThread
    final boolean zzjy() {
        return this.zzabr.contains("deferred_analytics_collection");
    }
}
