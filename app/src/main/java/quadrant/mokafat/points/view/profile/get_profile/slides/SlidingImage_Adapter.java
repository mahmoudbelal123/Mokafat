package quadrant.mokafat.points.view.profile.get_profile.slides;

import android.content.Context;
import android.os.Parcelable;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.List;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.helper.GlideApp;
import quadrant.mokafat.points.models.objects.slides.DataObjectDetails;

/* JADX INFO: loaded from: classes.dex */
public class SlidingImage_Adapter extends PagerAdapter {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Context context;
    private List<DataObjectDetails> imageModelArrayList;
    private LayoutInflater inflater;

    public SlidingImage_Adapter(Context context, List<DataObjectDetails> imageModelArrayList) {
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override // android.support.v4.view.PagerAdapter
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.imageModelArrayList.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = this.inflater.inflate(R.layout.slidingimages_layout, view, false);
        ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
        GlideApp.with(this.context).load("http://199.247.4.89/mokafat/dev/public/uploads/large/" + this.imageModelArrayList.get(position).getAttributes().getMain_image()).into(imageView);
        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override // android.support.v4.view.PagerAdapter
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override // android.support.v4.view.PagerAdapter
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override // android.support.v4.view.PagerAdapter
    public Parcelable saveState() {
        return null;
    }
}
