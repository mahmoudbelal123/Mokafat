package quadrant.mokafat.points.models.objects.get_points;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataObjectDetails {

    @SerializedName("id")
    private String id;

    @SerializedName("tier")
    private List<AttributesObject> tier;

    @SerializedName("types")
    private String type;

    public List<AttributesObject> getTier() {
        return this.tier;
    }

    public void setTier(List<AttributesObject> tier) {
        this.tier = tier;
    }

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
}
