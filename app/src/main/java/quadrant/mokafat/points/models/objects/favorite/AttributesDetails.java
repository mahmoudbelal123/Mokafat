package quadrant.mokafat.points.models.objects.favorite;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class AttributesDetails {

    @SerializedName("final_price")
    private String final_price;

    @SerializedName(FirebaseAnalytics.Param.ITEM_ID)
    private String item_id;

    @SerializedName("section")
    private String section;

    @SerializedName("title")
    private String title;

    @SerializedName("vendor")
    private String vendor;

    public String getItem_id() {
        return this.item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVendor() {
        return this.vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getSection() {
        return this.section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getFinal_price() {
        return this.final_price;
    }

    public void setFinal_price(String final_price) {
        this.final_price = final_price;
    }
}
