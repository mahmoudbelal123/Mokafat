package quadrant.mokafat.points.view.profile.favorite;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.apiClient.ApiInterface;

/* JADX INFO: loaded from: classes.dex */
public final class FavoritesPresenter_MembersInjector implements MembersInjector<FavoritesPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<ApiInterface> mApiInterfaceProvider;
    private final Provider<Context> mContextProvider;

    public FavoritesPresenter_MembersInjector(Provider<ApiInterface> mApiInterfaceProvider, Provider<Context> mContextProvider) {
        this.mApiInterfaceProvider = mApiInterfaceProvider;
        this.mContextProvider = mContextProvider;
    }

    public static MembersInjector<FavoritesPresenter> create(Provider<ApiInterface> mApiInterfaceProvider, Provider<Context> mContextProvider) {
        return new FavoritesPresenter_MembersInjector(mApiInterfaceProvider, mContextProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(FavoritesPresenter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mApiInterface = this.mApiInterfaceProvider.get();
        instance.mContext = this.mContextProvider.get();
    }

    public static void injectMApiInterface(FavoritesPresenter instance, Provider<ApiInterface> mApiInterfaceProvider) {
        instance.mApiInterface = mApiInterfaceProvider.get();
    }

    public static void injectMContext(FavoritesPresenter instance, Provider<Context> mContextProvider) {
        instance.mContext = mContextProvider.get();
    }
}
