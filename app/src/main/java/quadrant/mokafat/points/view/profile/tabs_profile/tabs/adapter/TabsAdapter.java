package quadrant.mokafat.points.view.profile.tabs_profile.tabs.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabOneFragment;
import quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabThreeFragment;
import quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabTwoFragment;

/* JADX INFO: loaded from: classes.dex */
public class TabsAdapter extends FragmentStatePagerAdapter {
    TabTwoFragment FRAGMENT_Favorites;
    TabThreeFragment FRAGMENT_Preferences;
    TabOneFragment FRAGMENT_Profile;
    String[] titles;

    public TabsAdapter(FragmentManager fm) {
        super(fm);
        this.titles = new String[2];
    }

    @Override // android.support.v4.app.FragmentStatePagerAdapter
    public Fragment getItem(int position) {
        if (position == 0) {
            if (this.FRAGMENT_Profile == null) {
                this.FRAGMENT_Profile = new TabOneFragment();
            }
            return this.FRAGMENT_Profile;
        }
        if (position == 1) {
            if (this.FRAGMENT_Favorites == null) {
                this.FRAGMENT_Favorites = new TabTwoFragment();
            }
            return this.FRAGMENT_Favorites;
        }
        if (this.FRAGMENT_Preferences == null) {
            this.FRAGMENT_Preferences = new TabThreeFragment();
        }
        return this.FRAGMENT_Preferences;
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
