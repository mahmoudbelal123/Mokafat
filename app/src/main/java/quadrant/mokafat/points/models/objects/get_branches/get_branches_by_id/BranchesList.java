package quadrant.mokafat.points.models.objects.get_branches.get_branches_by_id;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class BranchesList {

    @SerializedName("attributes")
    private AttributesListDetails attributes;

    public AttributesListDetails getAttributes() {
        return this.attributes;
    }

    public void setAttributes(AttributesListDetails attributes) {
        this.attributes = attributes;
    }

    public BranchesList(AttributesListDetails attributes) {
        this.attributes = attributes;
    }
}
