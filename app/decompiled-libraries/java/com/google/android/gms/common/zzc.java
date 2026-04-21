package com.google.android.gms.common;

import com.google.android.gms.common.GoogleCertificates;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
abstract class zzc extends GoogleCertificates.CertData {
    private static final WeakReference<byte[]> zzbf = new WeakReference<>(null);
    private WeakReference<byte[]> zzbe;

    zzc(byte[] bArr) {
        super(bArr);
        this.zzbe = zzbf;
    }

    @Override // com.google.android.gms.common.GoogleCertificates.CertData
    final byte[] getBytes() {
        byte[] bArrZzf;
        synchronized (this) {
            bArrZzf = this.zzbe.get();
            if (bArrZzf == null) {
                bArrZzf = zzf();
                this.zzbe = new WeakReference<>(bArrZzf);
            }
        }
        return bArrZzf;
    }

    protected abstract byte[] zzf();
}
