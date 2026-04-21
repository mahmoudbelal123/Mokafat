package quadrant.mokafat.points.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.request.RequestOptions;
import java.io.File;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class GlideRequests extends RequestManager {
    public GlideRequests(@NonNull Glide glide, @NonNull Lifecycle lifecycle, @NonNull RequestManagerTreeNode treeNode, @NonNull Context context) {
        super(glide, lifecycle, treeNode, context);
    }

    @Override // com.bumptech.glide.RequestManager
    @CheckResult
    @NonNull
    public <ResourceType> GlideRequest<ResourceType> as(@NonNull Class<ResourceType> resourceClass) {
        return new GlideRequest<>(this.glide, this, resourceClass, this.context);
    }

    @Override // com.bumptech.glide.RequestManager
    @NonNull
    public GlideRequests applyDefaultRequestOptions(@NonNull RequestOptions options) {
        return (GlideRequests) super.applyDefaultRequestOptions(options);
    }

    @Override // com.bumptech.glide.RequestManager
    @NonNull
    public GlideRequests setDefaultRequestOptions(@NonNull RequestOptions options) {
        return (GlideRequests) super.setDefaultRequestOptions(options);
    }

    @Override // com.bumptech.glide.RequestManager
    @CheckResult
    @NonNull
    public GlideRequest<Bitmap> asBitmap() {
        return (GlideRequest) super.asBitmap();
    }

    @Override // com.bumptech.glide.RequestManager
    @CheckResult
    @NonNull
    public GlideRequest<GifDrawable> asGif() {
        return (GlideRequest) super.asGif();
    }

    @Override // com.bumptech.glide.RequestManager
    @CheckResult
    @NonNull
    public GlideRequest<Drawable> asDrawable() {
        return (GlideRequest) super.asDrawable();
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    /* JADX INFO: renamed from: load, reason: merged with bridge method [inline-methods] */
    public RequestBuilder<Drawable> load2(@Nullable Bitmap bitmap) {
        return (GlideRequest) super.load2(bitmap);
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    /* JADX INFO: renamed from: load, reason: merged with bridge method [inline-methods] */
    public RequestBuilder<Drawable> load2(@Nullable Drawable drawable) {
        return (GlideRequest) super.load2(drawable);
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    /* JADX INFO: renamed from: load, reason: merged with bridge method [inline-methods] */
    public RequestBuilder<Drawable> load2(@Nullable String string) {
        return (GlideRequest) super.load2(string);
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    /* JADX INFO: renamed from: load, reason: merged with bridge method [inline-methods] */
    public RequestBuilder<Drawable> load2(@Nullable Uri uri) {
        return (GlideRequest) super.load2(uri);
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    /* JADX INFO: renamed from: load, reason: merged with bridge method [inline-methods] */
    public RequestBuilder<Drawable> load2(@Nullable File file) {
        return (GlideRequest) super.load2(file);
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    /* JADX INFO: renamed from: load, reason: merged with bridge method [inline-methods] */
    public RequestBuilder<Drawable> load2(@RawRes @DrawableRes @Nullable Integer id) {
        return (GlideRequest) super.load2(id);
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @Deprecated
    /* JADX INFO: renamed from: load, reason: merged with bridge method [inline-methods] */
    public RequestBuilder<Drawable> load2(@Nullable URL url) {
        return (GlideRequest) super.load2(url);
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    /* JADX INFO: renamed from: load, reason: merged with bridge method [inline-methods] */
    public RequestBuilder<Drawable> load2(@Nullable byte[] bytes) {
        return (GlideRequest) super.load2(bytes);
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    /* JADX INFO: renamed from: load, reason: merged with bridge method [inline-methods] */
    public RequestBuilder<Drawable> load2(@Nullable Object o) {
        return (GlideRequest) super.load2(o);
    }

    @Override // com.bumptech.glide.RequestManager
    @CheckResult
    @NonNull
    public GlideRequest<File> downloadOnly() {
        return (GlideRequest) super.downloadOnly();
    }

    @Override // com.bumptech.glide.RequestManager
    @CheckResult
    @NonNull
    public GlideRequest<File> download(@Nullable Object o) {
        return (GlideRequest) super.download(o);
    }

    @Override // com.bumptech.glide.RequestManager
    @CheckResult
    @NonNull
    public GlideRequest<File> asFile() {
        return (GlideRequest) super.asFile();
    }

    @Override // com.bumptech.glide.RequestManager
    protected void setRequestOptions(@NonNull RequestOptions toSet) {
        if (toSet instanceof GlideOptions) {
            super.setRequestOptions(toSet);
        } else {
            super.setRequestOptions(new GlideOptions().apply(toSet));
        }
    }
}
