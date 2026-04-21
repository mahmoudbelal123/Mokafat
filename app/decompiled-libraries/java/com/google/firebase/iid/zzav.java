package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.GuardedBy;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class zzav {
    private final SharedPreferences zzcz;
    private final zzx zzda;

    @GuardedBy("this")
    private final Map<String, zzy> zzdb;
    private final Context zzv;

    public zzav(Context context) {
        this(context, new zzx());
    }

    private zzav(Context context, zzx zzxVar) {
        this.zzdb = new ArrayMap();
        this.zzv = context;
        this.zzcz = context.getSharedPreferences("com.google.android.gms.appid", 0);
        this.zzda = zzxVar;
        File file = new File(ContextCompat.getNoBackupFilesDir(this.zzv), "com.google.android.gms.appid-no-backup");
        if (!file.exists()) {
            try {
                if (file.createNewFile() && !isEmpty()) {
                    Log.i("FirebaseInstanceId", "App restored, clearing state");
                    zzak();
                    FirebaseInstanceId.getInstance().zzl();
                }
            } catch (IOException e) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String strValueOf = String.valueOf(e.getMessage());
                    Log.d("FirebaseInstanceId", strValueOf.length() != 0 ? "Error creating file in no backup dir: ".concat(strValueOf) : new String("Error creating file in no backup dir: "));
                }
            }
        }
    }

    public final synchronized String zzaj() {
        return this.zzcz.getString("topic_operaion_queue", "");
    }

    public final synchronized void zzf(String str) {
        this.zzcz.edit().putString("topic_operaion_queue", str).apply();
    }

    private final synchronized boolean isEmpty() {
        return this.zzcz.getAll().isEmpty();
    }

    private static String zza(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(4 + String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append(str);
        sb.append("|T|");
        sb.append(str2);
        sb.append("|");
        sb.append(str3);
        return sb.toString();
    }

    static String zzd(String str, String str2) {
        StringBuilder sb = new StringBuilder(3 + String.valueOf(str).length() + String.valueOf(str2).length());
        sb.append(str);
        sb.append("|S|");
        sb.append(str2);
        return sb.toString();
    }

    public final synchronized void zzak() {
        this.zzdb.clear();
        zzx.zza(this.zzv);
        this.zzcz.edit().clear().commit();
    }

    public final synchronized zzaw zzb(String str, String str2, String str3) {
        return zzaw.zzi(this.zzcz.getString(zza(str, str2, str3), null));
    }

    public final synchronized void zza(String str, String str2, String str3, String str4, String str5) {
        String strZza = zzaw.zza(str4, str5, System.currentTimeMillis());
        if (strZza == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = this.zzcz.edit();
        editorEdit.putString(zza(str, str2, str3), strZza);
        editorEdit.commit();
    }

    public final synchronized void zzc(String str, String str2, String str3) {
        String strZza = zza(str, str2, str3);
        SharedPreferences.Editor editorEdit = this.zzcz.edit();
        editorEdit.remove(strZza);
        editorEdit.commit();
    }

    public final synchronized zzy zzg(String str) {
        zzy zzyVarZzc;
        zzy zzyVar = this.zzdb.get(str);
        if (zzyVar != null) {
            return zzyVar;
        }
        try {
            zzyVarZzc = this.zzda.zzb(this.zzv, str);
        } catch (zzz e) {
            Log.w("FirebaseInstanceId", "Stored data is corrupt, generating new identity");
            FirebaseInstanceId.getInstance().zzl();
            zzyVarZzc = this.zzda.zzc(this.zzv, str);
        }
        this.zzdb.put(str, zzyVarZzc);
        return zzyVarZzc;
    }

    public final synchronized void zzh(String str) {
        String strConcat = String.valueOf(str).concat("|T|");
        SharedPreferences.Editor editorEdit = this.zzcz.edit();
        for (String str2 : this.zzcz.getAll().keySet()) {
            if (str2.startsWith(strConcat)) {
                editorEdit.remove(str2);
            }
        }
        editorEdit.commit();
    }
}
