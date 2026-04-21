package quadrant.mokafat.points.models.objects.get_sections.get_items_for_sub_section;

import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GetItems {

    @SerializedName(DataBufferSafeParcelable.DATA_FIELD)
    private List<DataItemsDetails> data;

    @SerializedName("links")
    private LinksDetails links;

    public List<DataItemsDetails> getData() {
        return this.data;
    }

    public void setData(List<DataItemsDetails> data) {
        this.data = data;
    }

    public LinksDetails getLinks() {
        return this.links;
    }

    public void setLinks(LinksDetails links) {
        this.links = links;
    }
}
