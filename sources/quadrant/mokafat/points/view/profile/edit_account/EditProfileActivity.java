package quadrant.mokafat.points.view.profile.edit_account;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabOneFragment;
import quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabThreeFragment;
import quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabTwoFragment;
import quadrant.mokafat.points.view.profile.tabs_profile.tabs.adapter.TabsAdapter;

/* JADX INFO: loaded from: classes.dex */
public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView favoritesTxt;
    private TextView preferencesTxt;
    private TextView profileTxt;
    TabLayout tabLayout;
    TabsAdapter tabsAdapter;
    private TextView titleTxt;
    ViewPager viewPager;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        this.profileTxt = (TextView) findViewById(R.id.text_profile);
        this.favoritesTxt = (TextView) findViewById(R.id.text_favourite);
        this.preferencesTxt = (TextView) findViewById(R.id.text_preferences);
        this.titleTxt = (TextView) findViewById(R.id.toolbar_title_profile);
        this.profileTxt.setOnClickListener(this);
        this.favoritesTxt.setOnClickListener(this);
        this.preferencesTxt.setOnClickListener(this);
        this.tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        drawTab();
    }

    private void drawTab() {
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);
        this.viewPager.setAdapter(this.tabsAdapter);
        this.viewPager.setOffscreenPageLimit(0);
        this.tabLayout = (TabLayout) findViewById(R.id.tablayout);
        this.tabLayout.setupWithViewPager(this.viewPager);
        this.tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        this.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: quadrant.mokafat.points.view.profile.edit_account.EditProfileActivity.1
            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextColor(ContextCompat.getColor(EditProfileActivity.this, R.color.colorPrimaryDark));
                EditProfileActivity.this.viewPager.setCurrentItem(EditProfileActivity.this.tabLayout.getSelectedTabPosition());
                EditProfileActivity.this.tabLayout.getTabAt(tab.getPosition()).getCustomView().setBackgroundColor(-1);
            }

            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextColor(ContextCompat.getColor(EditProfileActivity.this, R.color.liver));
                EditProfileActivity.this.tabLayout.getTabAt(tab.getPosition()).getCustomView().setBackgroundColor(-3355444);
            }

            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        TextView tab1_tv = new TextView(this);
        tab1_tv.setText("Profile");
        tab1_tv.setTextSize(16.0f);
        tab1_tv.setGravity(17);
        tab1_tv.setWidth(ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        tab1_tv.setHeight(ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        tab1_tv.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        this.tabLayout.getTabAt(0).setCustomView(tab1_tv);
        TextView tab2_tv = new TextView(this);
        tab2_tv.setText("Favorites");
        tab2_tv.setTextSize(16.0f);
        tab2_tv.setGravity(17);
        tab2_tv.setWidth(ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        tab2_tv.setHeight(ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        tab2_tv.setTextColor(ContextCompat.getColor(this, R.color.liver));
        this.tabLayout.getTabAt(1).setCustomView(tab2_tv);
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: quadrant.mokafat.points.view.profile.edit_account.EditProfileActivity.2
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
        int id = v.getId();
        if (id == R.id.text_favourite) {
            this.profileTxt.setBackgroundResource(R.drawable.tab_un_selected);
            this.favoritesTxt.setBackgroundResource(R.drawable.tab_selected);
            this.preferencesTxt.setBackgroundResource(R.drawable.tab_un_selected);
            addFavouriteFragment();
            return;
        }
        if (id == R.id.text_preferences) {
            this.profileTxt.setBackgroundResource(R.drawable.tab_un_selected);
            this.favoritesTxt.setBackgroundResource(R.drawable.tab_un_selected);
            this.preferencesTxt.setBackgroundResource(R.drawable.tab_selected);
            addPreferencesFragment();
            return;
        }
        if (id == R.id.text_profile) {
            this.profileTxt.setBackgroundResource(R.drawable.tab_selected);
            this.favoritesTxt.setBackgroundResource(R.drawable.tab_un_selected);
            this.preferencesTxt.setBackgroundResource(R.drawable.tab_un_selected);
            addProfileFragment();
        }
    }

    private void addProfileFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.fragments_Container, new TabOneFragment());
        ft.commit();
    }

    private void addFavouriteFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.fragments_Container, new TabTwoFragment());
        ft.commit();
    }

    private void addPreferencesFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.fragments_Container, new TabThreeFragment());
        ft.commit();
    }
}
