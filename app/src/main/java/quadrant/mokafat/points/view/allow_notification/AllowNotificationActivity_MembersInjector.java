package quadrant.mokafat.points.view.allow_notification;

import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.view.profile.edit_account.EditAccountPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class AllowNotificationActivity_MembersInjector implements MembersInjector<AllowNotificationActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<EditAccountPresenter> editAccountPresenterProvider;

    public AllowNotificationActivity_MembersInjector(Provider<EditAccountPresenter> editAccountPresenterProvider) {
        this.editAccountPresenterProvider = editAccountPresenterProvider;
    }

    public static MembersInjector<AllowNotificationActivity> create(Provider<EditAccountPresenter> editAccountPresenterProvider) {
        return new AllowNotificationActivity_MembersInjector(editAccountPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AllowNotificationActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.editAccountPresenter = this.editAccountPresenterProvider.get();
    }

    public static void injectEditAccountPresenter(AllowNotificationActivity instance, Provider<EditAccountPresenter> editAccountPresenterProvider) {
        instance.editAccountPresenter = editAccountPresenterProvider.get();
    }
}
