package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public class VideoDecoder<T> implements ResourceDecoder<T, Bitmap> {
    public static final long DEFAULT_FRAME = -1;

    @VisibleForTesting
    static final int DEFAULT_FRAME_OPTION = 2;
    private static final String TAG = "VideoDecoder";
    private final BitmapPool bitmapPool;
    private final MediaMetadataRetrieverFactory factory;
    private final MediaMetadataRetrieverInitializer<T> initializer;
    public static final Option<Long> TARGET_FRAME = Option.disk("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.TargetFrame", -1L, new Option.CacheKeyUpdater<Long>() { // from class: com.bumptech.glide.load.resource.bitmap.VideoDecoder.1
        private final ByteBuffer buffer = ByteBuffer.allocate(8);

        @Override // com.bumptech.glide.load.Option.CacheKeyUpdater
        public void update(@NonNull byte[] keyBytes, @NonNull Long value, @NonNull MessageDigest messageDigest) {
            messageDigest.update(keyBytes);
            synchronized (this.buffer) {
                this.buffer.position(0);
                messageDigest.update(this.buffer.putLong(value.longValue()).array());
            }
        }
    });
    public static final Option<Integer> FRAME_OPTION = Option.disk("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.FrameOption", 2, new Option.CacheKeyUpdater<Integer>() { // from class: com.bumptech.glide.load.resource.bitmap.VideoDecoder.2
        private final ByteBuffer buffer = ByteBuffer.allocate(4);

        @Override // com.bumptech.glide.load.Option.CacheKeyUpdater
        public void update(@NonNull byte[] keyBytes, @NonNull Integer value, @NonNull MessageDigest messageDigest) {
            if (value == null) {
                return;
            }
            messageDigest.update(keyBytes);
            synchronized (this.buffer) {
                this.buffer.position(0);
                messageDigest.update(this.buffer.putInt(value.intValue()).array());
            }
        }
    });
    private static final MediaMetadataRetrieverFactory DEFAULT_FACTORY = new MediaMetadataRetrieverFactory();

    @VisibleForTesting
    interface MediaMetadataRetrieverInitializer<T> {
        void initialize(MediaMetadataRetriever mediaMetadataRetriever, T t);
    }

    public static ResourceDecoder<AssetFileDescriptor, Bitmap> asset(BitmapPool bitmapPool) {
        return new VideoDecoder(bitmapPool, new AssetFileDescriptorInitializer());
    }

    public static ResourceDecoder<ParcelFileDescriptor, Bitmap> parcel(BitmapPool bitmapPool) {
        return new VideoDecoder(bitmapPool, new ParcelFileDescriptorInitializer());
    }

    VideoDecoder(BitmapPool bitmapPool, MediaMetadataRetrieverInitializer<T> initializer) {
        this(bitmapPool, initializer, DEFAULT_FACTORY);
    }

    @VisibleForTesting
    VideoDecoder(BitmapPool bitmapPool, MediaMetadataRetrieverInitializer<T> initializer, MediaMetadataRetrieverFactory factory) {
        this.bitmapPool = bitmapPool;
        this.initializer = initializer;
        this.factory = factory;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public boolean handles(@NonNull T data, @NonNull Options options) {
        return true;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(@NonNull T resource, int outWidth, int outHeight, @NonNull Options options) throws Throwable {
        long frameTimeMicros = ((Long) options.get(TARGET_FRAME)).longValue();
        if (frameTimeMicros < 0 && frameTimeMicros != -1) {
            throw new IllegalArgumentException("Requested frame must be non-negative, or DEFAULT_FRAME, given: " + frameTimeMicros);
        }
        Integer frameOption = (Integer) options.get(FRAME_OPTION);
        if (frameOption == null) {
            frameOption = 2;
        }
        DownsampleStrategy downsampleStrategy = (DownsampleStrategy) options.get(DownsampleStrategy.OPTION);
        if (downsampleStrategy == null) {
            downsampleStrategy = DownsampleStrategy.DEFAULT;
        }
        DownsampleStrategy downsampleStrategy2 = downsampleStrategy;
        MediaMetadataRetriever mediaMetadataRetriever = this.factory.build();
        try {
            try {
                try {
                    this.initializer.initialize(mediaMetadataRetriever, resource);
                    Bitmap result = decodeFrame(mediaMetadataRetriever, frameTimeMicros, frameOption.intValue(), outWidth, outHeight, downsampleStrategy2);
                    mediaMetadataRetriever.release();
                    return BitmapResource.obtain(result, this.bitmapPool);
                } catch (RuntimeException e) {
                    e = e;
                    throw new IOException(e);
                }
            } catch (Throwable th) {
                th = th;
                Throwable th2 = th;
                mediaMetadataRetriever.release();
                throw th2;
            }
        } catch (RuntimeException e2) {
            e = e2;
        } catch (Throwable th3) {
            th = th3;
            Throwable th22 = th;
            mediaMetadataRetriever.release();
            throw th22;
        }
    }

    @Nullable
    private static Bitmap decodeFrame(MediaMetadataRetriever mediaMetadataRetriever, long frameTimeMicros, int frameOption, int outWidth, int outHeight, DownsampleStrategy strategy) {
        Bitmap result = null;
        if (Build.VERSION.SDK_INT >= 27 && outWidth != Integer.MIN_VALUE && outHeight != Integer.MIN_VALUE && strategy != DownsampleStrategy.NONE) {
            result = decodeScaledFrame(mediaMetadataRetriever, frameTimeMicros, frameOption, outWidth, outHeight, strategy);
        }
        if (result == null) {
            return decodeOriginalFrame(mediaMetadataRetriever, frameTimeMicros, frameOption);
        }
        return result;
    }

    @TargetApi(27)
    private static Bitmap decodeScaledFrame(MediaMetadataRetriever mediaMetadataRetriever, long frameTimeMicros, int frameOption, int outWidth, int outHeight, DownsampleStrategy strategy) {
        try {
            int originalWidth = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
            int originalHeight = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
            int orientation = Integer.parseInt(mediaMetadataRetriever.extractMetadata(24));
            if (orientation == 90 || orientation == 270) {
                originalWidth = originalHeight;
                originalHeight = originalWidth;
            }
            int originalWidth2 = originalWidth;
            int originalHeight2 = originalHeight;
            try {
                float scaleFactor = strategy.getScaleFactor(originalWidth2, originalHeight2, outWidth, outHeight);
                int decodeWidth = Math.round(originalWidth2 * scaleFactor);
                int decodeHeight = Math.round(originalHeight2 * scaleFactor);
                return mediaMetadataRetriever.getScaledFrameAtTime(frameTimeMicros, frameOption, decodeWidth, decodeHeight);
            } catch (Throwable th) {
                th = th;
                Throwable t = th;
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Exception trying to decode frame on oreo+", t);
                    return null;
                }
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static Bitmap decodeOriginalFrame(MediaMetadataRetriever mediaMetadataRetriever, long frameTimeMicros, int frameOption) {
        return mediaMetadataRetriever.getFrameAtTime(frameTimeMicros, frameOption);
    }

    @VisibleForTesting
    static class MediaMetadataRetrieverFactory {
        MediaMetadataRetrieverFactory() {
        }

        public MediaMetadataRetriever build() {
            return new MediaMetadataRetriever();
        }
    }

    private static final class AssetFileDescriptorInitializer implements MediaMetadataRetrieverInitializer<AssetFileDescriptor> {
        private AssetFileDescriptorInitializer() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.VideoDecoder.MediaMetadataRetrieverInitializer
        public void initialize(MediaMetadataRetriever retriever, AssetFileDescriptor data) {
            retriever.setDataSource(data.getFileDescriptor(), data.getStartOffset(), data.getLength());
        }
    }

    static final class ParcelFileDescriptorInitializer implements MediaMetadataRetrieverInitializer<ParcelFileDescriptor> {
        ParcelFileDescriptorInitializer() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.VideoDecoder.MediaMetadataRetrieverInitializer
        public void initialize(MediaMetadataRetriever retriever, ParcelFileDescriptor data) {
            retriever.setDataSource(data.getFileDescriptor());
        }
    }
}
