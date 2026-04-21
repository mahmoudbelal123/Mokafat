package quadrant.mokafat.points.models.objects.get_sections.get_items_for_sub_section;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class RelationshipsDetails {

    @SerializedName("vendor")
    private VendorDetails vendor;

    public VendorDetails getVendor() {
        return this.vendor;
    }

    public void setVendor(VendorDetails vendor) {
        this.vendor = vendor;
    }
}
