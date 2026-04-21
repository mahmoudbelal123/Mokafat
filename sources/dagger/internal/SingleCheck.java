package dagger.internal;

import dagger.Lazy;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class SingleCheck<T> implements Provider<T>, Lazy<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider<T> provider;

    private SingleCheck(Provider<T> provider) {
        this.provider = provider;
    }

    @Override // javax.inject.Provider
    public T get() {
        Provider<T> provider = this.provider;
        if (this.instance == UNINITIALIZED) {
            this.instance = provider.get();
            this.provider = null;
        }
        return (T) this.instance;
    }

    public static <T> Provider<T> provider(Provider<T> provider) {
        if ((provider instanceof SingleCheck) || (provider instanceof DoubleCheck)) {
            return provider;
        }
        return new SingleCheck((Provider) Preconditions.checkNotNull(provider));
    }
}
