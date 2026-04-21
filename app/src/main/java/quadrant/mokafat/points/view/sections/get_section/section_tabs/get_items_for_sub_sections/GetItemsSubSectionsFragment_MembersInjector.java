package quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections;

import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class GetItemsSubSectionsFragment_MembersInjector implements MembersInjector<GetItemsSubSectionsFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<GetItemsSubSectionsPresenter> getItemsSubSectionsPresenterProvider;

    public GetItemsSubSectionsFragment_MembersInjector(Provider<GetItemsSubSectionsPresenter> getItemsSubSectionsPresenterProvider) {
        this.getItemsSubSectionsPresenterProvider = getItemsSubSectionsPresenterProvider;
    }

    public static MembersInjector<GetItemsSubSectionsFragment> create(Provider<GetItemsSubSectionsPresenter> getItemsSubSectionsPresenterProvider) {
        return new GetItemsSubSectionsFragment_MembersInjector(getItemsSubSectionsPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(GetItemsSubSectionsFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.getItemsSubSectionsPresenter = this.getItemsSubSectionsPresenterProvider.get();
    }

    public static void injectGetItemsSubSectionsPresenter(GetItemsSubSectionsFragment instance, Provider<GetItemsSubSectionsPresenter> getItemsSubSectionsPresenterProvider) {
        instance.getItemsSubSectionsPresenter = getItemsSubSectionsPresenterProvider.get();
    }
}
