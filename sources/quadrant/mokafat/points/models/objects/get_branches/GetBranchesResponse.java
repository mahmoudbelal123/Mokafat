package quadrant.mokafat.points.models.objects.get_branches;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class GetBranchesResponse {

    @SerializedName("attributes")
    private AttributesObjectDetails attributes;

    @SerializedName("id")
    private String id;

    @SerializedName("relationships")
    private RelationshipsObjectDetails relationships;

    @SerializedName(AppMeasurement.Param.TYPE)
    private String type;

    public RelationshipsObjectDetails getRelationships() {
        return this.relationships;
    }

    public void setRelationships(RelationshipsObjectDetails relationships) {
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
