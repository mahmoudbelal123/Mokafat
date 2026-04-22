package quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_sections.get_items_for_sub_section.DataItemsDetails;
import quadrant.mokafat.points.models.objects.get_sections.get_items_for_sub_section.GetItems;

/* JADX INFO: loaded from: classes.dex */
public class GetItemsSubSectionsFragment extends Fragment implements GetItemsSubsectionsView {
    public GetItems getItems;

    @Inject
    GetItemsSubSectionsPresenter getItemsSubSectionsPresenter;
    private ItemsSubSectionsAdapter itemsSubSectionsAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    private TextView noItemsTxt;
    private ProgressBar progressBarLoadItems;
    private RecyclerView recyclerView;
    private View view;
    private boolean loading = true;
    int page = 1;
    List<DataItemsDetails> getItemsList = new ArrayList();

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_get_items, container, false);
        this.recyclerView = (RecyclerView) this.view.findViewById(R.id.recycleView_items_);
        this.noItemsTxt = (TextView) this.view.findViewById(R.id.text_default_no_items);
        this.progressBarLoadItems = (ProgressBar) this.view.findViewById(R.id.progress_items_subSections);
        this.mLayoutManager = new LinearLayoutManager(getActivity(), 1, false);
        this.recyclerView.setLayoutManager(this.mLayoutManager);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setLayoutManager(this.mLayoutManager);
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        this.getItemsSubSectionsPresenter.onAttach((GetItemsSubsectionsView) this);
        if (Utilities.FLAG_SECTIONS_HOME == 1) {
            this.getItemsSubSectionsPresenter.getItemsSectionsHome();
        } else {
            this.getItemsSubSectionsPresenter.getItemsSubSectionsPage(this.page);
        }
        return this.view;
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubsectionsView
    public void showMessage(String message) {
        Toast.makeText(getActivity(), "" + message, 0).show();
        this.progressBarLoadItems.setVisibility(4);
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubsectionsView
    public void showSItemsSubSections(List<DataItemsDetails> getItems) {
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubsectionsView
    public void showItemsSubSections(final GetItems getItems) {
        this.getItems = getItems;
        if (getItems.getData().size() == 0) {
            this.noItemsTxt.setVisibility(0);
        } else {
            this.noItemsTxt.setVisibility(4);
        }
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubSectionsFragment.1
            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    if (getItems.getLinks().getNext() != null) {
                        FragmentActivity activity = GetItemsSubSectionsFragment.this.getActivity();
                        StringBuilder sb = new StringBuilder();
                        sb.append("");
                        GetItemsSubSectionsFragment getItemsSubSectionsFragment = GetItemsSubSectionsFragment.this;
                        int i = getItemsSubSectionsFragment.page;
                        getItemsSubSectionsFragment.page = i + 1;
                        sb.append(i);
                        Toast.makeText(activity, sb.toString(), 0).show();
                        GetItemsSubSectionsFragment.this.getItemsSubSectionsPresenter.getItemsSubSectionsPage(GetItemsSubSectionsFragment.this.page);
                    } else {
                        return;
                    }
                }
                recyclerView.canScrollVertically(-1);
            }
        });
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
    public void showRecycle() {
    }

    @Override // quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubsectionsView
    public void showSItemsVendors(List<DataItemsDetails> getItems) {
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
