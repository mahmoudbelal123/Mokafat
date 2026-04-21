package quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners;

import java.util.List;
import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.get_vendors.partnersObject;

/* JADX INFO: loaded from: classes.dex */
public interface PartnersView extends BaseView {
    void hideLoading();

    void showLoading();

    void showMessage(String str);

    void showPartners(List<partnersObject> list);
}
