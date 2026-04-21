package quadrant.mokafat.points.models.objects.get_items.get_items_by_id;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class RelationShipsDetails {

    @SerializedName("vendor")
    private VendorDetails vendor;

    public VendorDetails getVendor() {
        return this.vendor;
    }

    public void setVendor(VendorDetails vendor) {
        this.vendor = vendor;
    }
}
