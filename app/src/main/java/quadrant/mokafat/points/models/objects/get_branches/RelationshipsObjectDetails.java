package quadrant.mokafat.points.models.objects.get_branches;

import com.google.gson.annotations.SerializedName;
import quadrant.mokafat.points.apiClient.EndPoints;

/* JADX INFO: loaded from: classes.dex */
public class RelationshipsObjectDetails {

    @SerializedName(EndPoints.GET_VENDORS)
    private VendorsDetails vendors;

    public VendorsDetails getVendors() {
        return this.vendors;
    }

    public void setVendors(VendorsDetails vendors) {
        this.vendors = vendors;
    }
}
