package quadrant.mokafat.points.models.objects.redemptions;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class AttributesObject {

    @SerializedName("earned_points")
    private String earned_points;

    @SerializedName("target")
    private String target;

    @SerializedName("total")
    private String total;

    @SerializedName(FirebaseAnalytics.Param.VALUE)
    private String value;

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTotal() {
        return this.total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getEarned_points() {
        return this.earned_points;
    }

    public void setEarned_points(String earned_points) {
        this.earned_points = earned_points;
    }
}
