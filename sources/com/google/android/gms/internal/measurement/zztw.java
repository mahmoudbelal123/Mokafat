package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zztw;
import com.google.android.gms.internal.measurement.zztx;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class zztw<MessageType extends zztw<MessageType, BuilderType>, BuilderType extends zztx<MessageType, BuilderType>> implements zzwt {
    private static boolean zzbts = false;
    protected int zzbtr = 0;

    @Override // com.google.android.gms.internal.measurement.zzwt
    public final zzud zztt() {
        try {
            zzuk zzukVarZzam = zzud.zzam(zzvu());
            zzb(zzukVarZzam.zzuf());
            return zzukVarZzam.zzue();
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(62 + String.valueOf(name).length() + String.valueOf("ByteString").length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("ByteString");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    int zztu() {
        throw new UnsupportedOperationException();
    }

    void zzah(int i) {
        throw new UnsupportedOperationException();
    }
}
