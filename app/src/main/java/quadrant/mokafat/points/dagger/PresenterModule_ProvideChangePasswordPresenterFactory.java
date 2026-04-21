package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.change_password.ChangePasswordPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideChangePasswordPresenterFactory implements Factory<ChangePasswordPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideChangePasswordPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public ChangePasswordPresenter get() {
        return (ChangePasswordPresenter) Preconditions.checkNotNull(this.module.provideChangePasswordPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ChangePasswordPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideChangePasswordPresenterFactory(module, contextProvider);
    }

    public static ChangePasswordPresenter proxyProvideChangePasswordPresenter(PresenterModule instance, Context context) {
        return instance.provideChangePasswordPresenter(context);
    }
}
