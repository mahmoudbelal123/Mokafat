package com.google.android.gms.internal.measurement;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'zzcdy' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes.dex */
public class zzyq {
    public static final zzyq zzcdq = new zzyq("DOUBLE", 0, zzyv.DOUBLE, 1);
    public static final zzyq zzcdr = new zzyq("FLOAT", 1, zzyv.FLOAT, 5);
    public static final zzyq zzcds = new zzyq("INT64", 2, zzyv.LONG, 0);
    public static final zzyq zzcdt = new zzyq("UINT64", 3, zzyv.LONG, 0);
    public static final zzyq zzcdu = new zzyq("INT32", 4, zzyv.INT, 0);
    public static final zzyq zzcdv = new zzyq("FIXED64", 5, zzyv.LONG, 1);
    public static final zzyq zzcdw = new zzyq("FIXED32", 6, zzyv.INT, 5);
    public static final zzyq zzcdx = new zzyq("BOOL", 7, zzyv.BOOLEAN, 0);
    public static final zzyq zzcdy;
    public static final zzyq zzcdz;
    public static final zzyq zzcea;
    public static final zzyq zzceb;
    public static final zzyq zzcec;
    public static final zzyq zzced;
    public static final zzyq zzcee;
    public static final zzyq zzcef;
    public static final zzyq zzceg;
    public static final zzyq zzceh;
    private static final /* synthetic */ zzyq[] zzcek;
    private final zzyv zzcei;
    private final int zzcej;

    public static zzyq[] values() {
        return (zzyq[]) zzcek.clone();
    }

    private zzyq(String str, int i, zzyv zzyvVar, int i2) {
        this.zzcei = zzyvVar;
        this.zzcej = i2;
    }

    public final zzyv zzyp() {
        return this.zzcei;
    }

    public final int zzyq() {
        return this.zzcej;
    }

    /* synthetic */ zzyq(String str, int i, zzyv zzyvVar, int i2, zzyp zzypVar) {
        this(str, i, zzyvVar, i2);
    }

    static {
        final int i = 2;
        final int i2 = 3;
        final String str = "STRING";
        final zzyv zzyvVar = zzyv.STRING;
        final int i3 = 8;
        zzcdy = new zzyq(str, i3, zzyvVar, i) { // from class: com.google.android.gms.internal.measurement.zzyr
            {
                int i4 = 8;
                int i5 = 2;
                zzyp zzypVar = null;
            }
        };
        final String str2 = "GROUP";
        final zzyv zzyvVar2 = zzyv.MESSAGE;
        final int i4 = 9;
        zzcdz = new zzyq(str2, i4, zzyvVar2, i2) { // from class: com.google.android.gms.internal.measurement.zzys
            {
                int i5 = 9;
                int i6 = 3;
                zzyp zzypVar = null;
            }
        };
        final String str3 = "MESSAGE";
        final zzyv zzyvVar3 = zzyv.MESSAGE;
        final int i5 = 10;
        zzcea = new zzyq(str3, i5, zzyvVar3, i) { // from class: com.google.android.gms.internal.measurement.zzyt
            {
                int i6 = 10;
                int i7 = 2;
                zzyp zzypVar = null;
            }
        };
        final String str4 = "BYTES";
        final zzyv zzyvVar4 = zzyv.BYTE_STRING;
        final int i6 = 11;
        zzceb = new zzyq(str4, i6, zzyvVar4, i) { // from class: com.google.android.gms.internal.measurement.zzyu
            {
                int i7 = 11;
                int i8 = 2;
                zzyp zzypVar = null;
            }
        };
        zzcec = new zzyq("UINT32", 12, zzyv.INT, 0);
        zzced = new zzyq("ENUM", 13, zzyv.ENUM, 0);
        zzcee = new zzyq("SFIXED32", 14, zzyv.INT, 5);
        zzcef = new zzyq("SFIXED64", 15, zzyv.LONG, 1);
        zzceg = new zzyq("SINT32", 16, zzyv.INT, 0);
        zzceh = new zzyq("SINT64", 17, zzyv.LONG, 0);
        zzcek = new zzyq[]{zzcdq, zzcdr, zzcds, zzcdt, zzcdu, zzcdv, zzcdw, zzcdx, zzcdy, zzcdz, zzcea, zzceb, zzcec, zzced, zzcee, zzcef, zzceg, zzceh};
    }
}
