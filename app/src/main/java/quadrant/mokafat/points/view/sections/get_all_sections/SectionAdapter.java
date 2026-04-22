package quadrant.mokafat.points.view.sections.get_all_sections;

import android.content.Context;
import androidx.fragment.app.FragmentTransaction;
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
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.SpecificSectionFragment;

/* JADX INFO: loaded from: classes.dex */
public class SectionAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    Context context;
    SectionsFragment sectionsFragment;
    List<DataObjectDetails> sectionsResponseList;

    public SectionAdapter(Context context, SectionsFragment sectionsFragment, List<DataObjectDetails> sectionsResponseList) {
        this.context = context;
        this.sectionsFragment = sectionsFragment;
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
        ImageView os_img;
        TextView sectionOffers;
        TextView sectionTitle;
        TextView sectionVendors;

        public Holder() {
        }
    }

    @Override // android.widget.Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater2 = (LayoutInflater) this.context.getSystemService("layout_inflater");
        if (convertView == null) {
            new View(this.context);
            View grid = inflater2.inflate(R.layout.item_section, (ViewGroup) null);
            ImageView os_img = (ImageView) grid.findViewById(R.id.imageView_section);
            TextView sectionTitle = (TextView) grid.findViewById(R.id.textView11_section_title);
            TextView sectionVendors = (TextView) grid.findViewById(R.id.textView16_vendors);
            TextView sectionOffers = (TextView) grid.findViewById(R.id.textView18_offers);
            sectionTitle.setText(this.sectionsResponseList.get(position).getAttributes().getTitle());
            GlideApp.with(this.context).load("http://199.247.4.89/mokafat/dev/public/uploads/large/" + this.sectionsResponseList.get(position).getAttributes().getMain_image()).into(os_img);
            sectionVendors.setText(this.sectionsResponseList.get(position).getAttributes().getTotal_vendors());
            sectionOffers.setText(this.sectionsResponseList.get(position).getAttributes().getTotal_offers());
            grid.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.sections.get_all_sections.SectionAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    Utilities.savePartner(SectionAdapter.this.context, "http://199.247.4.89/mokafat/dev/public/uploads/large/" + SectionAdapter.this.sectionsResponseList.get(position).getAttributes().getMain_image(), SectionAdapter.this.sectionsResponseList.get(position).getAttributes().getTitle(), SectionAdapter.this.sectionsResponseList.get(position).getAttributes().getTotal_offers(), SectionAdapter.this.sectionsResponseList.get(position).getAttributes().getTotal_vendors());
                    Utilities.SECTION_ID = SectionAdapter.this.sectionsResponseList.get(position).getId();
                    FragmentTransaction ft = SectionAdapter.this.sectionsFragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layout_container_1, new SpecificSectionFragment());
                    ft.commit();
                }
            });
            return grid;
        }
        return convertView;
    }
}
