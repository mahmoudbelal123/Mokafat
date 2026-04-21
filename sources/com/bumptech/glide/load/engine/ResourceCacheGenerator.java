package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class ResourceCacheGenerator implements DataFetcherGenerator, DataFetcher.DataCallback<Object> {
    private File cacheFile;
    private final DataFetcherGenerator.FetcherReadyCallback cb;
    private ResourceCacheKey currentKey;
    private final DecodeHelper<?> helper;
    private volatile ModelLoader.LoadData<?> loadData;
    private int modelLoaderIndex;
    private List<ModelLoader<File, ?>> modelLoaders;
    private int resourceClassIndex = -1;
    private int sourceIdIndex;
    private Key sourceKey;

    ResourceCacheGenerator(DecodeHelper<?> helper, DataFetcherGenerator.FetcherReadyCallback cb) {
        this.helper = helper;
        this.cb = cb;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v9 */
    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator
    public boolean startNext() {
        List<Key> sourceIds = this.helper.getCacheKeys();
        ?? r3 = 0;
        if (sourceIds.isEmpty()) {
            return false;
        }
        List<Class<?>> resourceClasses = this.helper.getRegisteredResourceClasses();
        if (resourceClasses.isEmpty() && File.class.equals(this.helper.getTranscodeClass())) {
            return false;
        }
        while (true) {
            if (this.modelLoaders == null || !hasNextModelLoader()) {
                this.resourceClassIndex++;
                if (this.resourceClassIndex >= resourceClasses.size()) {
                    this.sourceIdIndex++;
                    if (this.sourceIdIndex >= sourceIds.size()) {
                        return r3;
                    }
                    this.resourceClassIndex = r3;
                }
                Key sourceId = sourceIds.get(this.sourceIdIndex);
                Class<?> resourceClass = resourceClasses.get(this.resourceClassIndex);
                this.currentKey = new ResourceCacheKey(this.helper.getArrayPool(), sourceId, this.helper.getSignature(), this.helper.getWidth(), this.helper.getHeight(), this.helper.getTransformation(resourceClass), resourceClass, this.helper.getOptions());
                this.cacheFile = this.helper.getDiskCache().get(this.currentKey);
                if (this.cacheFile != null) {
                    this.sourceKey = sourceId;
                    this.modelLoaders = this.helper.getModelLoaders(this.cacheFile);
                    r3 = 0;
                    this.modelLoaderIndex = 0;
                } else {
                    r3 = 0;
                }
            } else {
                this.loadData = null;
                while (r3 == 0 && hasNextModelLoader()) {
                    List<ModelLoader<File, ?>> list = this.modelLoaders;
                    int i = this.modelLoaderIndex;
                    this.modelLoaderIndex = i + 1;
                    ModelLoader<File, ?> modelLoader = list.get(i);
                    this.loadData = modelLoader.buildLoadData(this.cacheFile, this.helper.getWidth(), this.helper.getHeight(), this.helper.getOptions());
                    if (this.loadData != null && this.helper.hasLoadPath(this.loadData.fetcher.getDataClass())) {
                        r3 = 1;
                        this.loadData.fetcher.loadData(this.helper.getPriority(), this);
                    }
                }
                return r3;
            }
        }
    }

    private boolean hasNextModelLoader() {
        return this.modelLoaderIndex < this.modelLoaders.size();
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator
    public void cancel() {
        ModelLoader.LoadData<?> local = this.loadData;
        if (local != null) {
            local.fetcher.cancel();
        }
    }

    @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
    public void onDataReady(Object data) {
        this.cb.onDataFetcherReady(this.sourceKey, data, this.loadData.fetcher, DataSource.RESOURCE_DISK_CACHE, this.currentKey);
    }

    @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
    public void onLoadFailed(@NonNull Exception e) {
        this.cb.onDataFetcherFailed(this.currentKey, e, this.loadData.fetcher, DataSource.RESOURCE_DISK_CACHE);
    }
}
