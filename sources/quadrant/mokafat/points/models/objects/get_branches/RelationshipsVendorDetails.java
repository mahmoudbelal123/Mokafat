package quadrant.mokafat.points.models.objects.get_branches;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RelationshipsVendorDetails {

    @SerializedName("items")
    private List<AttributesVendorDetails> items;

    public List<AttributesVendorDetails> getItems() {
        return this.items;
    }

    public void setItems(List<AttributesVendorDetails> items) {
        this.items = items;
    }
}
