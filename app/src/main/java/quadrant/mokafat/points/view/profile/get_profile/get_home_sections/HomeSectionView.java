package quadrant.mokafat.points.view.profile.get_profile.get_home_sections;

import java.util.List;
import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.get_sections.DataObjectDetails;

/* JADX INFO: loaded from: classes.dex */
public interface HomeSectionView extends BaseView {
    void hideLoading();

    void showLoading();

    void showMessage(String str);

    void showSections(List<DataObjectDetails> list);
}
