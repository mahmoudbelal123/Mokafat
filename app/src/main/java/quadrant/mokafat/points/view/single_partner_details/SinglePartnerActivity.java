package quadrant.mokafat.points.view.single_partner_details;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.TabsSinglePartnerAdapter;

/* JADX INFO: loaded from: classes.dex */
public class SinglePartnerActivity extends AppCompatActivity implements View.OnClickListener {
    TabLayout tabLayout;
    TabsSinglePartnerAdapter tabsAdapter;
    private ImageView toolbarBackBtn;
    private TextView toolbarTitleTxt;
    ViewPager viewPager;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_partner);
        this.toolbarBackBtn = (ImageView) findViewById(R.id.toolbar_back_single_partner);
        this.toolbarTitleTxt = (TextView) findViewById(R.id.toolbar_title);
        this.toolbarBackBtn.setOnClickListener(this);
        this.tabsAdapter = new TabsSinglePartnerAdapter(getSupportFragmentManager());
        if (getIntent().getStringExtra("partner_title") != null) {
            this.toolbarTitleTxt.setText(getIntent().getStringExtra("partner_title"));
        }
        drawTab();
    }

    private void drawTab() {
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);
        this.viewPager.setAdapter(this.tabsAdapter);
        this.viewPager.setOffscreenPageLimit(0);
        this.tabLayout = (TabLayout) findViewById(R.id.tablayout);
        this.tabLayout.setupWithViewPager(this.viewPager);
        this.tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        this.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: quadrant.mokafat.points.view.single_partner_details.SinglePartnerActivity.1
            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextColor(ContextCompat.getColor(SinglePartnerActivity.this, R.color.colorPrimaryDark));
                SinglePartnerActivity.this.viewPager.setCurrentItem(SinglePartnerActivity.this.tabLayout.getSelectedTabPosition());
            }

            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextColor(ContextCompat.getColor(SinglePartnerActivity.this, R.color.liver));
            }

            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        TextView tab1_tv = new TextView(this);
        tab1_tv.setText("Hots Deals");
        tab1_tv.setTextSize(16.0f);
        tab1_tv.setGravity(17);
        tab1_tv.setWidth(400);
        tab1_tv.setHeight(400);
        tab1_tv.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        this.tabLayout.getTabAt(0).setCustomView(tab1_tv);
        TextView tab2_tv = new TextView(this);
        tab2_tv.setText("All Branch");
        tab2_tv.setTextSize(16.0f);
        tab2_tv.setGravity(17);
        tab2_tv.setWidth(400);
        tab2_tv.setHeight(400);
        tab2_tv.setTextColor(ContextCompat.getColor(this, R.color.liver));
        this.tabLayout.getTabAt(1).setCustomView(tab2_tv);
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: quadrant.mokafat.points.view.single_partner_details.SinglePartnerActivity.2
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
        if (v.getId() == R.id.toolbar_back_single_partner) {
            finish();
        }
    }
}
