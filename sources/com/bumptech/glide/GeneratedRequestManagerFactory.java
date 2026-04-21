package com.bumptech.glide;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import quadrant.mokafat.points.helper.GlideRequests;

/* JADX INFO: loaded from: classes.dex */
final class GeneratedRequestManagerFactory implements RequestManagerRetriever.RequestManagerFactory {
    GeneratedRequestManagerFactory() {
    }

    @Override // com.bumptech.glide.manager.RequestManagerRetriever.RequestManagerFactory
    @NonNull
    public RequestManager build(@NonNull Glide glide, @NonNull Lifecycle lifecycle, @NonNull RequestManagerTreeNode treeNode, @NonNull Context context) {
        return new GlideRequests(glide, lifecycle, treeNode, context);
    }
}
