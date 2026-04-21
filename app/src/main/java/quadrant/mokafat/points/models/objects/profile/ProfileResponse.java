package quadrant.mokafat.points.models.objects.profile;

import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class ProfileResponse {

    @SerializedName(DataBufferSafeParcelable.DATA_FIELD)
    private ProfileObject data;

    public ProfileObject getData() {
        return this.data;
    }

    public void setData(ProfileObject data) {
        this.data = data;
    }
}
