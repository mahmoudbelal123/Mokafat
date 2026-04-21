package quadrant.mokafat.points.view.profile.update;

import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.view.profile.edit_account.EditAccountPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class UpdateMobileActivity_MembersInjector implements MembersInjector<UpdateMobileActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<EditAccountPresenter> mEditAccountPresenterProvider;

    public UpdateMobileActivity_MembersInjector(Provider<EditAccountPresenter> mEditAccountPresenterProvider) {
        this.mEditAccountPresenterProvider = mEditAccountPresenterProvider;
    }

    public static MembersInjector<UpdateMobileActivity> create(Provider<EditAccountPresenter> mEditAccountPresenterProvider) {
        return new UpdateMobileActivity_MembersInjector(mEditAccountPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(UpdateMobileActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mEditAccountPresenter = this.mEditAccountPresenterProvider.get();
    }

    public static void injectMEditAccountPresenter(UpdateMobileActivity instance, Provider<EditAccountPresenter> mEditAccountPresenterProvider) {
        instance.mEditAccountPresenter = mEditAccountPresenterProvider.get();
    }
}
