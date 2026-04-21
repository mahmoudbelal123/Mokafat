package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public class zzwa {
    private static final zzuz zzbtt = zzuz.zzvo();
    private zzud zzcad;
    private volatile zzwt zzcae;
    private volatile zzud zzcaf;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzwa)) {
            return false;
        }
        zzwa zzwaVar = (zzwa) obj;
        zzwt zzwtVar = this.zzcae;
        zzwt zzwtVar2 = zzwaVar.zzcae;
        if (zzwtVar == null && zzwtVar2 == null) {
            return zztt().equals(zzwaVar.zztt());
        }
        if (zzwtVar != null && zzwtVar2 != null) {
            return zzwtVar.equals(zzwtVar2);
        }
        if (zzwtVar != null) {
            return zzwtVar.equals(zzwaVar.zzh(zzwtVar.zzwf()));
        }
        return zzh(zzwtVar2.zzwf()).equals(zzwtVar2);
    }

    public int hashCode() {
        return 1;
    }

    private final zzwt zzh(zzwt zzwtVar) {
        if (this.zzcae == null) {
            synchronized (this) {
                if (this.zzcae == null) {
                    try {
                        this.zzcae = zzwtVar;
                        this.zzcaf = zzud.zzbtz;
                    } catch (zzvt e) {
                        this.zzcae = zzwtVar;
                        this.zzcaf = zzud.zzbtz;
                    }
                }
            }
        }
        return this.zzcae;
    }

    public final zzwt zzi(zzwt zzwtVar) {
        zzwt zzwtVar2 = this.zzcae;
        this.zzcad = null;
        this.zzcaf = null;
        this.zzcae = zzwtVar;
        return zzwtVar2;
    }

    public final int zzvu() {
        if (this.zzcaf != null) {
            return this.zzcaf.size();
        }
        if (this.zzcae != null) {
            return this.zzcae.zzvu();
        }
        return 0;
    }

    public final zzud zztt() {
        if (this.zzcaf != null) {
            return this.zzcaf;
        }
        synchronized (this) {
            if (this.zzcaf != null) {
                return this.zzcaf;
            }
            if (this.zzcae == null) {
                this.zzcaf = zzud.zzbtz;
            } else {
                this.zzcaf = this.zzcae.zztt();
            }
            return this.zzcaf;
        }
    }
}
