package quadrant.mokafat.points.view.profile.sections_items;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.List;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.models.objects.get_sections.DataObjectDetails;

/* JADX INFO: loaded from: classes.dex */
public class SectionsHomeItemsFragment extends Fragment implements SectionsHomeItemsView {
    private RecyclerView recyclerViewSections;

    @Inject
    SectionsHomeItemsPresenter sectionsHomeItemsPresenter;
    private View view;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_sections_home_items, container, false);
        this.recyclerViewSections = (RecyclerView) this.view.findViewById(R.id.recycleView_sections);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), 0, false);
        this.recyclerViewSections.setLayoutManager(mLayoutManager);
        this.recyclerViewSections.setItemAnimator(new DefaultItemAnimator());
        this.recyclerViewSections.setLayoutManager(mLayoutManager);
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        this.sectionsHomeItemsPresenter.onAttach((SectionsHomeItemsView) this);
        this.sectionsHomeItemsPresenter.getSections();
        return this.view;
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.view.profile.sections_items.SectionsHomeItemsView
    public void showLoading() {
    }

    @Override // quadrant.mokafat.points.view.profile.sections_items.SectionsHomeItemsView
    public void hideLoading() {
    }

    @Override // quadrant.mokafat.points.view.profile.sections_items.SectionsHomeItemsView
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, 0).show();
    }

    @Override // quadrant.mokafat.points.view.profile.sections_items.SectionsHomeItemsView
    public void showSections(List<DataObjectDetails> sectionsResponseList) {
        RecycleSectionsItemsAdapter recycleSectionsAdapter = new RecycleSectionsItemsAdapter(this, sectionsResponseList, getActivity());
        this.recyclerViewSections.setAdapter(recycleSectionsAdapter);
    }
}
