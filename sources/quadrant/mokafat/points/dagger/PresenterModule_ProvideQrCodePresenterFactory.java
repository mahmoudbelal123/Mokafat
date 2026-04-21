package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.qr_code.QrCodePresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideQrCodePresenterFactory implements Factory<QrCodePresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideQrCodePresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public QrCodePresenter get() {
        return (QrCodePresenter) Preconditions.checkNotNull(this.module.provideQrCodePresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<QrCodePresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideQrCodePresenterFactory(module, contextProvider);
    }

    public static QrCodePresenter proxyProvideQrCodePresenter(PresenterModule instance, Context context) {
        return instance.provideQrCodePresenter(context);
    }
}
