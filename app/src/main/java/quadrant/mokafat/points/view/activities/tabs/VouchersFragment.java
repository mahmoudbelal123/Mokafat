package quadrant.mokafat.points.view.activities.tabs;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.models.objects.get_vouchers_tab.DataObjectDetails;
import quadrant.mokafat.points.models.objects.get_vouchers_tab.GetVouchersTabResponse;
import quadrant.mokafat.points.view.activities.tabs.tabs_adapter.ExpandableListAdapter;
import quadrant.mokafat.points.view.get_vouchers_tab_setting.get_all_items.GetVoucherTabsPresenter;
import quadrant.mokafat.points.view.get_vouchers_tab_setting.get_all_items.GetVouchersTabsView;

/* JADX INFO: loaded from: classes.dex */
public class VouchersFragment extends Fragment implements GetVouchersTabsView {
    ExpandableListView expListView;

    @Inject
    GetVoucherTabsPresenter getVoucherTabsPresenter;
    ExpandableListAdapter listAdapter;
    HashMap<String, List<DataObjectDetails>> listDataChild;
    List<String> listDataHeader;
    private TextView txtUnUsedCounter;
    private TextView txtUsedCounter;
    private TextView txtValuesUnused;
    private View view;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragnment_vouchers_test, container, false);
        this.expListView = (ExpandableListView) this.view.findViewById(R.id.lvExp);
        this.txtValuesUnused = (TextView) this.view.findViewById(R.id.textView41_value_un_used);
        this.txtUnUsedCounter = (TextView) this.view.findViewById(R.id.textView_un_used_counter_vouchers);
        this.txtUsedCounter = (TextView) this.view.findViewById(R.id.textView_used_counter);
        Display newDisplay = getActivity().getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();
        this.expListView.setIndicatorBounds(width - 80, width);
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        this.getVoucherTabsPresenter.onAttach((GetVouchersTabsView) this);
        this.getVoucherTabsPresenter.getItemsPresenter();
        return this.view;
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.view.get_vouchers_tab_setting.get_all_items.GetVouchersTabsView
    public void showLoading() {
    }

    @Override // quadrant.mokafat.points.view.get_vouchers_tab_setting.get_all_items.GetVouchersTabsView
    public void hideLoading() {
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // quadrant.mokafat.points.view.get_vouchers_tab_setting.get_all_items.GetVouchersTabsView
    public void showResponse(GetVouchersTabResponse getVouchersTabResponse) {
        this.listDataHeader = new ArrayList();
        this.listDataChild = new HashMap<>();
        this.listDataHeader.add("Unused");
        this.listDataHeader.add("Used");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < getVouchersTabResponse.getData().size(); i4++) {
            if (getVouchersTabResponse.getData().get(i4).getAttributes().getUsed().equals("No")) {
                arrayList.add(new DataObjectDetails(getVouchersTabResponse.getData().get(i4).getAttributes()));
                i3 += Integer.parseInt(getVouchersTabResponse.getData().get(i4).getAttributes().getValue());
                i++;
            } else {
                arrayList2.add(new DataObjectDetails(getVouchersTabResponse.getData().get(i4).getAttributes()));
                i2++;
            }
        }
        this.listDataChild.put(this.listDataHeader.get(0), arrayList);
        this.listDataChild.put(this.listDataHeader.get(1), arrayList2);
        this.listAdapter = new ExpandableListAdapter(getActivity(), this.listDataHeader, this.listDataChild);
        this.expListView.setAdapter(this.listAdapter);
        this.txtValuesUnused.setText("" + i3);
        this.txtUsedCounter.setText("" + i2);
        this.txtUnUsedCounter.setText("" + i);
    }

    @Override // quadrant.mokafat.points.view.get_vouchers_tab_setting.get_all_items.GetVouchersTabsView
    public void showMessage(String message) {
    }
}
