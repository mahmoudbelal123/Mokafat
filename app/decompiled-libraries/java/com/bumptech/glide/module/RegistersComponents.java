package com.bumptech.glide.module;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public interface RegistersComponents {
    void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry);
}
