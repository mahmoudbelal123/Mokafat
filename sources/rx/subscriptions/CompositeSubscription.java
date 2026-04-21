package rx.subscriptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import rx.Subscription;
import rx.exceptions.Exceptions;

/* JADX INFO: loaded from: classes.dex */
public final class CompositeSubscription implements Subscription {
    private Set<Subscription> subscriptions;
    private volatile boolean unsubscribed;

    public CompositeSubscription() {
    }

    public CompositeSubscription(Subscription... subscriptions) {
        this.subscriptions = new HashSet(Arrays.asList(subscriptions));
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.unsubscribed;
    }

    public void add(Subscription s) {
        if (s.isUnsubscribed()) {
            return;
        }
        if (!this.unsubscribed) {
            synchronized (this) {
                if (!this.unsubscribed) {
                    if (this.subscriptions == null) {
                        this.subscriptions = new HashSet(4);
                    }
                    this.subscriptions.add(s);
                    return;
                }
            }
        }
        s.unsubscribe();
    }

    public void addAll(Subscription... subscriptions) {
        int i$ = 0;
        if (!this.unsubscribed) {
            synchronized (this) {
                if (!this.unsubscribed) {
                    if (this.subscriptions == null) {
                        this.subscriptions = new HashSet(subscriptions.length);
                    }
                    int len$ = subscriptions.length;
                    while (i$ < len$) {
                        Subscription s = subscriptions[i$];
                        if (!s.isUnsubscribed()) {
                            this.subscriptions.add(s);
                        }
                        i$++;
                    }
                    return;
                }
            }
        }
        int len$2 = subscriptions.length;
        while (i$ < len$2) {
            subscriptions[i$].unsubscribe();
            i$++;
        }
    }

    public void remove(Subscription s) {
        if (!this.unsubscribed) {
            synchronized (this) {
                if (!this.unsubscribed && this.subscriptions != null) {
                    boolean unsubscribe = this.subscriptions.remove(s);
                    if (unsubscribe) {
                        s.unsubscribe();
                    }
                }
            }
        }
    }

    public void clear() throws Throwable {
        if (this.unsubscribed) {
            return;
        }
        synchronized (this) {
            try {
                if (!this.unsubscribed && this.subscriptions != null) {
                    Collection<Subscription> unsubscribe = this.subscriptions;
                    try {
                        this.subscriptions = null;
                        unsubscribeFromAll(unsubscribe);
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    @Override // rx.Subscription
    public void unsubscribe() throws Throwable {
        if (this.unsubscribed) {
            return;
        }
        synchronized (this) {
            try {
                if (this.unsubscribed) {
                    return;
                }
                this.unsubscribed = true;
                Collection<Subscription> unsubscribe = this.subscriptions;
                try {
                    this.subscriptions = null;
                    unsubscribeFromAll(unsubscribe);
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    private static void unsubscribeFromAll(Collection<Subscription> subscriptions) {
        if (subscriptions == null) {
            return;
        }
        List<Throwable> es2 = null;
        for (Subscription s : subscriptions) {
            try {
                s.unsubscribe();
            } catch (Throwable e) {
                if (es2 == null) {
                    es2 = new ArrayList<>();
                }
                es2.add(e);
            }
        }
        Exceptions.throwIfAny(es2);
    }

    public boolean hasSubscriptions() {
        boolean z = false;
        if (this.unsubscribed) {
            return false;
        }
        synchronized (this) {
            if (!this.unsubscribed && this.subscriptions != null && !this.subscriptions.isEmpty()) {
                z = true;
            }
        }
        return z;
    }
}
