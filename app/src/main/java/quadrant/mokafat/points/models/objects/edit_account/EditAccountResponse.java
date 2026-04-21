package quadrant.mokafat.points.models.objects.edit_account;

import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class EditAccountResponse {

    @SerializedName(DataBufferSafeParcelable.DATA_FIELD)
    private DataEditAccountDetails data;

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return this.message;
    }

    public DataEditAccountDetails getData() {
        return this.data;
    }

    public void setData(DataEditAccountDetails data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
