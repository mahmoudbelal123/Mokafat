package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ModelLoaderRegistry {
    private final ModelLoaderCache cache;
    private final MultiModelLoaderFactory multiModelLoaderFactory;

    public ModelLoaderRegistry(@NonNull Pools.Pool<List<Throwable>> throwableListPool) {
        this(new MultiModelLoaderFactory(throwableListPool));
    }

    private ModelLoaderRegistry(@NonNull MultiModelLoaderFactory multiModelLoaderFactory) {
        this.cache = new ModelLoaderCache();
        this.multiModelLoaderFactory = multiModelLoaderFactory;
    }

    public synchronized <Model, Data> void append(@NonNull Class<Model> modelClass, @NonNull Class<Data> dataClass, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> factory) {
        this.multiModelLoaderFactory.append(modelClass, dataClass, factory);
        this.cache.clear();
    }

    public synchronized <Model, Data> void prepend(@NonNull Class<Model> modelClass, @NonNull Class<Data> dataClass, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> factory) {
        this.multiModelLoaderFactory.prepend(modelClass, dataClass, factory);
        this.cache.clear();
    }

    public synchronized <Model, Data> void remove(@NonNull Class<Model> modelClass, @NonNull Class<Data> dataClass) {
        tearDown(this.multiModelLoaderFactory.remove(modelClass, dataClass));
        this.cache.clear();
    }

    public synchronized <Model, Data> void replace(@NonNull Class<Model> modelClass, @NonNull Class<Data> dataClass, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> factory) {
        tearDown(this.multiModelLoaderFactory.replace(modelClass, dataClass, factory));
        this.cache.clear();
    }

    private <Model, Data> void tearDown(@NonNull List<ModelLoaderFactory<? extends Model, ? extends Data>> factories) {
        for (ModelLoaderFactory<? extends Model, ? extends Data> factory : factories) {
            factory.teardown();
        }
    }

    @NonNull
    public synchronized <A> List<ModelLoader<A, ?>> getModelLoaders(@NonNull A model) {
        List<ModelLoader<A, ?>> filteredLoaders;
        List<ModelLoader<A, ?>> modelLoaders = getModelLoadersForClass(getClass(model));
        int size = modelLoaders.size();
        filteredLoaders = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            ModelLoader<A, ?> loader = modelLoaders.get(i);
            if (loader.handles(model)) {
                filteredLoaders.add(loader);
            }
        }
        return filteredLoaders;
    }

    public synchronized <Model, Data> ModelLoader<Model, Data> build(@NonNull Class<Model> modelClass, @NonNull Class<Data> dataClass) {
        return this.multiModelLoaderFactory.build(modelClass, dataClass);
    }

    @NonNull
    public synchronized List<Class<?>> getDataClasses(@NonNull Class<?> modelClass) {
        return this.multiModelLoaderFactory.getDataClasses(modelClass);
    }

    @NonNull
    private <A> List<ModelLoader<A, ?>> getModelLoadersForClass(@NonNull Class<A> modelClass) {
        List<ModelLoader<A, ?>> loaders = this.cache.get(modelClass);
        if (loaders == null) {
            List<ModelLoader<A, ?>> loaders2 = Collections.unmodifiableList(this.multiModelLoaderFactory.build(modelClass));
            this.cache.put(modelClass, loaders2);
            return loaders2;
        }
        return loaders;
    }

    @NonNull
    private static <A> Class<A> getClass(@NonNull A a) {
        return (Class<A>) a.getClass();
    }

    private static class ModelLoaderCache {
        private final Map<Class<?>, Entry<?>> cachedModelLoaders = new HashMap();

        ModelLoaderCache() {
        }

        public void clear() {
            this.cachedModelLoaders.clear();
        }

        public <Model> void put(Class<Model> modelClass, List<ModelLoader<Model, ?>> loaders) {
            Entry<?> previous = this.cachedModelLoaders.put(modelClass, new Entry<>(loaders));
            if (previous != null) {
                throw new IllegalStateException("Already cached loaders for model: " + modelClass);
            }
        }

        @Nullable
        public <Model> List<ModelLoader<Model, ?>> get(Class<Model> cls) {
            Entry<?> entry = this.cachedModelLoaders.get(cls);
            if (entry == null) {
                return null;
            }
            return (List<ModelLoader<Model, ?>>) entry.loaders;
        }

        private static class Entry<Model> {
            final List<ModelLoader<Model, ?>> loaders;

            public Entry(List<ModelLoader<Model, ?>> loaders) {
                this.loaders = loaders;
            }
        }
    }
}
