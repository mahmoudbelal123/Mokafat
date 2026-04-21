package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatUtil {
    private static final String CACHE_FILE_PREFIX = ".font";
    private static final String TAG = "TypefaceCompatUtil";

    private TypefaceCompatUtil() {
    }

    @Nullable
    public static File getTempFile(Context context) {
        String prefix = CACHE_FILE_PREFIX + Process.myPid() + "-" + Process.myTid() + "-";
        for (int i = 0; i < 100; i++) {
            File file = new File(context.getCacheDir(), prefix + i);
            if (file.createNewFile()) {
                return file;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[Catch: IOException -> 0x0037, SYNTHETIC, TRY_LEAVE, TryCatch #1 {IOException -> 0x0037, blocks: (B:3:0x0001, B:6:0x0019, B:16:0x002a, B:20:0x0033, B:19:0x002f, B:21:0x0036), top: B:26:0x0001, inners: #2 }] */
    @android.support.annotation.RequiresApi(19)
    @android.support.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.nio.ByteBuffer mmap(java.io.File r10) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.io.IOException -> L37
            r1.<init>(r10)     // Catch: java.io.IOException -> L37
            java.nio.channels.FileChannel r2 = r1.getChannel()     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L20
            long r7 = r2.size()     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L20
            java.nio.channels.FileChannel$MapMode r4 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L20
            r5 = 0
            r3 = r2
            java.nio.MappedByteBuffer r3 = r3.map(r4, r5, r7)     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L20
            if (r1 == 0) goto L1c
            r1.close()     // Catch: java.io.IOException -> L37
        L1c:
            return r3
        L1d:
            r2 = move-exception
            r3 = r0
            goto L26
        L20:
            r2 = move-exception
            throw r2     // Catch: java.lang.Throwable -> L22
        L22:
            r3 = move-exception
            r9 = r3
            r3 = r2
            r2 = r9
        L26:
            if (r1 == 0) goto L36
            if (r3 == 0) goto L33
            r1.close()     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L37
            goto L36
        L2e:
            r4 = move-exception
            r3.addSuppressed(r4)     // Catch: java.io.IOException -> L37
            goto L36
        L33:
            r1.close()     // Catch: java.io.IOException -> L37
        L36:
            throw r2     // Catch: java.io.IOException -> L37
        L37:
            r1 = move-exception
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.mmap(java.io.File):java.nio.ByteBuffer");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:57:? A[Catch: all -> 0x0053, Throwable -> 0x0056, SYNTHETIC, TRY_LEAVE, TryCatch #2 {all -> 0x0053, blocks: (B:8:0x0014, B:11:0x0030, B:23:0x0046, B:27:0x004f, B:26:0x004b, B:28:0x0052), top: B:47:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:? A[Catch: IOException -> 0x006d, SYNTHETIC, TRY_LEAVE, TryCatch #6 {IOException -> 0x006d, blocks: (B:3:0x0005, B:6:0x0010, B:13:0x0035, B:37:0x0060, B:41:0x0069, B:40:0x0065, B:42:0x006c), top: B:52:0x0005, inners: #5 }] */
    @android.support.annotation.RequiresApi(19)
    @android.support.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.nio.ByteBuffer mmap(android.content.Context r12, android.os.CancellationSignal r13, android.net.Uri r14) throws java.lang.Throwable {
        /*
            android.content.ContentResolver r0 = r12.getContentResolver()
            r1 = 0
            java.lang.String r2 = "r"
            android.os.ParcelFileDescriptor r2 = r0.openFileDescriptor(r14, r2, r13)     // Catch: java.io.IOException -> L6d
            if (r2 != 0) goto L14
        Le:
            if (r2 == 0) goto L13
            r2.close()     // Catch: java.io.IOException -> L6d
        L13:
            return r1
        L14:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L56
            java.io.FileDescriptor r4 = r2.getFileDescriptor()     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L56
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L56
            java.nio.channels.FileChannel r4 = r3.getChannel()     // Catch: java.lang.Throwable -> L39 java.lang.Throwable -> L3c
            long r9 = r4.size()     // Catch: java.lang.Throwable -> L39 java.lang.Throwable -> L3c
            java.nio.channels.FileChannel$MapMode r6 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: java.lang.Throwable -> L39 java.lang.Throwable -> L3c
            r7 = 0
            r5 = r4
            java.nio.MappedByteBuffer r5 = r5.map(r6, r7, r9)     // Catch: java.lang.Throwable -> L39 java.lang.Throwable -> L3c
            if (r3 == 0) goto L33
            r3.close()     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L56
        L33:
            if (r2 == 0) goto L38
            r2.close()     // Catch: java.io.IOException -> L6d
        L38:
            return r5
        L39:
            r4 = move-exception
            r5 = r1
            goto L42
        L3c:
            r4 = move-exception
            throw r4     // Catch: java.lang.Throwable -> L3e
        L3e:
            r5 = move-exception
            r11 = r5
            r5 = r4
            r4 = r11
        L42:
            if (r3 == 0) goto L52
            if (r5 == 0) goto L4f
            r3.close()     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L53
            goto L52
        L4a:
            r6 = move-exception
            r5.addSuppressed(r6)     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L56
            goto L52
        L4f:
            r3.close()     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L56
        L52:
            throw r4     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L56
        L53:
            r3 = move-exception
            r4 = r1
            goto L5c
        L56:
            r3 = move-exception
            throw r3     // Catch: java.lang.Throwable -> L58
        L58:
            r4 = move-exception
            r11 = r4
            r4 = r3
            r3 = r11
        L5c:
            if (r2 == 0) goto L6c
            if (r4 == 0) goto L69
            r2.close()     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L6d
            goto L6c
        L64:
            r5 = move-exception
            r4.addSuppressed(r5)     // Catch: java.io.IOException -> L6d
            goto L6c
        L69:
            r2.close()     // Catch: java.io.IOException -> L6d
        L6c:
            throw r3     // Catch: java.io.IOException -> L6d
        L6d:
            r2 = move-exception
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.mmap(android.content.Context, android.os.CancellationSignal, android.net.Uri):java.nio.ByteBuffer");
    }

    @RequiresApi(19)
    @Nullable
    public static ByteBuffer copyToDirectBuffer(Context context, Resources res, int id) {
        File tmpFile = getTempFile(context);
        ByteBuffer byteBufferMmap = null;
        if (tmpFile == null) {
            return null;
        }
        try {
            if (copyToFile(tmpFile, res, id)) {
                byteBufferMmap = mmap(tmpFile);
            }
            return byteBufferMmap;
        } finally {
            tmpFile.delete();
        }
    }

    public static boolean copyToFile(File file, InputStream is) {
        FileOutputStream os = null;
        boolean z = false;
        try {
            try {
                os = new FileOutputStream(file, false);
                byte[] buffer = new byte[1024];
                while (true) {
                    int readLen = is.read(buffer);
                    if (readLen == -1) {
                        break;
                    }
                    os.write(buffer, 0, readLen);
                }
                z = true;
            } catch (IOException e) {
                Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
            }
            return z;
        } finally {
            closeQuietly(os);
        }
    }

    public static boolean copyToFile(File file, Resources res, int id) {
        InputStream is = null;
        try {
            is = res.openRawResource(id);
            return copyToFile(file, is);
        } finally {
            closeQuietly(is);
        }
    }

    public static void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
            }
        }
    }
}
