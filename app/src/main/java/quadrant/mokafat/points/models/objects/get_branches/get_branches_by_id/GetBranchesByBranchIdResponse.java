package quadrant.mokafat.points.models.objects.get_branches.get_branches_by_id;

import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GetBranchesByBranchIdResponse {

    @SerializedName(DataBufferSafeParcelable.DATA_FIELD)
    private List<DataDetailsBranchesCity> data;

    public List<DataDetailsBranchesCity> getData() {
        return this.data;
    }

    public void setData(List<DataDetailsBranchesCity> data) {
        this.data = data;
    }
}
