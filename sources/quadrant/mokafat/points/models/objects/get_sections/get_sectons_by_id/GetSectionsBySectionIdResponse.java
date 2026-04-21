package quadrant.mokafat.points.models.objects.get_sections.get_sectons_by_id;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.gson.annotations.SerializedName;
import quadrant.mokafat.points.models.objects.get_sections.AttributesObjectDetails;

/* JADX INFO: loaded from: classes.dex */
public class GetSectionsBySectionIdResponse {

    @SerializedName("attributes")
    private AttributesObjectDetails attributes;

    @SerializedName("id")
    private String id;

    @SerializedName("relationships")
    private DataObjectInsideChildrens relationships;

    @SerializedName(AppMeasurement.Param.TYPE)
    private String type;

    public DataObjectInsideChildrens getRelationships() {
        return this.relationships;
    }

    public void setRelationships(DataObjectInsideChildrens relationships) {
        this.relationships = relationships;
    }

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
