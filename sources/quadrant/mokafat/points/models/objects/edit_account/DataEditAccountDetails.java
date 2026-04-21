package quadrant.mokafat.points.models.objects.edit_account;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class DataEditAccountDetails {

    @SerializedName("email")
    private String email;

    @SerializedName("id")
    private String id;

    @SerializedName("main_image")
    private String main_image;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("name")
    private String name;

    @SerializedName("qr_image")
    private String qr_image;

    @SerializedName("total_points")
    private String total_points;

    @SerializedName("vendor_id")
    private String vendor_id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
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

    public String getTotal_points() {
        return this.total_points;
    }

    public void setTotal_points(String total_points) {
        this.total_points = total_points;
    }

    public String getVendor_id() {
        return this.vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }
}
