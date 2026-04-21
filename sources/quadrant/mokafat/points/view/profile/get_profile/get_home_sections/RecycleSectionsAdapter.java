package quadrant.mokafat.points.view.profile.get_profile.get_home_sections;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import java.util.List;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_sections.DataObjectDetails;
import quadrant.mokafat.points.view.profile.get_profile.slides.SlidesFragment;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.SpecificSectionFragment;

/* JADX INFO: loaded from: classes.dex */
public class RecycleSectionsAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    List<DataObjectDetails> sectionsResponseList;
    SlidesFragment slidesFragment;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageSection;
        public ProgressBar progressBar;
        public TextView sectionTitle;

        public MyViewHolder(View view) {
            super(view);
            this.imageSection = (ImageView) view.findViewById(R.id.imageView_section_home);
            this.sectionTitle = (TextView) view.findViewById(R.id.text_section_title);
            this.progressBar = (ProgressBar) view.findViewById(R.id.progress_section_load);
        }
    }

    public RecycleSectionsAdapter(SlidesFragment slidesFragment, List<DataObjectDetails> sectionsResponseList, Context context) {
        this.sectionsResponseList = sectionsResponseList;
        this.context = context;
        this.slidesFragment = slidesFragment;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section_home, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Glide.with(this.context).load2("http://199.247.4.89/mokafat/dev/public/uploads/large/" + this.sectionsResponseList.get(position).getAttributes().getMain_image()).listener(new RequestListener<Drawable>() { // from class: quadrant.mokafat.points.view.profile.get_profile.get_home_sections.RecycleSectionsAdapter.1
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.progressBar.setVisibility(8);
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.progressBar.setVisibility(8);
                return false;
            }
        }).into(holder.imageSection);
        holder.sectionTitle.setText(this.sectionsResponseList.get(position).getAttributes().getTitle());
        holder.imageSection.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.profile.get_profile.get_home_sections.RecycleSectionsAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Utilities.savePartner(RecycleSectionsAdapter.this.context, "http://199.247.4.89/mokafat/dev/public/uploads/large/" + RecycleSectionsAdapter.this.sectionsResponseList.get(position).getAttributes().getMain_image(), RecycleSectionsAdapter.this.sectionsResponseList.get(position).getAttributes().getTitle(), RecycleSectionsAdapter.this.sectionsResponseList.get(position).getAttributes().getTotal_offers(), RecycleSectionsAdapter.this.sectionsResponseList.get(position).getAttributes().getTotal_vendors());
                Utilities.SECTION_ID = RecycleSectionsAdapter.this.sectionsResponseList.get(position).getId();
                FragmentTransaction ft = RecycleSectionsAdapter.this.slidesFragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layout_container_1, new SpecificSectionFragment());
                ft.commit();
            }
        });
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.sectionsResponseList.size();
    }
}
