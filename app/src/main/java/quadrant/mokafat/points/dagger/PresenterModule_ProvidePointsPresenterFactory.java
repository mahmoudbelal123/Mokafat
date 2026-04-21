package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.activities.tabs.tabs_container.points_logic.PointsPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvidePointsPresenterFactory implements Factory<PointsPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvidePointsPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public PointsPresenter get() {
        return (PointsPresenter) Preconditions.checkNotNull(this.module.providePointsPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<PointsPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvidePointsPresenterFactory(module, contextProvider);
    }

    public static PointsPresenter proxyProvidePointsPresenter(PresenterModule instance, Context context) {
        return instance.providePointsPresenter(context);
    }
}
