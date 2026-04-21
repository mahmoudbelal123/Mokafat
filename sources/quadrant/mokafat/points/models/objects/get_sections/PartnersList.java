package quadrant.mokafat.points.models.objects.get_sections;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PartnersList {

    @SerializedName("partners")
    private List<PartnerDetails> partners;

    public List<PartnerDetails> getPartners() {
        return this.partners;
    }

    public void setPartners(List<PartnerDetails> partners) {
        this.partners = partners;
    }
}
