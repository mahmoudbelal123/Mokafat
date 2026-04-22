package quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.GlideApp;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_vendors.partnersObject;

/* JADX INFO: loaded from: classes.dex */
public class PartnersFragment extends Fragment implements PartnersView {
    private ImageView imageViewSection;
    private TextView offersSection;

    @Inject
    PartnersPresenter partnersPresenter;
    private ProgressBar progressBarLoadingPartners;
    private RecyclerView recyclerViewPartners;
    private TextView titleSection;
    VendorsAdapter vendorsAdapter;
    private TextView vendorsSection;
    private View view;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_partners, container, false);
        this.imageViewSection = (ImageView) this.view.findViewById(R.id.imageView_section_in_partners);
        this.titleSection = (TextView) this.view.findViewById(R.id.textView11_section_title_in_partner);
        this.vendorsSection = (TextView) this.view.findViewById(R.id.textView16_vendors);
        this.offersSection = (TextView) this.view.findViewById(R.id.textView18_offers);
        this.progressBarLoadingPartners = (ProgressBar) this.view.findViewById(R.id.progressbar_load_partner_for_section);
        this.recyclerViewPartners = (RecyclerView) this.view.findViewById(R.id.recycle_view_partners);
        GlideApp.with(getActivity()).load(Utilities.retrievePartnerImage(getContext())).into(this.imageViewSection);
        this.titleSection.setText(Utilities.retrievePartnerTitle(getActivity()));
        this.vendorsSection.setText(Utilities.retrievePartnerVendors(getActivity()));
        this.offersSection.setText(Utilities.retrievePartnerOffers(getActivity()));
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        this.partnersPresenter.onAttach((PartnersView) this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), 1, false);
        this.recyclerViewPartners.setLayoutManager(mLayoutManager);
        this.recyclerViewPartners.setItemAnimator(new DefaultItemAnimator());
        this.recyclerViewPartners.setLayoutManager(mLayoutManager);
        this.partnersPresenter.getVendorsForSection();
        return this.view;
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners.PartnersView
    public void showLoading() {
        this.progressBarLoadingPartners.setVisibility(0);
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners.PartnersView
    public void hideLoading() {
        this.progressBarLoadingPartners.setVisibility(4);
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners.PartnersView
    public void showMessage(String message) {
        Toast.makeText(getActivity(), "" + message, 0).show();
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners.PartnersView
    public void showPartners(List<partnersObject> getVendorsResponse) {
        this.vendorsAdapter = new VendorsAdapter(getVendorsResponse, getActivity());
        this.recyclerViewPartners.setAdapter(this.vendorsAdapter);
    }
}
