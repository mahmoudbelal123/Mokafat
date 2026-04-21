package quadrant.mokafat.points.models.objects.get_items.get_items_by_id;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class VendorDetails {

    @SerializedName("attributes")
    private AttributesDetails attributes;

    public AttributesDetails getAttributes() {
        return this.attributes;
    }

    public void setAttributes(AttributesDetails attributes) {
        this.attributes = attributes;
    }
}
