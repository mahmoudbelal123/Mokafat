package quadrant.mokafat.points.view.sections.inside_items;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.analytics.FirebaseAnalytics;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.GlideApp;
import quadrant.mokafat.points.models.objects.favorite.FavoriteResponse;
import quadrant.mokafat.points.models.objects.get_items.get_items_by_id.DataForItem;
import quadrant.mokafat.points.view.single_partner_details.SinglePartnerActivity;

/* JADX INFO: loaded from: classes.dex */
public class ItemsInsideSubsectionsActivity extends AppCompatActivity implements View.OnClickListener, GetOneItemView, ItemFavoriteView {
    private ImageView backBtn;
    private ImageView dealImage;
    private ImageView favoriteBtn;

    @Inject
    GetOneItemPresenter getOneItemPresenter;
    String imageUrl;

    @Inject
    ItemFavoritePresenter itemFavoritePresenter;
    private LinearLayout linearLayoutFindPartner;
    private ImageView mOpenGallery;
    private TextView mokafatPrice;
    private TextView oldPrice;
    private TextView textView_Details;
    private TextView textView_summary;
    String title;
    private TextView titleTxt;
    private TextView titleTxtItem;
    private TextView txtEndDate;
    private TextView txtStartDate;
    private ImageView vendorImage;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_inside_subsections);
        this.backBtn = (ImageView) findViewById(R.id.imageView5_back);
        this.favoriteBtn = (ImageView) findViewById(R.id.imageView7);
        this.titleTxt = (TextView) findViewById(R.id.textView11);
        this.dealImage = (ImageView) findViewById(R.id.imageView2);
        this.backBtn.setOnClickListener(this);
        this.vendorImage = (ImageView) findViewById(R.id.imageView5_item_vendor);
        this.mokafatPrice = (TextView) findViewById(R.id.textView16);
        this.oldPrice = (TextView) findViewById(R.id.textView33);
        this.titleTxtItem = (TextView) findViewById(R.id.textView_title_item);
        this.textView_summary = (TextView) findViewById(R.id.textView_summary);
        this.txtStartDate = (TextView) findViewById(R.id.textView_start_item_date);
        this.txtEndDate = (TextView) findViewById(R.id.textView_end_date);
        this.textView_Details = (TextView) findViewById(R.id.textView_Details);
        this.linearLayoutFindPartner = (LinearLayout) findViewById(R.id.linear_find_partner);
        this.favoriteBtn.setOnClickListener(this);
        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        this.itemFavoritePresenter.onAttach((ItemFavoriteView) this);
        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        this.getOneItemPresenter.onAttach((GetOneItemView) this);
        if (getIntent().getBooleanExtra("favorite", false)) {
            this.favoriteBtn.setBackgroundResource(R.drawable.favorite);
        } else {
            this.favoriteBtn.setBackgroundResource(R.drawable.favorite_icon);
        }
        try {
            this.getOneItemPresenter.getItem();
        } catch (Exception e) {
        }
    }

    private void getIntentData() {
        if (getIntent().getStringExtra("title") != null || getIntent().getStringExtra("deal_image") != null) {
            this.title = getIntent().getStringExtra("title");
            this.imageUrl = getIntent().getStringExtra("deal_image");
            this.titleTxt.setText(this.title);
            GlideApp.with((FragmentActivity) this).load(this.imageUrl).into(this.dealImage);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.imageView5_back) {
            finish();
        } else if (id == R.id.imageView7) {
            this.itemFavoritePresenter.makeItemFavorite();
        }
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onDetach() {
    }

    @Override // quadrant.mokafat.points.view.sections.inside_items.ItemFavoriteView
    public String getItemId() {
        if (getIntent().getStringExtra(FirebaseAnalytics.Param.ITEM_ID) != null) {
            return getIntent().getStringExtra(FirebaseAnalytics.Param.ITEM_ID);
        }
        return getIntent().getStringExtra(FirebaseAnalytics.Param.ITEM_ID);
    }

    @Override // quadrant.mokafat.points.view.sections.inside_items.ItemFavoriteView
    public void showFavorResponse(FavoriteResponse favoriteResponse) {
        if (favoriteResponse.getMessage().matches("Item has been favorite successfully")) {
            this.favoriteBtn.setBackgroundResource(R.drawable.favorite);
        } else {
            this.favoriteBtn.setBackgroundResource(R.drawable.favorite_icon);
        }
        Toast.makeText(this, "" + favoriteResponse.getMessage(), 0).show();
    }

    @Override // quadrant.mokafat.points.view.sections.inside_items.ItemFavoriteView
    public void showErrorMessage(String error) {
        Toast.makeText(this, error, 0).show();
    }

    @Override // quadrant.mokafat.points.view.sections.inside_items.GetOneItemView
    public String getItemIdForItem() {
        if (getIntent().getStringExtra(FirebaseAnalytics.Param.ITEM_ID) != null) {
            return getIntent().getStringExtra(FirebaseAnalytics.Param.ITEM_ID);
        }
        return getIntent().getStringExtra(FirebaseAnalytics.Param.ITEM_ID);
    }

    @Override // quadrant.mokafat.points.view.sections.inside_items.GetOneItemView
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, 0).show();
    }

    @Override // quadrant.mokafat.points.view.sections.inside_items.GetOneItemView
    public void showFailMessage(String message) {
        Toast.makeText(this, message, 0).show();
    }

    @Override // quadrant.mokafat.points.view.sections.inside_items.GetOneItemView
    public void showResponse(DataForItem dataForItem) {
        this.title = dataForItem.getData().getAttributes().getTitle();
        this.imageUrl = dataForItem.getData().getAttributes().getMain_image();
        this.titleTxt.setText(this.title);
        GlideApp.with((FragmentActivity) this).load(this.imageUrl).into(this.dealImage);
        GlideApp.with((FragmentActivity) this).load(dataForItem.getData().getRelationships().getVendor().getAttributes().getLogo()).into(this.vendorImage);
        this.mokafatPrice.setText(dataForItem.getData().getAttributes().getFinal_price());
        this.oldPrice.setText(dataForItem.getData().getAttributes().getOld_price());
        this.titleTxtItem.setText(dataForItem.getData().getAttributes().getTitle());
        this.textView_summary.setText(dataForItem.getData().getAttributes().getSummary());
        this.txtStartDate.setText(dataForItem.getData().getAttributes().getStart_date());
        this.txtEndDate.setText(dataForItem.getData().getAttributes().getEnd_date());
        this.textView_Details.setText(dataForItem.getData().getAttributes().getContent());
        this.linearLayoutFindPartner.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.sections.inside_items.ItemsInsideSubsectionsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent intent = new Intent(ItemsInsideSubsectionsActivity.this, (Class<?>) SinglePartnerActivity.class);
                intent.putExtra("partner_title", "" + ItemsInsideSubsectionsActivity.this.titleTxt.getText().toString());
                ItemsInsideSubsectionsActivity.this.startActivity(intent);
            }
        });
    }
}
