package quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.models.objects.get_sections.get_items_for_sub_section.DataItemsDetails;
import quadrant.mokafat.points.models.objects.get_sections.get_items_for_sub_section.GetItems;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubSectionsPresenter;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubsectionsView;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.ItemsSubSectionsAdapter;

/* JADX INFO: loaded from: classes.dex */
public class SingleHotDealsFragment extends Fragment implements GetItemsSubsectionsView {

    @Inject
    GetItemsSubSectionsPresenter getItemsSubSectionsPresenter;
    private ItemsSubSectionsAdapter itemsSubSectionsAdapter;
    private TextView noItemsTxt;
    private ProgressBar progressBarLoadItems;
    private RecyclerView recyclerView;
    private View view;
    int page = 1;
    List<DataItemsDetails> getItemsList = new ArrayList();

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_single_hot_deals, container, false);
        this.recyclerView = (RecyclerView) this.view.findViewById(R.id.recycleView_items_);
        this.noItemsTxt = (TextView) this.view.findViewById(R.id.text_default_no_items);
        this.progressBarLoadItems = (ProgressBar) this.view.findViewById(R.id.progress_items_subSections);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), 1, false);
        this.recyclerView.setLayoutManager(mLayoutManager);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setLayoutManager(mLayoutManager);
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        this.getItemsSubSectionsPresenter.onAttach((GetItemsSubsectionsView) this);
        this.getItemsSubSectionsPresenter.getItemsSubSectionsPage(this.page);
        return this.view;
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubsectionsView
    public void showMessage(String message) {
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubsectionsView
    public void showSItemsSubSections(List<DataItemsDetails> getItems) {
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubsectionsView
    public void showItemsSubSections(GetItems getItems) {
        if (getItems.getData().size() == 0) {
            this.noItemsTxt.setVisibility(0);
        } else {
            this.noItemsTxt.setVisibility(4);
        }
        this.recyclerView.setAdapter(this.itemsSubSectionsAdapter);
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubsectionsView
    public void showRecycle() {
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubsectionsView
    public void showSItemsVendors(List<DataItemsDetails> getItems) {
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubsectionsView
    public void showItemsSubSectionsPage(GetItems getItems) {
        int i = 0;
        if (getItems.getData().size() == 0) {
            this.noItemsTxt.setVisibility(0);
        } else {
            this.noItemsTxt.setVisibility(4);
        }
        while (true) {
            int i2 = i;
            if (i2 >= getItems.getData().size()) {
                break;
            }
            this.getItemsList.add(getItems.getData().get(i2));
            i = i2 + 1;
        }
        if (getItems.getLinks().getNext() != null) {
            this.page++;
            this.getItemsSubSectionsPresenter.getItemsSubSectionsPage(this.page);
        } else {
            this.itemsSubSectionsAdapter = new ItemsSubSectionsAdapter(this.getItemsList, getActivity());
            this.recyclerView.setAdapter(this.itemsSubSectionsAdapter);
        }
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubsectionsView
    public void showLoading() {
        this.progressBarLoadItems.setVisibility(0);
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubsectionsView
    public void hideLoading() {
        this.progressBarLoadItems.setVisibility(4);
    }
}
