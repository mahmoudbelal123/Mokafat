package quadrant.mokafat.points.models.objects.get_sections;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class PartnerDetails {

    @SerializedName("attributes")
    private AttributesPartnerDetails attributes;

    @SerializedName("id")
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AttributesPartnerDetails getAttributes() {
        return this.attributes;
    }

    public void setAttributes(AttributesPartnerDetails attributes) {
        this.attributes = attributes;
    }
}
