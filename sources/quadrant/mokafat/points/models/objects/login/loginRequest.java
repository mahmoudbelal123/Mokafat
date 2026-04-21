package quadrant.mokafat.points.models.objects.login;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class loginRequest {

    @SerializedName("device")
    private String device;

    @SerializedName("device_id")
    private String device_id;

    @SerializedName("password")
    private String password;

    @SerializedName("username")
    private String username;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice() {
        return this.device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDevice_id() {
        return this.device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
