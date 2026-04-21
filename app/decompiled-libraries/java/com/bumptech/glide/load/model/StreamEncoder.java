package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class StreamEncoder implements Encoder<InputStream> {
    private static final String TAG = "StreamEncoder";
    private final ArrayPool byteArrayPool;

    public StreamEncoder(ArrayPool byteArrayPool) {
        this.byteArrayPool = byteArrayPool;
    }

    @Override // com.bumptech.glide.load.Encoder
    public boolean encode(@NonNull InputStream data, @NonNull File file, @NonNull Options options) {
        byte[] buffer = (byte[]) this.byteArrayPool.get(65536, byte[].class);
        boolean success = false;
        OutputStream os = null;
        try {
            try {
                os = new FileOutputStream(file);
            } catch (IOException e) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Failed to encode data onto the OutputStream", e);
                }
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e2) {
                    }
                }
            }
            while (true) {
                int read = data.read(buffer);
                if (read == -1) {
                    break;
                }
                os.write(buffer, 0, read);
                this.byteArrayPool.put(buffer);
                return success;
            }
            os.close();
            success = true;
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e3) {
                }
            }
            this.byteArrayPool.put(buffer);
            return success;
        } catch (Throwable th) {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e4) {
                }
            }
            this.byteArrayPool.put(buffer);
            throw th;
        }
    }
}
