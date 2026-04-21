package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.internal.measurement.zzvm;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
final class zzwx<T> implements zzxj<T> {
    private static final int[] zzcax = new int[0];
    private static final Unsafe zzcay = zzyh.zzyk();
    private final int[] zzcaz;
    private final Object[] zzcba;
    private final int zzcbb;
    private final int zzcbc;
    private final zzwt zzcbd;
    private final boolean zzcbe;
    private final boolean zzcbf;
    private final boolean zzcbg;
    private final boolean zzcbh;
    private final int[] zzcbi;
    private final int zzcbj;
    private final int zzcbk;
    private final zzxa zzcbl;
    private final zzwd zzcbm;
    private final zzyb<?, ?> zzcbn;
    private final zzva<?> zzcbo;
    private final zzwo zzcbp;

    private zzwx(int[] iArr, Object[] objArr, int i, int i2, zzwt zzwtVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzxa zzxaVar, zzwd zzwdVar, zzyb<?, ?> zzybVar, zzva<?> zzvaVar, zzwo zzwoVar) {
        this.zzcaz = iArr;
        this.zzcba = objArr;
        this.zzcbb = i;
        this.zzcbc = i2;
        this.zzcbf = zzwtVar instanceof zzvm;
        this.zzcbg = z;
        this.zzcbe = zzvaVar != null && zzvaVar.zze(zzwtVar);
        this.zzcbh = false;
        this.zzcbi = iArr2;
        this.zzcbj = i3;
        this.zzcbk = i4;
        this.zzcbl = zzxaVar;
        this.zzcbm = zzwdVar;
        this.zzcbn = zzybVar;
        this.zzcbo = zzvaVar;
        this.zzcbd = zzwtVar;
        this.zzcbp = zzwoVar;
    }

    static <T> zzwx<T> zza(Class<T> cls, zzwr zzwrVar, zzxa zzxaVar, zzwd zzwdVar, zzyb<?, ?> zzybVar, zzva<?> zzvaVar, zzwo zzwoVar) {
        int i;
        int iCharAt;
        int iCharAt2;
        int iCharAt3;
        int i2;
        int i3;
        int i4;
        int[] iArr;
        int i5;
        int i6;
        char cCharAt;
        int i7;
        char cCharAt2;
        int i8;
        char cCharAt3;
        int i9;
        char cCharAt4;
        int i10;
        char cCharAt5;
        int i11;
        char cCharAt6;
        int i12;
        char cCharAt7;
        int i13;
        char cCharAt8;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int iObjectFieldOffset;
        int i19;
        int i20;
        int i21;
        int iObjectFieldOffset2;
        Field fieldZza;
        int i22;
        char cCharAt9;
        int i23;
        int i24;
        int i25;
        Field fieldZza2;
        Field fieldZza3;
        char cCharAt10;
        int i26;
        char cCharAt11;
        int i27;
        char cCharAt12;
        int i28;
        char cCharAt13;
        char cCharAt14;
        if (zzwrVar instanceof zzxh) {
            zzxh zzxhVar = (zzxh) zzwrVar;
            int i29 = 0;
            boolean z = zzxhVar.zzxg() == zzvm.zze.zzbzc;
            String strZzxp = zzxhVar.zzxp();
            int length = strZzxp.length();
            int iCharAt4 = strZzxp.charAt(0);
            if (iCharAt4 >= 55296) {
                int i30 = iCharAt4 & 8191;
                int i31 = 1;
                int i32 = 13;
                while (true) {
                    i = i31 + 1;
                    cCharAt14 = strZzxp.charAt(i31);
                    if (cCharAt14 < 55296) {
                        break;
                    }
                    i30 |= (cCharAt14 & 8191) << i32;
                    i32 += 13;
                    i31 = i;
                }
                iCharAt4 = (cCharAt14 << i32) | i30;
            } else {
                i = 1;
            }
            int i33 = i + 1;
            int iCharAt5 = strZzxp.charAt(i);
            if (iCharAt5 >= 55296) {
                int i34 = iCharAt5 & 8191;
                int i35 = 13;
                while (true) {
                    i28 = i33 + 1;
                    cCharAt13 = strZzxp.charAt(i33);
                    if (cCharAt13 < 55296) {
                        break;
                    }
                    i34 |= (cCharAt13 & 8191) << i35;
                    i35 += 13;
                    i33 = i28;
                }
                iCharAt5 = i34 | (cCharAt13 << i35);
                i33 = i28;
            }
            if (iCharAt5 != 0) {
                int i36 = i33 + 1;
                int iCharAt6 = strZzxp.charAt(i33);
                if (iCharAt6 >= 55296) {
                    int i37 = iCharAt6 & 8191;
                    int i38 = 13;
                    while (true) {
                        i13 = i36 + 1;
                        cCharAt8 = strZzxp.charAt(i36);
                        if (cCharAt8 < 55296) {
                            break;
                        }
                        i37 |= (cCharAt8 & 8191) << i38;
                        i38 += 13;
                        i36 = i13;
                    }
                    iCharAt6 = i37 | (cCharAt8 << i38);
                    i36 = i13;
                }
                int i39 = i36 + 1;
                int iCharAt7 = strZzxp.charAt(i36);
                if (iCharAt7 >= 55296) {
                    int i40 = iCharAt7 & 8191;
                    int i41 = 13;
                    while (true) {
                        i12 = i39 + 1;
                        cCharAt7 = strZzxp.charAt(i39);
                        if (cCharAt7 < 55296) {
                            break;
                        }
                        i40 |= (cCharAt7 & 8191) << i41;
                        i41 += 13;
                        i39 = i12;
                    }
                    iCharAt7 = i40 | (cCharAt7 << i41);
                    i39 = i12;
                }
                int i42 = i39 + 1;
                iCharAt = strZzxp.charAt(i39);
                if (iCharAt >= 55296) {
                    int i43 = iCharAt & 8191;
                    int i44 = 13;
                    while (true) {
                        i11 = i42 + 1;
                        cCharAt6 = strZzxp.charAt(i42);
                        if (cCharAt6 < 55296) {
                            break;
                        }
                        i43 |= (cCharAt6 & 8191) << i44;
                        i44 += 13;
                        i42 = i11;
                    }
                    iCharAt = i43 | (cCharAt6 << i44);
                    i42 = i11;
                }
                int i45 = i42 + 1;
                iCharAt2 = strZzxp.charAt(i42);
                if (iCharAt2 >= 55296) {
                    int i46 = iCharAt2 & 8191;
                    int i47 = 13;
                    while (true) {
                        i10 = i45 + 1;
                        cCharAt5 = strZzxp.charAt(i45);
                        if (cCharAt5 < 55296) {
                            break;
                        }
                        i46 |= (cCharAt5 & 8191) << i47;
                        i47 += 13;
                        i45 = i10;
                    }
                    iCharAt2 = i46 | (cCharAt5 << i47);
                    i45 = i10;
                }
                int i48 = i45 + 1;
                iCharAt3 = strZzxp.charAt(i45);
                if (iCharAt3 >= 55296) {
                    int i49 = iCharAt3 & 8191;
                    int i50 = 13;
                    while (true) {
                        i9 = i48 + 1;
                        cCharAt4 = strZzxp.charAt(i48);
                        if (cCharAt4 < 55296) {
                            break;
                        }
                        i49 |= (cCharAt4 & 8191) << i50;
                        i50 += 13;
                        i48 = i9;
                    }
                    iCharAt3 = i49 | (cCharAt4 << i50);
                    i48 = i9;
                }
                int i51 = i48 + 1;
                int iCharAt8 = strZzxp.charAt(i48);
                if (iCharAt8 >= 55296) {
                    int i52 = iCharAt8 & 8191;
                    int i53 = 13;
                    while (true) {
                        i8 = i51 + 1;
                        cCharAt3 = strZzxp.charAt(i51);
                        if (cCharAt3 < 55296) {
                            break;
                        }
                        i52 |= (cCharAt3 & 8191) << i53;
                        i53 += 13;
                        i51 = i8;
                    }
                    iCharAt8 = i52 | (cCharAt3 << i53);
                    i51 = i8;
                }
                int i54 = i51 + 1;
                int iCharAt9 = strZzxp.charAt(i51);
                if (iCharAt9 >= 55296) {
                    int i55 = 13;
                    int i56 = iCharAt9 & 8191;
                    int i57 = i54;
                    while (true) {
                        i7 = i57 + 1;
                        cCharAt2 = strZzxp.charAt(i57);
                        if (cCharAt2 < 55296) {
                            break;
                        }
                        i56 |= (cCharAt2 & 8191) << i55;
                        i55 += 13;
                        i57 = i7;
                    }
                    iCharAt9 = i56 | (cCharAt2 << i55);
                    i2 = i7;
                } else {
                    i2 = i54;
                }
                int i58 = i2 + 1;
                int iCharAt10 = strZzxp.charAt(i2);
                if (iCharAt10 >= 55296) {
                    int i59 = 13;
                    int i60 = iCharAt10 & 8191;
                    int i61 = i58;
                    while (true) {
                        i6 = i61 + 1;
                        cCharAt = strZzxp.charAt(i61);
                        if (cCharAt < 55296) {
                            break;
                        }
                        i60 |= (cCharAt & 8191) << i59;
                        i59 += 13;
                        i61 = i6;
                    }
                    iCharAt10 = i60 | (cCharAt << i59);
                    i58 = i6;
                }
                int[] iArr2 = new int[iCharAt10 + iCharAt8 + iCharAt9];
                int i62 = (iCharAt6 << 1) + iCharAt7;
                int i63 = iCharAt8;
                i3 = iCharAt10;
                i29 = i63;
                i4 = iCharAt6;
                i33 = i58;
                iArr = iArr2;
                i5 = i62;
            } else {
                iCharAt = 0;
                iCharAt2 = 0;
                iCharAt3 = 0;
                i3 = 0;
                i5 = 0;
                iArr = zzcax;
                i4 = 0;
            }
            Unsafe unsafe = zzcay;
            Object[] objArrZzxq = zzxhVar.zzxq();
            Class<?> cls2 = zzxhVar.zzxi().getClass();
            int[] iArr3 = new int[iCharAt3 * 3];
            Object[] objArr = new Object[iCharAt3 << 1];
            int i64 = i29 + i3;
            int i65 = i64;
            int i66 = i3;
            int i67 = i5;
            int i68 = 0;
            int i69 = 0;
            while (i33 < length) {
                int i70 = i33 + 1;
                int iCharAt11 = strZzxp.charAt(i33);
                int i71 = length;
                char c = 55296;
                if (iCharAt11 >= 55296) {
                    int i72 = 13;
                    int i73 = iCharAt11 & 8191;
                    int i74 = i70;
                    while (true) {
                        i27 = i74 + 1;
                        cCharAt12 = strZzxp.charAt(i74);
                        if (cCharAt12 < c) {
                            break;
                        }
                        i73 |= (cCharAt12 & 8191) << i72;
                        i72 += 13;
                        i74 = i27;
                        c = 55296;
                    }
                    iCharAt11 = i73 | (cCharAt12 << i72);
                    i14 = i27;
                } else {
                    i14 = i70;
                }
                int i75 = i14 + 1;
                int iCharAt12 = strZzxp.charAt(i14);
                int i76 = i64;
                char c2 = 55296;
                if (iCharAt12 >= 55296) {
                    int i77 = 13;
                    int i78 = iCharAt12 & 8191;
                    int i79 = i75;
                    while (true) {
                        i26 = i79 + 1;
                        cCharAt11 = strZzxp.charAt(i79);
                        if (cCharAt11 < c2) {
                            break;
                        }
                        i78 |= (cCharAt11 & 8191) << i77;
                        i77 += 13;
                        i79 = i26;
                        c2 = 55296;
                    }
                    iCharAt12 = i78 | (cCharAt11 << i77);
                    i15 = i26;
                } else {
                    i15 = i75;
                }
                int i80 = i3;
                int i81 = iCharAt12 & 255;
                boolean z2 = z;
                if ((iCharAt12 & 1024) != 0) {
                    iArr[i68] = i69;
                    i68++;
                }
                if (i81 > zzvg.MAP.id()) {
                    int i82 = i15 + 1;
                    int iCharAt13 = strZzxp.charAt(i15);
                    char c3 = 55296;
                    if (iCharAt13 >= 55296) {
                        int i83 = iCharAt13 & 8191;
                        int i84 = i82;
                        int i85 = 13;
                        while (true) {
                            i24 = i84 + 1;
                            cCharAt10 = strZzxp.charAt(i84);
                            if (cCharAt10 < c3) {
                                break;
                            }
                            i83 |= (cCharAt10 & 8191) << i85;
                            i85 += 13;
                            i84 = i24;
                            c3 = 55296;
                        }
                        iCharAt13 = i83 | (cCharAt10 << i85);
                    } else {
                        i24 = i82;
                    }
                    if (i81 == zzvg.MESSAGE.id() + 51 || i81 == zzvg.GROUP.id() + 51) {
                        i17 = i68;
                        i25 = 1;
                        objArr[((i69 / 3) << 1) + 1] = objArrZzxq[i67];
                        i67++;
                    } else {
                        if (i81 == zzvg.ENUM.id() + 51) {
                            i17 = i68;
                            if ((iCharAt4 & 1) == 1) {
                                objArr[((i69 / 3) << 1) + 1] = objArrZzxq[i67];
                                i67++;
                            }
                        } else {
                            i17 = i68;
                        }
                        i25 = 1;
                    }
                    int i86 = iCharAt13 << i25;
                    Object obj = objArrZzxq[i86];
                    if (obj instanceof Field) {
                        fieldZza2 = (Field) obj;
                    } else {
                        fieldZza2 = zza(cls2, (String) obj);
                        objArrZzxq[i86] = fieldZza2;
                    }
                    i16 = iCharAt2;
                    int iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldZza2);
                    int i87 = i86 + 1;
                    Object obj2 = objArrZzxq[i87];
                    if (obj2 instanceof Field) {
                        fieldZza3 = (Field) obj2;
                    } else {
                        fieldZza3 = zza(cls2, (String) obj2);
                        objArrZzxq[i87] = fieldZza3;
                    }
                    i19 = i4;
                    i18 = iCharAt;
                    i20 = i24;
                    iObjectFieldOffset = iObjectFieldOffset3;
                    iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZza3);
                    i21 = 0;
                } else {
                    i16 = iCharAt2;
                    i17 = i68;
                    int i88 = i67 + 1;
                    Field fieldZza4 = zza(cls2, (String) objArrZzxq[i67]);
                    if (i81 == zzvg.MESSAGE.id() || i81 == zzvg.GROUP.id()) {
                        i18 = iCharAt;
                        objArr[((i69 / 3) << 1) + 1] = fieldZza4.getType();
                    } else {
                        if (i81 == zzvg.MESSAGE_LIST.id() || i81 == zzvg.GROUP_LIST.id()) {
                            i18 = iCharAt;
                            i23 = i88 + 1;
                            objArr[((i69 / 3) << 1) + 1] = objArrZzxq[i88];
                        } else if (i81 == zzvg.ENUM.id() || i81 == zzvg.ENUM_LIST.id() || i81 == zzvg.ENUM_LIST_PACKED.id()) {
                            i18 = iCharAt;
                            if ((iCharAt4 & 1) == 1) {
                                i23 = i88 + 1;
                                objArr[((i69 / 3) << 1) + 1] = objArrZzxq[i88];
                            }
                        } else if (i81 == zzvg.MAP.id()) {
                            int i89 = i66 + 1;
                            iArr[i66] = i69;
                            int i90 = (i69 / 3) << 1;
                            int i91 = i88 + 1;
                            objArr[i90] = objArrZzxq[i88];
                            if ((iCharAt12 & 2048) != 0) {
                                i88 = i91 + 1;
                                objArr[i90 + 1] = objArrZzxq[i91];
                                i18 = iCharAt;
                                i66 = i89;
                            } else {
                                i18 = iCharAt;
                                i88 = i91;
                                i66 = i89;
                            }
                        } else {
                            i18 = iCharAt;
                        }
                        i88 = i23;
                    }
                    int i92 = i88;
                    iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZza4);
                    if ((iCharAt4 & 1) == 1 && i81 <= zzvg.GROUP.id()) {
                        int i93 = i15 + 1;
                        int iCharAt14 = strZzxp.charAt(i15);
                        if (iCharAt14 >= 55296) {
                            int i94 = iCharAt14 & 8191;
                            int i95 = 13;
                            while (true) {
                                i22 = i93 + 1;
                                cCharAt9 = strZzxp.charAt(i93);
                                if (cCharAt9 < 55296) {
                                    break;
                                }
                                i94 |= (cCharAt9 & 8191) << i95;
                                i95 += 13;
                                i93 = i22;
                            }
                            iCharAt14 = i94 | (cCharAt9 << i95);
                            i93 = i22;
                        }
                        int i96 = (i4 << 1) + (iCharAt14 / 32);
                        Object obj3 = objArrZzxq[i96];
                        if (obj3 instanceof Field) {
                            fieldZza = (Field) obj3;
                        } else {
                            fieldZza = zza(cls2, (String) obj3);
                            objArrZzxq[i96] = fieldZza;
                        }
                        i19 = i4;
                        i20 = i93;
                        iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZza);
                        i21 = iCharAt14 % 32;
                        i67 = i92;
                    } else {
                        i19 = i4;
                        i20 = i15;
                        i67 = i92;
                        i21 = 0;
                        iObjectFieldOffset2 = 0;
                    }
                }
                if (i81 >= 18 && i81 <= 49) {
                    iArr[i65] = iObjectFieldOffset;
                    i65++;
                }
                int i97 = i69 + 1;
                iArr3[i69] = iCharAt11;
                int i98 = i97 + 1;
                iArr3[i97] = ((iCharAt12 & 256) != 0 ? CrashUtils.ErrorDialogData.BINDER_CRASH : 0) | ((iCharAt12 & 512) != 0 ? CrashUtils.ErrorDialogData.DYNAMITE_CRASH : 0) | (i81 << 20) | iObjectFieldOffset;
                i69 = i98 + 1;
                iArr3[i98] = (i21 << 20) | iObjectFieldOffset2;
                length = i71;
                i64 = i76;
                i3 = i80;
                z = z2;
                i68 = i17;
                iCharAt2 = i16;
                iCharAt = i18;
                i4 = i19;
                i33 = i20;
            }
            return new zzwx<>(iArr3, objArr, iCharAt, iCharAt2, zzxhVar.zzxi(), z, false, iArr, i3, i64, zzxaVar, zzwdVar, zzybVar, zzvaVar, zzwoVar);
        }
        ((zzxw) zzwrVar).zzxg();
        throw new NoSuchMethodError();
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String string = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(40 + String.valueOf(str).length() + String.valueOf(name).length() + String.valueOf(string).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(string);
            throw new RuntimeException(sb.toString());
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzxj
    public final T newInstance() {
        return (T) this.zzcbl.newInstance(this.zzcbd);
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x01ba  */
    @Override // com.google.android.gms.internal.measurement.zzxj
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(T r10, T r11) {
        /*
            Method dump skipped, instruction units count: 636
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzwx.equals(java.lang.Object, java.lang.Object):boolean");
    }

    @Override // com.google.android.gms.internal.measurement.zzxj
    public final int hashCode(T t) {
        int length = this.zzcaz.length;
        int iZzbf = 0;
        for (int i = 0; i < length; i += 3) {
            int iZzbq = zzbq(i);
            int i2 = this.zzcaz[i];
            long j = 1048575 & iZzbq;
            int iHashCode = 37;
            switch ((iZzbq & 267386880) >>> 20) {
                case 0:
                    iZzbf = (iZzbf * 53) + zzvo.zzbf(Double.doubleToLongBits(zzyh.zzo(t, j)));
                    break;
                case 1:
                    iZzbf = (iZzbf * 53) + Float.floatToIntBits(zzyh.zzn(t, j));
                    break;
                case 2:
                    iZzbf = (iZzbf * 53) + zzvo.zzbf(zzyh.zzl(t, j));
                    break;
                case 3:
                    iZzbf = (iZzbf * 53) + zzvo.zzbf(zzyh.zzl(t, j));
                    break;
                case 4:
                    iZzbf = (iZzbf * 53) + zzyh.zzk(t, j);
                    break;
                case 5:
                    iZzbf = (iZzbf * 53) + zzvo.zzbf(zzyh.zzl(t, j));
                    break;
                case 6:
                    iZzbf = (iZzbf * 53) + zzyh.zzk(t, j);
                    break;
                case 7:
                    iZzbf = (iZzbf * 53) + zzvo.zzw(zzyh.zzm(t, j));
                    break;
                case 8:
                    iZzbf = (iZzbf * 53) + ((String) zzyh.zzp(t, j)).hashCode();
                    break;
                case 9:
                    Object objZzp = zzyh.zzp(t, j);
                    if (objZzp != null) {
                        iHashCode = objZzp.hashCode();
                    }
                    iZzbf = (iZzbf * 53) + iHashCode;
                    break;
                case 10:
                    iZzbf = (iZzbf * 53) + zzyh.zzp(t, j).hashCode();
                    break;
                case 11:
                    iZzbf = (iZzbf * 53) + zzyh.zzk(t, j);
                    break;
                case 12:
                    iZzbf = (iZzbf * 53) + zzyh.zzk(t, j);
                    break;
                case 13:
                    iZzbf = (iZzbf * 53) + zzyh.zzk(t, j);
                    break;
                case 14:
                    iZzbf = (iZzbf * 53) + zzvo.zzbf(zzyh.zzl(t, j));
                    break;
                case 15:
                    iZzbf = (iZzbf * 53) + zzyh.zzk(t, j);
                    break;
                case 16:
                    iZzbf = (iZzbf * 53) + zzvo.zzbf(zzyh.zzl(t, j));
                    break;
                case 17:
                    Object objZzp2 = zzyh.zzp(t, j);
                    if (objZzp2 != null) {
                        iHashCode = objZzp2.hashCode();
                    }
                    iZzbf = (iZzbf * 53) + iHashCode;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    iZzbf = (iZzbf * 53) + zzyh.zzp(t, j).hashCode();
                    break;
                case 50:
                    iZzbf = (iZzbf * 53) + zzyh.zzp(t, j).hashCode();
                    break;
                case 51:
                    if (zza(t, i2, i)) {
                        iZzbf = (iZzbf * 53) + zzvo.zzbf(Double.doubleToLongBits(zzf(t, j)));
                    }
                    break;
                case 52:
                    if (zza(t, i2, i)) {
                        iZzbf = (iZzbf * 53) + Float.floatToIntBits(zzg(t, j));
                    }
                    break;
                case 53:
                    if (zza(t, i2, i)) {
                        iZzbf = (iZzbf * 53) + zzvo.zzbf(zzi(t, j));
                    }
                    break;
                case 54:
                    if (zza(t, i2, i)) {
                        iZzbf = (iZzbf * 53) + zzvo.zzbf(zzi(t, j));
                    }
                    break;
                case 55:
                    if (zza(t, i2, i)) {
                        iZzbf = (iZzbf * 53) + zzh(t, j);
                    }
                    break;
                case 56:
                    if (zza(t, i2, i)) {
                        iZzbf = (iZzbf * 53) + zzvo.zzbf(zzi(t, j));
                    }
                    break;
                case 57:
                    if (zza(t, i2, i)) {
                        iZzbf = (iZzbf * 53) + zzh(t, j);
                    }
                    break;
                case 58:
                    if (zza(t, i2, i)) {
                        iZzbf = (iZzbf * 53) + zzvo.zzw(zzj(t, j));
                    }
                    break;
                case 59:
                    if (zza(t, i2, i)) {
                        iZzbf = (iZzbf * 53) + ((String) zzyh.zzp(t, j)).hashCode();
                    }
                    break;
                case 60:
                    if (zza(t, i2, i)) {
                        iZzbf = (iZzbf * 53) + zzyh.zzp(t, j).hashCode();
                    }
                    break;
                case 61:
                    if (zza(t, i2, i)) {
                        iZzbf = (iZzbf * 53) + zzyh.zzp(t, j).hashCode();
                    }
                    break;
                case 62:
                    if (zza(t, i2, i)) {
                        iZzbf = (iZzbf * 53) + zzh(t, j);
                    }
                    break;
                case 63:
                    if (zza(t, i2, i)) {
                        iZzbf = (iZzbf * 53) + zzh(t, j);
                    }
                    break;
                case 64:
                    if (zza(t, i2, i)) {
                        iZzbf = (iZzbf * 53) + zzh(t, j);
                    }
                    break;
                case 65:
                    if (zza(t, i2, i)) {
                        iZzbf = (iZzbf * 53) + zzvo.zzbf(zzi(t, j));
                    }
                    break;
                case 66:
                    if (zza(t, i2, i)) {
                        iZzbf = (iZzbf * 53) + zzh(t, j);
                    }
                    break;
                case 67:
                    if (zza(t, i2, i)) {
                        iZzbf = (iZzbf * 53) + zzvo.zzbf(zzi(t, j));
                    }
                    break;
                case 68:
                    if (zza(t, i2, i)) {
                        iZzbf = (iZzbf * 53) + zzyh.zzp(t, j).hashCode();
                    }
                    break;
            }
        }
        int iHashCode2 = (iZzbf * 53) + this.zzcbn.zzah(t).hashCode();
        if (this.zzcbe) {
            return (iHashCode2 * 53) + this.zzcbo.zzs(t).hashCode();
        }
        return iHashCode2;
    }

    @Override // com.google.android.gms.internal.measurement.zzxj
    public final void zzd(T t, T t2) {
        if (t2 == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.zzcaz.length; i += 3) {
            int iZzbq = zzbq(i);
            long j = 1048575 & iZzbq;
            int i2 = this.zzcaz[i];
            switch ((iZzbq & 267386880) >>> 20) {
                case 0:
                    if (zzb(t2, i)) {
                        zzyh.zza(t, j, zzyh.zzo(t2, j));
                        zzc(t, i);
                    }
                    break;
                case 1:
                    if (zzb(t2, i)) {
                        zzyh.zza((Object) t, j, zzyh.zzn(t2, j));
                        zzc(t, i);
                    }
                    break;
                case 2:
                    if (zzb(t2, i)) {
                        zzyh.zza((Object) t, j, zzyh.zzl(t2, j));
                        zzc(t, i);
                    }
                    break;
                case 3:
                    if (zzb(t2, i)) {
                        zzyh.zza((Object) t, j, zzyh.zzl(t2, j));
                        zzc(t, i);
                    }
                    break;
                case 4:
                    if (zzb(t2, i)) {
                        zzyh.zzb(t, j, zzyh.zzk(t2, j));
                        zzc(t, i);
                    }
                    break;
                case 5:
                    if (zzb(t2, i)) {
                        zzyh.zza((Object) t, j, zzyh.zzl(t2, j));
                        zzc(t, i);
                    }
                    break;
                case 6:
                    if (zzb(t2, i)) {
                        zzyh.zzb(t, j, zzyh.zzk(t2, j));
                        zzc(t, i);
                    }
                    break;
                case 7:
                    if (zzb(t2, i)) {
                        zzyh.zza(t, j, zzyh.zzm(t2, j));
                        zzc(t, i);
                    }
                    break;
                case 8:
                    if (zzb(t2, i)) {
                        zzyh.zza(t, j, zzyh.zzp(t2, j));
                        zzc(t, i);
                    }
                    break;
                case 9:
                    zza(t, t2, i);
                    break;
                case 10:
                    if (zzb(t2, i)) {
                        zzyh.zza(t, j, zzyh.zzp(t2, j));
                        zzc(t, i);
                    }
                    break;
                case 11:
                    if (zzb(t2, i)) {
                        zzyh.zzb(t, j, zzyh.zzk(t2, j));
                        zzc(t, i);
                    }
                    break;
                case 12:
                    if (zzb(t2, i)) {
                        zzyh.zzb(t, j, zzyh.zzk(t2, j));
                        zzc(t, i);
                    }
                    break;
                case 13:
                    if (zzb(t2, i)) {
                        zzyh.zzb(t, j, zzyh.zzk(t2, j));
                        zzc(t, i);
                    }
                    break;
                case 14:
                    if (zzb(t2, i)) {
                        zzyh.zza((Object) t, j, zzyh.zzl(t2, j));
                        zzc(t, i);
                    }
                    break;
                case 15:
                    if (zzb(t2, i)) {
                        zzyh.zzb(t, j, zzyh.zzk(t2, j));
                        zzc(t, i);
                    }
                    break;
                case 16:
                    if (zzb(t2, i)) {
                        zzyh.zza((Object) t, j, zzyh.zzl(t2, j));
                        zzc(t, i);
                    }
                    break;
                case 17:
                    zza(t, t2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzcbm.zza(t, t2, j);
                    break;
                case 50:
                    zzxl.zza(this.zzcbp, t, t2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zza(t2, i2, i)) {
                        zzyh.zza(t, j, zzyh.zzp(t2, j));
                        zzb(t, i2, i);
                    }
                    break;
                case 60:
                    zzb(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zza(t2, i2, i)) {
                        zzyh.zza(t, j, zzyh.zzp(t2, j));
                        zzb(t, i2, i);
                    }
                    break;
                case 68:
                    zzb(t, t2, i);
                    break;
            }
        }
        if (!this.zzcbg) {
            zzxl.zza(this.zzcbn, t, t2);
            if (this.zzcbe) {
                zzxl.zza(this.zzcbo, t, t2);
            }
        }
    }

    private final void zza(T t, T t2, int i) {
        long jZzbq = zzbq(i) & 1048575;
        if (!zzb(t2, i)) {
            return;
        }
        Object objZzp = zzyh.zzp(t, jZzbq);
        Object objZzp2 = zzyh.zzp(t2, jZzbq);
        if (objZzp != null && objZzp2 != null) {
            zzyh.zza(t, jZzbq, zzvo.zzb(objZzp, objZzp2));
            zzc(t, i);
        } else if (objZzp2 != null) {
            zzyh.zza(t, jZzbq, objZzp2);
            zzc(t, i);
        }
    }

    private final void zzb(T t, T t2, int i) {
        int iZzbq = zzbq(i);
        int i2 = this.zzcaz[i];
        long j = iZzbq & 1048575;
        if (!zza(t2, i2, i)) {
            return;
        }
        Object objZzp = zzyh.zzp(t, j);
        Object objZzp2 = zzyh.zzp(t2, j);
        if (objZzp != null && objZzp2 != null) {
            zzyh.zza(t, j, zzvo.zzb(objZzp, objZzp2));
            zzb(t, i2, i);
        } else if (objZzp2 != null) {
            zzyh.zza(t, j, objZzp2);
            zzb(t, i2, i);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:417:0x0a26 A[PHI: r4
      0x0a26: PHI (r4v4 int) = 
      (r4v1 int)
      (r4v1 int)
      (r4v1 int)
      (r4v14 int)
      (r4v1 int)
      (r4v15 int)
      (r4v16 int)
      (r4v1 int)
      (r4v17 int)
      (r4v1 int)
      (r4v18 int)
      (r4v1 int)
      (r4v19 int)
      (r4v1 int)
      (r4v20 int)
      (r4v1 int)
      (r4v21 int)
      (r4v1 int)
      (r4v22 int)
      (r4v1 int)
      (r4v23 int)
      (r4v1 int)
      (r4v24 int)
      (r4v1 int)
      (r4v25 int)
      (r4v26 int)
      (r4v34 int)
      (r4v35 int)
      (r4v36 int)
      (r4v37 int)
      (r4v1 int)
      (r4v46 int)
      (r4v1 int)
      (r4v47 int)
      (r4v1 int)
      (r4v48 int)
      (r4v1 int)
      (r4v49 int)
      (r4v1 int)
      (r4v50 int)
      (r4v1 int)
      (r4v51 int)
      (r4v1 int)
      (r4v52 int)
      (r4v1 int)
      (r4v53 int)
      (r4v1 int)
      (r4v54 int)
      (r4v1 int)
      (r4v55 int)
      (r4v1 int)
      (r4v56 int)
      (r4v1 int)
      (r4v57 int)
      (r4v1 int)
      (r4v58 int)
      (r4v1 int)
      (r4v59 int)
      (r4v60 int)
      (r4v61 int)
      (r4v1 int)
      (r4v62 int)
      (r4v1 int)
      (r4v63 int)
      (r4v1 int)
      (r4v64 int)
      (r4v1 int)
      (r4v65 int)
      (r4v1 int)
      (r4v66 int)
      (r4v1 int)
      (r4v67 int)
      (r4v1 int)
      (r4v68 int)
      (r4v1 int)
      (r4v69 int)
      (r4v1 int)
      (r4v70 int)
      (r4v71 int)
      (r4v1 int)
      (r4v72 int)
      (r4v1 int)
      (r4v73 int)
      (r4v1 int)
      (r4v74 int)
      (r4v1 int)
      (r4v75 int)
      (r4v1 int)
      (r4v76 int)
      (r4v1 int)
      (r4v77 int)
      (r4v1 int)
      (r4v78 int)
      (r4v1 int)
      (r4v79 int)
      (r4v1 int)
      (r4v80 int)
     binds: [B:254:0x05eb, B:456:0x0ae0, B:453:0x0ad5, B:454:0x0ad7, B:447:0x0ab7, B:451:0x0aca, B:450:0x0ac1, B:444:0x0aa4, B:445:0x0aa6, B:441:0x0a94, B:442:0x0a96, B:438:0x0a86, B:439:0x0a88, B:435:0x0a78, B:436:0x0a7a, B:432:0x0a6d, B:433:0x0a6f, B:429:0x0a61, B:430:0x0a63, B:426:0x0a53, B:427:0x0a55, B:423:0x0a45, B:424:0x0a47, B:420:0x0a30, B:421:0x0a33, B:416:0x0a19, B:407:0x09ae, B:406:0x099c, B:405:0x098e, B:404:0x0980, B:394:0x0921, B:398:0x092b, B:388:0x08fe, B:392:0x0908, B:382:0x08db, B:386:0x08e5, B:376:0x08b8, B:380:0x08c2, B:370:0x0895, B:374:0x089f, B:364:0x0872, B:368:0x087c, B:358:0x084f, B:362:0x0859, B:352:0x082c, B:356:0x0836, B:346:0x0809, B:350:0x0813, B:340:0x07e6, B:344:0x07f0, B:334:0x07c3, B:338:0x07cd, B:328:0x07a0, B:332:0x07aa, B:322:0x077d, B:326:0x0787, B:316:0x075a, B:320:0x0764, B:314:0x073d, B:313:0x072c, B:311:0x0721, B:312:0x0723, B:308:0x0713, B:309:0x0715, B:305:0x0702, B:306:0x0704, B:302:0x06f1, B:303:0x06f3, B:299:0x06e0, B:300:0x06e2, B:296:0x06d1, B:297:0x06d3, B:293:0x06c3, B:294:0x06c5, B:290:0x06b6, B:291:0x06b8, B:284:0x0696, B:288:0x06a9, B:287:0x06a0, B:281:0x0681, B:282:0x0683, B:278:0x066d, B:279:0x0670, B:275:0x065c, B:276:0x065e, B:272:0x064b, B:273:0x064d, B:269:0x063d, B:270:0x063f, B:266:0x062e, B:267:0x0630, B:263:0x061d, B:264:0x061f, B:260:0x060c, B:261:0x060e, B:257:0x05f4, B:258:0x05f7] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.google.android.gms.internal.measurement.zzxj
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zzae(T r22) {
        /*
            Method dump skipped, instruction units count: 3208
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzwx.zzae(java.lang.Object):int");
    }

    private static <UT, UB> int zza(zzyb<UT, UB> zzybVar, T t) {
        return zzybVar.zzae(zzybVar.zzah(t));
    }

    private static <E> List<E> zze(Object obj, long j) {
        return (List) zzyh.zzp(obj, j);
    }

    /* JADX WARN: Removed duplicated region for block: B:178:0x05e3  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0033  */
    @Override // com.google.android.gms.internal.measurement.zzxj
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r14, com.google.android.gms.internal.measurement.zzyw r15) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 3222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzwx.zza(java.lang.Object, com.google.android.gms.internal.measurement.zzyw):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzb(T r21, com.google.android.gms.internal.measurement.zzyw r22) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1540
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzwx.zzb(java.lang.Object, com.google.android.gms.internal.measurement.zzyw):void");
    }

    private final <K, V> void zza(zzyw zzywVar, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzywVar.zza(i, this.zzcbp.zzad(zzbo(i2)), this.zzcbp.zzz(obj));
        }
    }

    private static <UT, UB> void zza(zzyb<UT, UB> zzybVar, T t, zzyw zzywVar) throws IOException {
        zzybVar.zza(zzybVar.zzah(t), zzywVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.gms.internal.measurement.zzxj
    public final void zza(T t, zzxi zzxiVar, zzuz zzuzVar) throws IOException {
        int i;
        Object objZza;
        Object objZza2;
        if (zzuzVar == null) {
            throw new NullPointerException();
        }
        zzyb<?, ?> zzybVar = this.zzcbn;
        zzva<?> zzvaVar = this.zzcbo;
        zzvd zzvdVarZzt = null;
        Object objZza3 = null;
        while (true) {
            try {
                int iZzve = zzxiVar.zzve();
                if (iZzve >= this.zzcbb && iZzve <= this.zzcbc) {
                    int i2 = 0;
                    int length = (this.zzcaz.length / 3) - 1;
                    while (i2 <= length) {
                        int i3 = (length + i2) >>> 1;
                        i = i3 * 3;
                        int i4 = this.zzcaz[i];
                        if (iZzve != i4) {
                            if (iZzve < i4) {
                                length = i3 - 1;
                            } else {
                                i2 = i3 + 1;
                            }
                        }
                    }
                    i = -1;
                } else {
                    i = -1;
                }
                if (i < 0) {
                    if (iZzve == Integer.MAX_VALUE) {
                        for (int i5 = this.zzcbj; i5 < this.zzcbk; i5++) {
                            objZza3 = zza((Object) t, this.zzcbi[i5], objZza3, (zzyb<UT, Object>) zzybVar);
                        }
                        if (objZza3 != null) {
                            zzybVar.zzg(t, (Object) objZza3);
                            return;
                        }
                        return;
                    }
                    if (this.zzcbe) {
                        objZza = zzvaVar.zza(zzuzVar, this.zzcbd, iZzve);
                    } else {
                        objZza = null;
                    }
                    if (objZza != null) {
                        if (zzvdVarZzt == null) {
                            zzvdVarZzt = zzvaVar.zzt(t);
                        }
                        zzvd zzvdVar = zzvdVarZzt;
                        objZza3 = zzvaVar.zza(zzxiVar, objZza, zzuzVar, zzvdVar, objZza3, zzybVar);
                        zzvdVarZzt = zzvdVar;
                    } else {
                        zzybVar.zza(zzxiVar);
                        if (objZza3 == null) {
                            objZza3 = zzybVar.zzai(t);
                        }
                        if (!zzybVar.zza((Object) objZza3, zzxiVar)) {
                            for (int i6 = this.zzcbj; i6 < this.zzcbk; i6++) {
                                objZza3 = zza((Object) t, this.zzcbi[i6], objZza3, (zzyb<UT, Object>) zzybVar);
                            }
                            if (objZza3 != null) {
                                zzybVar.zzg(t, (Object) objZza3);
                                return;
                            }
                            return;
                        }
                    }
                } else {
                    int iZzbq = zzbq(i);
                    switch ((267386880 & iZzbq) >>> 20) {
                        case 0:
                            zzyh.zza(t, iZzbq & 1048575, zzxiVar.readDouble());
                            zzc(t, i);
                            break;
                        case 1:
                            zzyh.zza((Object) t, iZzbq & 1048575, zzxiVar.readFloat());
                            zzc(t, i);
                            break;
                        case 2:
                            zzyh.zza((Object) t, iZzbq & 1048575, zzxiVar.zzui());
                            zzc(t, i);
                            break;
                        case 3:
                            zzyh.zza((Object) t, iZzbq & 1048575, zzxiVar.zzuh());
                            zzc(t, i);
                            break;
                        case 4:
                            zzyh.zzb(t, iZzbq & 1048575, zzxiVar.zzuj());
                            zzc(t, i);
                            break;
                        case 5:
                            zzyh.zza((Object) t, iZzbq & 1048575, zzxiVar.zzuk());
                            zzc(t, i);
                            break;
                        case 6:
                            zzyh.zzb(t, iZzbq & 1048575, zzxiVar.zzul());
                            zzc(t, i);
                            break;
                        case 7:
                            zzyh.zza(t, iZzbq & 1048575, zzxiVar.zzum());
                            zzc(t, i);
                            break;
                        case 8:
                            zza(t, iZzbq, zzxiVar);
                            zzc(t, i);
                            break;
                        case 9:
                            if (zzb(t, i)) {
                                long j = iZzbq & 1048575;
                                zzyh.zza(t, j, zzvo.zzb(zzyh.zzp(t, j), zzxiVar.zza(zzbn(i), zzuzVar)));
                            } else {
                                zzyh.zza(t, iZzbq & 1048575, zzxiVar.zza(zzbn(i), zzuzVar));
                                zzc(t, i);
                            }
                            break;
                        case 10:
                            zzyh.zza(t, iZzbq & 1048575, zzxiVar.zzuo());
                            zzc(t, i);
                            break;
                        case 11:
                            zzyh.zzb(t, iZzbq & 1048575, zzxiVar.zzup());
                            zzc(t, i);
                            break;
                        case 12:
                            int iZzuq = zzxiVar.zzuq();
                            zzvr zzvrVarZzbp = zzbp(i);
                            if (zzvrVarZzbp != null && !zzvrVarZzbp.zzb(iZzuq)) {
                                objZza2 = zzxl.zza(iZzve, iZzuq, objZza3, (zzyb<UT, Object>) zzybVar);
                                objZza3 = objZza2;
                            }
                            zzyh.zzb(t, iZzbq & 1048575, iZzuq);
                            zzc(t, i);
                            break;
                        case 13:
                            zzyh.zzb(t, iZzbq & 1048575, zzxiVar.zzur());
                            zzc(t, i);
                            break;
                        case 14:
                            zzyh.zza((Object) t, iZzbq & 1048575, zzxiVar.zzus());
                            zzc(t, i);
                            break;
                        case 15:
                            zzyh.zzb(t, iZzbq & 1048575, zzxiVar.zzut());
                            zzc(t, i);
                            break;
                        case 16:
                            zzyh.zza((Object) t, iZzbq & 1048575, zzxiVar.zzuu());
                            zzc(t, i);
                            break;
                        case 17:
                            if (zzb(t, i)) {
                                long j2 = iZzbq & 1048575;
                                zzyh.zza(t, j2, zzvo.zzb(zzyh.zzp(t, j2), zzxiVar.zzb(zzbn(i), zzuzVar)));
                            } else {
                                zzyh.zza(t, iZzbq & 1048575, zzxiVar.zzb(zzbn(i), zzuzVar));
                                zzc(t, i);
                            }
                            break;
                        case 18:
                            zzxiVar.zzh(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 19:
                            zzxiVar.zzi(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 20:
                            zzxiVar.zzk(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 21:
                            zzxiVar.zzj(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 22:
                            zzxiVar.zzl(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 23:
                            zzxiVar.zzm(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 24:
                            zzxiVar.zzn(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 25:
                            zzxiVar.zzo(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 26:
                            if (zzbs(iZzbq)) {
                                zzxiVar.zzp(this.zzcbm.zza(t, iZzbq & 1048575));
                            } else {
                                zzxiVar.readStringList(this.zzcbm.zza(t, iZzbq & 1048575));
                            }
                            break;
                        case 27:
                            zzxiVar.zza(this.zzcbm.zza(t, iZzbq & 1048575), zzbn(i), zzuzVar);
                            break;
                        case 28:
                            zzxiVar.zzq(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 29:
                            zzxiVar.zzr(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 30:
                            List<Integer> listZza = this.zzcbm.zza(t, iZzbq & 1048575);
                            zzxiVar.zzs(listZza);
                            objZza2 = zzxl.zza(iZzve, listZza, zzbp(i), objZza3, zzybVar);
                            objZza3 = objZza2;
                            break;
                        case 31:
                            zzxiVar.zzt(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 32:
                            zzxiVar.zzu(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 33:
                            zzxiVar.zzv(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 34:
                            zzxiVar.zzw(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 35:
                            zzxiVar.zzh(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 36:
                            zzxiVar.zzi(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 37:
                            zzxiVar.zzk(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 38:
                            zzxiVar.zzj(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 39:
                            zzxiVar.zzl(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 40:
                            zzxiVar.zzm(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 41:
                            zzxiVar.zzn(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 42:
                            zzxiVar.zzo(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 43:
                            zzxiVar.zzr(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 44:
                            List<Integer> listZza2 = this.zzcbm.zza(t, iZzbq & 1048575);
                            zzxiVar.zzs(listZza2);
                            objZza2 = zzxl.zza(iZzve, listZza2, zzbp(i), objZza3, zzybVar);
                            objZza3 = objZza2;
                            break;
                        case 45:
                            zzxiVar.zzt(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 46:
                            zzxiVar.zzu(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 47:
                            zzxiVar.zzv(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 48:
                            zzxiVar.zzw(this.zzcbm.zza(t, iZzbq & 1048575));
                            break;
                        case 49:
                            zzxiVar.zzb(this.zzcbm.zza(t, iZzbq & 1048575), zzbn(i), zzuzVar);
                            break;
                        case 50:
                            Object objZzbo = zzbo(i);
                            long jZzbq = zzbq(i) & 1048575;
                            Object objZzp = zzyh.zzp(t, jZzbq);
                            if (objZzp == null) {
                                objZzp = this.zzcbp.zzac(objZzbo);
                                zzyh.zza(t, jZzbq, objZzp);
                            } else if (this.zzcbp.zzaa(objZzp)) {
                                Object objZzac = this.zzcbp.zzac(objZzbo);
                                this.zzcbp.zzc(objZzac, objZzp);
                                zzyh.zza(t, jZzbq, objZzac);
                                objZzp = objZzac;
                            }
                            zzxiVar.zza(this.zzcbp.zzy(objZzp), this.zzcbp.zzad(objZzbo), zzuzVar);
                            break;
                        case 51:
                            zzyh.zza(t, iZzbq & 1048575, Double.valueOf(zzxiVar.readDouble()));
                            zzb(t, iZzve, i);
                            break;
                        case 52:
                            zzyh.zza(t, iZzbq & 1048575, Float.valueOf(zzxiVar.readFloat()));
                            zzb(t, iZzve, i);
                            break;
                        case 53:
                            zzyh.zza(t, iZzbq & 1048575, Long.valueOf(zzxiVar.zzui()));
                            zzb(t, iZzve, i);
                            break;
                        case 54:
                            zzyh.zza(t, iZzbq & 1048575, Long.valueOf(zzxiVar.zzuh()));
                            zzb(t, iZzve, i);
                            break;
                        case 55:
                            zzyh.zza(t, iZzbq & 1048575, Integer.valueOf(zzxiVar.zzuj()));
                            zzb(t, iZzve, i);
                            break;
                        case 56:
                            zzyh.zza(t, iZzbq & 1048575, Long.valueOf(zzxiVar.zzuk()));
                            zzb(t, iZzve, i);
                            break;
                        case 57:
                            zzyh.zza(t, iZzbq & 1048575, Integer.valueOf(zzxiVar.zzul()));
                            zzb(t, iZzve, i);
                            break;
                        case 58:
                            zzyh.zza(t, iZzbq & 1048575, Boolean.valueOf(zzxiVar.zzum()));
                            zzb(t, iZzve, i);
                            break;
                        case 59:
                            zza(t, iZzbq, zzxiVar);
                            zzb(t, iZzve, i);
                            break;
                        case 60:
                            if (zza(t, iZzve, i)) {
                                long j3 = iZzbq & 1048575;
                                zzyh.zza(t, j3, zzvo.zzb(zzyh.zzp(t, j3), zzxiVar.zza(zzbn(i), zzuzVar)));
                            } else {
                                zzyh.zza(t, iZzbq & 1048575, zzxiVar.zza(zzbn(i), zzuzVar));
                                zzc(t, i);
                            }
                            zzb(t, iZzve, i);
                            break;
                        case 61:
                            zzyh.zza(t, iZzbq & 1048575, zzxiVar.zzuo());
                            zzb(t, iZzve, i);
                            break;
                        case 62:
                            zzyh.zza(t, iZzbq & 1048575, Integer.valueOf(zzxiVar.zzup()));
                            zzb(t, iZzve, i);
                            break;
                        case 63:
                            int iZzuq2 = zzxiVar.zzuq();
                            zzvr zzvrVarZzbp2 = zzbp(i);
                            if (zzvrVarZzbp2 != null && !zzvrVarZzbp2.zzb(iZzuq2)) {
                                objZza2 = zzxl.zza(iZzve, iZzuq2, objZza3, (zzyb<UT, Object>) zzybVar);
                                objZza3 = objZza2;
                            }
                            zzyh.zza(t, iZzbq & 1048575, Integer.valueOf(iZzuq2));
                            zzb(t, iZzve, i);
                            break;
                        case 64:
                            zzyh.zza(t, iZzbq & 1048575, Integer.valueOf(zzxiVar.zzur()));
                            zzb(t, iZzve, i);
                            break;
                        case 65:
                            zzyh.zza(t, iZzbq & 1048575, Long.valueOf(zzxiVar.zzus()));
                            zzb(t, iZzve, i);
                            break;
                        case 66:
                            zzyh.zza(t, iZzbq & 1048575, Integer.valueOf(zzxiVar.zzut()));
                            zzb(t, iZzve, i);
                            break;
                        case 67:
                            zzyh.zza(t, iZzbq & 1048575, Long.valueOf(zzxiVar.zzuu()));
                            zzb(t, iZzve, i);
                            break;
                        case 68:
                            zzyh.zza(t, iZzbq & 1048575, zzxiVar.zzb(zzbn(i), zzuzVar));
                            zzb(t, iZzve, i);
                            break;
                        default:
                            if (objZza3 == null) {
                                try {
                                    objZza3 = zzybVar.zzye();
                                } catch (zzvu e) {
                                    zzybVar.zza(zzxiVar);
                                    if (objZza3 == null) {
                                        objZza3 = zzybVar.zzai(t);
                                    }
                                    if (!zzybVar.zza((Object) objZza3, zzxiVar)) {
                                        for (int i7 = this.zzcbj; i7 < this.zzcbk; i7++) {
                                            objZza3 = zza((Object) t, this.zzcbi[i7], objZza3, (zzyb<UT, Object>) zzybVar);
                                        }
                                        if (objZza3 != null) {
                                            zzybVar.zzg(t, (Object) objZza3);
                                            return;
                                        }
                                        return;
                                    }
                                }
                                break;
                            }
                            if (!zzybVar.zza((Object) objZza3, zzxiVar)) {
                                for (int i8 = this.zzcbj; i8 < this.zzcbk; i8++) {
                                    objZza3 = zza((Object) t, this.zzcbi[i8], objZza3, (zzyb<UT, Object>) zzybVar);
                                }
                                if (objZza3 != null) {
                                    zzybVar.zzg(t, (Object) objZza3);
                                    return;
                                }
                                return;
                            }
                            break;
                            break;
                    }
                }
            } finally {
            }
        }
    }

    private final zzxj zzbn(int i) {
        int i2 = (i / 3) << 1;
        zzxj zzxjVar = (zzxj) this.zzcba[i2];
        if (zzxjVar != null) {
            return zzxjVar;
        }
        zzxj<T> zzxjVarZzi = zzxf.zzxn().zzi((Class) this.zzcba[i2 + 1]);
        this.zzcba[i2] = zzxjVarZzi;
        return zzxjVarZzi;
    }

    private final Object zzbo(int i) {
        return this.zzcba[(i / 3) << 1];
    }

    private final zzvr zzbp(int i) {
        return (zzvr) this.zzcba[((i / 3) << 1) + 1];
    }

    @Override // com.google.android.gms.internal.measurement.zzxj
    public final void zzu(T t) {
        for (int i = this.zzcbj; i < this.zzcbk; i++) {
            long jZzbq = zzbq(this.zzcbi[i]) & 1048575;
            Object objZzp = zzyh.zzp(t, jZzbq);
            if (objZzp != null) {
                zzyh.zza(t, jZzbq, this.zzcbp.zzab(objZzp));
            }
        }
        int length = this.zzcbi.length;
        for (int i2 = this.zzcbk; i2 < length; i2++) {
            this.zzcbm.zzb(t, this.zzcbi[i2]);
        }
        this.zzcbn.zzu(t);
        if (this.zzcbe) {
            this.zzcbo.zzu(t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzyb<UT, UB> zzybVar) {
        zzvr zzvrVarZzbp;
        int i2 = this.zzcaz[i];
        Object objZzp = zzyh.zzp(obj, zzbq(i) & 1048575);
        if (objZzp == null || (zzvrVarZzbp = zzbp(i)) == null) {
            return ub;
        }
        return (UB) zza(i, i2, this.zzcbp.zzy(objZzp), zzvrVarZzbp, ub, zzybVar);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzvr zzvrVar, UB ub, zzyb<UT, UB> zzybVar) {
        zzwm<?, ?> zzwmVarZzad = this.zzcbp.zzad(zzbo(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!zzvrVar.zzb(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzybVar.zzye();
                }
                zzuk zzukVarZzam = zzud.zzam(zzwl.zza(zzwmVarZzad, next.getKey(), next.getValue()));
                try {
                    zzwl.zza(zzukVarZzam.zzuf(), zzwmVarZzad, next.getKey(), next.getValue());
                    zzybVar.zza(ub, i2, zzukVarZzam.zzue());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    /* JADX WARN: Code restructure failed: missing block: B:89:0x0115, code lost:
    
        continue;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d4  */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14, types: [com.google.android.gms.internal.measurement.zzxj] */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.google.android.gms.internal.measurement.zzxj] */
    @Override // com.google.android.gms.internal.measurement.zzxj
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzaf(T r14) {
        /*
            Method dump skipped, instruction units count: 308
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzwx.zzaf(java.lang.Object):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object obj, int i, zzxj zzxjVar) {
        return zzxjVar.zzaf(zzyh.zzp(obj, i & 1048575));
    }

    private static void zza(int i, Object obj, zzyw zzywVar) throws IOException {
        if (obj instanceof String) {
            zzywVar.zzb(i, (String) obj);
        } else {
            zzywVar.zza(i, (zzud) obj);
        }
    }

    private final void zza(Object obj, int i, zzxi zzxiVar) throws IOException {
        if (zzbs(i)) {
            zzyh.zza(obj, i & 1048575, zzxiVar.zzun());
        } else if (this.zzcbf) {
            zzyh.zza(obj, i & 1048575, zzxiVar.readString());
        } else {
            zzyh.zza(obj, i & 1048575, zzxiVar.zzuo());
        }
    }

    private final int zzbq(int i) {
        return this.zzcaz[i + 1];
    }

    private final int zzbr(int i) {
        return this.zzcaz[i + 2];
    }

    private static boolean zzbs(int i) {
        return (i & CrashUtils.ErrorDialogData.DYNAMITE_CRASH) != 0;
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzyh.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzyh.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzyh.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzyh.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzyh.zzp(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zzb(t, i) == zzb(t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        if (this.zzcbg) {
            return zzb(t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zzb(T t, int i) {
        if (this.zzcbg) {
            int iZzbq = zzbq(i);
            long j = iZzbq & 1048575;
            switch ((iZzbq & 267386880) >>> 20) {
                case 0:
                    return zzyh.zzo(t, j) != 0.0d;
                case 1:
                    return zzyh.zzn(t, j) != 0.0f;
                case 2:
                    return zzyh.zzl(t, j) != 0;
                case 3:
                    return zzyh.zzl(t, j) != 0;
                case 4:
                    return zzyh.zzk(t, j) != 0;
                case 5:
                    return zzyh.zzl(t, j) != 0;
                case 6:
                    return zzyh.zzk(t, j) != 0;
                case 7:
                    return zzyh.zzm(t, j);
                case 8:
                    Object objZzp = zzyh.zzp(t, j);
                    if (objZzp instanceof String) {
                        return !((String) objZzp).isEmpty();
                    }
                    if (objZzp instanceof zzud) {
                        return !zzud.zzbtz.equals(objZzp);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzyh.zzp(t, j) != null;
                case 10:
                    return !zzud.zzbtz.equals(zzyh.zzp(t, j));
                case 11:
                    return zzyh.zzk(t, j) != 0;
                case 12:
                    return zzyh.zzk(t, j) != 0;
                case 13:
                    return zzyh.zzk(t, j) != 0;
                case 14:
                    return zzyh.zzl(t, j) != 0;
                case 15:
                    return zzyh.zzk(t, j) != 0;
                case 16:
                    return zzyh.zzl(t, j) != 0;
                case 17:
                    return zzyh.zzp(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        int iZzbr = zzbr(i);
        return (zzyh.zzk(t, (long) (iZzbr & 1048575)) & (1 << (iZzbr >>> 20))) != 0;
    }

    private final void zzc(T t, int i) {
        if (this.zzcbg) {
            return;
        }
        int iZzbr = zzbr(i);
        long j = iZzbr & 1048575;
        zzyh.zzb(t, j, zzyh.zzk(t, j) | (1 << (iZzbr >>> 20)));
    }

    private final boolean zza(T t, int i, int i2) {
        return zzyh.zzk(t, (long) (zzbr(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzyh.zzb(t, zzbr(i2) & 1048575, i);
    }
}
