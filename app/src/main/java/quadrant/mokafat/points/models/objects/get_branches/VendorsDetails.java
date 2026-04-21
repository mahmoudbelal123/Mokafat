package quadrant.mokafat.points.models.objects.get_branches;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class VendorsDetails {

    @SerializedName("attributes")
    private AttributesInsideVendorObject attributes;

    public AttributesInsideVendorObject getAttributes() {
        return this.attributes;
    }

    public void setAttributes(AttributesInsideVendorObject attributes) {
        this.attributes = attributes;
    }
}
