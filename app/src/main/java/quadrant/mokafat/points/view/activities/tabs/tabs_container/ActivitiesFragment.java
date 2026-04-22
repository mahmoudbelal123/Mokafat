package quadrant.mokafat.points.view.activities.tabs.tabs_container;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.view.activities.tabs.tabs_adapter.TabsActivitiesAdapter;
import quadrant.mokafat.points.view.sections.get_all_sections.SectionPresenter;

/* JADX INFO: loaded from: classes.dex */
public class ActivitiesFragment extends Fragment {

    @Inject
    SectionPresenter mSectionPresenter;
    TabLayout tabLayout;
    TabsActivitiesAdapter tabsAdapter;
    private View view;
    ViewPager viewPager;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_container_activities, container, false);
        this.tabsAdapter = new TabsActivitiesAdapter(getChildFragmentManager());
        drawTab();
        return this.view;
    }

    private void drawTab() {
        this.viewPager = (ViewPager) this.view.findViewById(R.id.viewpager);
        this.viewPager.setAdapter(this.tabsAdapter);
        this.viewPager.setOffscreenPageLimit(0);
        this.tabLayout = (TabLayout) this.view.findViewById(R.id.tablayout);
        this.tabLayout.setupWithViewPager(this.viewPager);
        this.tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        this.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: quadrant.mokafat.points.view.activities.tabs.tabs_container.ActivitiesFragment.1
            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextColor(ContextCompat.getColor(ActivitiesFragment.this.getActivity(), R.color.colorPrimaryDark));
                ActivitiesFragment.this.viewPager.setCurrentItem(ActivitiesFragment.this.tabLayout.getSelectedTabPosition());
            }

            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextColor(ContextCompat.getColor(ActivitiesFragment.this.getActivity(), R.color.liver));
            }

            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        TextView tab1_tv = new TextView(getActivity());
        tab1_tv.setText("points");
        tab1_tv.setTextSize(16.0f);
        tab1_tv.setGravity(17);
        tab1_tv.setWidth(400);
        tab1_tv.setHeight(400);
        tab1_tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        this.tabLayout.getTabAt(0).setCustomView(tab1_tv);
        TextView tab2_tv = new TextView(getActivity());
        tab2_tv.setText("Promos");
        tab2_tv.setTextSize(16.0f);
        tab2_tv.setGravity(17);
        tab2_tv.setWidth(400);
        tab2_tv.setHeight(400);
        tab2_tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.liver));
        this.tabLayout.getTabAt(1).setCustomView(tab2_tv);
        TextView tab3_tv = new TextView(getActivity());
        tab3_tv.setText("Vouchers");
        tab3_tv.setTextSize(16.0f);
        tab3_tv.setWidth(400);
        tab3_tv.setHeight(400);
        tab3_tv.setGravity(17);
        tab3_tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.liver));
        this.tabLayout.getTabAt(2).setCustomView(tab3_tv);
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: quadrant.mokafat.points.view.activities.tabs.tabs_container.ActivitiesFragment.2
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
