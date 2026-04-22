package quadrant.mokafat.points.view.get_location_map;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.NearByTabsAdapter;

/* JADX INFO: loaded from: classes.dex */
public class MapsActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mBackNearby;
    TabLayout tabLayout;
    NearByTabsAdapter tabsAdapter;
    ViewPager viewPager;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        this.mBackNearby = (ImageView) findViewById(R.id.toolbar_back_near_by);
        this.mBackNearby.setOnClickListener(this);
        this.tabsAdapter = new NearByTabsAdapter(getSupportFragmentManager());
        drawTab();
    }

    private void drawTab() {
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);
        this.viewPager.setAdapter(this.tabsAdapter);
        this.viewPager.setOffscreenPageLimit(0);
        this.tabLayout = (TabLayout) findViewById(R.id.tablayout);
        this.tabLayout.setupWithViewPager(this.viewPager);
        this.tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        this.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: quadrant.mokafat.points.view.get_location_map.MapsActivity.1
            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextColor(ContextCompat.getColor(MapsActivity.this, R.color.colorPrimaryDark));
                MapsActivity.this.viewPager.setCurrentItem(MapsActivity.this.tabLayout.getSelectedTabPosition());
            }

            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextColor(ContextCompat.getColor(MapsActivity.this, R.color.liver));
            }

            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        TextView tab1_tv = new TextView(this);
        tab1_tv.setText("Nearby Deals");
        tab1_tv.setTextSize(16.0f);
        tab1_tv.setGravity(17);
        tab1_tv.setWidth(400);
        tab1_tv.setHeight(400);
        tab1_tv.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        this.tabLayout.getTabAt(0).setCustomView(tab1_tv);
        TextView tab2_tv = new TextView(this);
        tab2_tv.setText("Nearby Partners");
        tab2_tv.setTextSize(16.0f);
        tab2_tv.setGravity(17);
        tab2_tv.setWidth(400);
        tab2_tv.setHeight(400);
        tab2_tv.setTextColor(ContextCompat.getColor(this, R.color.liver));
        this.tabLayout.getTabAt(1).setCustomView(tab2_tv);
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: quadrant.mokafat.points.view.get_location_map.MapsActivity.2
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

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() == R.id.toolbar_back_near_by) {
            finish();
        }
    }
}
