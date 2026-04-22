package quadrant.mokafat.points.view.get_partners;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.view.get_partners.tabs_adapter.TabsAllPartnersAdapter;

/* JADX INFO: loaded from: classes.dex */
public class PartnersContainerFragment extends Fragment {
    TabLayout tabLayout;
    TabsAllPartnersAdapter tabsAllPartnersAdapter;
    View view;
    ViewPager viewPager;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_partners_container, container, false);
        return this.view;
    }

    private void drawTab() {
        this.viewPager = (ViewPager) this.view.findViewById(R.id.viewpager);
        this.viewPager.setAdapter(this.tabsAllPartnersAdapter);
        this.viewPager.setOffscreenPageLimit(0);
        this.tabLayout = (TabLayout) this.view.findViewById(R.id.tablayout);
        this.tabLayout.setupWithViewPager(this.viewPager);
        this.tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        this.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: quadrant.mokafat.points.view.get_partners.PartnersContainerFragment.1
            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextColor(ContextCompat.getColor(PartnersContainerFragment.this.getActivity(), R.color.colorPrimaryDark));
                PartnersContainerFragment.this.viewPager.setCurrentItem(PartnersContainerFragment.this.tabLayout.getSelectedTabPosition());
            }

            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextColor(ContextCompat.getColor(PartnersContainerFragment.this.getActivity(), R.color.liver));
            }

            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        TextView tab1_tv = new TextView(getActivity());
        tab1_tv.setText("All");
        tab1_tv.setTextSize(16.0f);
        tab1_tv.setGravity(17);
        tab1_tv.setWidth(getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth() + ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        tab1_tv.setHeight(400);
        tab1_tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        this.tabLayout.getTabAt(0).setCustomView(tab1_tv);
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: quadrant.mokafat.points.view.get_partners.PartnersContainerFragment.2
            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
