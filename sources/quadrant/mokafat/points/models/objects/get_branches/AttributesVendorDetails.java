package quadrant.mokafat.points.models.objects.get_branches;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class AttributesVendorDetails {

    @SerializedName("attributes")
    private ItemsVendorDetails attributes;

    public ItemsVendorDetails getAttributes() {
        return this.attributes;
    }

    public void setAttributes(ItemsVendorDetails attributes) {
        this.attributes = attributes;
    }
}
