package quadrant.mokafat.points.models.objects.get_sections.get_items_for_sub_section;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class VendorAttributesDetails {

    @SerializedName("email")
    private String email;

    @SerializedName("logo")
    private String logo;

    @SerializedName("title")
    private String title;

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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
}
