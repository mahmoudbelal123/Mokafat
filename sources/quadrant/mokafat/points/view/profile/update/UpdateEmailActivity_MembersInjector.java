package quadrant.mokafat.points.view.profile.update;

import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.view.profile.edit_account.EditAccountPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class UpdateEmailActivity_MembersInjector implements MembersInjector<UpdateEmailActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<EditAccountPresenter> mEditAccountPresenterProvider;

    public UpdateEmailActivity_MembersInjector(Provider<EditAccountPresenter> mEditAccountPresenterProvider) {
        this.mEditAccountPresenterProvider = mEditAccountPresenterProvider;
    }

    public static MembersInjector<UpdateEmailActivity> create(Provider<EditAccountPresenter> mEditAccountPresenterProvider) {
        return new UpdateEmailActivity_MembersInjector(mEditAccountPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(UpdateEmailActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mEditAccountPresenter = this.mEditAccountPresenterProvider.get();
    }

    public static void injectMEditAccountPresenter(UpdateEmailActivity instance, Provider<EditAccountPresenter> mEditAccountPresenterProvider) {
        instance.mEditAccountPresenter = mEditAccountPresenterProvider.get();
    }
}
