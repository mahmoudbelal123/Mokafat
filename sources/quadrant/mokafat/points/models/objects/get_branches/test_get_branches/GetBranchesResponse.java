package quadrant.mokafat.points.models.objects.get_branches.test_get_branches;

import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GetBranchesResponse {

    @SerializedName(DataBufferSafeParcelable.DATA_FIELD)
    private List<ListDataObjectDetails> data;

    public List<ListDataObjectDetails> getData() {
        return this.data;
    }

    public void setData(List<ListDataObjectDetails> data) {
        this.data = data;
    }
}
