package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.sections.inside_items.GetOneItemPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideGetOneItemPresenterFactory implements Factory<GetOneItemPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideGetOneItemPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public GetOneItemPresenter get() {
        return (GetOneItemPresenter) Preconditions.checkNotNull(this.module.provideGetOneItemPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<GetOneItemPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideGetOneItemPresenterFactory(module, contextProvider);
    }

    public static GetOneItemPresenter proxyProvideGetOneItemPresenter(PresenterModule instance, Context context) {
        return instance.provideGetOneItemPresenter(context);
    }
}
