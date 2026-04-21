package quadrant.mokafat.points.models.objects.get_sections.get_items_for_sub_section;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RelationshipsDetailsObject {

    @SerializedName("items")
    private List<ItemDetails> items;

    public List<ItemDetails> getItems() {
        return this.items;
    }

    public void setItems(List<ItemDetails> items) {
        this.items = items;
    }
}
