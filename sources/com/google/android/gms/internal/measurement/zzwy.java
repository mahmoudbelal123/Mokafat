package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
final class zzwy<T> implements zzxj<T> {
    private final zzwt zzcbd;
    private final boolean zzcbe;
    private final zzyb<?, ?> zzcbn;
    private final zzva<?> zzcbo;

    private zzwy(zzyb<?, ?> zzybVar, zzva<?> zzvaVar, zzwt zzwtVar) {
        this.zzcbn = zzybVar;
        this.zzcbe = zzvaVar.zze(zzwtVar);
        this.zzcbo = zzvaVar;
        this.zzcbd = zzwtVar;
    }

    static <T> zzwy<T> zza(zzyb<?, ?> zzybVar, zzva<?> zzvaVar, zzwt zzwtVar) {
        return new zzwy<>(zzybVar, zzvaVar, zzwtVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzxj
    public final T newInstance() {
        return (T) this.zzcbd.zzwe().zzwi();
    }

    @Override // com.google.android.gms.internal.measurement.zzxj
    public final boolean equals(T t, T t2) {
        if (!this.zzcbn.zzah(t).equals(this.zzcbn.zzah(t2))) {
            return false;
        }
        if (this.zzcbe) {
            return this.zzcbo.zzs(t).equals(this.zzcbo.zzs(t2));
        }
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzxj
    public final int hashCode(T t) {
        int iHashCode = this.zzcbn.zzah(t).hashCode();
        if (this.zzcbe) {
            return (iHashCode * 53) + this.zzcbo.zzs(t).hashCode();
        }
        return iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzxj
    public final void zzd(T t, T t2) {
        zzxl.zza(this.zzcbn, t, t2);
        if (this.zzcbe) {
            zzxl.zza(this.zzcbo, t, t2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzxj
    public final void zza(T t, zzyw zzywVar) throws IOException {
        for (T t2 : this.zzcbo.zzs(t)) {
            zzvf zzvfVar = (zzvf) t2.getKey();
            if (zzvfVar.zzvx() != zzyv.MESSAGE || zzvfVar.zzvy() || zzvfVar.zzvz()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (t2 instanceof zzvy) {
                zzywVar.zza(zzvfVar.zzc(), (Object) ((zzvy) t2).zzwu().zztt());
            } else {
                zzywVar.zza(zzvfVar.zzc(), t2.getValue());
            }
        }
        zzyb<?, ?> zzybVar = this.zzcbn;
        zzybVar.zzc(zzybVar.zzah(t), zzywVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x008f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[LOOP:0: B:46:0x000c->B:54:?, LOOP_END, SYNTHETIC] */
    @Override // com.google.android.gms.internal.measurement.zzxj
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r11, com.google.android.gms.internal.measurement.zzxi r12, com.google.android.gms.internal.measurement.zzuz r13) throws java.io.IOException {
        /*
            r10 = this;
            com.google.android.gms.internal.measurement.zzyb<?, ?> r0 = r10.zzcbn
            com.google.android.gms.internal.measurement.zzva<?> r1 = r10.zzcbo
            java.lang.Object r2 = r0.zzai(r11)
            com.google.android.gms.internal.measurement.zzvd r3 = r1.zzt(r11)
        Lc:
            int r4 = r12.zzve()     // Catch: java.lang.Throwable -> L93
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 != r5) goto L19
            r0.zzg(r11, r2)
            return
        L19:
            int r4 = r12.getTag()     // Catch: java.lang.Throwable -> L93
            r6 = 11
            if (r4 == r6) goto L40
        L23:
            r5 = r4 & 7
            r6 = 2
            if (r5 != r6) goto L3b
            com.google.android.gms.internal.measurement.zzwt r5 = r10.zzcbd     // Catch: java.lang.Throwable -> L93
            int r4 = r4 >>> 3
            java.lang.Object r4 = r1.zza(r13, r5, r4)     // Catch: java.lang.Throwable -> L93
            if (r4 == 0) goto L36
            r1.zza(r12, r4, r13, r3)     // Catch: java.lang.Throwable -> L93
            goto L8c
        L36:
            boolean r4 = r0.zza(r2, r12)     // Catch: java.lang.Throwable -> L93
            goto L8d
        L3b:
            boolean r4 = r12.zzvf()     // Catch: java.lang.Throwable -> L93
            goto L8d
        L40:
            r4 = 0
            r6 = 0
            r7 = r4
            r4 = r6
        L46:
            int r8 = r12.zzve()     // Catch: java.lang.Throwable -> L93
            if (r8 == r5) goto L74
            int r8 = r12.getTag()     // Catch: java.lang.Throwable -> L93
            r9 = 16
            if (r8 != r9) goto L5f
            int r7 = r12.zzup()     // Catch: java.lang.Throwable -> L93
            com.google.android.gms.internal.measurement.zzwt r6 = r10.zzcbd     // Catch: java.lang.Throwable -> L93
            java.lang.Object r6 = r1.zza(r13, r6, r7)     // Catch: java.lang.Throwable -> L93
            goto L46
        L5f:
            r9 = 26
            if (r8 != r9) goto L6e
            if (r6 == 0) goto L69
            r1.zza(r12, r6, r13, r3)     // Catch: java.lang.Throwable -> L93
            goto L46
        L69:
            com.google.android.gms.internal.measurement.zzud r4 = r12.zzuo()     // Catch: java.lang.Throwable -> L93
            goto L46
        L6e:
            boolean r8 = r12.zzvf()     // Catch: java.lang.Throwable -> L93
            if (r8 != 0) goto L46
        L74:
            int r5 = r12.getTag()     // Catch: java.lang.Throwable -> L93
            r8 = 12
            if (r5 == r8) goto L81
            com.google.android.gms.internal.measurement.zzvt r12 = com.google.android.gms.internal.measurement.zzvt.zzwn()     // Catch: java.lang.Throwable -> L93
            throw r12     // Catch: java.lang.Throwable -> L93
        L81:
            if (r4 == 0) goto L8c
            if (r6 == 0) goto L89
            r1.zza(r4, r6, r13, r3)     // Catch: java.lang.Throwable -> L93
            goto L8c
        L89:
            r0.zza(r2, r7, r4)     // Catch: java.lang.Throwable -> L93
        L8c:
            r4 = 1
        L8d:
            if (r4 != 0) goto Lc
            r0.zzg(r11, r2)
            return
        L93:
            r12 = move-exception
            r0.zzg(r11, r2)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzwy.zza(java.lang.Object, com.google.android.gms.internal.measurement.zzxi, com.google.android.gms.internal.measurement.zzuz):void");
    }

    @Override // com.google.android.gms.internal.measurement.zzxj
    public final void zzu(T t) {
        this.zzcbn.zzu(t);
        this.zzcbo.zzu(t);
    }

    @Override // com.google.android.gms.internal.measurement.zzxj
    public final boolean zzaf(T t) {
        return this.zzcbo.zzs(t).isInitialized();
    }

    @Override // com.google.android.gms.internal.measurement.zzxj
    public final int zzae(T t) {
        zzyb<?, ?> zzybVar = this.zzcbn;
        int iZzaj = 0 + zzybVar.zzaj(zzybVar.zzah(t));
        if (this.zzcbe) {
            return iZzaj + this.zzcbo.zzs(t).zzvv();
        }
        return iZzaj;
    }
}
