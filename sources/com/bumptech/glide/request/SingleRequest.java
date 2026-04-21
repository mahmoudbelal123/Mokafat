package com.bumptech.glide.request;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools;
import android.util.Log;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.DrawableDecoderCompat;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;

/* JADX INFO: loaded from: classes.dex */
public final class SingleRequest<R> implements Request, SizeReadyCallback, ResourceCallback, FactoryPools.Poolable {
    private static final String GLIDE_TAG = "Glide";
    private TransitionFactory<? super R> animationFactory;
    private Context context;
    private Engine engine;
    private Drawable errorDrawable;
    private Drawable fallbackDrawable;
    private GlideContext glideContext;
    private int height;
    private boolean isCallingCallbacks;
    private Engine.LoadStatus loadStatus;

    @Nullable
    private Object model;
    private int overrideHeight;
    private int overrideWidth;
    private Drawable placeholderDrawable;
    private Priority priority;
    private RequestCoordinator requestCoordinator;
    private RequestListener<R> requestListener;
    private RequestOptions requestOptions;
    private Resource<R> resource;
    private long startTime;
    private final StateVerifier stateVerifier;
    private Status status;

    @Nullable
    private final String tag;
    private Target<R> target;

    @Nullable
    private RequestListener<R> targetListener;
    private Class<R> transcodeClass;
    private int width;
    private static final Pools.Pool<SingleRequest<?>> POOL = FactoryPools.simple(150, new FactoryPools.Factory<SingleRequest<?>>() { // from class: com.bumptech.glide.request.SingleRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bumptech.glide.util.pool.FactoryPools.Factory
        public SingleRequest<?> create() {
            return new SingleRequest<>();
        }
    });
    private static final String TAG = "Request";
    private static final boolean IS_VERBOSE_LOGGABLE = Log.isLoggable(TAG, 2);

    private enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CANCELLED,
        CLEARED,
        PAUSED
    }

    public static <R> SingleRequest<R> obtain(Context context, GlideContext glideContext, Object model, Class<R> transcodeClass, RequestOptions requestOptions, int overrideWidth, int overrideHeight, Priority priority, Target<R> target, RequestListener<R> targetListener, RequestListener<R> requestListener, RequestCoordinator requestCoordinator, Engine engine, TransitionFactory<? super R> animationFactory) {
        SingleRequest<R> request = (SingleRequest) POOL.acquire();
        if (request == null) {
            request = new SingleRequest<>();
        }
        request.init(context, glideContext, model, transcodeClass, requestOptions, overrideWidth, overrideHeight, priority, target, targetListener, requestListener, requestCoordinator, engine, animationFactory);
        return request;
    }

    SingleRequest() {
        this.tag = IS_VERBOSE_LOGGABLE ? String.valueOf(super.hashCode()) : null;
        this.stateVerifier = StateVerifier.newInstance();
    }

    private void init(Context context, GlideContext glideContext, Object model, Class<R> transcodeClass, RequestOptions requestOptions, int overrideWidth, int overrideHeight, Priority priority, Target<R> target, RequestListener<R> targetListener, RequestListener<R> requestListener, RequestCoordinator requestCoordinator, Engine engine, TransitionFactory<? super R> animationFactory) {
        this.context = context;
        this.glideContext = glideContext;
        this.model = model;
        this.transcodeClass = transcodeClass;
        this.requestOptions = requestOptions;
        this.overrideWidth = overrideWidth;
        this.overrideHeight = overrideHeight;
        this.priority = priority;
        this.target = target;
        this.targetListener = targetListener;
        this.requestListener = requestListener;
        this.requestCoordinator = requestCoordinator;
        this.engine = engine;
        this.animationFactory = animationFactory;
        this.status = Status.PENDING;
    }

    @Override // com.bumptech.glide.util.pool.FactoryPools.Poolable
    @NonNull
    public StateVerifier getVerifier() {
        return this.stateVerifier;
    }

    @Override // com.bumptech.glide.request.Request
    public void recycle() {
        assertNotCallingCallbacks();
        this.context = null;
        this.glideContext = null;
        this.model = null;
        this.transcodeClass = null;
        this.requestOptions = null;
        this.overrideWidth = -1;
        this.overrideHeight = -1;
        this.target = null;
        this.requestListener = null;
        this.targetListener = null;
        this.requestCoordinator = null;
        this.animationFactory = null;
        this.loadStatus = null;
        this.errorDrawable = null;
        this.placeholderDrawable = null;
        this.fallbackDrawable = null;
        this.width = -1;
        this.height = -1;
        POOL.release(this);
    }

    @Override // com.bumptech.glide.request.Request
    public void begin() {
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        this.startTime = LogTime.getLogTime();
        if (this.model == null) {
            if (Util.isValidDimensions(this.overrideWidth, this.overrideHeight)) {
                this.width = this.overrideWidth;
                this.height = this.overrideHeight;
            }
            int logLevel = getFallbackDrawable() == null ? 5 : 3;
            onLoadFailed(new GlideException("Received null model"), logLevel);
            return;
        }
        if (this.status == Status.RUNNING) {
            throw new IllegalArgumentException("Cannot restart a running request");
        }
        if (this.status == Status.COMPLETE) {
            onResourceReady(this.resource, DataSource.MEMORY_CACHE);
            return;
        }
        this.status = Status.WAITING_FOR_SIZE;
        if (Util.isValidDimensions(this.overrideWidth, this.overrideHeight)) {
            onSizeReady(this.overrideWidth, this.overrideHeight);
        } else {
            this.target.getSize(this);
        }
        if ((this.status == Status.RUNNING || this.status == Status.WAITING_FOR_SIZE) && canNotifyStatusChanged()) {
            this.target.onLoadStarted(getPlaceholderDrawable());
        }
        if (IS_VERBOSE_LOGGABLE) {
            logV("finished run method in " + LogTime.getElapsedMillis(this.startTime));
        }
    }

    void cancel() {
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        this.target.removeCallback(this);
        this.status = Status.CANCELLED;
        if (this.loadStatus != null) {
            this.loadStatus.cancel();
            this.loadStatus = null;
        }
    }

    private void assertNotCallingCallbacks() {
        if (this.isCallingCallbacks) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    @Override // com.bumptech.glide.request.Request
    public void clear() {
        Util.assertMainThread();
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        if (this.status == Status.CLEARED) {
            return;
        }
        cancel();
        if (this.resource != null) {
            releaseResource(this.resource);
        }
        if (canNotifyCleared()) {
            this.target.onLoadCleared(getPlaceholderDrawable());
        }
        this.status = Status.CLEARED;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isPaused() {
        return this.status == Status.PAUSED;
    }

    @Override // com.bumptech.glide.request.Request
    public void pause() {
        clear();
        this.status = Status.PAUSED;
    }

    private void releaseResource(Resource<?> resource) {
        this.engine.release(resource);
        this.resource = null;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isRunning() {
        return this.status == Status.RUNNING || this.status == Status.WAITING_FOR_SIZE;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isComplete() {
        return this.status == Status.COMPLETE;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isResourceSet() {
        return isComplete();
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isCancelled() {
        return this.status == Status.CANCELLED || this.status == Status.CLEARED;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isFailed() {
        return this.status == Status.FAILED;
    }

    private Drawable getErrorDrawable() {
        if (this.errorDrawable == null) {
            this.errorDrawable = this.requestOptions.getErrorPlaceholder();
            if (this.errorDrawable == null && this.requestOptions.getErrorId() > 0) {
                this.errorDrawable = loadDrawable(this.requestOptions.getErrorId());
            }
        }
        return this.errorDrawable;
    }

    private Drawable getPlaceholderDrawable() {
        if (this.placeholderDrawable == null) {
            this.placeholderDrawable = this.requestOptions.getPlaceholderDrawable();
            if (this.placeholderDrawable == null && this.requestOptions.getPlaceholderId() > 0) {
                this.placeholderDrawable = loadDrawable(this.requestOptions.getPlaceholderId());
            }
        }
        return this.placeholderDrawable;
    }

    private Drawable getFallbackDrawable() {
        if (this.fallbackDrawable == null) {
            this.fallbackDrawable = this.requestOptions.getFallbackDrawable();
            if (this.fallbackDrawable == null && this.requestOptions.getFallbackId() > 0) {
                this.fallbackDrawable = loadDrawable(this.requestOptions.getFallbackId());
            }
        }
        return this.fallbackDrawable;
    }

    private Drawable loadDrawable(@DrawableRes int resourceId) {
        Resources.Theme theme = this.requestOptions.getTheme() != null ? this.requestOptions.getTheme() : this.context.getTheme();
        return DrawableDecoderCompat.getDrawable(this.glideContext, resourceId, theme);
    }

    private void setErrorPlaceholder() {
        if (!canNotifyStatusChanged()) {
            return;
        }
        Drawable error = null;
        if (this.model == null) {
            error = getFallbackDrawable();
        }
        if (error == null) {
            error = getErrorDrawable();
        }
        if (error == null) {
            error = getPlaceholderDrawable();
        }
        this.target.onLoadFailed(error);
    }

    @Override // com.bumptech.glide.request.target.SizeReadyCallback
    public void onSizeReady(int width, int height) {
        this.stateVerifier.throwIfRecycled();
        if (IS_VERBOSE_LOGGABLE) {
            logV("Got onSizeReady in " + LogTime.getElapsedMillis(this.startTime));
        }
        if (this.status == Status.WAITING_FOR_SIZE) {
            this.status = Status.RUNNING;
            float sizeMultiplier = this.requestOptions.getSizeMultiplier();
            this.width = maybeApplySizeMultiplier(width, sizeMultiplier);
            this.height = maybeApplySizeMultiplier(height, sizeMultiplier);
            if (IS_VERBOSE_LOGGABLE) {
                logV("finished setup for calling load in " + LogTime.getElapsedMillis(this.startTime));
            }
            this.loadStatus = this.engine.load(this.glideContext, this.model, this.requestOptions.getSignature(), this.width, this.height, this.requestOptions.getResourceClass(), this.transcodeClass, this.priority, this.requestOptions.getDiskCacheStrategy(), this.requestOptions.getTransformations(), this.requestOptions.isTransformationRequired(), this.requestOptions.isScaleOnlyOrNoTransform(), this.requestOptions.getOptions(), this.requestOptions.isMemoryCacheable(), this.requestOptions.getUseUnlimitedSourceGeneratorsPool(), this.requestOptions.getUseAnimationPool(), this.requestOptions.getOnlyRetrieveFromCache(), this);
            if (this.status != Status.RUNNING) {
                this.loadStatus = null;
            }
            if (IS_VERBOSE_LOGGABLE) {
                logV("finished onSizeReady in " + LogTime.getElapsedMillis(this.startTime));
            }
        }
    }

    private static int maybeApplySizeMultiplier(int size, float sizeMultiplier) {
        return size == Integer.MIN_VALUE ? size : Math.round(size * sizeMultiplier);
    }

    private boolean canSetResource() {
        return this.requestCoordinator == null || this.requestCoordinator.canSetImage(this);
    }

    private boolean canNotifyCleared() {
        return this.requestCoordinator == null || this.requestCoordinator.canNotifyCleared(this);
    }

    private boolean canNotifyStatusChanged() {
        return this.requestCoordinator == null || this.requestCoordinator.canNotifyStatusChanged(this);
    }

    private boolean isFirstReadyResource() {
        return this.requestCoordinator == null || !this.requestCoordinator.isAnyResourceSet();
    }

    private void notifyLoadSuccess() {
        if (this.requestCoordinator != null) {
            this.requestCoordinator.onRequestSuccess(this);
        }
    }

    private void notifyLoadFailed() {
        if (this.requestCoordinator != null) {
            this.requestCoordinator.onRequestFailed(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.request.ResourceCallback
    public void onResourceReady(Resource<?> resource, DataSource dataSource) {
        this.stateVerifier.throwIfRecycled();
        this.loadStatus = null;
        if (resource == null) {
            GlideException exception = new GlideException("Expected to receive a Resource<R> with an object of " + this.transcodeClass + " inside, but instead got null.");
            onLoadFailed(exception);
            return;
        }
        Object received = resource.get();
        if (received == null || !this.transcodeClass.isAssignableFrom(received.getClass())) {
            releaseResource(resource);
            StringBuilder sb = new StringBuilder();
            sb.append("Expected to receive an object of ");
            sb.append(this.transcodeClass);
            sb.append(" but instead got ");
            sb.append(received != null ? received.getClass() : "");
            sb.append("{");
            sb.append(received);
            sb.append("} inside Resource{");
            sb.append(resource);
            sb.append("}.");
            sb.append(received != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data.");
            GlideException exception2 = new GlideException(sb.toString());
            onLoadFailed(exception2);
            return;
        }
        if (!canSetResource()) {
            releaseResource(resource);
            this.status = Status.COMPLETE;
        } else {
            onResourceReady(resource, received, dataSource);
        }
    }

    private void onResourceReady(Resource<R> resource, R result, DataSource dataSource) {
        boolean isFirstResource = isFirstReadyResource();
        this.status = Status.COMPLETE;
        this.resource = resource;
        if (this.glideContext.getLogLevel() <= 3) {
            Log.d(GLIDE_TAG, "Finished loading " + result.getClass().getSimpleName() + " from " + dataSource + " for " + this.model + " with size [" + this.width + "x" + this.height + "] in " + LogTime.getElapsedMillis(this.startTime) + " ms");
        }
        this.isCallingCallbacks = true;
        try {
            if ((this.requestListener == null || !this.requestListener.onResourceReady(result, this.model, this.target, dataSource, isFirstResource)) && (this.targetListener == null || !this.targetListener.onResourceReady(result, this.model, this.target, dataSource, isFirstResource))) {
                Transition<? super R> animation = this.animationFactory.build(dataSource, isFirstResource);
                this.target.onResourceReady(result, animation);
            }
            this.isCallingCallbacks = false;
            notifyLoadSuccess();
        } catch (Throwable th) {
            this.isCallingCallbacks = false;
            throw th;
        }
    }

    @Override // com.bumptech.glide.request.ResourceCallback
    public void onLoadFailed(GlideException e) {
        onLoadFailed(e, 5);
    }

    private void onLoadFailed(GlideException e, int maxLogLevel) {
        this.stateVerifier.throwIfRecycled();
        int logLevel = this.glideContext.getLogLevel();
        if (logLevel <= maxLogLevel) {
            Log.w(GLIDE_TAG, "Load failed for " + this.model + " with size [" + this.width + "x" + this.height + "]", e);
            if (logLevel <= 4) {
                e.logRootCauses(GLIDE_TAG);
            }
        }
        this.loadStatus = null;
        this.status = Status.FAILED;
        this.isCallingCallbacks = true;
        try {
            if ((this.requestListener == null || !this.requestListener.onLoadFailed(e, this.model, this.target, isFirstReadyResource())) && (this.targetListener == null || !this.targetListener.onLoadFailed(e, this.model, this.target, isFirstReadyResource()))) {
                setErrorPlaceholder();
            }
            this.isCallingCallbacks = false;
            notifyLoadFailed();
        } catch (Throwable th) {
            this.isCallingCallbacks = false;
            throw th;
        }
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isEquivalentTo(Request o) {
        if (!(o instanceof SingleRequest)) {
            return false;
        }
        SingleRequest<?> that = (SingleRequest) o;
        if (this.overrideWidth != that.overrideWidth || this.overrideHeight != that.overrideHeight || !Util.bothModelsNullEquivalentOrEquals(this.model, that.model) || !this.transcodeClass.equals(that.transcodeClass) || !this.requestOptions.equals(that.requestOptions) || this.priority != that.priority) {
            return false;
        }
        if (this.requestListener != null) {
            if (that.requestListener == null) {
                return false;
            }
        } else if (that.requestListener != null) {
            return false;
        }
        return true;
    }

    private void logV(String message) {
        Log.v(TAG, message + " this: " + this.tag);
    }
}
