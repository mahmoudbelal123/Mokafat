package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.view.profile.edit_account.EditAccountPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PresenterModule_ProvideEditAccountPresenterFactory implements Factory<EditAccountPresenter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Context> contextProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideEditAccountPresenterFactory(PresenterModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public EditAccountPresenter get() {
        return (EditAccountPresenter) Preconditions.checkNotNull(this.module.provideEditAccountPresenter(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<EditAccountPresenter> create(PresenterModule module, Provider<Context> contextProvider) {
        return new PresenterModule_ProvideEditAccountPresenterFactory(module, contextProvider);
    }

    public static EditAccountPresenter proxyProvideEditAccountPresenter(PresenterModule instance, Context context) {
        return instance.provideEditAccountPresenter(context);
    }
}
