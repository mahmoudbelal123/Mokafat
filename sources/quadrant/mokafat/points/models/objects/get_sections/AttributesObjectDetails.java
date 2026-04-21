package quadrant.mokafat.points.models.objects.get_sections;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class AttributesObjectDetails {

    @SerializedName(FirebaseAnalytics.Param.CONTENT)
    private String content;

    @SerializedName("icon_gray")
    private String icon_gray;

    @SerializedName("icon_selected")
    private String icon_selected;
    public boolean isExpanded = true;

    @SerializedName("main_image")
    private String main_image;

    @SerializedName("order")
    private long order;

    @SerializedName("parent_id")
    private String parent_id;

    @SerializedName("title")
    private String title;

    @SerializedName("total_offers")
    private String total_offers;

    @SerializedName("total_vendors")
    private String total_vendors;

    public AttributesObjectDetails(String title) {
        this.title = title;
    }

    public String getTotal_offers() {
        return this.total_offers;
    }

    public void setTotal_offers(String total_offers) {
        this.total_offers = total_offers;
    }

    public String getTotal_vendors() {
        return this.total_vendors;
    }

    public void setTotal_vendors(String total_vendors) {
        this.total_vendors = total_vendors;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isExpanded() {
        return this.isExpanded;
    }

    public String getParent_id() {
        return this.parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public void setExpanded(boolean expanded) {
        this.isExpanded = expanded;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getOrder() {
        return this.order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public String getIcon_gray() {
        return this.icon_gray;
    }

    public void setIcon_gray(String icon_gray) {
        this.icon_gray = icon_gray;
    }

    public String getIcon_selected() {
        return this.icon_selected;
    }

    public void setIcon_selected(String icon_selected) {
        this.icon_selected = icon_selected;
    }

    public String getMain_image() {
        return this.main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }
}
