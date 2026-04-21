package quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections;

import java.util.List;
import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.get_sections.get_items_for_sub_section.DataItemsDetails;
import quadrant.mokafat.points.models.objects.get_sections.get_items_for_sub_section.GetItems;

/* JADX INFO: loaded from: classes.dex */
public interface GetItemsSubsectionsView extends BaseView {
    void hideLoading();

    void showItemsSubSections(GetItems getItems);

    void showItemsSubSectionsPage(GetItems getItems);

    void showLoading();

    void showMessage(String str);

    void showRecycle();

    void showSItemsSubSections(List<DataItemsDetails> list);

    void showSItemsVendors(List<DataItemsDetails> list);
}
