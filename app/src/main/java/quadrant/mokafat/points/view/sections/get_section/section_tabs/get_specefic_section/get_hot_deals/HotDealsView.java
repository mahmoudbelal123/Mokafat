package quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals;

import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.get_sections.get_sectons_by_id.GetSectionsBySectionIdResponse;

/* JADX INFO: loaded from: classes.dex */
public interface HotDealsView extends BaseView {
    void hideLoading();

    void showLoading();

    void showMessage(String str);

    void showSubSections(GetSectionsBySectionIdResponse getSectionsBySectionIdResponse);
}
