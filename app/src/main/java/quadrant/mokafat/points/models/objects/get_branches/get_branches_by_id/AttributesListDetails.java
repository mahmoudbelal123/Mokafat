package quadrant.mokafat.points.models.objects.get_branches.get_branches_by_id;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class AttributesListDetails {

    @SerializedName("address")
    private String address;

    @SerializedName("city")
    private String city;

    @SerializedName("phone")
    private String phone;

    @SerializedName("working_hours")
    private String working_hours;

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
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
