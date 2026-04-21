package quadrant.mokafat.points.models.objects.get_vendors;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RelationshipsObject {

    @SerializedName("partners")
    private List<partnersObject> partners;

    public List<partnersObject> getPartners() {
        return this.partners;
    }

    public void setPartners(List<partnersObject> partners) {
        this.partners = partners;
    }
}
