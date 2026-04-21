package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.log_out.LogOutPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideLogOutPresenterFactory implements Factory<LogOutPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideLogOutPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public LogOutPresenter get() {
        return (LogOutPresenter) Preconditions.checkNotNull(this.module.provideLogOutPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<LogOutPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideLogOutPresenterFactory(module, contextProvider);
    }

    public static LogOutPresenter proxyProvideLogOutPresenter(PresenterModule instance, Context context) {
        return instance.provideLogOutPresenter(context);
    }
}
