package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class zzy {
    final String name;
    private final String origin;
    final long timestamp;
    final long zzaic;
    final zzaa zzaid;
    final String zztt;

    private zzy(zzbt zzbtVar, String str, String str2, String str3, long j, long j2, zzaa zzaaVar) {
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzaaVar);
        this.zztt = str2;
        this.name = str3;
        this.origin = TextUtils.isEmpty(str) ? null : str;
        this.timestamp = j;
        this.zzaic = j2;
        if (this.zzaic != 0 && this.zzaic > this.timestamp) {
            zzbtVar.zzgo().zzjg().zze("Event created with reverse previous/current timestamps. appId, name", zzap.zzbv(str2), zzap.zzbv(str3));
        }
        this.zzaid = zzaaVar;
    }

    zzy(zzbt zzbtVar, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        zzaa zzaaVar;
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        this.zztt = str2;
        this.name = str3;
        this.origin = TextUtils.isEmpty(str) ? null : str;
        this.timestamp = j;
        this.zzaic = j2;
        if (this.zzaic != 0 && this.zzaic > this.timestamp) {
            zzbtVar.zzgo().zzjg().zzg("Event created with reverse previous/current timestamps. appId", zzap.zzbv(str2));
        }
        if (bundle != null && !bundle.isEmpty()) {
            Bundle bundle2 = new Bundle(bundle);
            Iterator<String> it = bundle2.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next == null) {
                    zzbtVar.zzgo().zzjd().zzbx("Param name can't be null");
                    it.remove();
                } else {
                    Object objZzh = zzbtVar.zzgm().zzh(next, bundle2.get(next));
                    if (objZzh == null) {
                        zzbtVar.zzgo().zzjg().zzg("Param value can't be null", zzbtVar.zzgl().zzbt(next));
                        it.remove();
                    } else {
                        zzbtVar.zzgm().zza(bundle2, next, objZzh);
                    }
                }
            }
            zzaaVar = new zzaa(bundle2);
        } else {
            zzaaVar = new zzaa(new Bundle());
        }
        this.zzaid = zzaaVar;
    }

    final zzy zza(zzbt zzbtVar, long j) {
        return new zzy(zzbtVar, this.origin, this.zztt, this.name, this.timestamp, j, this.zzaid);
    }

    public final String toString() {
        String str = this.zztt;
        String str2 = this.name;
        String strValueOf = String.valueOf(this.zzaid);
        StringBuilder sb = new StringBuilder(33 + String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(strValueOf).length());
        sb.append("Event{appId='");
        sb.append(str);
        sb.append("', name='");
        sb.append(str2);
        sb.append("', params=");
        sb.append(strValueOf);
        sb.append('}');
        return sb.toString();
    }
}
