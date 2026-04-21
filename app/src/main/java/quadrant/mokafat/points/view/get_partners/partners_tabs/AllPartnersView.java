package quadrant.mokafat.points.view.get_partners.partners_tabs;

import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.get_sections.GetSectionsResponse;

/* JADX INFO: loaded from: classes.dex */
public interface AllPartnersView extends BaseView {
    void showMessage(String str);

    void showResponse(GetSectionsResponse getSectionsResponse);
}
