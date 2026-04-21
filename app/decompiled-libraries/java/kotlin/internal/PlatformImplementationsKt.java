package kotlin.internal;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: PlatformImplementations.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\b\u0010\u0002\u001a\u00020\u0003H\u0002\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"IMPLEMENTATIONS", "Lkotlin/internal/PlatformImplementations;", "getJavaVersion", "", "kotlin-stdlib"}, k = 2, mv = {1, 1, 5})
public final class PlatformImplementationsKt {

    @JvmField
    @NotNull
    public static final PlatformImplementations IMPLEMENTATIONS;

    /* JADX WARN: Removed duplicated region for block: B:20:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x002a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static {
        /*
            r0 = 0
            int r1 = getJavaVersion()
            r2 = 65544(0x10008, float:9.1847E-41)
            if (r1 < r2) goto L24
            java.lang.String r2 = "kotlin.internal.JRE8PlatformImplementations"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch: java.lang.ClassNotFoundException -> L23
            java.lang.Object r2 = r2.newInstance()     // Catch: java.lang.ClassNotFoundException -> L23
            if (r2 != 0) goto L20
            kotlin.TypeCastException r2 = new kotlin.TypeCastException     // Catch: java.lang.ClassNotFoundException -> L23
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.internal.PlatformImplementations"
            r2.<init>(r3)     // Catch: java.lang.ClassNotFoundException -> L23
            throw r2     // Catch: java.lang.ClassNotFoundException -> L23
        L20:
            kotlin.internal.PlatformImplementations r2 = (kotlin.internal.PlatformImplementations) r2     // Catch: java.lang.ClassNotFoundException -> L23
            goto L47
        L23:
            r2 = move-exception
        L24:
            r2 = 65543(0x10007, float:9.1845E-41)
            if (r1 < r2) goto L42
            java.lang.String r2 = "kotlin.internal.JRE7PlatformImplementations"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch: java.lang.ClassNotFoundException -> L41
            java.lang.Object r2 = r2.newInstance()     // Catch: java.lang.ClassNotFoundException -> L41
            if (r2 != 0) goto L3e
            kotlin.TypeCastException r2 = new kotlin.TypeCastException     // Catch: java.lang.ClassNotFoundException -> L41
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.internal.PlatformImplementations"
            r2.<init>(r3)     // Catch: java.lang.ClassNotFoundException -> L41
            throw r2     // Catch: java.lang.ClassNotFoundException -> L41
        L3e:
            kotlin.internal.PlatformImplementations r2 = (kotlin.internal.PlatformImplementations) r2     // Catch: java.lang.ClassNotFoundException -> L41
            goto L47
        L41:
            r2 = move-exception
        L42:
            kotlin.internal.PlatformImplementations r2 = new kotlin.internal.PlatformImplementations
            r2.<init>()
        L47:
            kotlin.internal.PlatformImplementations r2 = (kotlin.internal.PlatformImplementations) r2
            kotlin.internal.PlatformImplementationsKt.IMPLEMENTATIONS = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.internal.PlatformImplementationsKt.<clinit>():void");
    }

    private static final int getJavaVersion() {
        String version = System.getProperty("java.specification.version");
        if (version == null) {
            return 65542;
        }
        int firstDot = StringsKt.indexOf$default((CharSequence) version, '.', 0, false, 6, (Object) null);
        if (firstDot < 0) {
            try {
                return 65536 * Integer.parseInt(version);
            } catch (NumberFormatException e) {
                return 65542;
            }
        }
        int secondDot = StringsKt.indexOf$default((CharSequence) version, '.', firstDot + 1, false, 4, (Object) null);
        if (secondDot < 0) {
            secondDot = version.length();
        }
        if (version == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String firstPart = version.substring(0, firstDot);
        Intrinsics.checkExpressionValueIsNotNull(firstPart, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        int i = firstDot + 1;
        if (version == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String secondPart = version.substring(i, secondDot);
        Intrinsics.checkExpressionValueIsNotNull(secondPart, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        try {
            return Integer.parseInt(secondPart) + (Integer.parseInt(firstPart) * 65536);
        } catch (NumberFormatException e2) {
            return 65542;
        }
    }
}
