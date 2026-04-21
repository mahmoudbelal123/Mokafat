package quadrant.mokafat.points.view.single_partner_details.single_partner_tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab1.SingleHotDealsFragment;
import quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.SingleAllBranchFragment;

/* JADX INFO: loaded from: classes.dex */
public class TabsSinglePartnerAdapter extends FragmentStatePagerAdapter {
    SingleHotDealsFragment FRAGMENT_HOT_;
    SingleAllBranchFragment FRAGMENT_PARTNERS_ALL_BRANCH;
    String[] titles;

    public TabsSinglePartnerAdapter(FragmentManager fm) {
        super(fm);
        this.titles = new String[2];
    }

    @Override // android.support.v4.app.FragmentStatePagerAdapter
    public Fragment getItem(int position) {
        if (position == 0) {
            if (this.FRAGMENT_HOT_ == null) {
                this.FRAGMENT_HOT_ = new SingleHotDealsFragment();
            }
            return this.FRAGMENT_HOT_;
        }
        if (this.FRAGMENT_PARTNERS_ALL_BRANCH == null) {
            this.FRAGMENT_PARTNERS_ALL_BRANCH = new SingleAllBranchFragment();
        }
        return this.FRAGMENT_PARTNERS_ALL_BRANCH;
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
