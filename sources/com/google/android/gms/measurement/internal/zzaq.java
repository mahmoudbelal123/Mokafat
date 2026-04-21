package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
final class zzaq implements Runnable {
    private final /* synthetic */ int zzamh;
    private final /* synthetic */ String zzami;
    private final /* synthetic */ Object zzamj;
    private final /* synthetic */ Object zzamk;
    private final /* synthetic */ Object zzaml;
    private final /* synthetic */ zzap zzamm;

    zzaq(zzap zzapVar, int i, String str, Object obj, Object obj2, Object obj3) {
        this.zzamm = zzapVar;
        this.zzamh = i;
        this.zzami = str;
        this.zzamj = obj;
        this.zzamk = obj2;
        this.zzaml = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzba zzbaVarZzgp = this.zzamm.zzadj.zzgp();
        if (!zzbaVarZzgp.isInitialized()) {
            this.zzamm.zza(6, "Persisted config not initialized. Not logging error/warn");
            return;
        }
        if (this.zzamm.zzalw == 0) {
            if (this.zzamm.zzgq().zzdw()) {
                zzap zzapVar = this.zzamm;
                this.zzamm.zzgr();
                zzapVar.zzalw = 'C';
            } else {
                zzap zzapVar2 = this.zzamm;
                this.zzamm.zzgr();
                zzapVar2.zzalw = 'c';
            }
        }
        if (this.zzamm.zzadt < 0) {
            this.zzamm.zzadt = this.zzamm.zzgq().zzhc();
        }
        char cCharAt = "01VDIWEA?".charAt(this.zzamh);
        char c = this.zzamm.zzalw;
        long j = this.zzamm.zzadt;
        String strZza = zzap.zza(true, this.zzami, this.zzamj, this.zzamk, this.zzaml);
        StringBuilder sb = new StringBuilder(24 + String.valueOf(strZza).length());
        sb.append("2");
        sb.append(cCharAt);
        sb.append(c);
        sb.append(j);
        sb.append(":");
        sb.append(strZza);
        String string = sb.toString();
        if (string.length() > 1024) {
            string = this.zzami.substring(0, 1024);
        }
        zzbaVarZzgp.zzand.zzc(string, 1L);
    }
}
