package quadrant.mokafat.points.view.profile.get_profile.slides;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.slides.DataObjectDetails;
import quadrant.mokafat.points.view.profile.get_profile.get_home_sections.HomeSectionPresenter;
import quadrant.mokafat.points.view.profile.get_profile.get_home_sections.HomeSectionView;
import quadrant.mokafat.points.view.profile.get_profile.get_home_sections.RecycleSectionsAdapter;
import quadrant.mokafat.points.view.profile.sections_items.SectionsHomeItemsFragment;

/* JADX INFO: loaded from: classes.dex */
public class SlidesFragment extends Fragment implements SlidesView, HomeSectionView {
    private static ViewPager mPager;
    CirclePageIndicator indicator;

    @Inject
    HomeSectionPresenter mSectionPresenter;
    private ProgressBar progressBarSlides;
    private RecyclerView recyclerView;
    private RecycleSectionsAdapter sectionAdapter;

    @Inject
    SlidesPresenter slidesPresenter;
    Timer swipeTimer;
    private View view;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    static /* synthetic */ int access$008() {
        int i = currentPage;
        currentPage = i + 1;
        return i;
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.swipeTimer = new Timer();
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_slides, container, false);
        Utilities.back = true;
        mPager = (ViewPager) this.view.findViewById(R.id.pager);
        this.progressBarSlides = (ProgressBar) this.view.findViewById(R.id.progress_slides_home);
        this.recyclerView = (RecyclerView) this.view.findViewById(R.id.recycleView_sections);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), 0, false);
        this.recyclerView.setLayoutManager(mLayoutManager);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setLayoutManager(mLayoutManager);
        this.indicator = (CirclePageIndicator) this.view.findViewById(R.id.indicator);
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        this.slidesPresenter.onAttach((SlidesView) this);
        this.mSectionPresenter.onAttach((HomeSectionView) this);
        this.slidesPresenter.getSlidesPresenter();
        this.mSectionPresenter.getSections();
        new Handler();
        new Runnable() { // from class: quadrant.mokafat.points.view.profile.get_profile.slides.SlidesFragment.1
            @Override // java.lang.Runnable
            public void run() {
                if (SlidesFragment.currentPage == SlidesFragment.NUM_PAGES) {
                    int unused = SlidesFragment.currentPage = 0;
                }
                SlidesFragment.mPager.setCurrentItem(SlidesFragment.access$008(), true);
            }
        };
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_sections, new SectionsHomeItemsFragment());
        ft.commit();
        return this.view;
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.swipeTimer.cancel();
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        this.swipeTimer.cancel();
    }

    @Override // android.support.v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.swipeTimer.cancel();
    }

    private void init(List<DataObjectDetails> getSlidesResponse) {
        mPager.setAdapter(new SlidingImage_Adapter(getActivity(), getSlidesResponse));
        this.indicator.setViewPager(mPager);
        float density = getResources().getDisplayMetrics().density;
        this.indicator.setRadius(3.0f * density);
        NUM_PAGES = getSlidesResponse.size();
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() { // from class: quadrant.mokafat.points.view.profile.get_profile.slides.SlidesFragment.2
            @Override // java.lang.Runnable
            public void run() {
                if (SlidesFragment.currentPage == SlidesFragment.NUM_PAGES) {
                    int unused = SlidesFragment.currentPage = 0;
                }
                SlidesFragment.mPager.setCurrentItem(SlidesFragment.access$008(), true);
            }
        };
        this.swipeTimer.schedule(new TimerTask() { // from class: quadrant.mokafat.points.view.profile.get_profile.slides.SlidesFragment.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                handler.post(Update);
            }
        }, 1600L, 1600L);
        this.indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: quadrant.mokafat.points.view.profile.get_profile.slides.SlidesFragment.4
            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                int unused = SlidesFragment.currentPage = position;
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int pos) {
            }
        });
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.slides.SlidesView
    public void showResponse(List<DataObjectDetails> getSlidesResponse) {
        init(getSlidesResponse);
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.get_home_sections.HomeSectionView
    public void showLoading() {
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.get_home_sections.HomeSectionView
    public void hideLoading() {
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.slides.SlidesView, quadrant.mokafat.points.view.profile.get_profile.get_home_sections.HomeSectionView
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, 0).show();
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.slides.SlidesView
    public void showLadingSlides() {
        this.progressBarSlides.setVisibility(0);
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.slides.SlidesView
    public void hideLoadingSlides() {
        this.progressBarSlides.setVisibility(4);
    }

    @Override // quadrant.mokafat.points.view.profile.get_profile.get_home_sections.HomeSectionView
    public void showSections(List<quadrant.mokafat.points.models.objects.get_sections.DataObjectDetails> sectionsResponseList) {
        try {
            this.sectionAdapter = new RecycleSectionsAdapter(this, sectionsResponseList, getActivity());
            this.recyclerView.setAdapter(this.sectionAdapter);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "try, again", 0).show();
        }
    }
}
