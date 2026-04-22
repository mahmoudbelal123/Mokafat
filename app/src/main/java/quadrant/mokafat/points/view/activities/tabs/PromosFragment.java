package quadrant.mokafat.points.view.activities.tabs;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.models.objects.redemptions.GetRedemptionsResponse;
import quadrant.mokafat.points.view.activities.tabs.tabs_adapter.PromosAdapter;
import quadrant.mokafat.points.view.activities.tabs.tabs_container.promos_logic.PromosPresenter;
import quadrant.mokafat.points.view.activities.tabs.tabs_container.promos_logic.PromosView;

/* JADX INFO: loaded from: classes.dex */
public class PromosFragment extends Fragment implements PromosView {
    private PromosAdapter promosAdapter;

    @Inject
    PromosPresenter promosPresenter;
    private RecyclerView recyclerView;
    private TextView textView_Promos_Earned;
    private TextView txtPromos;
    private View view;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_promos, container, false);
        this.recyclerView = (RecyclerView) this.view.findViewById(R.id.recycleView_promos);
        this.textView_Promos_Earned = (TextView) this.view.findViewById(R.id.textView_Promos_Earned);
        this.txtPromos = (TextView) this.view.findViewById(R.id.textView42_you_saved_promos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), 1, false);
        this.recyclerView.setLayoutManager(mLayoutManager);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setLayoutManager(mLayoutManager);
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        this.promosPresenter.onAttach((PromosView) this);
        this.promosPresenter.getPromos();
        return this.view;
    }

    @Override // quadrant.mokafat.points.view.activities.tabs.tabs_container.promos_logic.PromosView
    public void showResponse(GetRedemptionsResponse listPromos) {
        double values = 0.0d;
        int pointsEarnedCounter = 0;
        for (int i = 0; i < listPromos.getData().size(); i++) {
            values += Double.parseDouble(listPromos.getData().get(i).getAttributes().getValue());
            pointsEarnedCounter += Integer.parseInt(listPromos.getData().get(i).getAttributes().getEarned_points());
        }
        this.promosAdapter = new PromosAdapter(listPromos.getData(), getActivity());
        this.recyclerView.setAdapter(this.promosAdapter);
        this.txtPromos.setText("EGP " + values);
        this.textView_Promos_Earned.setText("" + pointsEarnedCounter);
    }

    @Override // quadrant.mokafat.points.view.activities.tabs.tabs_container.promos_logic.PromosView
    public void showMessage(String message) {
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }
}
