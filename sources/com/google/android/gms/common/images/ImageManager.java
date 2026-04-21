package com.google.android.gms.common.images;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.images.ImageRequest;
import com.google.android.gms.common.images.internal.PostProcessedResourceCache;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final class ImageManager {
    public static final int PRIORITY_HIGH = 3;
    public static final int PRIORITY_LOW = 1;
    public static final int PRIORITY_MEDIUM = 2;
    private static final Object zzov = new Object();
    private static HashSet<Uri> zzow = new HashSet<>();
    private static ImageManager zzox;
    private static ImageManager zzoy;
    private final Context mContext;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final ExecutorService zzoz = Executors.newFixedThreadPool(4);
    private final zza zzpa;
    private final PostProcessedResourceCache zzpb;
    private final Map<ImageRequest, ImageReceiver> zzpc;
    private final Map<Uri, ImageReceiver> zzpd;
    private final Map<Uri, Long> zzpe;

    @KeepName
    private final class ImageReceiver extends ResultReceiver {
        private final Uri mUri;
        private final ArrayList<ImageRequest> zzpf;

        ImageReceiver(Uri uri) {
            super(new Handler(Looper.getMainLooper()));
            this.mUri = uri;
            this.zzpf = new ArrayList<>();
        }

        @Override // android.os.ResultReceiver
        public final void onReceiveResult(int i, Bundle bundle) {
            ImageManager.this.zzoz.execute(ImageManager.this.new zzb(this.mUri, (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }

        public final void zza(ImageRequest imageRequest) {
            Asserts.checkMainThread("ImageReceiver.addImageRequest() must be called in the main thread");
            this.zzpf.add(imageRequest);
        }

        public final void zzb(ImageRequest imageRequest) {
            Asserts.checkMainThread("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.zzpf.remove(imageRequest);
        }

        public final void zzco() {
            Intent intent = new Intent(Constants.ACTION_LOAD_IMAGE);
            intent.putExtra(Constants.EXTRA_URI, this.mUri);
            intent.putExtra(Constants.EXTRA_RESULT_RECEIVER, this);
            intent.putExtra(Constants.EXTRA_PRIORITY, 3);
            ImageManager.this.mContext.sendBroadcast(intent);
        }
    }

    public interface OnImageLoadedListener {
        void onImageLoaded(Uri uri, Drawable drawable, boolean z);
    }

    private static final class zza extends LruCache<ImageRequest.zza, Bitmap> {
        /* JADX WARN: Illegal instructions before constructor call */
        public zza(Context context) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            super((int) (0.33f * ((context.getApplicationInfo().flags & 1048576) != 0 ? activityManager.getLargeMemoryClass() : activityManager.getMemoryClass()) * 1048576));
        }

        @Override // android.support.v4.util.LruCache
        protected final /* synthetic */ void entryRemoved(boolean z, ImageRequest.zza zzaVar, Bitmap bitmap, Bitmap bitmap2) {
            super.entryRemoved(z, zzaVar, bitmap, bitmap2);
        }

        @Override // android.support.v4.util.LruCache
        protected final /* synthetic */ int sizeOf(ImageRequest.zza zzaVar, Bitmap bitmap) {
            Bitmap bitmap2 = bitmap;
            return bitmap2.getHeight() * bitmap2.getRowBytes();
        }
    }

    private final class zzb implements Runnable {
        private final Uri mUri;
        private final ParcelFileDescriptor zzph;

        public zzb(Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.mUri = uri;
            this.zzph = parcelFileDescriptor;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Asserts.checkNotMainThread("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            boolean z = false;
            Bitmap bitmapDecodeFileDescriptor = null;
            if (this.zzph != null) {
                try {
                    bitmapDecodeFileDescriptor = BitmapFactory.decodeFileDescriptor(this.zzph.getFileDescriptor());
                } catch (OutOfMemoryError e) {
                    String strValueOf = String.valueOf(this.mUri);
                    StringBuilder sb = new StringBuilder(34 + String.valueOf(strValueOf).length());
                    sb.append("OOM while loading bitmap for uri: ");
                    sb.append(strValueOf);
                    Log.e("ImageManager", sb.toString(), e);
                    z = true;
                }
                try {
                    this.zzph.close();
                } catch (IOException e2) {
                    Log.e("ImageManager", "closed failed", e2);
                }
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ImageManager.this.mHandler.post(ImageManager.this.new zze(this.mUri, bitmapDecodeFileDescriptor, z, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException e3) {
                String strValueOf2 = String.valueOf(this.mUri);
                StringBuilder sb2 = new StringBuilder(32 + String.valueOf(strValueOf2).length());
                sb2.append("Latch interrupted while posting ");
                sb2.append(strValueOf2);
                Log.w("ImageManager", sb2.toString());
            }
        }
    }

    private final class zzc implements Runnable {
        private final ImageRequest zzpi;

        public zzc(ImageRequest imageRequest) {
            this.zzpi = imageRequest;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Asserts.checkMainThread("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zzpc.get(this.zzpi);
            if (imageReceiver != null) {
                ImageManager.this.zzpc.remove(this.zzpi);
                imageReceiver.zzb(this.zzpi);
            }
            ImageRequest.zza zzaVar = this.zzpi.zzpk;
            if (zzaVar.uri == null) {
                this.zzpi.zza(ImageManager.this.mContext, ImageManager.this.zzpb, true);
                return;
            }
            Bitmap bitmapZza = ImageManager.this.zza(zzaVar);
            if (bitmapZza != null) {
                this.zzpi.zza(ImageManager.this.mContext, bitmapZza, true);
                return;
            }
            Long l = (Long) ImageManager.this.zzpe.get(zzaVar.uri);
            if (l != null) {
                if (SystemClock.elapsedRealtime() - l.longValue() < 3600000) {
                    this.zzpi.zza(ImageManager.this.mContext, ImageManager.this.zzpb, true);
                    return;
                }
                ImageManager.this.zzpe.remove(zzaVar.uri);
            }
            this.zzpi.zza(ImageManager.this.mContext, ImageManager.this.zzpb);
            ImageReceiver imageReceiver2 = (ImageReceiver) ImageManager.this.zzpd.get(zzaVar.uri);
            if (imageReceiver2 == null) {
                imageReceiver2 = ImageManager.this.new ImageReceiver(zzaVar.uri);
                ImageManager.this.zzpd.put(zzaVar.uri, imageReceiver2);
            }
            imageReceiver2.zza(this.zzpi);
            if (!(this.zzpi instanceof ImageRequest.ListenerImageRequest)) {
                ImageManager.this.zzpc.put(this.zzpi, imageReceiver2);
            }
            synchronized (ImageManager.zzov) {
                if (!ImageManager.zzow.contains(zzaVar.uri)) {
                    ImageManager.zzow.add(zzaVar.uri);
                    imageReceiver2.zzco();
                }
            }
        }
    }

    private static final class zzd implements ComponentCallbacks2 {
        private final zza zzpa;

        public zzd(zza zzaVar) {
            this.zzpa = zzaVar;
        }

        @Override // android.content.ComponentCallbacks
        public final void onConfigurationChanged(Configuration configuration) {
        }

        @Override // android.content.ComponentCallbacks
        public final void onLowMemory() {
            this.zzpa.evictAll();
        }

        @Override // android.content.ComponentCallbacks2
        public final void onTrimMemory(int i) {
            if (i >= 60) {
                this.zzpa.evictAll();
            } else if (i >= 20) {
                this.zzpa.trimToSize(this.zzpa.size() / 2);
            }
        }
    }

    private final class zze implements Runnable {
        private final Bitmap mBitmap;
        private final Uri mUri;
        private final CountDownLatch zzfd;
        private boolean zzpj;

        public zze(Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.mUri = uri;
            this.mBitmap = bitmap;
            this.zzpj = z;
            this.zzfd = countDownLatch;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Asserts.checkMainThread("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean z = this.mBitmap != null;
            if (ImageManager.this.zzpa != null) {
                if (this.zzpj) {
                    ImageManager.this.zzpa.evictAll();
                    System.gc();
                    this.zzpj = false;
                    ImageManager.this.mHandler.post(this);
                    return;
                }
                if (z) {
                    ImageManager.this.zzpa.put(new ImageRequest.zza(this.mUri), this.mBitmap);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zzpd.remove(this.mUri);
            if (imageReceiver != null) {
                ArrayList arrayList = imageReceiver.zzpf;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ImageRequest imageRequest = (ImageRequest) arrayList.get(i);
                    if (z) {
                        imageRequest.zza(ImageManager.this.mContext, this.mBitmap, false);
                    } else {
                        ImageManager.this.zzpe.put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
                        imageRequest.zza(ImageManager.this.mContext, ImageManager.this.zzpb, false);
                    }
                    if (!(imageRequest instanceof ImageRequest.ListenerImageRequest)) {
                        ImageManager.this.zzpc.remove(imageRequest);
                    }
                }
            }
            this.zzfd.countDown();
            synchronized (ImageManager.zzov) {
                ImageManager.zzow.remove(this.mUri);
            }
        }
    }

    private ImageManager(Context context, boolean z) {
        this.mContext = context.getApplicationContext();
        if (z) {
            this.zzpa = new zza(this.mContext);
            this.mContext.registerComponentCallbacks(new zzd(this.zzpa));
        } else {
            this.zzpa = null;
        }
        this.zzpb = new PostProcessedResourceCache();
        this.zzpc = new HashMap();
        this.zzpd = new HashMap();
        this.zzpe = new HashMap();
    }

    public static ImageManager create(Context context) {
        return create(context, false);
    }

    public static ImageManager create(Context context, boolean z) {
        if (z) {
            if (zzoy == null) {
                zzoy = new ImageManager(context, true);
            }
            return zzoy;
        }
        if (zzox == null) {
            zzox = new ImageManager(context, false);
        }
        return zzox;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap zza(ImageRequest.zza zzaVar) {
        if (this.zzpa == null) {
            return null;
        }
        return this.zzpa.get(zzaVar);
    }

    public final void loadImage(ImageView imageView, int i) {
        loadImage(new ImageRequest.ImageViewImageRequest(imageView, i));
    }

    public final void loadImage(ImageView imageView, Uri uri) {
        loadImage(new ImageRequest.ImageViewImageRequest(imageView, uri));
    }

    public final void loadImage(ImageView imageView, Uri uri, int i) {
        ImageRequest.ImageViewImageRequest imageViewImageRequest = new ImageRequest.ImageViewImageRequest(imageView, uri);
        imageViewImageRequest.setNoDataPlaceholder(i);
        loadImage(imageViewImageRequest);
    }

    public final void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri) {
        loadImage(new ImageRequest.ListenerImageRequest(onImageLoadedListener, uri));
    }

    public final void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri, int i) {
        ImageRequest.ListenerImageRequest listenerImageRequest = new ImageRequest.ListenerImageRequest(onImageLoadedListener, uri);
        listenerImageRequest.setNoDataPlaceholder(i);
        loadImage(listenerImageRequest);
    }

    public final void loadImage(ImageRequest imageRequest) {
        Asserts.checkMainThread("ImageManager.loadImage() must be called in the main thread");
        new zzc(imageRequest).run();
    }
}
