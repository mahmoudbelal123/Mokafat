package quadrant.mokafat.points.models.objects.edit_account;

import com.google.gson.annotations.SerializedName;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class EditAccountRequest {

    @SerializedName("email")
    private String email;

    @SerializedName("main_image")
    private File main_image;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("name")
    private String name;

    @SerializedName("push_notification")
    private boolean push_notification;

    @SerializedName("receive_new_offers")
    private boolean receive_new_offers;

    public boolean isPush_notification() {
        return this.push_notification;
    }

    public void setPush_notification(boolean push_notification) {
        this.push_notification = push_notification;
    }

    public boolean isReceive_new_offers() {
        return this.receive_new_offers;
    }

    public void setReceive_new_offers(boolean receive_new_offers) {
        this.receive_new_offers = receive_new_offers;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public File getMain_image() {
        return this.main_image;
    }

    public void setMain_image(File main_image) {
        this.main_image = main_image;
    }
}
