package quadrant.mokafat.points.models.objects.login;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class VendorObject {

    @SerializedName(FirebaseAnalytics.Param.CONTENT)
    private String content;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("email")
    private String email;

    @SerializedName("id")
    private int id;

    @SerializedName("logo")
    private String logo;

    @SerializedName("meta_description")
    private String meta_description;

    @SerializedName("meta_keywords")
    private String meta_keywords;

    @SerializedName("order")
    private String order;

    @SerializedName("phone")
    private String phone;

    @SerializedName("slug")
    private String slug;

    @SerializedName("title")
    private String title;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("website")
    private String website;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getMeta_description() {
        return this.meta_description;
    }

    public void setMeta_description(String meta_description) {
        this.meta_description = meta_description;
    }

    public String getMeta_keywords() {
        return this.meta_keywords;
    }

    public void setMeta_keywords(String meta_keywords) {
        this.meta_keywords = meta_keywords;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
