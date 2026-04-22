package quadrant.mokafat.points.view.splash;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.apiClient.EndPoints;
import quadrant.mokafat.points.view.start_tour.StartTourActivity;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mWebSiteTxt;
    private Intent myIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.myIntent = new Intent(this, StartTourActivity.class);
        this.mWebSiteTxt = (TextView) findViewById(R.id.textView_mokafat_web_site);
        this.mWebSiteTxt.setOnClickListener(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActivity.this.startActivity(SplashActivity.this.myIntent);
                SplashActivity.this.finish();
            }
        }, 2000L);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.textView_mokafat_web_site) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(EndPoints.MOKAFAT_URL));
            startActivity(intent);
        }
    }
}
