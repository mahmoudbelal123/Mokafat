package quadrant.mokafat.points.view.sections.get_all_sections;

import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class SectionsFragment_MembersInjector implements MembersInjector<SectionsFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<SectionPresenter> mSectionPresenterProvider;

    public SectionsFragment_MembersInjector(Provider<SectionPresenter> mSectionPresenterProvider) {
        this.mSectionPresenterProvider = mSectionPresenterProvider;
    }

    public static MembersInjector<SectionsFragment> create(Provider<SectionPresenter> mSectionPresenterProvider) {
        return new SectionsFragment_MembersInjector(mSectionPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SectionsFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mSectionPresenter = this.mSectionPresenterProvider.get();
    }

    public static void injectMSectionPresenter(SectionsFragment instance, Provider<SectionPresenter> mSectionPresenterProvider) {
        instance.mSectionPresenter = mSectionPresenterProvider.get();
    }
}
