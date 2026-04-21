package quadrant.mokafat.points.view.get_vouchers_tab_setting.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.ArrayList;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.models.objects.get_vouchers_tab.AttributesObjectDetails;
import quadrant.mokafat.points.view.get_vouchers_tab_setting.models.Item;

/* JADX INFO: loaded from: classes.dex */
public class SectionedExpandableGridAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final int VIEW_TYPE_ITEM = 2131427444;
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
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: quadrant.mokafat.points.view.get_vouchers_tab_setting.adapters.SectionedExpandableGridAdapter.1
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
        switch (holder.viewType) {
            case R.layout.item_vouchers /* 2131427444 */:
                final Item item = (Item) this.mDataArrayList.get(position);
                holder.view.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.get_vouchers_tab_setting.adapters.SectionedExpandableGridAdapter.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        SectionedExpandableGridAdapter.this.mItemClickListener.itemClicked(item);
                    }
                });
                break;
            case R.layout.layout_content_test /* 2131427445 */:
                final AttributesObjectDetails section = (AttributesObjectDetails) this.mDataArrayList.get(position);
                holder.providerTxt.setText(section.getProvider());
                holder.providerTxt.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.get_vouchers_tab_setting.adapters.SectionedExpandableGridAdapter.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        SectionedExpandableGridAdapter.this.mItemClickListener.itemClicked(section);
                    }
                });
                holder.sectionToggleButton.setChecked(section.isExpanded);
                holder.sectionToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: quadrant.mokafat.points.view.get_vouchers_tab_setting.adapters.SectionedExpandableGridAdapter.4
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        SectionedExpandableGridAdapter.this.mSectionStateChangeListener.onSectionStateChanged(section, isChecked);
                    }
                });
                break;
        }
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
        return R.layout.item_vouchers;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        TextView providerTxt;
        ToggleButton sectionToggleButton;
        View view;
        int viewType;

        public ViewHolder(View view, int viewType) {
            super(view);
            this.viewType = viewType;
            this.view = view;
            if (viewType == R.layout.item_vouchers) {
                this.providerTxt = (TextView) view.findViewById(R.id.text_provider);
                this.sectionToggleButton = (ToggleButton) view.findViewById(R.id.toggle_button_section);
            }
        }
    }
}
