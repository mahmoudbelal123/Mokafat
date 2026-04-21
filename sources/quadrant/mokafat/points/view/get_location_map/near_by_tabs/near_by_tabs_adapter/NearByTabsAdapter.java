package quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.NearByDealsFragment;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab2.NearByPartnersFragment;

/* JADX INFO: loaded from: classes.dex */
public class NearByTabsAdapter extends FragmentStatePagerAdapter {
    NearByDealsFragment FRAGMENT_Deals;
    NearByPartnersFragment FRAGMENT_Partners;
    String[] titles;

    public NearByTabsAdapter(FragmentManager fm) {
        super(fm);
        this.titles = new String[2];
    }

    @Override // android.support.v4.app.FragmentStatePagerAdapter
    public Fragment getItem(int position) {
        if (position == 0) {
            if (this.FRAGMENT_Deals == null) {
                this.FRAGMENT_Deals = new NearByDealsFragment();
            }
            return this.FRAGMENT_Deals;
        }
        if (this.FRAGMENT_Partners == null) {
            this.FRAGMENT_Partners = new NearByPartnersFragment();
        }
        return this.FRAGMENT_Partners;
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
