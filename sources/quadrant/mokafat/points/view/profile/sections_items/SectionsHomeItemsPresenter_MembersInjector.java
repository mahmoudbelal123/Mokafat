package quadrant.mokafat.points.view.profile.sections_items;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.apiClient.ApiInterface;

/* JADX INFO: loaded from: classes.dex */
public final class SectionsHomeItemsPresenter_MembersInjector implements MembersInjector<SectionsHomeItemsPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<ApiInterface> mApiInterfaceProvider;
    private final Provider<Context> mContextProvider;

    public SectionsHomeItemsPresenter_MembersInjector(Provider<ApiInterface> mApiInterfaceProvider, Provider<Context> mContextProvider) {
        this.mApiInterfaceProvider = mApiInterfaceProvider;
        this.mContextProvider = mContextProvider;
    }

    public static MembersInjector<SectionsHomeItemsPresenter> create(Provider<ApiInterface> mApiInterfaceProvider, Provider<Context> mContextProvider) {
        return new SectionsHomeItemsPresenter_MembersInjector(mApiInterfaceProvider, mContextProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SectionsHomeItemsPresenter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mApiInterface = this.mApiInterfaceProvider.get();
        instance.mContext = this.mContextProvider.get();
    }

    public static void injectMApiInterface(SectionsHomeItemsPresenter instance, Provider<ApiInterface> mApiInterfaceProvider) {
        instance.mApiInterface = mApiInterfaceProvider.get();
    }

    public static void injectMContext(SectionsHomeItemsPresenter instance, Provider<Context> mContextProvider) {
        instance.mContext = mContextProvider.get();
    }
}
