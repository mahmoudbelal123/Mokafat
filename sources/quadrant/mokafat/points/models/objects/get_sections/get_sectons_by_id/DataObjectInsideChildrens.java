package quadrant.mokafat.points.models.objects.get_sections.get_sectons_by_id;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataObjectInsideChildrens {

    @SerializedName("childrens")
    private List<DataListDetails> childrens;

    @SerializedName("items")
    private List<String> items;

    public List<DataListDetails> getChildrens() {
        return this.childrens;
    }

    public void setChildrens(List<DataListDetails> childrens) {
        this.childrens = childrens;
    }

    public List<String> getItems() {
        return this.items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
