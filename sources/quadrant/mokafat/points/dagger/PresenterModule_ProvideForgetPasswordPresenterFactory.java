package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.forget_password.ForgetPasswordPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideForgetPasswordPresenterFactory implements Factory<ForgetPasswordPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideForgetPasswordPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public ForgetPasswordPresenter get() {
        return (ForgetPasswordPresenter) Preconditions.checkNotNull(this.module.provideForgetPasswordPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ForgetPasswordPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideForgetPasswordPresenterFactory(module, contextProvider);
    }

    public static ForgetPasswordPresenter proxyProvideForgetPasswordPresenter(PresenterModule instance, Context context) {
        return instance.provideForgetPasswordPresenter(context);
    }
}
