package quadrant.mokafat.points.view.start_tour.slider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.view.login.LoginActivity;

/* JADX INFO: loaded from: classes.dex */
public class SliderActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView[] dots;
    private LinearLayout dotsLayout;
    private int[] layouts;
    private Button mContinueSignIn;
    private MyViewPagerAdapter myViewPagerAdapter;
    private ViewPager viewPager;
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() { // from class: quadrant.mokafat.points.view.start_tour.slider.SliderActivity.1
        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int position) {
            SliderActivity.this.addBottomDots(position);
            int length = SliderActivity.this.layouts.length;
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int arg0) {
        }
    };
    private AlphaAnimation buttonClick = new AlphaAnimation(2.0f, 0.8f);

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        this.viewPager = (ViewPager) findViewById(R.id.view_pager);
        this.dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        this.mContinueSignIn = (Button) findViewById(R.id.button_continue_to_sign_in);
        this.mContinueSignIn.setOnClickListener(this);
        this.layouts = new int[]{R.layout.intro_slide1, R.layout.intro_slide2, R.layout.intro_slide3};
        addBottomDots(0);
        changeStatusBarColor();
        this.myViewPagerAdapter = new MyViewPagerAdapter();
        this.viewPager.setAdapter(this.myViewPagerAdapter);
        this.viewPager.addOnPageChangeListener(this.viewPagerPageChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBottomDots(int currentPage) {
        this.dots = new TextView[this.layouts.length];
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_in_active);
        this.dotsLayout.removeAllViews();
        for (int i = 0; i < this.dots.length; i++) {
            this.dots[i] = new TextView(this);
            this.dots[i].setText(Html.fromHtml("&#8226;"));
            this.dots[i].setTextSize(35.0f);
            this.dots[i].setPadding(5, 0, 5, 0);
            this.dots[i].setTextColor(colorsInactive[currentPage]);
            this.dotsLayout.addView(this.dots[i]);
        }
        if (this.dots.length > 0) {
            this.dots[currentPage].setTextColor(colorsActive[currentPage]);
        }
    }

    private int getItem(int i) {
        return this.viewPager.getCurrentItem() + i;
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() == R.id.button_continue_to_sign_in) {
            this.mContinueSignIn.startAnimation(this.buttonClick);
            startActivity(new Intent(this, (Class<?>) LoginActivity.class));
        }
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override // android.support.v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup container, int position) {
            this.layoutInflater = (LayoutInflater) SliderActivity.this.getSystemService("layout_inflater");
            View view = this.layoutInflater.inflate(SliderActivity.this.layouts[position], container, false);
            container.addView(view);
            return view;
        }

        @Override // android.support.v4.view.PagerAdapter
        public int getCount() {
            return SliderActivity.this.layouts.length;
        }

        @Override // android.support.v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override // android.support.v4.view.PagerAdapter
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
