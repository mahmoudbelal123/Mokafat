package quadrant.mokafat.points.view.get_vouchers_tab_setting.models;

/* JADX INFO: loaded from: classes.dex */
public class Item {
    private final int id;
    private final String logoUrl;
    private final String name;
    private String vendorId;

    public String getLogoUrl() {
        return this.logoUrl;
    }

    public Item(String logoUrl, String name, String vendorId, int id) {
        this.name = name;
        this.vendorId = vendorId;
        this.logoUrl = logoUrl;
        this.id = id;
    }

    public String getVendorId() {
        return this.vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
