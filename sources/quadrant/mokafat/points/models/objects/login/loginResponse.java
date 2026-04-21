package quadrant.mokafat.points.models.objects.login;

import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class loginResponse {

    @SerializedName(DataBufferSafeParcelable.DATA_FIELD)
    private DataObject data;

    @SerializedName("message")
    private String message;

    @SerializedName("token")
    private String token;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataObject getData() {
        return this.data;
    }

    public void setData(DataObject data) {
        this.data = data;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
