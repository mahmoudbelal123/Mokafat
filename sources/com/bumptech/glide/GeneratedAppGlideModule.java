package com.bumptech.glide;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.module.AppGlideModule;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
abstract class GeneratedAppGlideModule extends AppGlideModule {
    @NonNull
    abstract Set<Class<?>> getExcludedModuleClasses();

    GeneratedAppGlideModule() {
    }

    @Nullable
    RequestManagerRetriever.RequestManagerFactory getRequestManagerFactory() {
        return null;
    }
}
