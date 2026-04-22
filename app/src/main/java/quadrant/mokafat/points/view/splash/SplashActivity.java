package quadrant.mokafat.points.view.splash;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.apiClient.EndPoints;
import quadrant.mokafat.points.view.start_tour.StartTourActivity;

/* JADX INFO: loaded from: classes.dex */
public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mWebSiteTxt;
    private Intent myIntent;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.myIntent = new Intent(this, (Class<?>) StartTourActivity.class);
        this.mWebSiteTxt = (TextView) findViewById(R.id.textView_mokafat_web_site);
        this.mWebSiteTxt.setOnClickListener(this);
        new Handler().postDelayed(new Runnable() { // from class: quadrant.mokafat.points.view.splash.SplashActivity.1
            @Override // java.lang.Runnable
            public void run() {
                SplashActivity.this.startActivity(SplashActivity.this.myIntent);
                SplashActivity.this.finish();
            }
        }, 2000L);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() == R.id.textView_mokafat_web_site) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(EndPoints.MOKAFAT_URL));
            startActivity(intent);
        }
    }
}
