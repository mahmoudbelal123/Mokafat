package com.google.android.gms.common;

import com.google.android.gms.common.GoogleCertificates;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
final class zzb extends GoogleCertificates.CertData {
    private final byte[] zzbd;

    zzb(byte[] bArr) {
        super(Arrays.copyOfRange(bArr, 0, 25));
        this.zzbd = bArr;
    }

    @Override // com.google.android.gms.common.GoogleCertificates.CertData
    final byte[] getBytes() {
        return this.zzbd;
    }
}
