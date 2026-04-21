package quadrant.mokafat.points.view.activities.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_points.GetPointsResponse;
import quadrant.mokafat.points.view.activities.tabs.tabs_adapter.PointsAdapter;
import quadrant.mokafat.points.view.activities.tabs.tabs_container.points_logic.PointsPresenter;
import quadrant.mokafat.points.view.activities.tabs.tabs_container.points_logic.PointsView;

/* JADX INFO: loaded from: classes.dex */
public class PointsFragment extends Fragment implements PointsView {
    private PointsAdapter pointsAdapter;

    @Inject
    PointsPresenter pointsPresenter;
    private RecyclerView recyclerView;
    private TextView textView_redeemed_points_counter;
    private TextView txtSaved;
    private View view;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_points, container, false);
        this.txtSaved = (TextView) this.view.findViewById(R.id.textView41_you_saved_points);
        this.textView_redeemed_points_counter = (TextView) this.view.findViewById(R.id.textView_redeemed_points_counter);
        this.recyclerView = (RecyclerView) this.view.findViewById(R.id.recycleView_points);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), 1, false);
        this.recyclerView.setLayoutManager(mLayoutManager);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setLayoutManager(mLayoutManager);
        this.txtSaved.setText(Utilities.retrieveUserInfoSharedPreferences(getActivity()).getData().getTotal_points());
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        this.pointsPresenter.onAttach((PointsView) this);
        this.pointsPresenter.getPoints();
        return this.view;
    }

    @Override // quadrant.mokafat.points.view.activities.tabs.tabs_container.points_logic.PointsView
    public void showResponse(GetPointsResponse listPoints) {
        int totalRedeemedPoints = 0;
        for (int totalRedeemedPoints2 = 0; totalRedeemedPoints2 < listPoints.getData().size(); totalRedeemedPoints2++) {
            totalRedeemedPoints += Integer.parseInt(listPoints.getData().get(totalRedeemedPoints2).getTier().get(0).getAttributes().getTotal_points());
        }
        this.pointsAdapter = new PointsAdapter(listPoints.getData(), getActivity());
        this.recyclerView.setAdapter(this.pointsAdapter);
        this.textView_redeemed_points_counter.setText("" + totalRedeemedPoints);
    }

    @Override // quadrant.mokafat.points.view.activities.tabs.tabs_container.points_logic.PointsView
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, 0).show();
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }
}
