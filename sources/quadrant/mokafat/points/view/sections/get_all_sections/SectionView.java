package quadrant.mokafat.points.view.sections.get_all_sections;

import java.util.List;
import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.get_sections.DataObjectDetails;

/* JADX INFO: loaded from: classes.dex */
public interface SectionView extends BaseView {
    void showMessage(String str);

    void showSections(List<DataObjectDetails> list);
}
