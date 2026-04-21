package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzdj implements Runnable {
    private final /* synthetic */ boolean zzaes;
    private final /* synthetic */ zzcs zzarc;

    zzdj(zzcs zzcsVar, boolean z) {
        this.zzarc = zzcsVar;
        this.zzaes = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zIsEnabled = this.zzarc.zzadj.isEnabled();
        boolean zZzko = this.zzarc.zzadj.zzko();
        this.zzarc.zzadj.zzd(this.zzaes);
        if (zZzko == this.zzaes) {
            this.zzarc.zzadj.zzgo().zzjl().zzg("Default data collection state already set to", Boolean.valueOf(this.zzaes));
        }
        if (this.zzarc.zzadj.isEnabled() == zIsEnabled || this.zzarc.zzadj.isEnabled() != this.zzarc.zzadj.zzko()) {
            this.zzarc.zzadj.zzgo().zzji().zze("Default data collection is different than actual status", Boolean.valueOf(this.zzaes), Boolean.valueOf(zIsEnabled));
        }
        this.zzarc.zzky();
    }
}
