package com.google.android.gms.common.util;

import android.os.ParcelFileDescriptor;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Arrays;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class IOUtils {

    private static final class zza extends ByteArrayOutputStream {
        private zza() {
        }

        final void zza(byte[] bArr, int i) {
            System.arraycopy(this.buf, 0, bArr, i, this.count);
        }
    }

    private static final class zzb {
        private final File file;

        private zzb(File file) {
            this.file = (File) Preconditions.checkNotNull(file);
        }

        public final byte[] zzdd() throws Throwable {
            FileInputStream fileInputStream;
            Throwable th;
            try {
                fileInputStream = new FileInputStream(this.file);
                try {
                    byte[] bArrZza = IOUtils.zza(fileInputStream, fileInputStream.getChannel().size());
                    IOUtils.closeQuietly(fileInputStream);
                    return bArrZza;
                } catch (Throwable th2) {
                    th = th2;
                    IOUtils.closeQuietly(fileInputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                fileInputStream = null;
                th = th3;
            }
        }
    }

    private IOUtils() {
    }

    public static void close(@Nullable Closeable closeable, String str, String str2) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.d(str, str2, e);
            }
        }
    }

    public static void closeQuietly(@Nullable ParcelFileDescriptor parcelFileDescriptor) {
        if (parcelFileDescriptor != null) {
            try {
                parcelFileDescriptor.close();
            } catch (IOException e) {
            }
        }
    }

    public static void closeQuietly(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public static void closeQuietly(@Nullable ServerSocket serverSocket) {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
            }
        }
    }

    public static void closeQuietly(@Nullable Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    public static long copyStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        return copyStream(inputStream, outputStream, false);
    }

    public static long copyStream(InputStream inputStream, OutputStream outputStream, boolean z) throws IOException {
        return copyStream(inputStream, outputStream, z, 1024);
    }

    public static long copyStream(InputStream inputStream, OutputStream outputStream, boolean z, int i) throws IOException {
        byte[] bArr = new byte[i];
        long j = 0;
        while (true) {
            try {
                int i2 = inputStream.read(bArr, 0, i);
                if (i2 == -1) {
                    break;
                }
                long j2 = j + ((long) i2);
                outputStream.write(bArr, 0, i2);
                j = j2;
            } finally {
                if (z) {
                    closeQuietly(inputStream);
                    closeQuietly(outputStream);
                }
            }
        }
        return j;
    }

    public static boolean isGzipByteBuffer(byte[] bArr) {
        if (bArr.length > 1) {
            if ((((bArr[1] & 255) << 8) | (bArr[0] & 255)) == 35615) {
                return true;
            }
        }
        return false;
    }

    @WorkerThread
    public static void lockAndTruncateFile(File file) throws Throwable {
        RandomAccessFile randomAccessFile;
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        FileLock fileLock = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                FileChannel channel = randomAccessFile.getChannel();
                FileLock fileLockLock = channel.lock();
                try {
                    channel.truncate(0L);
                    if (fileLockLock != null && fileLockLock.isValid()) {
                        try {
                            fileLockLock.release();
                        } catch (IOException e) {
                        }
                    }
                    closeQuietly(randomAccessFile);
                } catch (Throwable th) {
                    th = th;
                    fileLock = fileLockLock;
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException e2) {
                        }
                    }
                    if (randomAccessFile == null) {
                        throw th;
                    }
                    closeQuietly(randomAccessFile);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            randomAccessFile = null;
        }
    }

    public static byte[] readInputStreamFully(InputStream inputStream) throws IOException {
        return readInputStreamFully(inputStream, true);
    }

    public static byte[] readInputStreamFully(InputStream inputStream, boolean z) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copyStream(inputStream, byteArrayOutputStream, z);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] toByteArray(File file) throws IOException {
        return new zzb(file).zzdd();
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        zza(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private static long zza(InputStream inputStream, OutputStream outputStream) throws IOException {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(outputStream);
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return j;
            }
            outputStream.write(bArr, 0, i);
            j += (long) i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] zza(InputStream inputStream, long j) throws IOException {
        if (j > 2147483647L) {
            StringBuilder sb = new StringBuilder(68);
            sb.append("file is too large to fit in a byte array: ");
            sb.append(j);
            sb.append(" bytes");
            throw new OutOfMemoryError(sb.toString());
        }
        if (j == 0) {
            return toByteArray(inputStream);
        }
        int i = (int) j;
        byte[] bArr = new byte[i];
        int i2 = i;
        while (i2 > 0) {
            int i3 = i - i2;
            int i4 = inputStream.read(bArr, i3, i2);
            if (i4 == -1) {
                return Arrays.copyOf(bArr, i3);
            }
            i2 -= i4;
        }
        int i5 = inputStream.read();
        if (i5 == -1) {
            return bArr;
        }
        zza zzaVar = new zza();
        zzaVar.write(i5);
        zza(inputStream, zzaVar);
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length + zzaVar.size());
        zzaVar.zza(bArrCopyOf, bArr.length);
        return bArrCopyOf;
    }
}
