package quadrant.mokafat.points.models.objects.get_branches;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class ItemsVendorDetails {

    @SerializedName(FirebaseAnalytics.Param.CONTENT)
    private String content;

    @SerializedName(FirebaseAnalytics.Param.END_DATE)
    private String end_date;

    @SerializedName("final_price")
    private String final_price;

    @SerializedName("main_image")
    private String main_image;

    @SerializedName("old_price")
    private String old_price;

    @SerializedName(FirebaseAnalytics.Param.START_DATE)
    private String start_date;

    @SerializedName("summary")
    private String summary;

    @SerializedName("title")
    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOld_price() {
        return this.old_price;
    }

    public void setOld_price(String old_price) {
        this.old_price = old_price;
    }

    public String getFinal_price() {
        return this.final_price;
    }

    public void setFinal_price(String final_price) {
        this.final_price = final_price;
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

    public String getMain_image() {
        return this.main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }
}
