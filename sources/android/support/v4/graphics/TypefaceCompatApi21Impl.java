package android.support.v4.graphics;

import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(21)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static final String TAG = "TypefaceCompatApi21Impl";

    TypefaceCompatApi21Impl() {
    }

    private File getFile(ParcelFileDescriptor fd) {
        try {
            String path = Os.readlink("/proc/self/fd/" + fd.getFd());
            if (!OsConstants.S_ISREG(Os.stat(path).st_mode)) {
                return null;
            }
            return new File(path);
        } catch (ErrnoException e) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[Catch: all -> 0x0063, Throwable -> 0x0066, SYNTHETIC, TRY_LEAVE, TryCatch #2 {all -> 0x0063, blocks: (B:7:0x001a, B:9:0x0020, B:12:0x0027, B:16:0x0031, B:19:0x0040, B:31:0x0056, B:35:0x005f, B:34:0x005b, B:36:0x0062), top: B:55:0x001a }] */
    /* JADX WARN: Removed duplicated region for block: B:68:? A[Catch: IOException -> 0x007d, SYNTHETIC, TRY_LEAVE, TryCatch #6 {IOException -> 0x007d, blocks: (B:6:0x000f, B:14:0x002d, B:21:0x0045, B:45:0x0070, B:49:0x0079, B:48:0x0075, B:50:0x007c), top: B:60:0x000f, inners: #5 }] */
    @Override // android.support.v4.graphics.TypefaceCompatBaseImpl, android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r11, android.os.CancellationSignal r12, @android.support.annotation.NonNull android.support.v4.provider.FontsContractCompat.FontInfo[] r13, int r14) throws java.lang.Throwable {
        /*
            r10 = this;
            r0 = 0
            r1 = 1
            int r2 = r13.length
            if (r2 >= r1) goto L6
            return r0
        L6:
            android.support.v4.provider.FontsContractCompat$FontInfo r1 = r10.findBestInfo(r13, r14)
            android.content.ContentResolver r2 = r11.getContentResolver()
            android.net.Uri r3 = r1.getUri()     // Catch: java.io.IOException -> L7d
            java.lang.String r4 = "r"
            android.os.ParcelFileDescriptor r3 = r2.openFileDescriptor(r3, r4, r12)     // Catch: java.io.IOException -> L7d
            java.io.File r4 = r10.getFile(r3)     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L66
            if (r4 == 0) goto L31
            boolean r5 = r4.canRead()     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L66
            if (r5 != 0) goto L27
            goto L31
        L27:
            android.graphics.Typeface r5 = android.graphics.Typeface.createFromFile(r4)     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L66
            if (r3 == 0) goto L30
            r3.close()     // Catch: java.io.IOException -> L7d
        L30:
            return r5
        L31:
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L66
            java.io.FileDescriptor r6 = r3.getFileDescriptor()     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L66
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L66
            android.graphics.Typeface r6 = super.createFromInputStream(r11, r5)     // Catch: java.lang.Throwable -> L49 java.lang.Throwable -> L4c
            if (r5 == 0) goto L43
            r5.close()     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L66
        L43:
            if (r3 == 0) goto L48
            r3.close()     // Catch: java.io.IOException -> L7d
        L48:
            return r6
        L49:
            r6 = move-exception
            r7 = r0
            goto L52
        L4c:
            r6 = move-exception
            throw r6     // Catch: java.lang.Throwable -> L4e
        L4e:
            r7 = move-exception
            r9 = r7
            r7 = r6
            r6 = r9
        L52:
            if (r5 == 0) goto L62
            if (r7 == 0) goto L5f
            r5.close()     // Catch: java.lang.Throwable -> L5a java.lang.Throwable -> L63
            goto L62
        L5a:
            r8 = move-exception
            r7.addSuppressed(r8)     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L66
            goto L62
        L5f:
            r5.close()     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L66
        L62:
            throw r6     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L66
        L63:
            r4 = move-exception
            r5 = r0
            goto L6c
        L66:
            r4 = move-exception
            throw r4     // Catch: java.lang.Throwable -> L68
        L68:
            r5 = move-exception
            r9 = r5
            r5 = r4
            r4 = r9
        L6c:
            if (r3 == 0) goto L7c
            if (r5 == 0) goto L79
            r3.close()     // Catch: java.lang.Throwable -> L74 java.io.IOException -> L7d
            goto L7c
        L74:
            r6 = move-exception
            r5.addSuppressed(r6)     // Catch: java.io.IOException -> L7d
            goto L7c
        L79:
            r3.close()     // Catch: java.io.IOException -> L7d
        L7c:
            throw r4     // Catch: java.io.IOException -> L7d
        L7d:
            r3 = move-exception
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi21Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }
}
