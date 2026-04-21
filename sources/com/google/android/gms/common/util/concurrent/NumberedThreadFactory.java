package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class NumberedThreadFactory implements ThreadFactory {
    private final int priority;
    private final ThreadFactory zzaau;
    private final String zzaav;
    private final AtomicInteger zzaaw;

    public NumberedThreadFactory(String str) {
        this(str, 0);
    }

    public NumberedThreadFactory(String str, int i) {
        this.zzaaw = new AtomicInteger();
        this.zzaau = Executors.defaultThreadFactory();
        this.zzaav = (String) Preconditions.checkNotNull(str, "Name must not be null");
        this.priority = i;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread threadNewThread = this.zzaau.newThread(new zza(runnable, this.priority));
        String str = this.zzaav;
        int andIncrement = this.zzaaw.getAndIncrement();
        StringBuilder sb = new StringBuilder(13 + String.valueOf(str).length());
        sb.append(str);
        sb.append("[");
        sb.append(andIncrement);
        sb.append("]");
        threadNewThread.setName(sb.toString());
        return threadNewThread;
    }
}
