package quadrant.mokafat.points.view.profile.sections_items;

import java.util.List;
import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.get_sections.DataObjectDetails;

/* JADX INFO: loaded from: classes.dex */
public interface SectionsHomeItemsView extends BaseView {
    void hideLoading();

    void showLoading();

    void showMessage(String str);

    void showSections(List<DataObjectDetails> list);
}
