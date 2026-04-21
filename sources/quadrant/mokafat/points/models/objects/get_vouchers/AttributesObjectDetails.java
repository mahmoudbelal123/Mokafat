package quadrant.mokafat.points.models.objects.get_vouchers;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class AttributesObjectDetails {

    @SerializedName("main_image")
    private String main_image;

    @SerializedName("total_points")
    private String total_points;

    public String getTotal_points() {
        return this.total_points;
    }

    public void setTotal_points(String total_points) {
        this.total_points = total_points;
    }

    public String getMain_image() {
        return this.main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }
}
