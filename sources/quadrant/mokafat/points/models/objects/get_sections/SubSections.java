package quadrant.mokafat.points.models.objects.get_sections;

import java.util.List;
import quadrant.mokafat.points.models.objects.get_sections.get_sectons_by_id.DataListDetails;

/* JADX INFO: loaded from: classes.dex */
public class SubSections {
    private List<DataListDetails> dataListDetails;
    boolean isSelected;

    public SubSections(List<DataListDetails> dataListDetails, boolean isSelected) {
        this.dataListDetails = dataListDetails;
        this.isSelected = isSelected;
    }

    public List<DataListDetails> getDataListDetails() {
        return this.dataListDetails;
    }

    public void setDataListDetails(List<DataListDetails> dataListDetails) {
        this.dataListDetails = dataListDetails;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }
}
