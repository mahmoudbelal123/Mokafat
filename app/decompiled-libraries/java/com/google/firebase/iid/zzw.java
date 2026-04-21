package com.google.firebase.iid;

/* JADX INFO: loaded from: classes.dex */
final class zzw implements InstanceIdResult {
    private final String zzbm;
    private final String zzbn;

    zzw(String str, String str2) {
        this.zzbm = str;
        this.zzbn = str2;
    }

    @Override // com.google.firebase.iid.InstanceIdResult
    public final String getId() {
        return this.zzbm;
    }

    @Override // com.google.firebase.iid.InstanceIdResult
    public final String getToken() {
        return this.zzbn;
    }
}
