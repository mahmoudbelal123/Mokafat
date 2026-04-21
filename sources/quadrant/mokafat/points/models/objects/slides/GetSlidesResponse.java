package quadrant.mokafat.points.models.objects.slides;

import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GetSlidesResponse {

    @SerializedName(DataBufferSafeParcelable.DATA_FIELD)
    private List<DataObjectDetails> data;

    public List<DataObjectDetails> getData() {
        return this.data;
    }

    public void setData(List<DataObjectDetails> data) {
        this.data = data;
    }
}
