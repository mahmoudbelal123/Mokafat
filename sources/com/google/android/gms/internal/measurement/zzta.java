package com.google.android.gms.internal.measurement;

import java.io.PrintStream;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class zzta extends zzsx {
    private final zzsy zzbrz = new zzsy();

    zzta() {
    }

    @Override // com.google.android.gms.internal.measurement.zzsx
    public final void zza(Throwable th, PrintStream printStream) {
        th.printStackTrace(printStream);
        List<Throwable> listZza = this.zzbrz.zza(th, false);
        if (listZza == null) {
            return;
        }
        synchronized (listZza) {
            for (Throwable th2 : listZza) {
                printStream.print("Suppressed: ");
                th2.printStackTrace(printStream);
            }
        }
    }
}
