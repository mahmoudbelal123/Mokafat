package quadrant.mokafat.points.models.objects.get_branches.test_get_branches;

import com.google.gson.annotations.SerializedName;
import quadrant.mokafat.points.apiClient.EndPoints;

/* JADX INFO: loaded from: classes.dex */
public class TestRelationshipsDetails {

    @SerializedName(EndPoints.GET_VENDORS)
    private TestVendorsDetails vendors;

    public TestVendorsDetails getVendors() {
        return this.vendors;
    }

    public void setVendors(TestVendorsDetails vendors) {
        this.vendors = vendors;
    }
}
