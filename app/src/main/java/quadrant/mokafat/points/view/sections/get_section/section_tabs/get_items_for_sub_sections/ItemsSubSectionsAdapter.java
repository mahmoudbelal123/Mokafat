package quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections;

import android.content.Context;
import android.content.Intent;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.helper.GlideApp;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_sections.get_items_for_sub_section.DataItemsDetails;
import quadrant.mokafat.points.view.sections.inside_items.ItemsInsideSubsectionsActivity;

/* JADX INFO: loaded from: classes.dex */
public class ItemsSubSectionsAdapter extends RecyclerView.Adapter<ItemsSubSectionsAdapter.MyViewHolder> {
    private Context context;
    View itemView;
    private List<DataItemsDetails> listItemsSubSections;

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
            this.roomTypeTxt = (TextView) view.findViewById(R.id.textView23);
            this.startDateTxt = (TextView) view.findViewById(R.id.textView21);
            this.finalPriceTxt = (TextView) view.findViewById(R.id.textView27);
            this.oldPriceTxt.setPaintFlags(this.oldPriceTxt.getPaintFlags() | 16);
        }
    }

    public ItemsSubSectionsAdapter(List<DataItemsDetails> getItemsList, Context context) {
        this.listItemsSubSections = getItemsList;
        this.context = context;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_section_items2, parent, false);
        return new MyViewHolder(this.itemView);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        GlideApp.with(this.context).load("http://199.247.4.89/mokafat/dev/public/uploads/large/" + this.listItemsSubSections.get(position).getRelationshipsDetails().getVendor().getAttributes().getLogo()).into(holder.vendorLogo);
        holder.roomTypeTxt.setText(this.listItemsSubSections.get(position).getAttributes().getContent());
        holder.cityTxt.setText(this.listItemsSubSections.get(position).getAttributes().getSummary());
        holder.finalPriceTxt.setText(this.listItemsSubSections.get(position).getAttributes().getFinal_price());
        holder.startDateTxt.setText(this.listItemsSubSections.get(position).getAttributes().getEnd_date());
        holder.oldPriceTxt.setText(this.listItemsSubSections.get(position).getAttributes().getOld_price());
        GlideApp.with(this.context).load("http://199.247.4.89/mokafat/dev/public/uploads/large/" + this.listItemsSubSections.get(position).getAttributes().getMain_image()).into(holder.itemImage);
        holder.itemMainTitleTxt.setText(this.listItemsSubSections.get(position).getAttributes().getTitle());
        holder.mCardContainer.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.ItemsSubSectionsAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent intent = new Intent(ItemsSubSectionsAdapter.this.context, (Class<?>) ItemsInsideSubsectionsActivity.class);
                intent.putExtra(FirebaseAnalytics.Param.ITEM_ID, ((DataItemsDetails) ItemsSubSectionsAdapter.this.listItemsSubSections.get(position)).getId());
                intent.putExtra("favorite", ((DataItemsDetails) ItemsSubSectionsAdapter.this.listItemsSubSections.get(position)).getAttributes().isFavorite());
                Utilities.VENDOR_ID = ((DataItemsDetails) ItemsSubSectionsAdapter.this.listItemsSubSections.get(position)).getRelationshipsDetails().getVendor().getId();
                ItemsSubSectionsAdapter.this.context.startActivity(intent);
            }
        });
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.listItemsSubSections.size();
    }
}
