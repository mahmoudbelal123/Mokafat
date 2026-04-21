package quadrant.mokafat.points.view.activities.tabs;

import dagger.MembersInjector;
import javax.inject.Provider;
import quadrant.mokafat.points.view.activities.tabs.tabs_container.points_logic.PointsPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class PointsFragment_MembersInjector implements MembersInjector<PointsFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<PointsPresenter> pointsPresenterProvider;

    public PointsFragment_MembersInjector(Provider<PointsPresenter> pointsPresenterProvider) {
        this.pointsPresenterProvider = pointsPresenterProvider;
    }

    public static MembersInjector<PointsFragment> create(Provider<PointsPresenter> pointsPresenterProvider) {
        return new PointsFragment_MembersInjector(pointsPresenterProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(PointsFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.pointsPresenter = this.pointsPresenterProvider.get();
    }

    public static void injectPointsPresenter(PointsFragment instance, Provider<PointsPresenter> pointsPresenterProvider) {
        instance.pointsPresenter = pointsPresenterProvider.get();
    }
}
