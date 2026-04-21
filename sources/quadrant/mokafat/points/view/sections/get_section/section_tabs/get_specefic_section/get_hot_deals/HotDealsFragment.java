package quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.GlideApp;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_sections.get_sectons_by_id.GetSectionsBySectionIdResponse;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.SubSectionsAdapter;

/* JADX INFO: loaded from: classes.dex */
public class HotDealsFragment extends Fragment implements HotDealsView {
    SubSectionsAdapter adapter;

    @Inject
    HotDealsPresenter hotDealsPresenter;
    ProgressBar progressBarHotDeals;
    RecyclerView recyclerView;
    private ImageView sectionImage;
    private TextView sectionVendors;
    private TextView sectionsOffers;
    private TextView titleSection;
    private View view;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_hot_deals, container, false);
        this.recyclerView = (RecyclerView) this.view.findViewById(R.id.picker);
        this.sectionImage = (ImageView) this.view.findViewById(R.id.imageView_section_);
        this.titleSection = (TextView) this.view.findViewById(R.id.textView11_section_title);
        this.sectionVendors = (TextView) this.view.findViewById(R.id.textView16_vendors);
        this.sectionsOffers = (TextView) this.view.findViewById(R.id.textView18_offers);
        this.progressBarHotDeals = (ProgressBar) this.view.findViewById(R.id.progressbar_subsection_loading);
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        this.hotDealsPresenter.onAttach((HotDealsView) this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), 0, false);
        this.recyclerView.setLayoutManager(mLayoutManager);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setLayoutManager(mLayoutManager);
        this.hotDealsPresenter.getSubSections();
        return this.view;
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals.HotDealsView
    public void showLoading() {
        this.progressBarHotDeals.setVisibility(0);
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals.HotDealsView
    public void hideLoading() {
        this.progressBarHotDeals.setVisibility(4);
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals.HotDealsView
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, 0).show();
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals.HotDealsView
    public void showSubSections(GetSectionsBySectionIdResponse getSectionsBySectionIdResponse) {
        Utilities.Offers = getSectionsBySectionIdResponse.getAttributes().getTotal_offers();
        Utilities.Vendors = getSectionsBySectionIdResponse.getAttributes().getTotal_vendors();
        this.titleSection.setText(getSectionsBySectionIdResponse.getAttributes().getTitle());
        this.sectionVendors.setText(getSectionsBySectionIdResponse.getAttributes().getTotal_vendors());
        this.sectionsOffers.setText(getSectionsBySectionIdResponse.getAttributes().getTotal_offers());
        GlideApp.with(getActivity()).load2("http://199.247.4.89/mokafat/dev/public/uploads/large/" + getSectionsBySectionIdResponse.getAttributes().getMain_image()).into(this.sectionImage);
        this.adapter = new SubSectionsAdapter(getSectionsBySectionIdResponse.getRelationships().getChildrens(), getActivity(), this);
        this.recyclerView.setAdapter(this.adapter);
    }
}
