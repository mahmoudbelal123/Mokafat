package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.login.LoginPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideMainPresenterFactory implements Factory<LoginPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideMainPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public LoginPresenter get() {
        return (LoginPresenter) Preconditions.checkNotNull(this.module.provideMainPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<LoginPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideMainPresenterFactory(module, contextProvider);
    }

    public static LoginPresenter proxyProvideMainPresenter(PresenterModule instance, Context context) {
        return instance.provideMainPresenter(context);
    }
}
