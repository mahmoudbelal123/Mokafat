package quadrant.mokafat.points.models.objects.get_items.get_items_by_id;

import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class DataForItem {

    @SerializedName(DataBufferSafeParcelable.DATA_FIELD)
    private GetItemsByItemIdResponse data;

    public GetItemsByItemIdResponse getData() {
        return this.data;
    }

    public void setData(GetItemsByItemIdResponse data) {
        this.data = data;
    }
}
