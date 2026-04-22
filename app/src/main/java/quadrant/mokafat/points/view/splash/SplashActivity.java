package quadrant.mokafat.points.view.splash;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import quadrant.mokafat.points.apiClient.EndPoints;
import quadrant.mokafat.points.view.start_tour.StartTourActivity;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mWebSiteTxt;
    private Intent myIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Build layout programmatically to avoid R.layout dependency issues
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setBackgroundColor(Color.parseColor("#c42b3a"));

        LinearLayout.LayoutParams rootParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        root.setLayoutParams(rootParams);

        // Logo placeholder
        ImageView logo = new ImageView(this);
        LinearLayout.LayoutParams logoParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        logoParams.weight = 1f;
        logoParams.gravity = Gravity.CENTER;
        logo.setLayoutParams(logoParams);

        int drawableId = getResources().getIdentifier("splash_icon", "drawable", getPackageName());
        if (drawableId != 0) {
            logo.setImageResource(drawableId);
        }

        // Website text
        mWebSiteTxt = new TextView(this);
        mWebSiteTxt.setId(android.R.id.text1);
        mWebSiteTxt.setText("© Copyright Mokafat www.mokafat.com 2018");
        mWebSiteTxt.setTextColor(Color.WHITE);
        mWebSiteTxt.setTextSize(12f);
        mWebSiteTxt.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams txtParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        txtParams.bottomMargin = 32;
        mWebSiteTxt.setLayoutParams(txtParams);
        mWebSiteTxt.setOnClickListener(this);

        root.addView(logo);
        root.addView(mWebSiteTxt);

        setContentView(root);

        myIntent = new Intent(this, StartTourActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(myIntent);
                finish();
            }
        }, 2000L);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(EndPoints.MOKAFAT_URL));
        startActivity(intent);
    }
}
