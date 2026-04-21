package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubSectionsPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideGetItemsSubsectionsPresenterFactory implements Factory<GetItemsSubSectionsPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideGetItemsSubsectionsPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public GetItemsSubSectionsPresenter get() {
        return (GetItemsSubSectionsPresenter) Preconditions.checkNotNull(this.module.provideGetItemsSubsectionsPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<GetItemsSubSectionsPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideGetItemsSubsectionsPresenterFactory(module, contextProvider);
    }

    public static GetItemsSubSectionsPresenter proxyProvideGetItemsSubsectionsPresenter(PresenterModule instance, Context context) {
        return instance.provideGetItemsSubsectionsPresenter(context);
    }
}
