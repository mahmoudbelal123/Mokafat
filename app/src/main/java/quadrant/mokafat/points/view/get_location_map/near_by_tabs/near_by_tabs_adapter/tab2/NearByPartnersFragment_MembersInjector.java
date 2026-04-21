package quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab2;

import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class NearByPartnersFragment_MembersInjector implements MembersInjector<NearByPartnersFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<GetNearbyPartnersPresenter> nearbyDealsPresenterProvider;

    public NearByPartnersFragment_MembersInjector(Provider<GetNearbyPartnersPresenter> nearbyDealsPresenterProvider) {
        this.nearbyDealsPresenterProvider = nearbyDealsPresenterProvider;
    }

    public static MembersInjector<NearByPartnersFragment> create(Provider<GetNearbyPartnersPresenter> nearbyDealsPresenterProvider) {
        return new NearByPartnersFragment_MembersInjector(nearbyDealsPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(NearByPartnersFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.nearbyDealsPresenter = this.nearbyDealsPresenterProvider.get();
    }

    public static void injectNearbyDealsPresenter(NearByPartnersFragment instance, Provider<GetNearbyPartnersPresenter> nearbyDealsPresenterProvider) {
        instance.nearbyDealsPresenter = nearbyDealsPresenterProvider.get();
    }
}
