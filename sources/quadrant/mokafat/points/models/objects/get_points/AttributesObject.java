package quadrant.mokafat.points.models.objects.get_points;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class AttributesObject {

    @SerializedName("attributes")
    private TierObject attributes;

    public TierObject getAttributes() {
        return this.attributes;
    }

    public void setAttributes(TierObject attributes) {
        this.attributes = attributes;
    }
}
