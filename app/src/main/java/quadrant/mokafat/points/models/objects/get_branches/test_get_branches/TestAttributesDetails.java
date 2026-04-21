package quadrant.mokafat.points.models.objects.get_branches.test_get_branches;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class TestAttributesDetails {

    @SerializedName("address")
    private String address;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("phone")
    private String phone;

    @SerializedName("title")
    private String title;

    @SerializedName("vendor_id")
    private int vendor_id;

    @SerializedName("working_hours")
    private String working_hours;

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public String getAddress() {
        return this.title;
    }

    public void setAddress(String address) {
        this.title = address;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getVendor_id() {
        return this.vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWorking_hours() {
        return this.working_hours;
    }

    public void setWorking_hours(String working_hours) {
        this.working_hours = working_hours;
    }
}
