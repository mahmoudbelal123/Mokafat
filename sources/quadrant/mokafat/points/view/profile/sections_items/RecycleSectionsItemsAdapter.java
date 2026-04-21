package quadrant.mokafat.points.view.profile.sections_items;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_sections.DataObjectDetails;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubSectionsFragment;

/* JADX INFO: loaded from: classes.dex */
public class RecycleSectionsItemsAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    boolean flag = false;
    SectionsHomeItemsFragment sectionsHomeItemsFragment;
    List<DataObjectDetails> sectionsResponseList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView sectionTitle;

        public MyViewHolder(View view) {
            super(view);
            this.sectionTitle = (TextView) view.findViewById(R.id.text_section_title);
        }
    }

    public RecycleSectionsItemsAdapter(SectionsHomeItemsFragment sectionsHomeItemsFragment, List<DataObjectDetails> sectionsResponseList, Context context) {
        this.sectionsResponseList = sectionsResponseList;
        this.context = context;
        this.sectionsHomeItemsFragment = sectionsHomeItemsFragment;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section_for_home_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (position == 0 && !this.flag) {
            this.sectionsResponseList.get(position).setSelected(true);
            Utilities.SUB_SECTION_ID = this.sectionsResponseList.get(position).getId();
            FragmentTransaction ft = this.sectionsHomeItemsFragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_container, new GetItemsSubSectionsFragment());
            ft.commit();
            this.flag = true;
        }
        holder.sectionTitle.setSelected(this.sectionsResponseList.get(position).isSelected());
        if (!this.sectionsResponseList.get(position).isSelected()) {
            holder.sectionTitle.setText(this.sectionsResponseList.get(position).getAttributes().getTitle());
            holder.sectionTitle.setTextColor(-3355444);
        } else {
            holder.sectionTitle.setText(this.sectionsResponseList.get(position).getAttributes().getTitle());
            holder.sectionTitle.setTextColor(-1);
        }
        holder.sectionTitle.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.profile.sections_items.RecycleSectionsItemsAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                RecycleSectionsItemsAdapter.this.resetAll();
                RecycleSectionsItemsAdapter.this.sectionsResponseList.get(position).setSelected(true);
                RecycleSectionsItemsAdapter.this.notifyDataSetChanged();
                Utilities.PARENT_ID = RecycleSectionsItemsAdapter.this.sectionsResponseList.get(position).getAttributes().getParent_id();
                Utilities.FLAG_SECTIONS_HOME = 1;
                FragmentTransaction ft2 = RecycleSectionsItemsAdapter.this.sectionsHomeItemsFragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_container, new GetItemsSubSectionsFragment());
                ft2.commit();
            }
        });
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.sectionsResponseList.size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetAll() {
        for (int i = 0; i < this.sectionsResponseList.size(); i++) {
            this.sectionsResponseList.get(i).setSelected(false);
        }
        notifyDataSetChanged();
    }
}
