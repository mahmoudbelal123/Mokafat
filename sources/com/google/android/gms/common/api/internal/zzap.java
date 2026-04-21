package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
final class zzap extends zzat {
    private final /* synthetic */ zzaj zzhv;
    private final ArrayList<Api.Client> zzib;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzap(zzaj zzajVar, ArrayList<Api.Client> arrayList) {
        super(zzajVar, null);
        this.zzhv = zzajVar;
        this.zzib = arrayList;
    }

    @Override // com.google.android.gms.common.api.internal.zzat
    @WorkerThread
    public final void zzaq() {
        this.zzhv.zzhf.zzfq.zzim = this.zzhv.zzaw();
        ArrayList<Api.Client> arrayList = this.zzib;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Api.Client client = arrayList.get(i);
            i++;
            client.getRemoteService(this.zzhv.zzhr, this.zzhv.zzhf.zzfq.zzim);
        }
    }
}
