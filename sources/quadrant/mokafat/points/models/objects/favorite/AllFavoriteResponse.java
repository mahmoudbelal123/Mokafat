package quadrant.mokafat.points.models.objects.favorite;

import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AllFavoriteResponse {

    @SerializedName(DataBufferSafeParcelable.DATA_FIELD)
    private List<DataObjectDetails> data;

    @SerializedName("links")
    private LinksDetails links;

    public LinksDetails getLinks() {
        return this.links;
    }

    public void setLinks(LinksDetails links) {
        this.links = links;
    }

    public List<DataObjectDetails> getData() {
        return this.data;
    }

    public void setData(List<DataObjectDetails> data) {
        this.data = data;
    }
}
