package quadrant.mokafat.points.models.objects.get_vendors;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class partnersObject {

    @SerializedName("attributes")
    private AttributesPartner attributes;

    @SerializedName("id")
    private String id;

    @SerializedName(AppMeasurement.Param.TYPE)
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AttributesPartner getAttributes() {
        return this.attributes;
    }

    public void setAttributes(AttributesPartner attributes) {
        this.attributes = attributes;
    }
}
