package quadrant.mokafat.points.view.profile.get_profile.get_home_sections;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.helper.GlideApp;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_sections.DataObjectDetails;
import quadrant.mokafat.points.view.profile.get_profile.slides.SlidesFragment;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.SpecificSectionFragment;

/* JADX INFO: loaded from: classes.dex */
public class HomeSectionAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    Context context;
    List<DataObjectDetails> sectionsResponseList;
    SlidesFragment slidesFragment;

    public HomeSectionAdapter(Context context, SlidesFragment slidesFragment, List<DataObjectDetails> sectionsResponseList) {
        this.context = context;
        this.slidesFragment = slidesFragment;
        this.sectionsResponseList = sectionsResponseList;
        inflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.sectionsResponseList.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return Integer.valueOf(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        ImageView imageSection;
        TextView sectionTitle;

        public Holder() {
        }
    }

    @Override // android.widget.Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView = inflater.inflate(R.layout.item_section_home, (ViewGroup) null);
        holder.imageSection = (ImageView) rowView.findViewById(R.id.imageView_section_home);
        holder.sectionTitle = (TextView) rowView.findViewById(R.id.textView11_section_title);
        holder.sectionTitle.setText(this.sectionsResponseList.get(position).getAttributes().getTitle());
        GlideApp.with(this.context).load2("http://199.247.4.89/mokafat/dev/public/uploads/large/" + this.sectionsResponseList.get(position).getAttributes().getMain_image()).into(holder.imageSection);
        rowView.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.profile.get_profile.get_home_sections.HomeSectionAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Utilities.savePartner(HomeSectionAdapter.this.context, "http://199.247.4.89/mokafat/dev/public/uploads/large/" + HomeSectionAdapter.this.sectionsResponseList.get(position).getAttributes().getMain_image(), HomeSectionAdapter.this.sectionsResponseList.get(position).getAttributes().getTitle(), HomeSectionAdapter.this.sectionsResponseList.get(position).getAttributes().getTotal_offers(), HomeSectionAdapter.this.sectionsResponseList.get(position).getAttributes().getTotal_vendors());
                Utilities.SECTION_ID = HomeSectionAdapter.this.sectionsResponseList.get(position).getId();
                FragmentTransaction ft = HomeSectionAdapter.this.slidesFragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layout_container_1, new SpecificSectionFragment());
                ft.commit();
            }
        });
        return rowView;
    }
}
