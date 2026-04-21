package quadrant.mokafat.points.models.objects.get_points;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class TierObject {

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("total_points")
    private String total_points;

    @SerializedName(FirebaseAnalytics.Param.VALUE)
    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTotal_points() {
        return this.total_points;
    }

    public void setTotal_points(String total_points) {
        this.total_points = total_points;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
