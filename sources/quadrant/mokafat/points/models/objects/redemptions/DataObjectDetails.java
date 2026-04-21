package quadrant.mokafat.points.models.objects.redemptions;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class DataObjectDetails {

    @SerializedName("attributes")
    private AttributesObject attributes;

    @SerializedName("id")
    private String id;

    @SerializedName(AppMeasurement.Param.TYPE)
    private String type;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AttributesObject getAttributes() {
        return this.attributes;
    }

    public void setAttributes(AttributesObject attributes) {
        this.attributes = attributes;
    }
}
