package com.bumptech.glide.provider;

import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.bumptech.glide.load.engine.DecodePath;
import com.bumptech.glide.load.engine.LoadPath;
import com.bumptech.glide.load.resource.transcode.UnitTranscoder;
import com.bumptech.glide.util.MultiClassKey;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public class LoadPathCache {
    private static final LoadPath<?, ?, ?> NO_PATHS_SIGNAL = new LoadPath<>(Object.class, Object.class, Object.class, Collections.singletonList(new DecodePath(Object.class, Object.class, Object.class, Collections.emptyList(), new UnitTranscoder(), null)), null);
    private final ArrayMap<MultiClassKey, LoadPath<?, ?, ?>> cache = new ArrayMap<>();
    private final AtomicReference<MultiClassKey> keyRef = new AtomicReference<>();

    public boolean isEmptyLoadPath(@Nullable LoadPath<?, ?, ?> path) {
        return NO_PATHS_SIGNAL.equals(path);
    }

    @Nullable
    public <Data, TResource, Transcode> LoadPath<Data, TResource, Transcode> get(Class<Data> dataClass, Class<TResource> resourceClass, Class<Transcode> transcodeClass) {
        LoadPath<Data, TResource, Transcode> loadPath;
        MultiClassKey key = getKey(dataClass, resourceClass, transcodeClass);
        synchronized (this.cache) {
            loadPath = (LoadPath) this.cache.get(key);
        }
        this.keyRef.set(key);
        return loadPath;
    }

    public void put(Class<?> dataClass, Class<?> resourceClass, Class<?> transcodeClass, @Nullable LoadPath<?, ?, ?> loadPath) {
        LoadPath<?, ?, ?> loadPath2;
        synchronized (this.cache) {
            ArrayMap<MultiClassKey, LoadPath<?, ?, ?>> arrayMap = this.cache;
            MultiClassKey multiClassKey = new MultiClassKey(dataClass, resourceClass, transcodeClass);
            if (loadPath != null) {
                loadPath2 = loadPath;
            } else {
                loadPath2 = NO_PATHS_SIGNAL;
            }
            arrayMap.put(multiClassKey, loadPath2);
        }
    }

    private MultiClassKey getKey(Class<?> dataClass, Class<?> resourceClass, Class<?> transcodeClass) {
        MultiClassKey key = this.keyRef.getAndSet(null);
        if (key == null) {
            key = new MultiClassKey();
        }
        key.set(dataClass, resourceClass, transcodeClass);
        return key;
    }
}
