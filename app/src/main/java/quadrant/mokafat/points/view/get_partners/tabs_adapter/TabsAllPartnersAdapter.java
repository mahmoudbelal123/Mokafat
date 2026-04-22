package quadrant.mokafat.points.view.get_partners.tabs_adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import quadrant.mokafat.points.view.get_partners.partners_tabs.AllPartnersFragment;

/* JADX INFO: loaded from: classes.dex */
public class TabsAllPartnersAdapter extends FragmentStatePagerAdapter {
    AllPartnersFragment FRAGMENT_ALL_PARTNERS;
    String[] titles;

    public TabsAllPartnersAdapter(FragmentManager fm) {
        super(fm);
        this.titles = new String[1];
    }

    @Override // android.support.v4.app.FragmentStatePagerAdapter
    public Fragment getItem(int position) {
        if (position == 0) {
            if (this.FRAGMENT_ALL_PARTNERS == null) {
                this.FRAGMENT_ALL_PARTNERS = new AllPartnersFragment();
            }
            return this.FRAGMENT_ALL_PARTNERS;
        }
        return this.FRAGMENT_ALL_PARTNERS;
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
