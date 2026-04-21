package quadrant.mokafat.points.models.objects.slides;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class AttributesObjectDetails {

    @SerializedName("main_image")
    private String main_image;

    @SerializedName("title")
    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMain_image() {
        return this.main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }
}
