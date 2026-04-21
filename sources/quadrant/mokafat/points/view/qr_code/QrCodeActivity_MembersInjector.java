package quadrant.mokafat.points.view.qr_code;

import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class QrCodeActivity_MembersInjector implements MembersInjector<QrCodeActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<QrCodePresenter> mQrCodePresenterProvider;

    public QrCodeActivity_MembersInjector(Provider<QrCodePresenter> mQrCodePresenterProvider) {
        this.mQrCodePresenterProvider = mQrCodePresenterProvider;
    }

    public static MembersInjector<QrCodeActivity> create(Provider<QrCodePresenter> mQrCodePresenterProvider) {
        return new QrCodeActivity_MembersInjector(mQrCodePresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(QrCodeActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mQrCodePresenter = this.mQrCodePresenterProvider.get();
    }

    public static void injectMQrCodePresenter(QrCodeActivity instance, Provider<QrCodePresenter> mQrCodePresenterProvider) {
        instance.mQrCodePresenter = mQrCodePresenterProvider.get();
    }
}
