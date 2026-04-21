package quadrant.mokafat.points.models.objects.get_branches.test_get_branches;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class ListDataObjectDetails {

    @SerializedName("attributes")
    private TestAttributesDetails attributes;

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

    public TestAttributesDetails getAttributes() {
        return this.attributes;
    }

    public void setAttributes(TestAttributesDetails attributes) {
        this.attributes = attributes;
    }
}
