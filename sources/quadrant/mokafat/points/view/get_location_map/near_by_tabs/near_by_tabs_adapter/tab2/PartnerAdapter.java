package quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.helper.GlideApp;
import quadrant.mokafat.points.models.objects.get_branches.GetBranchesResponse;

/* JADX INFO: loaded from: classes.dex */
public class PartnerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<GetBranchesResponse> listItemsSubSections;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView vendorLogo;
        public TextView vendorTitle;

        public MyViewHolder(View view) {
            super(view);
            this.vendorLogo = (ImageView) view.findViewById(R.id.imageView16_vendor_logo);
            this.vendorTitle = (TextView) view.findViewById(R.id.textView30_title_vendor);
        }
    }

    public PartnerAdapter(List<GetBranchesResponse> listSubSections, Context context) {
        this.listItemsSubSections = listSubSections;
        this.context = context;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_partner, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.vendorTitle.setText(this.listItemsSubSections.get(position).getRelationships().getVendors().getAttributes().getTitle());
        GlideApp.with(this.context).load2("http://199.247.4.89/mokafat/dev/public/uploads/large/" + this.listItemsSubSections.get(position).getRelationships().getVendors().getAttributes().getLogo()).into(holder.vendorLogo);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.listItemsSubSections.size();
    }
}
