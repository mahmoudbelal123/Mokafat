package quadrant.mokafat.points.view.activities.tabs.tabs_container;

import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.view.sections.get_all_sections.SectionPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class ActivitiesFragment_MembersInjector implements MembersInjector<ActivitiesFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<SectionPresenter> mSectionPresenterProvider;

    public ActivitiesFragment_MembersInjector(Provider<SectionPresenter> mSectionPresenterProvider) {
        this.mSectionPresenterProvider = mSectionPresenterProvider;
    }

    public static MembersInjector<ActivitiesFragment> create(Provider<SectionPresenter> mSectionPresenterProvider) {
        return new ActivitiesFragment_MembersInjector(mSectionPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ActivitiesFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mSectionPresenter = this.mSectionPresenterProvider.get();
    }

    public static void injectMSectionPresenter(ActivitiesFragment instance, Provider<SectionPresenter> mSectionPresenterProvider) {
        instance.mSectionPresenter = mSectionPresenterProvider.get();
    }
}
