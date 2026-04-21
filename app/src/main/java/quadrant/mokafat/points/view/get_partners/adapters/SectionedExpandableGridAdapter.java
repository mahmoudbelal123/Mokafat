package quadrant.mokafat.points.view.get_partners.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.ArrayList;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.helper.GlideApp;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_sections.AttributesObjectDetails;
import quadrant.mokafat.points.view.get_vouchers_tab_setting.models.Item;
import quadrant.mokafat.points.view.single_partner_details.SinglePartnerActivity;

/* JADX INFO: loaded from: classes.dex */
public class SectionedExpandableGridAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final int VIEW_TYPE_ITEM = 2131427434;
    private static final int VIEW_TYPE_SECTION = 2131427445;
    private final Context mContext;
    private ArrayList<Object> mDataArrayList;
    private final ItemClickListener mItemClickListener;
    private final SectionStateChangeListener mSectionStateChangeListener;

    public SectionedExpandableGridAdapter(Context context, ArrayList<Object> dataArrayList, final GridLayoutManager gridLayoutManager, ItemClickListener itemClickListener, SectionStateChangeListener sectionStateChangeListener) {
        this.mContext = context;
        this.mItemClickListener = itemClickListener;
        this.mSectionStateChangeListener = sectionStateChangeListener;
        this.mDataArrayList = dataArrayList;
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: quadrant.mokafat.points.view.get_partners.adapters.SectionedExpandableGridAdapter.1
            @Override // android.support.v7.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int position) {
                if (SectionedExpandableGridAdapter.this.isSection(position)) {
                    return gridLayoutManager.getSpanCount();
                }
                return 1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSection(int position) {
        return this.mDataArrayList.get(position) instanceof AttributesObjectDetails;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.mContext).inflate(viewType, parent, false), viewType);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        int i = holder.viewType;
        if (i != R.layout.item_partner_section) {
            if (i == R.layout.layout_content_test) {
                final AttributesObjectDetails section = (AttributesObjectDetails) this.mDataArrayList.get(position);
                holder.titleTxt.setText(section.getTitle());
                holder.titleTxt.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.get_partners.adapters.SectionedExpandableGridAdapter.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        SectionedExpandableGridAdapter.this.mItemClickListener.itemClicked(section);
                    }
                });
                holder.sectionToggleButton.setChecked(section.isExpanded);
                holder.sectionToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: quadrant.mokafat.points.view.get_partners.adapters.SectionedExpandableGridAdapter.5
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        SectionedExpandableGridAdapter.this.mSectionStateChangeListener.onSectionStateChanged(section, isChecked);
                    }
                });
                return;
            }
            return;
        }
        final Item item = (Item) this.mDataArrayList.get(position);
        GlideApp.with(this.mContext).load2("http://199.247.4.89/mokafat/dev/public/uploads/large/" + item.getLogoUrl()).into(holder.partnerImage);
        holder.titleTxt.setText(item.getName());
        holder.view.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.get_partners.adapters.SectionedExpandableGridAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SectionedExpandableGridAdapter.this.mItemClickListener.itemClicked(item);
            }
        });
        holder.partnerImage.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.get_partners.adapters.SectionedExpandableGridAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent intent = new Intent(SectionedExpandableGridAdapter.this.mContext, (Class<?>) SinglePartnerActivity.class);
                Utilities.VENDOR_ID = item.getVendorId();
                intent.putExtra("partner_title", item.getName());
                intent.putExtra("partner_logo", item.getLogoUrl());
                SectionedExpandableGridAdapter.this.mContext.startActivity(intent);
            }
        });
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mDataArrayList.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        if (isSection(position)) {
            return R.layout.layout_content_test;
        }
        return R.layout.item_partner_section;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView partnerImage;
        ToggleButton sectionToggleButton;
        TextView titleTxt;
        View view;
        int viewType;

        public ViewHolder(View view, int viewType) {
            super(view);
            this.viewType = viewType;
            this.view = view;
            if (viewType == R.layout.item_partner_section) {
                this.titleTxt = (TextView) view.findViewById(R.id.text_partner_name);
                this.partnerImage = (ImageView) view.findViewById(R.id.imageView_partner_logo);
            } else {
                this.titleTxt = (TextView) view.findViewById(R.id.text_section);
                this.sectionToggleButton = (ToggleButton) view.findViewById(R.id.toggle_button_section);
            }
        }
    }
}
