package quadrant.mokafat.points.models.objects.get_vendors.get_vendors_by_id;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.gson.annotations.SerializedName;
import quadrant.mokafat.points.models.objects.get_vendors.AttributesObjectDetails;

/* JADX INFO: loaded from: classes.dex */
public class GetVendorsByVendorIdResponse {

    @SerializedName("attributes")
    private AttributesObjectDetails attributes;

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

    public AttributesObjectDetails getAttributes() {
        return this.attributes;
    }

    public void setAttributes(AttributesObjectDetails attributes) {
        this.attributes = attributes;
    }
}
