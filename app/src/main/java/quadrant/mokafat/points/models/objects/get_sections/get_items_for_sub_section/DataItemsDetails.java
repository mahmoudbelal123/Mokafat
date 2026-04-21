package quadrant.mokafat.points.models.objects.get_sections.get_items_for_sub_section;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class DataItemsDetails {

    @SerializedName("relationships")
    private RelationshipsDetails RelationshipsDetails;

    @SerializedName("attributes")
    private AttributesDetails attributes;

    @SerializedName("id")
    private String id;

    @SerializedName(AppMeasurement.Param.TYPE)
    private String type;

    public RelationshipsDetails getRelationshipsDetails() {
        return this.RelationshipsDetails;
    }

    public void setRelationshipsDetails(RelationshipsDetails relationshipsDetails) {
        this.RelationshipsDetails = relationshipsDetails;
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

    public AttributesDetails getAttributes() {
        return this.attributes;
    }

    public void setAttributes(AttributesDetails attributes) {
        this.attributes = attributes;
    }
}
