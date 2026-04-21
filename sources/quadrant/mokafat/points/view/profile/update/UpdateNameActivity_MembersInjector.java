package quadrant.mokafat.points.view.profile.update;

import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.view.profile.edit_account.EditAccountPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class UpdateNameActivity_MembersInjector implements MembersInjector<UpdateNameActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<EditAccountPresenter> mEditAccountPresenterProvider;

    public UpdateNameActivity_MembersInjector(Provider<EditAccountPresenter> mEditAccountPresenterProvider) {
        this.mEditAccountPresenterProvider = mEditAccountPresenterProvider;
    }

    public static MembersInjector<UpdateNameActivity> create(Provider<EditAccountPresenter> mEditAccountPresenterProvider) {
        return new UpdateNameActivity_MembersInjector(mEditAccountPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(UpdateNameActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mEditAccountPresenter = this.mEditAccountPresenterProvider.get();
    }

    public static void injectMEditAccountPresenter(UpdateNameActivity instance, Provider<EditAccountPresenter> mEditAccountPresenterProvider) {
        instance.mEditAccountPresenter = mEditAccountPresenterProvider.get();
    }
}
