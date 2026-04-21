package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
final class zzyn extends IllegalArgumentException {
    zzyn(int i, int i2) {
        StringBuilder sb = new StringBuilder(54);
        sb.append("Unpaired surrogate at index ");
        sb.append(i);
        sb.append(" of ");
        sb.append(i2);
        super(sb.toString());
    }
}
