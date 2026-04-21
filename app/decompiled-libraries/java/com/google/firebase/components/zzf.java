package com.google.firebase.components;

import android.support.annotation.GuardedBy;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.firebase:firebase-common@@16.0.1 */
/* JADX INFO: loaded from: classes.dex */
class zzf implements Publisher, Subscriber {

    @GuardedBy("this")
    private final Map<Class<?>, ConcurrentHashMap<EventHandler<Object>, Executor>> zza = new HashMap();

    @GuardedBy("this")
    private Queue<Event<?>> zzb = new ArrayDeque();
    private final Executor zzc;

    zzf(Executor executor) {
        this.zzc = executor;
    }

    @Override // com.google.firebase.events.Publisher
    public void publish(final Event<?> event) {
        Preconditions.checkNotNull(event);
        synchronized (this) {
            if (this.zzb != null) {
                this.zzb.add(event);
                return;
            }
            for (final Map.Entry<EventHandler<Object>, Executor> entry : zza(event)) {
                entry.getValue().execute(new Runnable(entry, event) { // from class: com.google.firebase.components.zzg
                    private final Map.Entry zza;
                    private final Event zzb;

                    {
                        this.zza = entry;
                        this.zzb = event;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        ((EventHandler) this.zza.getKey()).handle(this.zzb);
                    }
                });
            }
        }
    }

    private synchronized Set<Map.Entry<EventHandler<Object>, Executor>> zza(Event<?> event) {
        ConcurrentHashMap<EventHandler<Object>, Executor> concurrentHashMap;
        concurrentHashMap = this.zza.get(event.getType());
        return concurrentHashMap == null ? Collections.emptySet() : concurrentHashMap.entrySet();
    }

    @Override // com.google.firebase.events.Subscriber
    public synchronized <T> void subscribe(Class<T> type, Executor executor, EventHandler<? super T> handler) {
        Preconditions.checkNotNull(type);
        Preconditions.checkNotNull(handler);
        Preconditions.checkNotNull(executor);
        if (!this.zza.containsKey(type)) {
            this.zza.put(type, new ConcurrentHashMap<>());
        }
        this.zza.get(type).put(handler, executor);
    }

    @Override // com.google.firebase.events.Subscriber
    public <T> void subscribe(Class<T> type, EventHandler<? super T> handler) {
        subscribe(type, this.zzc, handler);
    }

    @Override // com.google.firebase.events.Subscriber
    public synchronized <T> void unsubscribe(Class<T> type, EventHandler<? super T> handler) {
        Preconditions.checkNotNull(type);
        Preconditions.checkNotNull(handler);
        if (this.zza.containsKey(type)) {
            ConcurrentHashMap<EventHandler<Object>, Executor> concurrentHashMap = this.zza.get(type);
            concurrentHashMap.remove(handler);
            if (concurrentHashMap.isEmpty()) {
                this.zza.remove(type);
            }
        }
    }

    final void zza() {
        Queue<Event<?>> queue;
        synchronized (this) {
            if (this.zzb != null) {
                queue = this.zzb;
                this.zzb = null;
            } else {
                queue = null;
            }
        }
        if (queue != null) {
            Iterator<Event<?>> it = queue.iterator();
            while (it.hasNext()) {
                publish(it.next());
            }
        }
    }
}
