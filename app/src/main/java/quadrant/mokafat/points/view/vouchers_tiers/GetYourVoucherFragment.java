package quadrant.mokafat.points.view.vouchers_tiers;

import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import androidx.core.internal.view.SupportMenu;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.models.objects.get_vouchers.GetYourVouchersResponse;

/* JADX INFO: loaded from: classes.dex */
public class GetYourVoucherFragment extends Fragment implements GetYourVoucherView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    GetYourVoucherPresenter mGetYourVoucherPresenter;
    private SwipeRefreshLayout mSwipeRefresh;
    private ProgressBar progressBarGetYourVoucher;
    private RecyclerView recyclerViewTiers;
    private TiersAdapter tiersAdapter;
    private View view;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_get_your_voucher, container, false);
        this.mSwipeRefresh = (SwipeRefreshLayout) this.view.findViewById(R.id.swipe_refresh);
        this.mSwipeRefresh.setOnRefreshListener(this);
        this.recyclerViewTiers = (RecyclerView) this.view.findViewById(R.id.recycler_view_tiers);
        this.progressBarGetYourVoucher = (ProgressBar) this.view.findViewById(R.id.progress_get_your_voucher);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), 1, false);
        this.recyclerViewTiers.setLayoutManager(mLayoutManager);
        this.recyclerViewTiers.setItemAnimator(new DefaultItemAnimator());
        this.recyclerViewTiers.setLayoutManager(mLayoutManager);
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        this.mGetYourVoucherPresenter.onAttach((GetYourVoucherView) this);
        this.mGetYourVoucherPresenter.getVouchersPresenter();
        return this.view;
    }

    @Override // quadrant.mokafat.points.view.vouchers_tiers.GetYourVoucherView
    public void showLoading() {
        this.progressBarGetYourVoucher.setVisibility(0);
    }

    @Override // quadrant.mokafat.points.view.vouchers_tiers.GetYourVoucherView
    public void hideLoading() {
        this.progressBarGetYourVoucher.setVisibility(8);
    }

    @Override // quadrant.mokafat.points.view.vouchers_tiers.GetYourVoucherView
    public void showResponse(GetYourVouchersResponse responseVouchers) {
        this.tiersAdapter = new TiersAdapter(responseVouchers.getData(), getActivity());
        this.recyclerViewTiers.setAdapter(this.tiersAdapter);
    }

    @Override // quadrant.mokafat.points.view.vouchers_tiers.GetYourVoucherView
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, 0).show();
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
    public void onRefresh() {
        refreshContent();
    }

    private void refreshContent() {
        new Handler().postDelayed(new Runnable() { // from class: quadrant.mokafat.points.view.vouchers_tiers.GetYourVoucherFragment.1
            @Override // java.lang.Runnable
            public void run() {
                GetYourVoucherFragment.this.mGetYourVoucherPresenter.getVouchersPresenter();
                GetYourVoucherFragment.this.mSwipeRefresh.setRefreshing(false);
            }
        }, 3000L);
        this.mSwipeRefresh.setColorSchemeColors(SupportMenu.CATEGORY_MASK, -16711936, SupportMenu.CATEGORY_MASK);
    }
}
