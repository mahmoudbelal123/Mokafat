package quadrant.mokafat.points.models.objects.change_password;

import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class ChangePasswordResponse {

    @SerializedName(DataBufferSafeParcelable.DATA_FIELD)
    private String data;

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
