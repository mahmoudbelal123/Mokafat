package quadrant.mokafat.points.view.activities.tabs.tabs_adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import quadrant.mokafat.points.view.activities.tabs.PointsFragment;
import quadrant.mokafat.points.view.activities.tabs.PromosFragment;
import quadrant.mokafat.points.view.activities.tabs.VouchersFragment;

/* JADX INFO: loaded from: classes.dex */
public class TabsActivitiesAdapter extends FragmentStatePagerAdapter {
    PointsFragment FRAGMENT_Points;
    PromosFragment FRAGMENT_Promos;
    VouchersFragment FRAGMENT_Vouchers;
    String[] titles;

    public TabsActivitiesAdapter(FragmentManager fm) {
        super(fm);
        this.titles = new String[3];
    }

    @Override // android.support.v4.app.FragmentStatePagerAdapter
    public Fragment getItem(int position) {
        if (position == 0) {
            if (this.FRAGMENT_Points == null) {
                this.FRAGMENT_Points = new PointsFragment();
            }
            return this.FRAGMENT_Points;
        }
        if (position == 1) {
            if (this.FRAGMENT_Promos == null) {
                this.FRAGMENT_Promos = new PromosFragment();
            }
            return this.FRAGMENT_Promos;
        }
        if (this.FRAGMENT_Vouchers == null) {
            this.FRAGMENT_Vouchers = new VouchersFragment();
        }
        return this.FRAGMENT_Vouchers;
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.titles.length;
    }

    @Override // android.support.v4.view.PagerAdapter
    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }
}
