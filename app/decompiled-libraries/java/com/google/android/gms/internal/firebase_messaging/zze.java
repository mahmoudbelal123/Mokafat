package com.google.android.gms.internal.firebase_messaging;

/* JADX INFO: loaded from: classes.dex */
final class zze extends zzb {
    private final zzc zzg = new zzc();

    zze() {
    }

    @Override // com.google.android.gms.internal.firebase_messaging.zzb
    public final void zza(Throwable th, Throwable th2) {
        if (th2 == th) {
            throw new IllegalArgumentException("Self suppression is not allowed.", th2);
        }
        if (th2 == null) {
            throw new NullPointerException("The suppressed exception cannot be null.");
        }
        this.zzg.zza(th, true).add(th2);
    }
}
