package com.google.android.gms.measurement.internal;

import android.os.Binder;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes.dex */
public final class zzbv extends zzah {
    private final zzfa zzamz;
    private Boolean zzaql;

    @Nullable
    private String zzaqm;

    public zzbv(zzfa zzfaVar) {
        this(zzfaVar, null);
    }

    private zzbv(zzfa zzfaVar, @Nullable String str) {
        Preconditions.checkNotNull(zzfaVar);
        this.zzamz = zzfaVar;
        this.zzaqm = null;
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    @BinderThread
    public final void zzb(zzh zzhVar) {
        zzb(zzhVar, false);
        zze(new zzbw(this, zzhVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    @BinderThread
    public final void zza(zzad zzadVar, zzh zzhVar) {
        Preconditions.checkNotNull(zzadVar);
        zzb(zzhVar, false);
        zze(new zzcg(this, zzadVar, zzhVar));
    }

    @VisibleForTesting
    final zzad zzb(zzad zzadVar, zzh zzhVar) {
        boolean z = false;
        if ("_cmp".equals(zzadVar.name) && zzadVar.zzaid != null && zzadVar.zzaid.size() != 0) {
            String string = zzadVar.zzaid.getString("_cis");
            if (!TextUtils.isEmpty(string) && (("referrer broadcast".equals(string) || "referrer API".equals(string)) && this.zzamz.zzgq().zzbg(zzhVar.packageName))) {
                z = true;
            }
        }
        if (z) {
            this.zzamz.zzgo().zzjj().zzg("Event has been filtered ", zzadVar.toString());
            return new zzad("_cmpx", zzadVar.zzaid, zzadVar.origin, zzadVar.zzaip);
        }
        return zzadVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    @BinderThread
    public final void zza(zzad zzadVar, String str, String str2) {
        Preconditions.checkNotNull(zzadVar);
        Preconditions.checkNotEmpty(str);
        zzc(str, true);
        zze(new zzch(this, zzadVar, str));
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    @BinderThread
    public final byte[] zza(zzad zzadVar, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadVar);
        zzc(str, true);
        this.zzamz.zzgo().zzjk().zzg("Log and bundle. event", this.zzamz.zzgl().zzbs(zzadVar.name));
        long jNanoTime = this.zzamz.zzbx().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zzamz.zzgn().zzc(new zzci(this, zzadVar, str)).get();
            if (bArr == null) {
                this.zzamz.zzgo().zzjd().zzg("Log and bundle returned null. appId", zzap.zzbv(str));
                bArr = new byte[0];
            }
            this.zzamz.zzgo().zzjk().zzd("Log and bundle processed. event, size, time_ms", this.zzamz.zzgl().zzbs(zzadVar.name), Integer.valueOf(bArr.length), Long.valueOf((this.zzamz.zzbx().nanoTime() / 1000000) - jNanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e) {
            this.zzamz.zzgo().zzjd().zzd("Failed to log and bundle. appId, event, error", zzap.zzbv(str), this.zzamz.zzgl().zzbs(zzadVar.name), e);
            return null;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    @BinderThread
    public final void zza(zzfh zzfhVar, zzh zzhVar) {
        Preconditions.checkNotNull(zzfhVar);
        zzb(zzhVar, false);
        if (zzfhVar.getValue() == null) {
            zze(new zzcj(this, zzfhVar, zzhVar));
        } else {
            zze(new zzck(this, zzfhVar, zzhVar));
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    @BinderThread
    public final List<zzfh> zza(zzh zzhVar, boolean z) {
        zzb(zzhVar, false);
        try {
            List<zzfj> list = (List) this.zzamz.zzgn().zzb(new zzcl(this, zzhVar)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzfj zzfjVar : list) {
                if (z || !zzfk.zzcv(zzfjVar.name)) {
                    arrayList.add(new zzfh(zzfjVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzamz.zzgo().zzjd().zze("Failed to get user attributes. appId", zzap.zzbv(zzhVar.packageName), e);
            return null;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    @BinderThread
    public final void zza(zzh zzhVar) {
        zzb(zzhVar, false);
        zze(new zzcm(this, zzhVar));
    }

    @BinderThread
    private final void zzb(zzh zzhVar, boolean z) {
        Preconditions.checkNotNull(zzhVar);
        zzc(zzhVar.packageName, false);
        this.zzamz.zzgm().zzt(zzhVar.zzafx, zzhVar.zzagk);
    }

    @BinderThread
    private final void zzc(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            this.zzamz.zzgo().zzjd().zzbx("Measurement Service called without app package");
            throw new SecurityException("Measurement Service called without app package");
        }
        if (z) {
            try {
                if (this.zzaql == null) {
                    this.zzaql = Boolean.valueOf("com.google.android.gms".equals(this.zzaqm) || UidVerifier.isGooglePlayServicesUid(this.zzamz.getContext(), Binder.getCallingUid()) || GoogleSignatureVerifier.getInstance(this.zzamz.getContext()).isUidGoogleSigned(Binder.getCallingUid()));
                }
                if (this.zzaql.booleanValue()) {
                    return;
                }
            } catch (SecurityException e) {
                this.zzamz.zzgo().zzjd().zzg("Measurement Service called with invalid calling package. appId", zzap.zzbv(str));
                throw e;
            }
        }
        if (this.zzaqm == null && GooglePlayServicesUtilLight.uidHasPackageName(this.zzamz.getContext(), Binder.getCallingUid(), str)) {
            this.zzaqm = str;
        }
        if (!str.equals(this.zzaqm)) {
            throw new SecurityException(String.format("Unknown calling package name '%s'.", str));
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    @BinderThread
    public final void zza(long j, String str, String str2, String str3) {
        zze(new zzcn(this, str2, str3, str, j));
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    @BinderThread
    public final String zzc(zzh zzhVar) {
        zzb(zzhVar, false);
        return this.zzamz.zzh(zzhVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    @BinderThread
    public final void zza(zzl zzlVar, zzh zzhVar) {
        Preconditions.checkNotNull(zzlVar);
        Preconditions.checkNotNull(zzlVar.zzahb);
        zzb(zzhVar, false);
        zzl zzlVar2 = new zzl(zzlVar);
        zzlVar2.packageName = zzhVar.packageName;
        if (zzlVar.zzahb.getValue() == null) {
            zze(new zzbx(this, zzlVar2, zzhVar));
        } else {
            zze(new zzby(this, zzlVar2, zzhVar));
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    @BinderThread
    public final void zzb(zzl zzlVar) {
        Preconditions.checkNotNull(zzlVar);
        Preconditions.checkNotNull(zzlVar.zzahb);
        zzc(zzlVar.packageName, true);
        zzl zzlVar2 = new zzl(zzlVar);
        if (zzlVar.zzahb.getValue() == null) {
            zze(new zzbz(this, zzlVar2));
        } else {
            zze(new zzca(this, zzlVar2));
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    @BinderThread
    public final List<zzfh> zza(String str, String str2, boolean z, zzh zzhVar) {
        zzb(zzhVar, false);
        try {
            List<zzfj> list = (List) this.zzamz.zzgn().zzb(new zzcb(this, zzhVar, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzfj zzfjVar : list) {
                if (z || !zzfk.zzcv(zzfjVar.name)) {
                    arrayList.add(new zzfh(zzfjVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzamz.zzgo().zzjd().zze("Failed to get user attributes. appId", zzap.zzbv(zzhVar.packageName), e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    @BinderThread
    public final List<zzfh> zza(String str, String str2, String str3, boolean z) {
        zzc(str, true);
        try {
            List<zzfj> list = (List) this.zzamz.zzgn().zzb(new zzcc(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzfj zzfjVar : list) {
                if (z || !zzfk.zzcv(zzfjVar.name)) {
                    arrayList.add(new zzfh(zzfjVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzamz.zzgo().zzjd().zze("Failed to get user attributes. appId", zzap.zzbv(str), e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    @BinderThread
    public final List<zzl> zza(String str, String str2, zzh zzhVar) {
        zzb(zzhVar, false);
        try {
            return (List) this.zzamz.zzgn().zzb(new zzcd(this, zzhVar, str, str2)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zzamz.zzgo().zzjd().zzg("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    @BinderThread
    public final List<zzl> zze(String str, String str2, String str3) {
        zzc(str, true);
        try {
            return (List) this.zzamz.zzgn().zzb(new zzce(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zzamz.zzgo().zzjd().zzg("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    @BinderThread
    public final void zzd(zzh zzhVar) {
        zzc(zzhVar.packageName, false);
        zze(new zzcf(this, zzhVar));
    }

    @VisibleForTesting
    private final void zze(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (zzaf.zzakv.get().booleanValue() && this.zzamz.zzgn().zzkb()) {
            runnable.run();
        } else {
            this.zzamz.zzgn().zzc(runnable);
        }
    }
}
