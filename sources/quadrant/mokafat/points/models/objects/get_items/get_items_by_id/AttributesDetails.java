package quadrant.mokafat.points.models.objects.get_items.get_items_by_id;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class AttributesDetails {

    @SerializedName("logo")
    private String logo;

    @SerializedName("titel")
    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
