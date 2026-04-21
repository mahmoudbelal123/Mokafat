package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class zzur implements zzxi {
    private int tag;
    private final zzuo zzbur;
    private int zzbus;
    private int zzbut = 0;

    public static zzur zza(zzuo zzuoVar) {
        return zzuoVar.zzbuk != null ? zzuoVar.zzbuk : new zzur(zzuoVar);
    }

    private zzur(zzuo zzuoVar) {
        this.zzbur = (zzuo) zzvo.zza(zzuoVar, "input");
        this.zzbur.zzbuk = this;
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final int zzve() throws IOException {
        if (this.zzbut != 0) {
            this.tag = this.zzbut;
            this.zzbut = 0;
        } else {
            this.tag = this.zzbur.zzug();
        }
        if (this.tag == 0 || this.tag == this.zzbus) {
            return Integer.MAX_VALUE;
        }
        return this.tag >>> 3;
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final int getTag() {
        return this.tag;
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final boolean zzvf() throws IOException {
        if (this.zzbur.zzuw() || this.tag == this.zzbus) {
            return false;
        }
        return this.zzbur.zzao(this.tag);
    }

    private final void zzat(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzvt.zzwo();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final double readDouble() throws IOException {
        zzat(1);
        return this.zzbur.readDouble();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final float readFloat() throws IOException {
        zzat(5);
        return this.zzbur.readFloat();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final long zzuh() throws IOException {
        zzat(0);
        return this.zzbur.zzuh();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final long zzui() throws IOException {
        zzat(0);
        return this.zzbur.zzui();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final int zzuj() throws IOException {
        zzat(0);
        return this.zzbur.zzuj();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final long zzuk() throws IOException {
        zzat(1);
        return this.zzbur.zzuk();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final int zzul() throws IOException {
        zzat(5);
        return this.zzbur.zzul();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final boolean zzum() throws IOException {
        zzat(0);
        return this.zzbur.zzum();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final String readString() throws IOException {
        zzat(2);
        return this.zzbur.readString();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final String zzun() throws IOException {
        zzat(2);
        return this.zzbur.zzun();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final <T> T zza(zzxj<T> zzxjVar, zzuz zzuzVar) throws IOException {
        zzat(2);
        return (T) zzc(zzxjVar, zzuzVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final <T> T zzb(zzxj<T> zzxjVar, zzuz zzuzVar) throws IOException {
        zzat(3);
        return (T) zzd(zzxjVar, zzuzVar);
    }

    private final <T> T zzc(zzxj<T> zzxjVar, zzuz zzuzVar) throws IOException {
        int iZzup = this.zzbur.zzup();
        if (this.zzbur.zzbuh >= this.zzbur.zzbui) {
            throw zzvt.zzwp();
        }
        int iZzaq = this.zzbur.zzaq(iZzup);
        T tNewInstance = zzxjVar.newInstance();
        this.zzbur.zzbuh++;
        zzxjVar.zza(tNewInstance, this, zzuzVar);
        zzxjVar.zzu(tNewInstance);
        this.zzbur.zzan(0);
        zzuo zzuoVar = this.zzbur;
        zzuoVar.zzbuh--;
        this.zzbur.zzar(iZzaq);
        return tNewInstance;
    }

    private final <T> T zzd(zzxj<T> zzxjVar, zzuz zzuzVar) throws IOException {
        int i = this.zzbus;
        this.zzbus = ((this.tag >>> 3) << 3) | 4;
        try {
            T tNewInstance = zzxjVar.newInstance();
            zzxjVar.zza(tNewInstance, this, zzuzVar);
            zzxjVar.zzu(tNewInstance);
            if (this.tag != this.zzbus) {
                throw zzvt.zzwq();
            }
            return tNewInstance;
        } finally {
            this.zzbus = i;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final zzud zzuo() throws IOException {
        zzat(2);
        return this.zzbur.zzuo();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final int zzup() throws IOException {
        zzat(0);
        return this.zzbur.zzup();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final int zzuq() throws IOException {
        zzat(0);
        return this.zzbur.zzuq();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final int zzur() throws IOException {
        zzat(5);
        return this.zzbur.zzur();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final long zzus() throws IOException {
        zzat(1);
        return this.zzbur.zzus();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final int zzut() throws IOException {
        zzat(0);
        return this.zzbur.zzut();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final long zzuu() throws IOException {
        zzat(0);
        return this.zzbur.zzuu();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final void zzh(List<Double> list) throws IOException {
        int iZzug;
        int iZzug2;
        if (list instanceof zzuw) {
            zzuw zzuwVar = (zzuw) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int iZzup = this.zzbur.zzup();
                    zzau(iZzup);
                    int iZzux = this.zzbur.zzux() + iZzup;
                    do {
                        zzuwVar.zzd(this.zzbur.readDouble());
                    } while (this.zzbur.zzux() < iZzux);
                    return;
                default:
                    throw zzvt.zzwo();
            }
            do {
                zzuwVar.zzd(this.zzbur.readDouble());
                if (this.zzbur.zzuw()) {
                    return;
                } else {
                    iZzug2 = this.zzbur.zzug();
                }
            } while (iZzug2 == this.tag);
            this.zzbut = iZzug2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int iZzup2 = this.zzbur.zzup();
                zzau(iZzup2);
                int iZzux2 = this.zzbur.zzux() + iZzup2;
                do {
                    list.add(Double.valueOf(this.zzbur.readDouble()));
                } while (this.zzbur.zzux() < iZzux2);
                return;
            default:
                throw zzvt.zzwo();
        }
        do {
            list.add(Double.valueOf(this.zzbur.readDouble()));
            if (this.zzbur.zzuw()) {
                return;
            } else {
                iZzug = this.zzbur.zzug();
            }
        } while (iZzug == this.tag);
        this.zzbut = iZzug;
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final void zzi(List<Float> list) throws IOException {
        int iZzug;
        int iZzug2;
        if (list instanceof zzvj) {
            zzvj zzvjVar = (zzvj) list;
            int i = this.tag & 7;
            if (i == 2) {
                int iZzup = this.zzbur.zzup();
                zzav(iZzup);
                int iZzux = this.zzbur.zzux() + iZzup;
                do {
                    zzvjVar.zzc(this.zzbur.readFloat());
                } while (this.zzbur.zzux() < iZzux);
                return;
            }
            if (i == 5) {
                do {
                    zzvjVar.zzc(this.zzbur.readFloat());
                    if (this.zzbur.zzuw()) {
                        return;
                    } else {
                        iZzug2 = this.zzbur.zzug();
                    }
                } while (iZzug2 == this.tag);
                this.zzbut = iZzug2;
                return;
            }
            throw zzvt.zzwo();
        }
        int i2 = this.tag & 7;
        if (i2 == 2) {
            int iZzup2 = this.zzbur.zzup();
            zzav(iZzup2);
            int iZzux2 = this.zzbur.zzux() + iZzup2;
            do {
                list.add(Float.valueOf(this.zzbur.readFloat()));
            } while (this.zzbur.zzux() < iZzux2);
            return;
        }
        if (i2 == 5) {
            do {
                list.add(Float.valueOf(this.zzbur.readFloat()));
                if (this.zzbur.zzuw()) {
                    return;
                } else {
                    iZzug = this.zzbur.zzug();
                }
            } while (iZzug == this.tag);
            this.zzbut = iZzug;
            return;
        }
        throw zzvt.zzwo();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final void zzj(List<Long> list) throws IOException {
        int iZzug;
        int iZzug2;
        if (list instanceof zzwh) {
            zzwh zzwhVar = (zzwh) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzwhVar.zzbg(this.zzbur.zzuh());
                    if (this.zzbur.zzuw()) {
                        return;
                    } else {
                        iZzug2 = this.zzbur.zzug();
                    }
                } while (iZzug2 == this.tag);
                this.zzbut = iZzug2;
                return;
            }
            if (i == 2) {
                int iZzux = this.zzbur.zzux() + this.zzbur.zzup();
                do {
                    zzwhVar.zzbg(this.zzbur.zzuh());
                } while (this.zzbur.zzux() < iZzux);
                zzaw(iZzux);
                return;
            }
            throw zzvt.zzwo();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zzbur.zzuh()));
                if (this.zzbur.zzuw()) {
                    return;
                } else {
                    iZzug = this.zzbur.zzug();
                }
            } while (iZzug == this.tag);
            this.zzbut = iZzug;
            return;
        }
        if (i2 == 2) {
            int iZzux2 = this.zzbur.zzux() + this.zzbur.zzup();
            do {
                list.add(Long.valueOf(this.zzbur.zzuh()));
            } while (this.zzbur.zzux() < iZzux2);
            zzaw(iZzux2);
            return;
        }
        throw zzvt.zzwo();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final void zzk(List<Long> list) throws IOException {
        int iZzug;
        int iZzug2;
        if (list instanceof zzwh) {
            zzwh zzwhVar = (zzwh) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzwhVar.zzbg(this.zzbur.zzui());
                    if (this.zzbur.zzuw()) {
                        return;
                    } else {
                        iZzug2 = this.zzbur.zzug();
                    }
                } while (iZzug2 == this.tag);
                this.zzbut = iZzug2;
                return;
            }
            if (i == 2) {
                int iZzux = this.zzbur.zzux() + this.zzbur.zzup();
                do {
                    zzwhVar.zzbg(this.zzbur.zzui());
                } while (this.zzbur.zzux() < iZzux);
                zzaw(iZzux);
                return;
            }
            throw zzvt.zzwo();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zzbur.zzui()));
                if (this.zzbur.zzuw()) {
                    return;
                } else {
                    iZzug = this.zzbur.zzug();
                }
            } while (iZzug == this.tag);
            this.zzbut = iZzug;
            return;
        }
        if (i2 == 2) {
            int iZzux2 = this.zzbur.zzux() + this.zzbur.zzup();
            do {
                list.add(Long.valueOf(this.zzbur.zzui()));
            } while (this.zzbur.zzux() < iZzux2);
            zzaw(iZzux2);
            return;
        }
        throw zzvt.zzwo();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final void zzl(List<Integer> list) throws IOException {
        int iZzug;
        int iZzug2;
        if (list instanceof zzvn) {
            zzvn zzvnVar = (zzvn) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzvnVar.zzbm(this.zzbur.zzuj());
                    if (this.zzbur.zzuw()) {
                        return;
                    } else {
                        iZzug2 = this.zzbur.zzug();
                    }
                } while (iZzug2 == this.tag);
                this.zzbut = iZzug2;
                return;
            }
            if (i == 2) {
                int iZzux = this.zzbur.zzux() + this.zzbur.zzup();
                do {
                    zzvnVar.zzbm(this.zzbur.zzuj());
                } while (this.zzbur.zzux() < iZzux);
                zzaw(iZzux);
                return;
            }
            throw zzvt.zzwo();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzbur.zzuj()));
                if (this.zzbur.zzuw()) {
                    return;
                } else {
                    iZzug = this.zzbur.zzug();
                }
            } while (iZzug == this.tag);
            this.zzbut = iZzug;
            return;
        }
        if (i2 == 2) {
            int iZzux2 = this.zzbur.zzux() + this.zzbur.zzup();
            do {
                list.add(Integer.valueOf(this.zzbur.zzuj()));
            } while (this.zzbur.zzux() < iZzux2);
            zzaw(iZzux2);
            return;
        }
        throw zzvt.zzwo();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final void zzm(List<Long> list) throws IOException {
        int iZzug;
        int iZzug2;
        if (list instanceof zzwh) {
            zzwh zzwhVar = (zzwh) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int iZzup = this.zzbur.zzup();
                    zzau(iZzup);
                    int iZzux = this.zzbur.zzux() + iZzup;
                    do {
                        zzwhVar.zzbg(this.zzbur.zzuk());
                    } while (this.zzbur.zzux() < iZzux);
                    return;
                default:
                    throw zzvt.zzwo();
            }
            do {
                zzwhVar.zzbg(this.zzbur.zzuk());
                if (this.zzbur.zzuw()) {
                    return;
                } else {
                    iZzug2 = this.zzbur.zzug();
                }
            } while (iZzug2 == this.tag);
            this.zzbut = iZzug2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int iZzup2 = this.zzbur.zzup();
                zzau(iZzup2);
                int iZzux2 = this.zzbur.zzux() + iZzup2;
                do {
                    list.add(Long.valueOf(this.zzbur.zzuk()));
                } while (this.zzbur.zzux() < iZzux2);
                return;
            default:
                throw zzvt.zzwo();
        }
        do {
            list.add(Long.valueOf(this.zzbur.zzuk()));
            if (this.zzbur.zzuw()) {
                return;
            } else {
                iZzug = this.zzbur.zzug();
            }
        } while (iZzug == this.tag);
        this.zzbut = iZzug;
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final void zzn(List<Integer> list) throws IOException {
        int iZzug;
        int iZzug2;
        if (list instanceof zzvn) {
            zzvn zzvnVar = (zzvn) list;
            int i = this.tag & 7;
            if (i == 2) {
                int iZzup = this.zzbur.zzup();
                zzav(iZzup);
                int iZzux = this.zzbur.zzux() + iZzup;
                do {
                    zzvnVar.zzbm(this.zzbur.zzul());
                } while (this.zzbur.zzux() < iZzux);
                return;
            }
            if (i == 5) {
                do {
                    zzvnVar.zzbm(this.zzbur.zzul());
                    if (this.zzbur.zzuw()) {
                        return;
                    } else {
                        iZzug2 = this.zzbur.zzug();
                    }
                } while (iZzug2 == this.tag);
                this.zzbut = iZzug2;
                return;
            }
            throw zzvt.zzwo();
        }
        int i2 = this.tag & 7;
        if (i2 == 2) {
            int iZzup2 = this.zzbur.zzup();
            zzav(iZzup2);
            int iZzux2 = this.zzbur.zzux() + iZzup2;
            do {
                list.add(Integer.valueOf(this.zzbur.zzul()));
            } while (this.zzbur.zzux() < iZzux2);
            return;
        }
        if (i2 == 5) {
            do {
                list.add(Integer.valueOf(this.zzbur.zzul()));
                if (this.zzbur.zzuw()) {
                    return;
                } else {
                    iZzug = this.zzbur.zzug();
                }
            } while (iZzug == this.tag);
            this.zzbut = iZzug;
            return;
        }
        throw zzvt.zzwo();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final void zzo(List<Boolean> list) throws IOException {
        int iZzug;
        int iZzug2;
        if (list instanceof zzub) {
            zzub zzubVar = (zzub) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzubVar.addBoolean(this.zzbur.zzum());
                    if (this.zzbur.zzuw()) {
                        return;
                    } else {
                        iZzug2 = this.zzbur.zzug();
                    }
                } while (iZzug2 == this.tag);
                this.zzbut = iZzug2;
                return;
            }
            if (i == 2) {
                int iZzux = this.zzbur.zzux() + this.zzbur.zzup();
                do {
                    zzubVar.addBoolean(this.zzbur.zzum());
                } while (this.zzbur.zzux() < iZzux);
                zzaw(iZzux);
                return;
            }
            throw zzvt.zzwo();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Boolean.valueOf(this.zzbur.zzum()));
                if (this.zzbur.zzuw()) {
                    return;
                } else {
                    iZzug = this.zzbur.zzug();
                }
            } while (iZzug == this.tag);
            this.zzbut = iZzug;
            return;
        }
        if (i2 == 2) {
            int iZzux2 = this.zzbur.zzux() + this.zzbur.zzup();
            do {
                list.add(Boolean.valueOf(this.zzbur.zzum()));
            } while (this.zzbur.zzux() < iZzux2);
            zzaw(iZzux2);
            return;
        }
        throw zzvt.zzwo();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final void readStringList(List<String> list) throws IOException {
        zza(list, false);
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final void zzp(List<String> list) throws IOException {
        zza(list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int iZzug;
        int iZzug2;
        if ((this.tag & 7) != 2) {
            throw zzvt.zzwo();
        }
        if ((list instanceof zzwc) && !z) {
            zzwc zzwcVar = (zzwc) list;
            do {
                zzwcVar.zzc(zzuo());
                if (this.zzbur.zzuw()) {
                    return;
                } else {
                    iZzug2 = this.zzbur.zzug();
                }
            } while (iZzug2 == this.tag);
            this.zzbut = iZzug2;
            return;
        }
        do {
            list.add(z ? zzun() : readString());
            if (this.zzbur.zzuw()) {
                return;
            } else {
                iZzug = this.zzbur.zzug();
            }
        } while (iZzug == this.tag);
        this.zzbut = iZzug;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzxi
    public final <T> void zza(List<T> list, zzxj<T> zzxjVar, zzuz zzuzVar) throws IOException {
        int iZzug;
        if ((this.tag & 7) != 2) {
            throw zzvt.zzwo();
        }
        int i = this.tag;
        do {
            list.add(zzc(zzxjVar, zzuzVar));
            if (this.zzbur.zzuw() || this.zzbut != 0) {
                return;
            } else {
                iZzug = this.zzbur.zzug();
            }
        } while (iZzug == i);
        this.zzbut = iZzug;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzxi
    public final <T> void zzb(List<T> list, zzxj<T> zzxjVar, zzuz zzuzVar) throws IOException {
        int iZzug;
        if ((this.tag & 7) != 3) {
            throw zzvt.zzwo();
        }
        int i = this.tag;
        do {
            list.add(zzd(zzxjVar, zzuzVar));
            if (this.zzbur.zzuw() || this.zzbut != 0) {
                return;
            } else {
                iZzug = this.zzbur.zzug();
            }
        } while (iZzug == i);
        this.zzbut = iZzug;
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final void zzq(List<zzud> list) throws IOException {
        int iZzug;
        if ((this.tag & 7) != 2) {
            throw zzvt.zzwo();
        }
        do {
            list.add(zzuo());
            if (this.zzbur.zzuw()) {
                return;
            } else {
                iZzug = this.zzbur.zzug();
            }
        } while (iZzug == this.tag);
        this.zzbut = iZzug;
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final void zzr(List<Integer> list) throws IOException {
        int iZzug;
        int iZzug2;
        if (list instanceof zzvn) {
            zzvn zzvnVar = (zzvn) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzvnVar.zzbm(this.zzbur.zzup());
                    if (this.zzbur.zzuw()) {
                        return;
                    } else {
                        iZzug2 = this.zzbur.zzug();
                    }
                } while (iZzug2 == this.tag);
                this.zzbut = iZzug2;
                return;
            }
            if (i == 2) {
                int iZzux = this.zzbur.zzux() + this.zzbur.zzup();
                do {
                    zzvnVar.zzbm(this.zzbur.zzup());
                } while (this.zzbur.zzux() < iZzux);
                zzaw(iZzux);
                return;
            }
            throw zzvt.zzwo();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzbur.zzup()));
                if (this.zzbur.zzuw()) {
                    return;
                } else {
                    iZzug = this.zzbur.zzug();
                }
            } while (iZzug == this.tag);
            this.zzbut = iZzug;
            return;
        }
        if (i2 == 2) {
            int iZzux2 = this.zzbur.zzux() + this.zzbur.zzup();
            do {
                list.add(Integer.valueOf(this.zzbur.zzup()));
            } while (this.zzbur.zzux() < iZzux2);
            zzaw(iZzux2);
            return;
        }
        throw zzvt.zzwo();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final void zzs(List<Integer> list) throws IOException {
        int iZzug;
        int iZzug2;
        if (list instanceof zzvn) {
            zzvn zzvnVar = (zzvn) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzvnVar.zzbm(this.zzbur.zzuq());
                    if (this.zzbur.zzuw()) {
                        return;
                    } else {
                        iZzug2 = this.zzbur.zzug();
                    }
                } while (iZzug2 == this.tag);
                this.zzbut = iZzug2;
                return;
            }
            if (i == 2) {
                int iZzux = this.zzbur.zzux() + this.zzbur.zzup();
                do {
                    zzvnVar.zzbm(this.zzbur.zzuq());
                } while (this.zzbur.zzux() < iZzux);
                zzaw(iZzux);
                return;
            }
            throw zzvt.zzwo();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzbur.zzuq()));
                if (this.zzbur.zzuw()) {
                    return;
                } else {
                    iZzug = this.zzbur.zzug();
                }
            } while (iZzug == this.tag);
            this.zzbut = iZzug;
            return;
        }
        if (i2 == 2) {
            int iZzux2 = this.zzbur.zzux() + this.zzbur.zzup();
            do {
                list.add(Integer.valueOf(this.zzbur.zzuq()));
            } while (this.zzbur.zzux() < iZzux2);
            zzaw(iZzux2);
            return;
        }
        throw zzvt.zzwo();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final void zzt(List<Integer> list) throws IOException {
        int iZzug;
        int iZzug2;
        if (list instanceof zzvn) {
            zzvn zzvnVar = (zzvn) list;
            int i = this.tag & 7;
            if (i == 2) {
                int iZzup = this.zzbur.zzup();
                zzav(iZzup);
                int iZzux = this.zzbur.zzux() + iZzup;
                do {
                    zzvnVar.zzbm(this.zzbur.zzur());
                } while (this.zzbur.zzux() < iZzux);
                return;
            }
            if (i == 5) {
                do {
                    zzvnVar.zzbm(this.zzbur.zzur());
                    if (this.zzbur.zzuw()) {
                        return;
                    } else {
                        iZzug2 = this.zzbur.zzug();
                    }
                } while (iZzug2 == this.tag);
                this.zzbut = iZzug2;
                return;
            }
            throw zzvt.zzwo();
        }
        int i2 = this.tag & 7;
        if (i2 == 2) {
            int iZzup2 = this.zzbur.zzup();
            zzav(iZzup2);
            int iZzux2 = this.zzbur.zzux() + iZzup2;
            do {
                list.add(Integer.valueOf(this.zzbur.zzur()));
            } while (this.zzbur.zzux() < iZzux2);
            return;
        }
        if (i2 == 5) {
            do {
                list.add(Integer.valueOf(this.zzbur.zzur()));
                if (this.zzbur.zzuw()) {
                    return;
                } else {
                    iZzug = this.zzbur.zzug();
                }
            } while (iZzug == this.tag);
            this.zzbut = iZzug;
            return;
        }
        throw zzvt.zzwo();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final void zzu(List<Long> list) throws IOException {
        int iZzug;
        int iZzug2;
        if (list instanceof zzwh) {
            zzwh zzwhVar = (zzwh) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int iZzup = this.zzbur.zzup();
                    zzau(iZzup);
                    int iZzux = this.zzbur.zzux() + iZzup;
                    do {
                        zzwhVar.zzbg(this.zzbur.zzus());
                    } while (this.zzbur.zzux() < iZzux);
                    return;
                default:
                    throw zzvt.zzwo();
            }
            do {
                zzwhVar.zzbg(this.zzbur.zzus());
                if (this.zzbur.zzuw()) {
                    return;
                } else {
                    iZzug2 = this.zzbur.zzug();
                }
            } while (iZzug2 == this.tag);
            this.zzbut = iZzug2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int iZzup2 = this.zzbur.zzup();
                zzau(iZzup2);
                int iZzux2 = this.zzbur.zzux() + iZzup2;
                do {
                    list.add(Long.valueOf(this.zzbur.zzus()));
                } while (this.zzbur.zzux() < iZzux2);
                return;
            default:
                throw zzvt.zzwo();
        }
        do {
            list.add(Long.valueOf(this.zzbur.zzus()));
            if (this.zzbur.zzuw()) {
                return;
            } else {
                iZzug = this.zzbur.zzug();
            }
        } while (iZzug == this.tag);
        this.zzbut = iZzug;
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final void zzv(List<Integer> list) throws IOException {
        int iZzug;
        int iZzug2;
        if (list instanceof zzvn) {
            zzvn zzvnVar = (zzvn) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzvnVar.zzbm(this.zzbur.zzut());
                    if (this.zzbur.zzuw()) {
                        return;
                    } else {
                        iZzug2 = this.zzbur.zzug();
                    }
                } while (iZzug2 == this.tag);
                this.zzbut = iZzug2;
                return;
            }
            if (i == 2) {
                int iZzux = this.zzbur.zzux() + this.zzbur.zzup();
                do {
                    zzvnVar.zzbm(this.zzbur.zzut());
                } while (this.zzbur.zzux() < iZzux);
                zzaw(iZzux);
                return;
            }
            throw zzvt.zzwo();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzbur.zzut()));
                if (this.zzbur.zzuw()) {
                    return;
                } else {
                    iZzug = this.zzbur.zzug();
                }
            } while (iZzug == this.tag);
            this.zzbut = iZzug;
            return;
        }
        if (i2 == 2) {
            int iZzux2 = this.zzbur.zzux() + this.zzbur.zzup();
            do {
                list.add(Integer.valueOf(this.zzbur.zzut()));
            } while (this.zzbur.zzux() < iZzux2);
            zzaw(iZzux2);
            return;
        }
        throw zzvt.zzwo();
    }

    @Override // com.google.android.gms.internal.measurement.zzxi
    public final void zzw(List<Long> list) throws IOException {
        int iZzug;
        int iZzug2;
        if (list instanceof zzwh) {
            zzwh zzwhVar = (zzwh) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzwhVar.zzbg(this.zzbur.zzuu());
                    if (this.zzbur.zzuw()) {
                        return;
                    } else {
                        iZzug2 = this.zzbur.zzug();
                    }
                } while (iZzug2 == this.tag);
                this.zzbut = iZzug2;
                return;
            }
            if (i == 2) {
                int iZzux = this.zzbur.zzux() + this.zzbur.zzup();
                do {
                    zzwhVar.zzbg(this.zzbur.zzuu());
                } while (this.zzbur.zzux() < iZzux);
                zzaw(iZzux);
                return;
            }
            throw zzvt.zzwo();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zzbur.zzuu()));
                if (this.zzbur.zzuw()) {
                    return;
                } else {
                    iZzug = this.zzbur.zzug();
                }
            } while (iZzug == this.tag);
            this.zzbut = iZzug;
            return;
        }
        if (i2 == 2) {
            int iZzux2 = this.zzbur.zzux() + this.zzbur.zzup();
            do {
                list.add(Long.valueOf(this.zzbur.zzuu()));
            } while (this.zzbur.zzux() < iZzux2);
            zzaw(iZzux2);
            return;
        }
        throw zzvt.zzwo();
    }

    private static void zzau(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzvt.zzwq();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0061, code lost:
    
        r6.put(r1, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0069, code lost:
    
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzxi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <K, V> void zza(java.util.Map<K, V> r6, com.google.android.gms.internal.measurement.zzwm<K, V> r7, com.google.android.gms.internal.measurement.zzuz r8) throws java.io.IOException {
        /*
            r5 = this;
            r0 = 2
            r5.zzat(r0)
            com.google.android.gms.internal.measurement.zzuo r0 = r5.zzbur
            int r0 = r0.zzup()
            com.google.android.gms.internal.measurement.zzuo r1 = r5.zzbur
            int r0 = r1.zzaq(r0)
            K r1 = r7.zzcas
            V r2 = r7.zzbre
        L14:
            int r3 = r5.zzve()     // Catch: java.lang.Throwable -> L6a
            r4 = 2147483647(0x7fffffff, float:NaN)
            if (r3 == r4) goto L61
            com.google.android.gms.internal.measurement.zzuo r4 = r5.zzbur     // Catch: java.lang.Throwable -> L6a
            boolean r4 = r4.zzuw()     // Catch: java.lang.Throwable -> L6a
            if (r4 != 0) goto L61
            switch(r3) {
                case 1: goto L3c;
                case 2: goto L2d;
                default: goto L28;
            }
        L28:
            boolean r3 = r5.zzvf()     // Catch: com.google.android.gms.internal.measurement.zzvu -> L51 java.lang.Throwable -> L6a
            goto L46
        L2d:
            com.google.android.gms.internal.measurement.zzyq r3 = r7.zzcat     // Catch: com.google.android.gms.internal.measurement.zzvu -> L51 java.lang.Throwable -> L6a
            V r4 = r7.zzbre     // Catch: com.google.android.gms.internal.measurement.zzvu -> L51 java.lang.Throwable -> L6a
            java.lang.Class r4 = r4.getClass()     // Catch: com.google.android.gms.internal.measurement.zzvu -> L51 java.lang.Throwable -> L6a
            java.lang.Object r3 = r5.zza(r3, r4, r8)     // Catch: com.google.android.gms.internal.measurement.zzvu -> L51 java.lang.Throwable -> L6a
            r2 = r3
            goto L14
        L3c:
            com.google.android.gms.internal.measurement.zzyq r3 = r7.zzcar     // Catch: com.google.android.gms.internal.measurement.zzvu -> L51 java.lang.Throwable -> L6a
            r4 = 0
            java.lang.Object r3 = r5.zza(r3, r4, r4)     // Catch: com.google.android.gms.internal.measurement.zzvu -> L51 java.lang.Throwable -> L6a
            r1 = r3
            goto L14
        L46:
            if (r3 != 0) goto L50
            com.google.android.gms.internal.measurement.zzvt r3 = new com.google.android.gms.internal.measurement.zzvt     // Catch: com.google.android.gms.internal.measurement.zzvu -> L51 java.lang.Throwable -> L6a
            java.lang.String r4 = "Unable to parse map entry."
            r3.<init>(r4)     // Catch: com.google.android.gms.internal.measurement.zzvu -> L51 java.lang.Throwable -> L6a
            throw r3     // Catch: com.google.android.gms.internal.measurement.zzvu -> L51 java.lang.Throwable -> L6a
        L50:
            goto L14
        L51:
            r3 = move-exception
            boolean r3 = r5.zzvf()     // Catch: java.lang.Throwable -> L6a
            if (r3 != 0) goto L60
            com.google.android.gms.internal.measurement.zzvt r6 = new com.google.android.gms.internal.measurement.zzvt     // Catch: java.lang.Throwable -> L6a
            java.lang.String r7 = "Unable to parse map entry."
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L6a
            throw r6     // Catch: java.lang.Throwable -> L6a
        L60:
            goto L14
        L61:
            r6.put(r1, r2)     // Catch: java.lang.Throwable -> L6a
            com.google.android.gms.internal.measurement.zzuo r6 = r5.zzbur
            r6.zzar(r0)
            return
        L6a:
            r6 = move-exception
            com.google.android.gms.internal.measurement.zzuo r7 = r5.zzbur
            r7.zzar(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzur.zza(java.util.Map, com.google.android.gms.internal.measurement.zzwm, com.google.android.gms.internal.measurement.zzuz):void");
    }

    private final Object zza(zzyq zzyqVar, Class<?> cls, zzuz zzuzVar) throws IOException {
        switch (zzus.zzbuu[zzyqVar.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzum());
            case 2:
                return zzuo();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzuq());
            case 5:
                return Integer.valueOf(zzul());
            case 6:
                return Long.valueOf(zzuk());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zzuj());
            case 9:
                return Long.valueOf(zzui());
            case 10:
                zzat(2);
                return zzc(zzxf.zzxn().zzi(cls), zzuzVar);
            case 11:
                return Integer.valueOf(zzur());
            case 12:
                return Long.valueOf(zzus());
            case 13:
                return Integer.valueOf(zzut());
            case 14:
                return Long.valueOf(zzuu());
            case 15:
                return zzun();
            case 16:
                return Integer.valueOf(zzup());
            case 17:
                return Long.valueOf(zzuh());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static void zzav(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzvt.zzwq();
        }
    }

    private final void zzaw(int i) throws IOException {
        if (this.zzbur.zzux() != i) {
            throw zzvt.zzwk();
        }
    }
}
