package quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.helper.GlideApp;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_sections.get_sectons_by_id.DataListDetails;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubSectionsFragment;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals.HotDealsFragment;

/* JADX INFO: loaded from: classes.dex */
public class SubSectionsAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    boolean flag = false;
    HotDealsFragment hotDealsFragment;
    private List<DataListDetails> listSubSections;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout subSection;
        public ImageView subSectionImage;
        public TextView subSectionTitle;

        public MyViewHolder(View view) {
            super(view);
            this.subSectionImage = (ImageView) view.findViewById(R.id.imageView_sub_section);
            this.subSectionTitle = (TextView) view.findViewById(R.id.textView11_title_sub_section);
            this.subSection = (LinearLayout) view.findViewById(R.id.linear_sub_sections);
        }
    }

    public SubSectionsAdapter(List<DataListDetails> listSubSections, Context context, HotDealsFragment hotDealsFragment) {
        this.listSubSections = listSubSections;
        this.context = context;
        this.hotDealsFragment = hotDealsFragment;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_section, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (position == 0 && !this.flag) {
            this.listSubSections.get(position).setSelected(true);
            Utilities.SUB_SECTION_ID = this.listSubSections.get(position).getId();
            FragmentTransaction ft = this.hotDealsFragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_container, new GetItemsSubSectionsFragment());
            ft.commit();
            this.flag = true;
        }
        holder.subSectionImage.setSelected(this.listSubSections.get(position).isSelected());
        if (!this.listSubSections.get(position).isSelected()) {
            holder.subSectionTitle.setText(this.listSubSections.get(position).getAttributes().getTitle());
            holder.subSectionTitle.setTextColor(-3355444);
        } else {
            GlideApp.with(this.context).load2("http://199.247.4.89/mokafat/dev/public/uploads/large/" + this.listSubSections.get(position).getAttributes().getIcon_selected()).into(holder.subSectionImage);
            holder.subSectionTitle.setText(this.listSubSections.get(position).getAttributes().getTitle());
            holder.subSectionTitle.setTextColor(-1);
        }
        holder.subSection.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.SubSectionsAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SubSectionsAdapter.this.resetAll();
                ((DataListDetails) SubSectionsAdapter.this.listSubSections.get(position)).setSelected(true);
                SubSectionsAdapter.this.notifyDataSetChanged();
                Utilities.SUB_SECTION_ID = ((DataListDetails) SubSectionsAdapter.this.listSubSections.get(position)).getId();
                Utilities.FLAG_SECTIONS_HOME = 0;
                FragmentTransaction ft2 = SubSectionsAdapter.this.hotDealsFragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_container, new GetItemsSubSectionsFragment());
                ft2.commit();
            }
        });
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.listSubSections.size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetAll() {
        for (int i = 0; i < this.listSubSections.size(); i++) {
            this.listSubSections.get(i).setSelected(false);
        }
        notifyDataSetChanged();
    }
}
