package com.google.android.gms.internal.measurement;

import java.io.PrintStream;

/* JADX INFO: loaded from: classes.dex */
public final class zzsw {
    private static final zzsx zzbrt;
    private static final int zzbru;

    static final class zza extends zzsx {
        zza() {
        }

        @Override // com.google.android.gms.internal.measurement.zzsx
        public final void zza(Throwable th, PrintStream printStream) {
            th.printStackTrace(printStream);
        }
    }

    public static void zza(Throwable th, PrintStream printStream) {
        zzbrt.zza(th, printStream);
    }

    private static Integer zztk() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0018 A[Catch: Throwable -> 0x0016, TryCatch #1 {Throwable -> 0x0016, blocks: (B:5:0x0008, B:7:0x0010, B:10:0x0018, B:12:0x0021, B:13:0x0027), top: B:27:0x0008 }] */
    static {
        /*
            r0 = 1
            java.lang.Integer r1 = zztk()     // Catch: java.lang.Throwable -> L2d
            if (r1 == 0) goto L18
            int r2 = r1.intValue()     // Catch: java.lang.Throwable -> L16
            r3 = 19
            if (r2 < r3) goto L18
            com.google.android.gms.internal.measurement.zztb r2 = new com.google.android.gms.internal.measurement.zztb     // Catch: java.lang.Throwable -> L16
            r2.<init>()     // Catch: java.lang.Throwable -> L16
            goto L65
        L16:
            r2 = move-exception
            goto L2f
        L18:
            java.lang.String r2 = "com.google.devtools.build.android.desugar.runtime.twr_disable_mimic"
            boolean r2 = java.lang.Boolean.getBoolean(r2)     // Catch: java.lang.Throwable -> L16
            r2 = r2 ^ r0
            if (r2 == 0) goto L27
            com.google.android.gms.internal.measurement.zzta r2 = new com.google.android.gms.internal.measurement.zzta     // Catch: java.lang.Throwable -> L16
            r2.<init>()     // Catch: java.lang.Throwable -> L16
            goto L65
        L27:
            com.google.android.gms.internal.measurement.zzsw$zza r2 = new com.google.android.gms.internal.measurement.zzsw$zza     // Catch: java.lang.Throwable -> L16
            r2.<init>()     // Catch: java.lang.Throwable -> L16
            goto L65
        L2d:
            r2 = move-exception
            r1 = 0
        L2f:
            java.io.PrintStream r3 = java.lang.System.err
            java.lang.Class<com.google.android.gms.internal.measurement.zzsw$zza> r4 = com.google.android.gms.internal.measurement.zzsw.zza.class
            java.lang.String r4 = r4.getName()
            r5 = 132(0x84, float:1.85E-43)
            java.lang.String r6 = java.lang.String.valueOf(r4)
            int r6 = r6.length()
            int r5 = r5 + r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r5)
            java.lang.String r5 = "An error has occured when initializing the try-with-resources desuguring strategy. The default strategy "
            r6.append(r5)
            r6.append(r4)
            java.lang.String r4 = "will be used. The error is: "
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            r3.println(r4)
            java.io.PrintStream r3 = java.lang.System.err
            r2.printStackTrace(r3)
            com.google.android.gms.internal.measurement.zzsw$zza r2 = new com.google.android.gms.internal.measurement.zzsw$zza
            r2.<init>()
        L65:
            com.google.android.gms.internal.measurement.zzsw.zzbrt = r2
            if (r1 != 0) goto L6a
            goto L6e
        L6a:
            int r0 = r1.intValue()
        L6e:
            com.google.android.gms.internal.measurement.zzsw.zzbru = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzsw.<clinit>():void");
    }
}
