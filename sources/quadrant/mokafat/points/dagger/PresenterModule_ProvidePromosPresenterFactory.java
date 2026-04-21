package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.activities.tabs.tabs_container.promos_logic.PromosPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvidePromosPresenterFactory implements Factory<PromosPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvidePromosPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public PromosPresenter get() {
        return (PromosPresenter) Preconditions.checkNotNull(this.module.providePromosPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<PromosPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvidePromosPresenterFactory(module, contextProvider);
    }

    public static PromosPresenter proxyProvidePromosPresenter(PresenterModule instance, Context context) {
        return instance.providePromosPresenter(context);
    }
}
