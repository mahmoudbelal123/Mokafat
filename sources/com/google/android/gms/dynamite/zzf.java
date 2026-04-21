package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* JADX INFO: loaded from: classes.dex */
final class zzf implements DynamiteModule.VersionPolicy {
    zzf() {
    }

    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context context, String str, DynamiteModule.VersionPolicy.IVersions iVersions) throws DynamiteModule.LoadingException {
        int i;
        DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        selectionResult.localVersion = iVersions.getLocalVersion(context, str);
        selectionResult.remoteVersion = iVersions.getRemoteVersion(context, str, true);
        if (selectionResult.localVersion == 0 && selectionResult.remoteVersion == 0) {
            i = 0;
        } else {
            if (selectionResult.remoteVersion >= selectionResult.localVersion) {
                selectionResult.selection = 1;
                return selectionResult;
            }
            i = -1;
        }
        selectionResult.selection = i;
        return selectionResult;
    }
}
