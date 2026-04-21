package quadrant.mokafat.points.models.objects.vouchers;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class AttributesObject {

    @SerializedName("code")
    private String code;

    @SerializedName("end date")
    private String end_date;

    @SerializedName("provider")
    private String provider;

    @SerializedName("redeemed_at")
    private String redeemed_at;

    @SerializedName("start date")
    private String start_date;

    @SerializedName("target")
    private String target;

    @SerializedName("used")
    private String used;

    @SerializedName(FirebaseAnalytics.Param.VALUE)
    private String value;

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getStart_date() {
        return this.start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return this.end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getUsed() {
        return this.used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getRedeemed_at() {
        return this.redeemed_at;
    }

    public void setRedeemed_at(String redeemed_at) {
        this.redeemed_at = redeemed_at;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
