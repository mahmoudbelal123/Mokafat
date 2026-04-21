package quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1;

import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class NearByDealsFragment_MembersInjector implements MembersInjector<NearByDealsFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<GetItemsPresenter> getItemsPresenterProvider;
    private final Provider<GetNearbyDealsPresenter> nearbyDealsPresenterProvider;

    public NearByDealsFragment_MembersInjector(Provider<GetNearbyDealsPresenter> nearbyDealsPresenterProvider, Provider<GetItemsPresenter> getItemsPresenterProvider) {
        this.nearbyDealsPresenterProvider = nearbyDealsPresenterProvider;
        this.getItemsPresenterProvider = getItemsPresenterProvider;
    }

    public static MembersInjector<NearByDealsFragment> create(Provider<GetNearbyDealsPresenter> nearbyDealsPresenterProvider, Provider<GetItemsPresenter> getItemsPresenterProvider) {
        return new NearByDealsFragment_MembersInjector(nearbyDealsPresenterProvider, getItemsPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(NearByDealsFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.nearbyDealsPresenter = this.nearbyDealsPresenterProvider.get();
        instance.getItemsPresenter = this.getItemsPresenterProvider.get();
    }

    public static void injectNearbyDealsPresenter(NearByDealsFragment instance, Provider<GetNearbyDealsPresenter> nearbyDealsPresenterProvider) {
        instance.nearbyDealsPresenter = nearbyDealsPresenterProvider.get();
    }

    public static void injectGetItemsPresenter(NearByDealsFragment instance, Provider<GetItemsPresenter> getItemsPresenterProvider) {
        instance.getItemsPresenter = getItemsPresenterProvider.get();
    }
}
