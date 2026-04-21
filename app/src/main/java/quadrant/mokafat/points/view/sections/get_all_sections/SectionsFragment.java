package quadrant.mokafat.points.view.sections.get_all_sections;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;
import java.util.List;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.models.objects.get_sections.DataObjectDetails;

/* JADX INFO: loaded from: classes.dex */
public class SectionsFragment extends Fragment implements SectionView {
    private GridView gridViewSections;

    @Inject
    SectionPresenter mSectionPresenter;
    private SectionAdapter sectionAdapter;
    private View view;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_sections, container, false);
        this.gridViewSections = (GridView) this.view.findViewById(R.id.gridViews_sections);
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        this.mSectionPresenter.onAttach((SectionView) this);
        this.mSectionPresenter.getSections();
        return this.view;
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.view.sections.get_all_sections.SectionView
    public void showMessage(String message) {
        Toast.makeText(getActivity(), "" + message, 0).show();
    }

    @Override // quadrant.mokafat.points.view.sections.get_all_sections.SectionView
    public void showSections(List<DataObjectDetails> sectionsResponseList) {
        this.sectionAdapter = new SectionAdapter(getActivity(), this, sectionsResponseList);
        this.gridViewSections.setAdapter((ListAdapter) this.sectionAdapter);
    }
}
