package quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab1;

import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubSectionsPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class SingleHotDealsFragment_MembersInjector implements MembersInjector<SingleHotDealsFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<GetItemsSubSectionsPresenter> getItemsSubSectionsPresenterProvider;

    public SingleHotDealsFragment_MembersInjector(Provider<GetItemsSubSectionsPresenter> getItemsSubSectionsPresenterProvider) {
        this.getItemsSubSectionsPresenterProvider = getItemsSubSectionsPresenterProvider;
    }

    public static MembersInjector<SingleHotDealsFragment> create(Provider<GetItemsSubSectionsPresenter> getItemsSubSectionsPresenterProvider) {
        return new SingleHotDealsFragment_MembersInjector(getItemsSubSectionsPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SingleHotDealsFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.getItemsSubSectionsPresenter = this.getItemsSubSectionsPresenterProvider.get();
    }

    public static void injectGetItemsSubSectionsPresenter(SingleHotDealsFragment instance, Provider<GetItemsSubSectionsPresenter> getItemsSubSectionsPresenterProvider) {
        instance.getItemsSubSectionsPresenter = getItemsSubSectionsPresenterProvider.get();
    }
}
