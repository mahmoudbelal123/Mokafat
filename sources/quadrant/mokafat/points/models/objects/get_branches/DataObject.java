package quadrant.mokafat.points.models.objects.get_branches;

import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataObject {

    @SerializedName(DataBufferSafeParcelable.DATA_FIELD)
    private List<GetBranchesResponse> data;

    public List<GetBranchesResponse> getData() {
        return this.data;
    }

    public void setData(List<GetBranchesResponse> data) {
        this.data = data;
    }
}
