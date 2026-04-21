package quadrant.mokafat.points.models.objects.forget_password;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class ForgetPasswordRequest {

    @SerializedName("username")
    private String username;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
