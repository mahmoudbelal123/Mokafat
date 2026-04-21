package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.bumptech.glide.load.Key;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;

/* JADX INFO: loaded from: classes.dex */
final class zzx {
    zzx() {
    }

    @WorkerThread
    final zzy zzb(Context context, String str) throws Throwable {
        zzy zzyVarZzd = zzd(context, str);
        return zzyVarZzd != null ? zzyVarZzd : zzc(context, str);
    }

    @WorkerThread
    final zzy zzc(Context context, String str) throws Throwable {
        zzy zzyVar = new zzy(zza.zzb(), System.currentTimeMillis());
        zzy zzyVarZza = zza(context, str, zzyVar, true);
        if (zzyVarZza == null || zzyVarZza.equals(zzyVar)) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Generated new key");
            }
            zza(context, str, zzyVar);
            return zzyVar;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Loaded key after generating new one, using loaded one");
        }
        return zzyVarZza;
    }

    static void zza(Context context) {
        for (File file : zzb(context).listFiles()) {
            if (file.getName().startsWith("com.google.InstanceId")) {
                file.delete();
            }
        }
    }

    @Nullable
    private final zzy zzd(Context context, String str) throws Throwable {
        try {
            zzy zzyVarZze = zze(context, str);
            if (zzyVarZze != null) {
                zza(context, str, zzyVarZze);
                return zzyVarZze;
            }
            e = null;
        } catch (zzz e) {
            e = e;
        }
        try {
            zzy zzyVarZza = zza(context.getSharedPreferences("com.google.android.gms.appid", 0), str);
            if (zzyVarZza != null) {
                zza(context, str, zzyVarZza, false);
                return zzyVarZza;
            }
        } catch (zzz e2) {
            e = e2;
        }
        if (e == null) {
            return null;
        }
        throw e;
    }

    private static KeyPair zzc(String str, String str2) throws zzz {
        try {
            byte[] bArrDecode = Base64.decode(str, 8);
            byte[] bArrDecode2 = Base64.decode(str2, 8);
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                return new KeyPair(keyFactory.generatePublic(new X509EncodedKeySpec(bArrDecode)), keyFactory.generatePrivate(new PKCS8EncodedKeySpec(bArrDecode2)));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                String strValueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(19 + String.valueOf(strValueOf).length());
                sb.append("Invalid key stored ");
                sb.append(strValueOf);
                Log.w("FirebaseInstanceId", sb.toString());
                throw new zzz(e);
            }
        } catch (IllegalArgumentException e2) {
            throw new zzz(e2);
        }
    }

    @Nullable
    private final zzy zze(Context context, String str) throws zzz {
        File fileZzf = zzf(context, str);
        if (!fileZzf.exists()) {
            return null;
        }
        try {
            return zza(fileZzf);
        } catch (zzz | IOException e) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String strValueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(40 + String.valueOf(strValueOf).length());
                sb.append("Failed to read key from file, retrying: ");
                sb.append(strValueOf);
                Log.d("FirebaseInstanceId", sb.toString());
            }
            try {
                return zza(fileZzf);
            } catch (IOException e2) {
                String strValueOf2 = String.valueOf(e2);
                StringBuilder sb2 = new StringBuilder(45 + String.valueOf(strValueOf2).length());
                sb2.append("IID file exists, but failed to read from it: ");
                sb2.append(strValueOf2);
                Log.w("FirebaseInstanceId", sb2.toString());
                throw new zzz(e2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b4 A[Catch: all -> 0x00b8, Throwable -> 0x00bb, TRY_ENTER, TryCatch #8 {Throwable -> 0x00bb, all -> 0x00b8, blocks: (B:7:0x0043, B:15:0x0060, B:24:0x00a2, B:34:0x00b4, B:35:0x00b7), top: B:54:0x0043 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:? A[Catch: all -> 0x00b8, Throwable -> 0x00bb, SYNTHETIC, TRY_LEAVE, TryCatch #8 {Throwable -> 0x00bb, all -> 0x00b8, blocks: (B:7:0x0043, B:15:0x0060, B:24:0x00a2, B:34:0x00b4, B:35:0x00b7), top: B:54:0x0043 }] */
    @android.support.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.google.firebase.iid.zzy zza(android.content.Context r9, java.lang.String r10, com.google.firebase.iid.zzy r11, boolean r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzx.zza(android.content.Context, java.lang.String, com.google.firebase.iid.zzy, boolean):com.google.firebase.iid.zzy");
    }

    private static File zzb(Context context) {
        File noBackupFilesDir = ContextCompat.getNoBackupFilesDir(context);
        if (noBackupFilesDir != null && noBackupFilesDir.isDirectory()) {
            return noBackupFilesDir;
        }
        Log.w("FirebaseInstanceId", "noBackupFilesDir doesn't exist, using regular files directory instead");
        return context.getFilesDir();
    }

    private static File zzf(Context context, String str) {
        String string;
        if (TextUtils.isEmpty(str)) {
            string = "com.google.InstanceId.properties";
        } else {
            try {
                String strEncodeToString = Base64.encodeToString(str.getBytes(Key.STRING_CHARSET_NAME), 11);
                StringBuilder sb = new StringBuilder(33 + String.valueOf(strEncodeToString).length());
                sb.append("com.google.InstanceId_");
                sb.append(strEncodeToString);
                sb.append(".properties");
                string = sb.toString();
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
        return new File(zzb(context), string);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002f A[Catch: all -> 0x0033, Throwable -> 0x0035, TRY_ENTER, TryCatch #3 {, blocks: (B:3:0x0006, B:7:0x001d, B:17:0x002f, B:18:0x0032), top: B:25:0x0006, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[Catch: all -> 0x0033, Throwable -> 0x0035, SYNTHETIC, TRY_LEAVE, TryCatch #3 {, blocks: (B:3:0x0006, B:7:0x001d, B:17:0x002f, B:18:0x0032), top: B:25:0x0006, outer: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.google.firebase.iid.zzy zza(java.io.File r10) throws com.google.firebase.iid.zzz, java.io.IOException {
        /*
            r9 = this;
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r10)
            r10 = 0
            java.nio.channels.FileChannel r7 = r0.getChannel()     // Catch: java.lang.Throwable -> L33 java.lang.Throwable -> L35
            r2 = 0
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r6 = 1
            r1 = r7
            r1.lock(r2, r4, r6)     // Catch: java.lang.Throwable -> L24 java.lang.Throwable -> L27
            com.google.firebase.iid.zzy r1 = zza(r7)     // Catch: java.lang.Throwable -> L24 java.lang.Throwable -> L27
            if (r7 == 0) goto L20
            zza(r10, r7)     // Catch: java.lang.Throwable -> L33 java.lang.Throwable -> L35
        L20:
            zza(r10, r0)
            return r1
        L24:
            r1 = move-exception
            r2 = r10
            goto L2d
        L27:
            r1 = move-exception
            throw r1     // Catch: java.lang.Throwable -> L29
        L29:
            r2 = move-exception
            r8 = r2
            r2 = r1
            r1 = r8
        L2d:
            if (r7 == 0) goto L32
            zza(r2, r7)     // Catch: java.lang.Throwable -> L33 java.lang.Throwable -> L35
        L32:
            throw r1     // Catch: java.lang.Throwable -> L33 java.lang.Throwable -> L35
        L33:
            r1 = move-exception
            goto L37
        L35:
            r10 = move-exception
            throw r10     // Catch: java.lang.Throwable -> L33
        L37:
            zza(r10, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzx.zza(java.io.File):com.google.firebase.iid.zzy");
    }

    private static zzy zza(FileChannel fileChannel) throws zzz, IOException {
        Properties properties = new Properties();
        properties.load(Channels.newInputStream(fileChannel));
        String property = properties.getProperty("pub");
        String property2 = properties.getProperty("pri");
        if (property == null || property2 == null) {
            throw new zzz("Invalid properties file");
        }
        try {
            return new zzy(zzc(property, property2), Long.parseLong(properties.getProperty("cre")));
        } catch (NumberFormatException e) {
            throw new zzz(e);
        }
    }

    @Nullable
    private static zzy zza(SharedPreferences sharedPreferences, String str) throws zzz {
        String string = sharedPreferences.getString(zzav.zzd(str, "|P|"), null);
        String string2 = sharedPreferences.getString(zzav.zzd(str, "|K|"), null);
        if (string == null || string2 == null) {
            return null;
        }
        return new zzy(zzc(string, string2), zzb(sharedPreferences, str));
    }

    private final void zza(Context context, String str, zzy zzyVar) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.appid", 0);
        try {
            if (zzyVar.equals(zza(sharedPreferences, str))) {
                return;
            }
        } catch (zzz e) {
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Writing key to shared preferences");
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString(zzav.zzd(str, "|P|"), zzyVar.zzu());
        editorEdit.putString(zzav.zzd(str, "|K|"), zzyVar.zzv());
        editorEdit.putString(zzav.zzd(str, "cre"), String.valueOf(zzyVar.zzbp));
        editorEdit.commit();
    }

    private static long zzb(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(zzav.zzd(str, "cre"), null);
        if (string != null) {
            try {
                return Long.parseLong(string);
            } catch (NumberFormatException e) {
                return 0L;
            }
        }
        return 0L;
    }

    private static /* synthetic */ void zza(Throwable th, FileChannel fileChannel) {
        if (th == null) {
            fileChannel.close();
            return;
        }
        try {
            fileChannel.close();
        } catch (Throwable th2) {
            com.google.android.gms.internal.firebase_messaging.zza.zza(th, th2);
        }
    }

    private static /* synthetic */ void zza(Throwable th, RandomAccessFile randomAccessFile) throws IOException {
        if (th == null) {
            randomAccessFile.close();
            return;
        }
        try {
            randomAccessFile.close();
        } catch (Throwable th2) {
            com.google.android.gms.internal.firebase_messaging.zza.zza(th, th2);
        }
    }

    private static /* synthetic */ void zza(Throwable th, FileInputStream fileInputStream) throws IOException {
        if (th == null) {
            fileInputStream.close();
            return;
        }
        try {
            fileInputStream.close();
        } catch (Throwable th2) {
            com.google.android.gms.internal.firebase_messaging.zza.zza(th, th2);
        }
    }
}
