package quadrant.mokafat.points.view.profile.get_profile.slides;

import java.util.List;
import quadrant.mokafat.points.baseClass.BaseView;
import quadrant.mokafat.points.models.objects.slides.DataObjectDetails;

/* JADX INFO: loaded from: classes.dex */
public interface SlidesView extends BaseView {
    void hideLoadingSlides();

    void showLadingSlides();

    void showMessage(String str);

    void showResponse(List<DataObjectDetails> list);
}
