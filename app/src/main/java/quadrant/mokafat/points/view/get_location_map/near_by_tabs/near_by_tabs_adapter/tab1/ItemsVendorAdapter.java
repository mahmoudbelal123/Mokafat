package quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1;

import android.content.Context;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.models.objects.get_branches.GetBranchesResponse;

/* JADX INFO: loaded from: classes.dex */
public class ItemsVendorAdapter extends RecyclerView.Adapter<ItemsVendorAdapter.MyViewHolder> {
    private Context context;
    private List<GetBranchesResponse> listItemsSubSections;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cityTxt;
        public TextView daysNightsTxt;
        public TextView finalPriceTxt;
        public ImageView itemImage;
        public TextView itemMainTitleTxt;
        public CardView mCardContainer;
        public TextView oldPriceTxt;
        public TextView roomTypeTxt;
        public TextView startDateTxt;
        public ImageView vendorLogo;

        public MyViewHolder(View view) {
            super(view);
            this.itemImage = (ImageView) view.findViewById(R.id.imageView2_sub_section_item_image);
            this.oldPriceTxt = (TextView) view.findViewById(R.id.textView29_old_price);
            this.vendorLogo = (ImageView) view.findViewById(R.id.imageView5_vendor_logo);
            this.mCardContainer = (CardView) view.findViewById(R.id.card_items_container);
            this.itemMainTitleTxt = (TextView) view.findViewById(R.id.textView11_sub_section_item_title);
            this.cityTxt = (TextView) view.findViewById(R.id.textView19);
            this.daysNightsTxt = (TextView) view.findViewById(R.id.textView21);
            this.roomTypeTxt = (TextView) view.findViewById(R.id.textView23);
            this.startDateTxt = (TextView) view.findViewById(R.id.textView21);
            this.finalPriceTxt = (TextView) view.findViewById(R.id.textView27);
            this.oldPriceTxt.setPaintFlags(this.oldPriceTxt.getPaintFlags() | 16);
        }
    }

    public ItemsVendorAdapter(List<GetBranchesResponse> listSubSections, Context context) {
        this.listItemsSubSections = listSubSections;
        this.context = context;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_section_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder holder, int position) {
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.listItemsSubSections.size();
    }
}
