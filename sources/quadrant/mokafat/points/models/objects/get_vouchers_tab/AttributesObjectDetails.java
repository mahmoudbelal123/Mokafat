package quadrant.mokafat.points.models.objects.get_vouchers_tab;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class AttributesObjectDetails {

    @SerializedName("code")
    private String code;

    @SerializedName("end date")
    private String end_date;
    public boolean isExpanded = true;

    @SerializedName("provider")
    private String provider;

    @SerializedName("start date")
    private String start_date;

    @SerializedName("target")
    private String target;

    @SerializedName("used")
    private String used;

    @SerializedName(FirebaseAnalytics.Param.VALUE)
    private String value;

    public AttributesObjectDetails(String provider) {
        this.provider = provider;
    }

    public boolean isExpanded() {
        return this.isExpanded;
    }

    public void setExpanded(boolean expanded) {
        this.isExpanded = expanded;
    }

    public String getUsed() {
        return this.used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
