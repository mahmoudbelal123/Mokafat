package quadrant.mokafat.points.view.sections.get_section.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals.HotDealsFragment;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners.PartnersFragment;

/* JADX INFO: loaded from: classes.dex */
public class TabsAdapter extends FragmentStatePagerAdapter {
    HotDealsFragment FRAGMENT_HOT_DEALS;
    PartnersFragment FRAGMENT_PARTNERS;
    String[] titles;

    public TabsAdapter(FragmentManager fm) {
        super(fm);
        this.titles = new String[2];
    }

    @Override // android.support.v4.app.FragmentStatePagerAdapter
    public Fragment getItem(int position) {
        if (position == 0) {
            if (this.FRAGMENT_HOT_DEALS == null) {
                this.FRAGMENT_HOT_DEALS = new HotDealsFragment();
            }
            return this.FRAGMENT_HOT_DEALS;
        }
        if (this.FRAGMENT_PARTNERS == null) {
            this.FRAGMENT_PARTNERS = new PartnersFragment();
        }
        return this.FRAGMENT_PARTNERS;
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
