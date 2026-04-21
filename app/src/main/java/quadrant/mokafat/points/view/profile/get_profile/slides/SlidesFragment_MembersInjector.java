package quadrant.mokafat.points.view.profile.get_profile.slides;

import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.view.profile.get_profile.get_home_sections.HomeSectionPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class SlidesFragment_MembersInjector implements MembersInjector<SlidesFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<HomeSectionPresenter> mSectionPresenterProvider;
    private final Provider<SlidesPresenter> slidesPresenterProvider;

    public SlidesFragment_MembersInjector(Provider<HomeSectionPresenter> mSectionPresenterProvider, Provider<SlidesPresenter> slidesPresenterProvider) {
        this.mSectionPresenterProvider = mSectionPresenterProvider;
        this.slidesPresenterProvider = slidesPresenterProvider;
    }

    public static MembersInjector<SlidesFragment> create(Provider<HomeSectionPresenter> mSectionPresenterProvider, Provider<SlidesPresenter> slidesPresenterProvider) {
        return new SlidesFragment_MembersInjector(mSectionPresenterProvider, slidesPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SlidesFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mSectionPresenter = this.mSectionPresenterProvider.get();
        instance.slidesPresenter = this.slidesPresenterProvider.get();
    }

    public static void injectMSectionPresenter(SlidesFragment instance, Provider<HomeSectionPresenter> mSectionPresenterProvider) {
        instance.mSectionPresenter = mSectionPresenterProvider.get();
    }

    public static void injectSlidesPresenter(SlidesFragment instance, Provider<SlidesPresenter> slidesPresenterProvider) {
        instance.slidesPresenter = slidesPresenterProvider.get();
    }
}
