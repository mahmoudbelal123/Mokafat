package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.profile.get_profile.ProfilePresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideProfilePresenterFactory implements Factory<ProfilePresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideProfilePresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public ProfilePresenter get() {
        return (ProfilePresenter) Preconditions.checkNotNull(this.module.provideProfilePresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ProfilePresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideProfilePresenterFactory(module, contextProvider);
    }

    public static ProfilePresenter proxyProvideProfilePresenter(PresenterModule instance, Context context) {
        return instance.provideProfilePresenter(context);
    }
}
