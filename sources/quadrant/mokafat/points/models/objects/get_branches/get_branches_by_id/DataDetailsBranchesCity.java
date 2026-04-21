package quadrant.mokafat.points.models.objects.get_branches.get_branches_by_id;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataDetailsBranchesCity {

    @SerializedName("branches")
    private List<BranchesList> branches;

    @SerializedName("city")
    private String city;

    public DataDetailsBranchesCity(List<BranchesList> branchesList) {
        this.branches = branchesList;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<BranchesList> getBranches() {
        return this.branches;
    }

    public void setBranches(List<BranchesList> branches) {
        this.branches = branches;
    }
}
