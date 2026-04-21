package quadrant.mokafat.points.models.objects.login;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class DataObject {

    @SerializedName("account_id")
    private int account_id;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("email")
    private String email;

    @SerializedName("id")
    private int id;

    @SerializedName("main_image")
    private String main_image;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("name")
    private String name;

    @SerializedName("qr_image")
    private String qr_image;

    @SerializedName("role")
    private String role;

    @SerializedName("total_points")
    private String total_points;

    @SerializedName("vendor")
    private VendorObject vendor;

    @SerializedName("vendor_id")
    private int vendor_id;

    public String getTotal_points() {
        return this.total_points;
    }

    public void setTotal_points(String total_points) {
        this.total_points = total_points;
    }

    public int getId() {
        return this.id;
    }

    public VendorObject getVendor() {
        return this.vendor;
    }

    public void setVendor(VendorObject vendor) {
        this.vendor = vendor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMain_image() {
        return this.main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public String getQr_image() {
        return this.qr_image;
    }

    public void setQr_image(String qr_image) {
        this.qr_image = qr_image;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAccount_id() {
        return this.account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getVendor_id() {
        return this.vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
