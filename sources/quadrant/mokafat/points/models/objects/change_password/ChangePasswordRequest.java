package quadrant.mokafat.points.models.objects.change_password;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class ChangePasswordRequest {

    @SerializedName("old-password")
    private String old_password;

    @SerializedName("password")
    private String password;

    public String getOld_password() {
        return this.old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
