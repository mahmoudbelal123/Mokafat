package quadrant.mokafat.points.view.get_partners.partners_tabs;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.models.objects.get_sections.AttributesObjectDetails;
import quadrant.mokafat.points.models.objects.get_sections.GetSectionsResponse;
import quadrant.mokafat.points.view.get_partners.adapters.ItemClickListener;
import quadrant.mokafat.points.view.get_partners.adapters.SectionedExpandableLayoutHelper;
import quadrant.mokafat.points.view.get_vouchers_tab_setting.models.Item;

/* JADX INFO: loaded from: classes.dex */
public class AllPartnersFragment extends Fragment implements AllPartnersView, ItemClickListener {

    @Inject
    AllPartnersPresenter allPartnersPresenter;
    ArrayList<Item> arrayList = new ArrayList<>();
    RecyclerView mRecyclerView;
    SectionedExpandableLayoutHelper sectionedExpandableLayoutHelper;
    private View view;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_all_partners, container, false);
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        this.allPartnersPresenter.onAttach((AllPartnersView) this);
        this.allPartnersPresenter.getSectionsObservable();
        this.mRecyclerView = (RecyclerView) this.view.findViewById(R.id.recycler_view_all_partners);
        this.sectionedExpandableLayoutHelper = new SectionedExpandableLayoutHelper(getActivity(), this.mRecyclerView, this, 2);
        return this.view;
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.view.get_partners.adapters.ItemClickListener
    public void itemClicked(Item item) {
    }

    @Override // quadrant.mokafat.points.view.get_partners.adapters.ItemClickListener
    public void itemClicked(AttributesObjectDetails attributesPartner) {
    }

    @Override // quadrant.mokafat.points.view.get_partners.partners_tabs.AllPartnersView
    public void showResponse(GetSectionsResponse getSectionsResponse) {
        for (int i = 0; i < getSectionsResponse.getData().size(); i++) {
            for (int j = 0; j < getSectionsResponse.getData().get(i).getRelationships().getPartners().size(); j++) {
                this.arrayList.add(new Item(getSectionsResponse.getData().get(i).getRelationships().getPartners().get(j).getAttributes().getLogo(), getSectionsResponse.getData().get(i).getRelationships().getPartners().get(j).getAttributes().getTitle(), getSectionsResponse.getData().get(i).getRelationships().getPartners().get(j).getId(), j));
            }
            this.sectionedExpandableLayoutHelper.addSection(getSectionsResponse.getData().get(i).getAttributes().getTitle(), this.arrayList);
            this.arrayList = new ArrayList<>();
        }
        this.sectionedExpandableLayoutHelper.notifyDataSetChanged();
    }

    @Override // quadrant.mokafat.points.view.get_partners.partners_tabs.AllPartnersView
    public void showMessage(String message) {
        Toast.makeText(getActivity(), "" + message, 0).show();
    }
}
