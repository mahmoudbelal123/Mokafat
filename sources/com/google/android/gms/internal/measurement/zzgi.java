package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfq;
import es.dmoral.toasty.BuildConfig;
import java.io.IOException;
import quadrant.mokafat.points.notification.MyNotificationManager;

/* JADX INFO: loaded from: classes.dex */
public final class zzgi extends zzza<zzgi> {
    private static volatile zzgi[] zzawz;
    public Integer zzaxa = null;
    public zzgf[] zzaxb = zzgf.zzmq();
    public zzgl[] zzaxc = zzgl.zzmu();
    public Long zzaxd = null;
    public Long zzaxe = null;
    public Long zzaxf = null;
    public Long zzaxg = null;
    public Long zzaxh = null;
    public String zzaxi = null;
    public String zzaxj = null;
    public String zzaxk = null;
    public String zzaia = null;
    public Integer zzaxl = null;
    public String zzage = null;
    public String zztt = null;
    public String zzts = null;
    public Long zzaxm = null;
    public Long zzaxn = null;
    public String zzaxo = null;
    public Boolean zzaxp = null;
    public String zzafw = null;
    public Long zzaxq = null;
    public Integer zzaxr = null;
    public String zzagv = null;
    public String zzafx = null;
    public Boolean zzaxs = null;
    public zzgd[] zzaxt = zzgd.zzmo();
    public String zzafz = null;
    public Integer zzaxu = null;
    private Integer zzaxv = null;
    private Integer zzaxw = null;
    public String zzaxx = null;
    public Long zzaxy = null;
    public Long zzaxz = null;
    public String zzaya = null;
    private String zzayb = null;
    public Integer zzayc = null;
    public String zzawj = null;
    private zzfq.zzb zzayd = null;

    public static zzgi[] zzms() {
        if (zzawz == null) {
            synchronized (zzze.zzcfl) {
                if (zzawz == null) {
                    zzawz = new zzgi[0];
                }
            }
        }
        return zzawz;
    }

    public zzgi() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgi)) {
            return false;
        }
        zzgi zzgiVar = (zzgi) obj;
        if (this.zzaxa == null) {
            if (zzgiVar.zzaxa != null) {
                return false;
            }
        } else if (!this.zzaxa.equals(zzgiVar.zzaxa)) {
            return false;
        }
        if (!zzze.equals(this.zzaxb, zzgiVar.zzaxb) || !zzze.equals(this.zzaxc, zzgiVar.zzaxc)) {
            return false;
        }
        if (this.zzaxd == null) {
            if (zzgiVar.zzaxd != null) {
                return false;
            }
        } else if (!this.zzaxd.equals(zzgiVar.zzaxd)) {
            return false;
        }
        if (this.zzaxe == null) {
            if (zzgiVar.zzaxe != null) {
                return false;
            }
        } else if (!this.zzaxe.equals(zzgiVar.zzaxe)) {
            return false;
        }
        if (this.zzaxf == null) {
            if (zzgiVar.zzaxf != null) {
                return false;
            }
        } else if (!this.zzaxf.equals(zzgiVar.zzaxf)) {
            return false;
        }
        if (this.zzaxg == null) {
            if (zzgiVar.zzaxg != null) {
                return false;
            }
        } else if (!this.zzaxg.equals(zzgiVar.zzaxg)) {
            return false;
        }
        if (this.zzaxh == null) {
            if (zzgiVar.zzaxh != null) {
                return false;
            }
        } else if (!this.zzaxh.equals(zzgiVar.zzaxh)) {
            return false;
        }
        if (this.zzaxi == null) {
            if (zzgiVar.zzaxi != null) {
                return false;
            }
        } else if (!this.zzaxi.equals(zzgiVar.zzaxi)) {
            return false;
        }
        if (this.zzaxj == null) {
            if (zzgiVar.zzaxj != null) {
                return false;
            }
        } else if (!this.zzaxj.equals(zzgiVar.zzaxj)) {
            return false;
        }
        if (this.zzaxk == null) {
            if (zzgiVar.zzaxk != null) {
                return false;
            }
        } else if (!this.zzaxk.equals(zzgiVar.zzaxk)) {
            return false;
        }
        if (this.zzaia == null) {
            if (zzgiVar.zzaia != null) {
                return false;
            }
        } else if (!this.zzaia.equals(zzgiVar.zzaia)) {
            return false;
        }
        if (this.zzaxl == null) {
            if (zzgiVar.zzaxl != null) {
                return false;
            }
        } else if (!this.zzaxl.equals(zzgiVar.zzaxl)) {
            return false;
        }
        if (this.zzage == null) {
            if (zzgiVar.zzage != null) {
                return false;
            }
        } else if (!this.zzage.equals(zzgiVar.zzage)) {
            return false;
        }
        if (this.zztt == null) {
            if (zzgiVar.zztt != null) {
                return false;
            }
        } else if (!this.zztt.equals(zzgiVar.zztt)) {
            return false;
        }
        if (this.zzts == null) {
            if (zzgiVar.zzts != null) {
                return false;
            }
        } else if (!this.zzts.equals(zzgiVar.zzts)) {
            return false;
        }
        if (this.zzaxm == null) {
            if (zzgiVar.zzaxm != null) {
                return false;
            }
        } else if (!this.zzaxm.equals(zzgiVar.zzaxm)) {
            return false;
        }
        if (this.zzaxn == null) {
            if (zzgiVar.zzaxn != null) {
                return false;
            }
        } else if (!this.zzaxn.equals(zzgiVar.zzaxn)) {
            return false;
        }
        if (this.zzaxo == null) {
            if (zzgiVar.zzaxo != null) {
                return false;
            }
        } else if (!this.zzaxo.equals(zzgiVar.zzaxo)) {
            return false;
        }
        if (this.zzaxp == null) {
            if (zzgiVar.zzaxp != null) {
                return false;
            }
        } else if (!this.zzaxp.equals(zzgiVar.zzaxp)) {
            return false;
        }
        if (this.zzafw == null) {
            if (zzgiVar.zzafw != null) {
                return false;
            }
        } else if (!this.zzafw.equals(zzgiVar.zzafw)) {
            return false;
        }
        if (this.zzaxq == null) {
            if (zzgiVar.zzaxq != null) {
                return false;
            }
        } else if (!this.zzaxq.equals(zzgiVar.zzaxq)) {
            return false;
        }
        if (this.zzaxr == null) {
            if (zzgiVar.zzaxr != null) {
                return false;
            }
        } else if (!this.zzaxr.equals(zzgiVar.zzaxr)) {
            return false;
        }
        if (this.zzagv == null) {
            if (zzgiVar.zzagv != null) {
                return false;
            }
        } else if (!this.zzagv.equals(zzgiVar.zzagv)) {
            return false;
        }
        if (this.zzafx == null) {
            if (zzgiVar.zzafx != null) {
                return false;
            }
        } else if (!this.zzafx.equals(zzgiVar.zzafx)) {
            return false;
        }
        if (this.zzaxs == null) {
            if (zzgiVar.zzaxs != null) {
                return false;
            }
        } else if (!this.zzaxs.equals(zzgiVar.zzaxs)) {
            return false;
        }
        if (!zzze.equals(this.zzaxt, zzgiVar.zzaxt)) {
            return false;
        }
        if (this.zzafz == null) {
            if (zzgiVar.zzafz != null) {
                return false;
            }
        } else if (!this.zzafz.equals(zzgiVar.zzafz)) {
            return false;
        }
        if (this.zzaxu == null) {
            if (zzgiVar.zzaxu != null) {
                return false;
            }
        } else if (!this.zzaxu.equals(zzgiVar.zzaxu)) {
            return false;
        }
        if (this.zzaxv == null) {
            if (zzgiVar.zzaxv != null) {
                return false;
            }
        } else if (!this.zzaxv.equals(zzgiVar.zzaxv)) {
            return false;
        }
        if (this.zzaxw == null) {
            if (zzgiVar.zzaxw != null) {
                return false;
            }
        } else if (!this.zzaxw.equals(zzgiVar.zzaxw)) {
            return false;
        }
        if (this.zzaxx == null) {
            if (zzgiVar.zzaxx != null) {
                return false;
            }
        } else if (!this.zzaxx.equals(zzgiVar.zzaxx)) {
            return false;
        }
        if (this.zzaxy == null) {
            if (zzgiVar.zzaxy != null) {
                return false;
            }
        } else if (!this.zzaxy.equals(zzgiVar.zzaxy)) {
            return false;
        }
        if (this.zzaxz == null) {
            if (zzgiVar.zzaxz != null) {
                return false;
            }
        } else if (!this.zzaxz.equals(zzgiVar.zzaxz)) {
            return false;
        }
        if (this.zzaya == null) {
            if (zzgiVar.zzaya != null) {
                return false;
            }
        } else if (!this.zzaya.equals(zzgiVar.zzaya)) {
            return false;
        }
        if (this.zzayb == null) {
            if (zzgiVar.zzayb != null) {
                return false;
            }
        } else if (!this.zzayb.equals(zzgiVar.zzayb)) {
            return false;
        }
        if (this.zzayc == null) {
            if (zzgiVar.zzayc != null) {
                return false;
            }
        } else if (!this.zzayc.equals(zzgiVar.zzayc)) {
            return false;
        }
        if (this.zzawj == null) {
            if (zzgiVar.zzawj != null) {
                return false;
            }
        } else if (!this.zzawj.equals(zzgiVar.zzawj)) {
            return false;
        }
        if (this.zzayd == null) {
            if (zzgiVar.zzayd != null) {
                return false;
            }
        } else if (!this.zzayd.equals(zzgiVar.zzayd)) {
            return false;
        }
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            return zzgiVar.zzcfc == null || zzgiVar.zzcfc.isEmpty();
        }
        return this.zzcfc.equals(zzgiVar.zzcfc);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((527 + getClass().getName().hashCode()) * 31) + (this.zzaxa == null ? 0 : this.zzaxa.hashCode())) * 31) + zzze.hashCode(this.zzaxb)) * 31) + zzze.hashCode(this.zzaxc)) * 31) + (this.zzaxd == null ? 0 : this.zzaxd.hashCode())) * 31) + (this.zzaxe == null ? 0 : this.zzaxe.hashCode())) * 31) + (this.zzaxf == null ? 0 : this.zzaxf.hashCode())) * 31) + (this.zzaxg == null ? 0 : this.zzaxg.hashCode())) * 31) + (this.zzaxh == null ? 0 : this.zzaxh.hashCode())) * 31) + (this.zzaxi == null ? 0 : this.zzaxi.hashCode())) * 31) + (this.zzaxj == null ? 0 : this.zzaxj.hashCode())) * 31) + (this.zzaxk == null ? 0 : this.zzaxk.hashCode())) * 31) + (this.zzaia == null ? 0 : this.zzaia.hashCode())) * 31) + (this.zzaxl == null ? 0 : this.zzaxl.hashCode())) * 31) + (this.zzage == null ? 0 : this.zzage.hashCode())) * 31) + (this.zztt == null ? 0 : this.zztt.hashCode())) * 31) + (this.zzts == null ? 0 : this.zzts.hashCode())) * 31) + (this.zzaxm == null ? 0 : this.zzaxm.hashCode())) * 31) + (this.zzaxn == null ? 0 : this.zzaxn.hashCode())) * 31) + (this.zzaxo == null ? 0 : this.zzaxo.hashCode())) * 31) + (this.zzaxp == null ? 0 : this.zzaxp.hashCode())) * 31) + (this.zzafw == null ? 0 : this.zzafw.hashCode())) * 31) + (this.zzaxq == null ? 0 : this.zzaxq.hashCode())) * 31) + (this.zzaxr == null ? 0 : this.zzaxr.hashCode())) * 31) + (this.zzagv == null ? 0 : this.zzagv.hashCode())) * 31) + (this.zzafx == null ? 0 : this.zzafx.hashCode())) * 31) + (this.zzaxs == null ? 0 : this.zzaxs.hashCode())) * 31) + zzze.hashCode(this.zzaxt)) * 31) + (this.zzafz == null ? 0 : this.zzafz.hashCode())) * 31) + (this.zzaxu == null ? 0 : this.zzaxu.hashCode())) * 31) + (this.zzaxv == null ? 0 : this.zzaxv.hashCode())) * 31) + (this.zzaxw == null ? 0 : this.zzaxw.hashCode())) * 31) + (this.zzaxx == null ? 0 : this.zzaxx.hashCode())) * 31) + (this.zzaxy == null ? 0 : this.zzaxy.hashCode())) * 31) + (this.zzaxz == null ? 0 : this.zzaxz.hashCode())) * 31) + (this.zzaya == null ? 0 : this.zzaya.hashCode())) * 31) + (this.zzayb == null ? 0 : this.zzayb.hashCode())) * 31) + (this.zzayc == null ? 0 : this.zzayc.hashCode())) * 31) + (this.zzawj == null ? 0 : this.zzawj.hashCode());
        zzfq.zzb zzbVar = this.zzayd;
        int iHashCode3 = ((iHashCode2 * 31) + (zzbVar == null ? 0 : zzbVar.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            iHashCode = this.zzcfc.hashCode();
        }
        return iHashCode3 + iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzaxa != null) {
            zzyyVar.zzd(1, this.zzaxa.intValue());
        }
        if (this.zzaxb != null && this.zzaxb.length > 0) {
            for (int i = 0; i < this.zzaxb.length; i++) {
                zzgf zzgfVar = this.zzaxb[i];
                if (zzgfVar != null) {
                    zzyyVar.zza(2, zzgfVar);
                }
            }
        }
        if (this.zzaxc != null && this.zzaxc.length > 0) {
            for (int i2 = 0; i2 < this.zzaxc.length; i2++) {
                zzgl zzglVar = this.zzaxc[i2];
                if (zzglVar != null) {
                    zzyyVar.zza(3, zzglVar);
                }
            }
        }
        if (this.zzaxd != null) {
            zzyyVar.zzi(4, this.zzaxd.longValue());
        }
        if (this.zzaxe != null) {
            zzyyVar.zzi(5, this.zzaxe.longValue());
        }
        if (this.zzaxf != null) {
            zzyyVar.zzi(6, this.zzaxf.longValue());
        }
        if (this.zzaxh != null) {
            zzyyVar.zzi(7, this.zzaxh.longValue());
        }
        if (this.zzaxi != null) {
            zzyyVar.zzb(8, this.zzaxi);
        }
        if (this.zzaxj != null) {
            zzyyVar.zzb(9, this.zzaxj);
        }
        if (this.zzaxk != null) {
            zzyyVar.zzb(10, this.zzaxk);
        }
        if (this.zzaia != null) {
            zzyyVar.zzb(11, this.zzaia);
        }
        if (this.zzaxl != null) {
            zzyyVar.zzd(12, this.zzaxl.intValue());
        }
        if (this.zzage != null) {
            zzyyVar.zzb(13, this.zzage);
        }
        if (this.zztt != null) {
            zzyyVar.zzb(14, this.zztt);
        }
        if (this.zzts != null) {
            zzyyVar.zzb(16, this.zzts);
        }
        if (this.zzaxm != null) {
            zzyyVar.zzi(17, this.zzaxm.longValue());
        }
        if (this.zzaxn != null) {
            zzyyVar.zzi(18, this.zzaxn.longValue());
        }
        if (this.zzaxo != null) {
            zzyyVar.zzb(19, this.zzaxo);
        }
        if (this.zzaxp != null) {
            zzyyVar.zzb(20, this.zzaxp.booleanValue());
        }
        if (this.zzafw != null) {
            zzyyVar.zzb(21, this.zzafw);
        }
        if (this.zzaxq != null) {
            zzyyVar.zzi(22, this.zzaxq.longValue());
        }
        if (this.zzaxr != null) {
            zzyyVar.zzd(23, this.zzaxr.intValue());
        }
        if (this.zzagv != null) {
            zzyyVar.zzb(24, this.zzagv);
        }
        if (this.zzafx != null) {
            zzyyVar.zzb(25, this.zzafx);
        }
        if (this.zzaxg != null) {
            zzyyVar.zzi(26, this.zzaxg.longValue());
        }
        if (this.zzaxs != null) {
            zzyyVar.zzb(28, this.zzaxs.booleanValue());
        }
        if (this.zzaxt != null && this.zzaxt.length > 0) {
            for (int i3 = 0; i3 < this.zzaxt.length; i3++) {
                zzgd zzgdVar = this.zzaxt[i3];
                if (zzgdVar != null) {
                    zzyyVar.zza(29, zzgdVar);
                }
            }
        }
        if (this.zzafz != null) {
            zzyyVar.zzb(30, this.zzafz);
        }
        if (this.zzaxu != null) {
            zzyyVar.zzd(31, this.zzaxu.intValue());
        }
        if (this.zzaxv != null) {
            zzyyVar.zzd(32, this.zzaxv.intValue());
        }
        if (this.zzaxw != null) {
            zzyyVar.zzd(33, this.zzaxw.intValue());
        }
        if (this.zzaxx != null) {
            zzyyVar.zzb(34, this.zzaxx);
        }
        if (this.zzaxy != null) {
            zzyyVar.zzi(35, this.zzaxy.longValue());
        }
        if (this.zzaxz != null) {
            zzyyVar.zzi(36, this.zzaxz.longValue());
        }
        if (this.zzaya != null) {
            zzyyVar.zzb(37, this.zzaya);
        }
        if (this.zzayb != null) {
            zzyyVar.zzb(38, this.zzayb);
        }
        if (this.zzayc != null) {
            zzyyVar.zzd(39, this.zzayc.intValue());
        }
        if (this.zzawj != null) {
            zzyyVar.zzb(41, this.zzawj);
        }
        if (this.zzayd != null) {
            zzyyVar.zze(44, this.zzayd);
        }
        super.zza(zzyyVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    protected final int zzf() {
        int iZzf = super.zzf();
        if (this.zzaxa != null) {
            iZzf += zzyy.zzh(1, this.zzaxa.intValue());
        }
        if (this.zzaxb != null && this.zzaxb.length > 0) {
            int iZzb = iZzf;
            for (int i = 0; i < this.zzaxb.length; i++) {
                zzgf zzgfVar = this.zzaxb[i];
                if (zzgfVar != null) {
                    iZzb += zzyy.zzb(2, zzgfVar);
                }
            }
            iZzf = iZzb;
        }
        if (this.zzaxc != null && this.zzaxc.length > 0) {
            int iZzb2 = iZzf;
            for (int i2 = 0; i2 < this.zzaxc.length; i2++) {
                zzgl zzglVar = this.zzaxc[i2];
                if (zzglVar != null) {
                    iZzb2 += zzyy.zzb(3, zzglVar);
                }
            }
            iZzf = iZzb2;
        }
        if (this.zzaxd != null) {
            iZzf += zzyy.zzd(4, this.zzaxd.longValue());
        }
        if (this.zzaxe != null) {
            iZzf += zzyy.zzd(5, this.zzaxe.longValue());
        }
        if (this.zzaxf != null) {
            iZzf += zzyy.zzd(6, this.zzaxf.longValue());
        }
        if (this.zzaxh != null) {
            iZzf += zzyy.zzd(7, this.zzaxh.longValue());
        }
        if (this.zzaxi != null) {
            iZzf += zzyy.zzc(8, this.zzaxi);
        }
        if (this.zzaxj != null) {
            iZzf += zzyy.zzc(9, this.zzaxj);
        }
        if (this.zzaxk != null) {
            iZzf += zzyy.zzc(10, this.zzaxk);
        }
        if (this.zzaia != null) {
            iZzf += zzyy.zzc(11, this.zzaia);
        }
        if (this.zzaxl != null) {
            iZzf += zzyy.zzh(12, this.zzaxl.intValue());
        }
        if (this.zzage != null) {
            iZzf += zzyy.zzc(13, this.zzage);
        }
        if (this.zztt != null) {
            iZzf += zzyy.zzc(14, this.zztt);
        }
        if (this.zzts != null) {
            iZzf += zzyy.zzc(16, this.zzts);
        }
        if (this.zzaxm != null) {
            iZzf += zzyy.zzd(17, this.zzaxm.longValue());
        }
        if (this.zzaxn != null) {
            iZzf += zzyy.zzd(18, this.zzaxn.longValue());
        }
        if (this.zzaxo != null) {
            iZzf += zzyy.zzc(19, this.zzaxo);
        }
        if (this.zzaxp != null) {
            this.zzaxp.booleanValue();
            iZzf += zzyy.zzbb(20) + 1;
        }
        if (this.zzafw != null) {
            iZzf += zzyy.zzc(21, this.zzafw);
        }
        if (this.zzaxq != null) {
            iZzf += zzyy.zzd(22, this.zzaxq.longValue());
        }
        if (this.zzaxr != null) {
            iZzf += zzyy.zzh(23, this.zzaxr.intValue());
        }
        if (this.zzagv != null) {
            iZzf += zzyy.zzc(24, this.zzagv);
        }
        if (this.zzafx != null) {
            iZzf += zzyy.zzc(25, this.zzafx);
        }
        if (this.zzaxg != null) {
            iZzf += zzyy.zzd(26, this.zzaxg.longValue());
        }
        if (this.zzaxs != null) {
            this.zzaxs.booleanValue();
            iZzf += zzyy.zzbb(28) + 1;
        }
        if (this.zzaxt != null && this.zzaxt.length > 0) {
            for (int i3 = 0; i3 < this.zzaxt.length; i3++) {
                zzgd zzgdVar = this.zzaxt[i3];
                if (zzgdVar != null) {
                    iZzf += zzyy.zzb(29, zzgdVar);
                }
            }
        }
        if (this.zzafz != null) {
            iZzf += zzyy.zzc(30, this.zzafz);
        }
        if (this.zzaxu != null) {
            iZzf += zzyy.zzh(31, this.zzaxu.intValue());
        }
        if (this.zzaxv != null) {
            iZzf += zzyy.zzh(32, this.zzaxv.intValue());
        }
        if (this.zzaxw != null) {
            iZzf += zzyy.zzh(33, this.zzaxw.intValue());
        }
        if (this.zzaxx != null) {
            iZzf += zzyy.zzc(34, this.zzaxx);
        }
        if (this.zzaxy != null) {
            iZzf += zzyy.zzd(35, this.zzaxy.longValue());
        }
        if (this.zzaxz != null) {
            iZzf += zzyy.zzd(36, this.zzaxz.longValue());
        }
        if (this.zzaya != null) {
            iZzf += zzyy.zzc(37, this.zzaya);
        }
        if (this.zzayb != null) {
            iZzf += zzyy.zzc(38, this.zzayb);
        }
        if (this.zzayc != null) {
            iZzf += zzyy.zzh(39, this.zzayc.intValue());
        }
        if (this.zzawj != null) {
            iZzf += zzyy.zzc(41, this.zzawj);
        }
        if (this.zzayd != null) {
            return iZzf + zzut.zzc(44, this.zzayd);
        }
        return iZzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzzg
    public final /* synthetic */ zzzg zza(zzyx zzyxVar) throws IOException {
        int length;
        int length2;
        int length3;
        while (true) {
            int iZzug = zzyxVar.zzug();
            switch (iZzug) {
                case 0:
                    return this;
                case 8:
                    this.zzaxa = Integer.valueOf(zzyxVar.zzuy());
                    break;
                case 18:
                    int iZzb = zzzj.zzb(zzyxVar, 18);
                    if (this.zzaxb != null) {
                        length = this.zzaxb.length;
                    } else {
                        length = 0;
                    }
                    zzgf[] zzgfVarArr = new zzgf[iZzb + length];
                    if (length != 0) {
                        System.arraycopy(this.zzaxb, 0, zzgfVarArr, 0, length);
                    }
                    while (length < zzgfVarArr.length - 1) {
                        zzgfVarArr[length] = new zzgf();
                        zzyxVar.zza(zzgfVarArr[length]);
                        zzyxVar.zzug();
                        length++;
                    }
                    zzgfVarArr[length] = new zzgf();
                    zzyxVar.zza(zzgfVarArr[length]);
                    this.zzaxb = zzgfVarArr;
                    break;
                case 26:
                    int iZzb2 = zzzj.zzb(zzyxVar, 26);
                    if (this.zzaxc != null) {
                        length2 = this.zzaxc.length;
                    } else {
                        length2 = 0;
                    }
                    zzgl[] zzglVarArr = new zzgl[iZzb2 + length2];
                    if (length2 != 0) {
                        System.arraycopy(this.zzaxc, 0, zzglVarArr, 0, length2);
                    }
                    while (length2 < zzglVarArr.length - 1) {
                        zzglVarArr[length2] = new zzgl();
                        zzyxVar.zza(zzglVarArr[length2]);
                        zzyxVar.zzug();
                        length2++;
                    }
                    zzglVarArr[length2] = new zzgl();
                    zzyxVar.zza(zzglVarArr[length2]);
                    this.zzaxc = zzglVarArr;
                    break;
                case 32:
                    this.zzaxd = Long.valueOf(zzyxVar.zzuz());
                    break;
                case 40:
                    this.zzaxe = Long.valueOf(zzyxVar.zzuz());
                    break;
                case 48:
                    this.zzaxf = Long.valueOf(zzyxVar.zzuz());
                    break;
                case 56:
                    this.zzaxh = Long.valueOf(zzyxVar.zzuz());
                    break;
                case 66:
                    this.zzaxi = zzyxVar.readString();
                    break;
                case 74:
                    this.zzaxj = zzyxVar.readString();
                    break;
                case 82:
                    this.zzaxk = zzyxVar.readString();
                    break;
                case 90:
                    this.zzaia = zzyxVar.readString();
                    break;
                case 96:
                    this.zzaxl = Integer.valueOf(zzyxVar.zzuy());
                    break;
                case 106:
                    this.zzage = zzyxVar.readString();
                    break;
                case 114:
                    this.zztt = zzyxVar.readString();
                    break;
                case BuildConfig.VERSION_CODE /* 130 */:
                    this.zzts = zzyxVar.readString();
                    break;
                case 136:
                    this.zzaxm = Long.valueOf(zzyxVar.zzuz());
                    break;
                case 144:
                    this.zzaxn = Long.valueOf(zzyxVar.zzuz());
                    break;
                case 154:
                    this.zzaxo = zzyxVar.readString();
                    break;
                case 160:
                    this.zzaxp = Boolean.valueOf(zzyxVar.zzum());
                    break;
                case 170:
                    this.zzafw = zzyxVar.readString();
                    break;
                case 176:
                    this.zzaxq = Long.valueOf(zzyxVar.zzuz());
                    break;
                case 184:
                    this.zzaxr = Integer.valueOf(zzyxVar.zzuy());
                    break;
                case 194:
                    this.zzagv = zzyxVar.readString();
                    break;
                case 202:
                    this.zzafx = zzyxVar.readString();
                    break;
                case 208:
                    this.zzaxg = Long.valueOf(zzyxVar.zzuz());
                    break;
                case 224:
                    this.zzaxs = Boolean.valueOf(zzyxVar.zzum());
                    break;
                case MyNotificationManager.ID_BIG_NOTIFICATION /* 234 */:
                    int iZzb3 = zzzj.zzb(zzyxVar, MyNotificationManager.ID_BIG_NOTIFICATION);
                    if (this.zzaxt != null) {
                        length3 = this.zzaxt.length;
                    } else {
                        length3 = 0;
                    }
                    zzgd[] zzgdVarArr = new zzgd[iZzb3 + length3];
                    if (length3 != 0) {
                        System.arraycopy(this.zzaxt, 0, zzgdVarArr, 0, length3);
                    }
                    while (length3 < zzgdVarArr.length - 1) {
                        zzgdVarArr[length3] = new zzgd();
                        zzyxVar.zza(zzgdVarArr[length3]);
                        zzyxVar.zzug();
                        length3++;
                    }
                    zzgdVarArr[length3] = new zzgd();
                    zzyxVar.zza(zzgdVarArr[length3]);
                    this.zzaxt = zzgdVarArr;
                    break;
                case 242:
                    this.zzafz = zzyxVar.readString();
                    break;
                case 248:
                    this.zzaxu = Integer.valueOf(zzyxVar.zzuy());
                    break;
                case 256:
                    this.zzaxv = Integer.valueOf(zzyxVar.zzuy());
                    break;
                case 264:
                    this.zzaxw = Integer.valueOf(zzyxVar.zzuy());
                    break;
                case 274:
                    this.zzaxx = zzyxVar.readString();
                    break;
                case 280:
                    this.zzaxy = Long.valueOf(zzyxVar.zzuz());
                    break;
                case 288:
                    this.zzaxz = Long.valueOf(zzyxVar.zzuz());
                    break;
                case 298:
                    this.zzaya = zzyxVar.readString();
                    break;
                case 306:
                    this.zzayb = zzyxVar.readString();
                    break;
                case 312:
                    this.zzayc = Integer.valueOf(zzyxVar.zzuy());
                    break;
                case 330:
                    this.zzawj = zzyxVar.readString();
                    break;
                case 354:
                    this.zzayd = (zzfq.zzb) zzyxVar.zza(zzfq.zzb.zza());
                    break;
                default:
                    if (!super.zza(zzyxVar, iZzug)) {
                        return this;
                    }
                    break;
                    break;
            }
        }
    }
}
