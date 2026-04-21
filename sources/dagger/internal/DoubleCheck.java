package dagger.internal;

import dagger.Lazy;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class DoubleCheck<T> implements Provider<T>, Lazy<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider<T> provider;

    private DoubleCheck(Provider<T> provider) {
        this.provider = provider;
    }

    @Override // javax.inject.Provider
    public T get() {
        T t = (T) this.instance;
        if (t == UNINITIALIZED) {
            synchronized (this) {
                t = this.instance;
                if (t == UNINITIALIZED) {
                    t = this.provider.get();
                    Object obj = this.instance;
                    if (obj != UNINITIALIZED && obj != t) {
                        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj + " & " + t + ". This is likely due to a circular dependency.");
                    }
                    this.instance = t;
                    this.provider = null;
                }
            }
        }
        return (T) t;
    }

    public static <T> Provider<T> provider(Provider<T> delegate) {
        Preconditions.checkNotNull(delegate);
        if (delegate instanceof DoubleCheck) {
            return delegate;
        }
        return new DoubleCheck(delegate);
    }

    public static <T> Lazy<T> lazy(Provider<T> provider) {
        if (provider instanceof Lazy) {
            Lazy<T> lazy = (Lazy) provider;
            return lazy;
        }
        return new DoubleCheck((Provider) Preconditions.checkNotNull(provider));
    }
}
