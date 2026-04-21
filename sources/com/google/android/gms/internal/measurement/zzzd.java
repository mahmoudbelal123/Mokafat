package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class zzzd implements Cloneable {
    private Object value;
    private zzzb<?, ?> zzcfj;
    private List<zzzi> zzcfk = new ArrayList();

    zzzd() {
    }

    final void zza(zzzi zzziVar) throws IOException {
        Object objZzah;
        if (this.zzcfk != null) {
            this.zzcfk.add(zzziVar);
            return;
        }
        if (this.value instanceof zzzg) {
            byte[] bArr = zzziVar.zzbug;
            zzyx zzyxVarZzj = zzyx.zzj(bArr, 0, bArr.length);
            int iZzuy = zzyxVarZzj.zzuy();
            if (iZzuy != bArr.length - zzyy.zzbc(iZzuy)) {
                throw zzzf.zzyw();
            }
            objZzah = ((zzzg) this.value).zza(zzyxVarZzj);
        } else if (this.value instanceof zzzg[]) {
            zzzg[] zzzgVarArr = (zzzg[]) this.zzcfj.zzah(Collections.singletonList(zzziVar));
            zzzg[] zzzgVarArr2 = (zzzg[]) this.value;
            zzzg[] zzzgVarArr3 = (zzzg[]) Arrays.copyOf(zzzgVarArr2, zzzgVarArr2.length + zzzgVarArr.length);
            System.arraycopy(zzzgVarArr, 0, zzzgVarArr3, zzzgVarArr2.length, zzzgVarArr.length);
            objZzah = zzzgVarArr3;
        } else {
            objZzah = this.zzcfj.zzah(Collections.singletonList(zzziVar));
        }
        this.zzcfj = this.zzcfj;
        this.value = objZzah;
        this.zzcfk = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    final <T> T zzb(zzzb<?, T> zzzbVar) {
        if (this.value != null) {
            if (!this.zzcfj.equals(zzzbVar)) {
                throw new IllegalStateException("Tried to getExtension with a different Extension.");
            }
        } else {
            this.zzcfj = zzzbVar;
            this.value = zzzbVar.zzah(this.zzcfk);
            this.zzcfk = null;
        }
        return (T) this.value;
    }

    final int zzf() {
        if (this.value != null) {
            zzzb<?, ?> zzzbVar = this.zzcfj;
            Object obj = this.value;
            if (zzzbVar.zzcfe) {
                int length = Array.getLength(obj);
                int iZzak = 0;
                for (int i = 0; i < length; i++) {
                    if (Array.get(obj, i) != null) {
                        iZzak += zzzbVar.zzak(Array.get(obj, i));
                    }
                }
                return iZzak;
            }
            return zzzbVar.zzak(obj);
        }
        int iZzbj = 0;
        for (zzzi zzziVar : this.zzcfk) {
            iZzbj += zzyy.zzbj(zzziVar.tag) + 0 + zzziVar.zzbug.length;
        }
        return iZzbj;
    }

    final void zza(zzyy zzyyVar) throws IOException {
        if (this.value != null) {
            zzzb<?, ?> zzzbVar = this.zzcfj;
            Object obj = this.value;
            if (zzzbVar.zzcfe) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    Object obj2 = Array.get(obj, i);
                    if (obj2 != null) {
                        zzzbVar.zza(obj2, zzyyVar);
                    }
                }
                return;
            }
            zzzbVar.zza(obj, zzyyVar);
            return;
        }
        for (zzzi zzziVar : this.zzcfk) {
            zzyyVar.zzca(zzziVar.tag);
            zzyyVar.zzp(zzziVar.zzbug);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzzd)) {
            return false;
        }
        zzzd zzzdVar = (zzzd) obj;
        if (this.value != null && zzzdVar.value != null) {
            if (this.zzcfj != zzzdVar.zzcfj) {
                return false;
            }
            if (!this.zzcfj.zzcfd.isArray()) {
                return this.value.equals(zzzdVar.value);
            }
            if (this.value instanceof byte[]) {
                return Arrays.equals((byte[]) this.value, (byte[]) zzzdVar.value);
            }
            if (this.value instanceof int[]) {
                return Arrays.equals((int[]) this.value, (int[]) zzzdVar.value);
            }
            if (this.value instanceof long[]) {
                return Arrays.equals((long[]) this.value, (long[]) zzzdVar.value);
            }
            if (this.value instanceof float[]) {
                return Arrays.equals((float[]) this.value, (float[]) zzzdVar.value);
            }
            if (this.value instanceof double[]) {
                return Arrays.equals((double[]) this.value, (double[]) zzzdVar.value);
            }
            if (this.value instanceof boolean[]) {
                return Arrays.equals((boolean[]) this.value, (boolean[]) zzzdVar.value);
            }
            return Arrays.deepEquals((Object[]) this.value, (Object[]) zzzdVar.value);
        }
        if (this.zzcfk != null && zzzdVar.zzcfk != null) {
            return this.zzcfk.equals(zzzdVar.zzcfk);
        }
        try {
            return Arrays.equals(toByteArray(), zzzdVar.toByteArray());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public final int hashCode() {
        try {
            return 527 + Arrays.hashCode(toByteArray());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzf()];
        zza(zzyy.zzo(bArr));
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzyv, reason: merged with bridge method [inline-methods] */
    public final zzzd clone() {
        zzzd zzzdVar = new zzzd();
        try {
            zzzdVar.zzcfj = this.zzcfj;
            if (this.zzcfk == null) {
                zzzdVar.zzcfk = null;
            } else {
                zzzdVar.zzcfk.addAll(this.zzcfk);
            }
            if (this.value != null) {
                if (this.value instanceof zzzg) {
                    zzzdVar.value = (zzzg) ((zzzg) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zzzdVar.value = ((byte[]) this.value).clone();
                } else {
                    int i = 0;
                    if (this.value instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) this.value;
                        byte[][] bArr2 = new byte[bArr.length][];
                        zzzdVar.value = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (this.value instanceof boolean[]) {
                        zzzdVar.value = ((boolean[]) this.value).clone();
                    } else if (this.value instanceof int[]) {
                        zzzdVar.value = ((int[]) this.value).clone();
                    } else if (this.value instanceof long[]) {
                        zzzdVar.value = ((long[]) this.value).clone();
                    } else if (this.value instanceof float[]) {
                        zzzdVar.value = ((float[]) this.value).clone();
                    } else if (this.value instanceof double[]) {
                        zzzdVar.value = ((double[]) this.value).clone();
                    } else if (this.value instanceof zzzg[]) {
                        zzzg[] zzzgVarArr = (zzzg[]) this.value;
                        zzzg[] zzzgVarArr2 = new zzzg[zzzgVarArr.length];
                        zzzdVar.value = zzzgVarArr2;
                        while (i < zzzgVarArr.length) {
                            zzzgVarArr2[i] = (zzzg) zzzgVarArr[i].clone();
                            i++;
                        }
                    }
                }
            }
            return zzzdVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
