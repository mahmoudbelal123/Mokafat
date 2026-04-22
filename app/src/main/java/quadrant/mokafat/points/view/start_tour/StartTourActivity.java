package quadrant.mokafat.points.view.start_tour;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.view.login.LoginActivity;
import quadrant.mokafat.points.view.start_tour.slider.SliderActivity;

/* JADX INFO: loaded from: classes.dex */
public class StartTourActivity extends AppCompatActivity implements View.OnClickListener {
    private AlphaAnimation buttonClick = new AlphaAnimation(2.0f, 0.8f);
    private TextView mSkipTxt;
    private Button mStartTour;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_tour);
        this.mStartTour = (Button) findViewById(R.id.button_start_tour);
        this.mStartTour.setOnClickListener(this);
        this.mSkipTxt = (TextView) findViewById(R.id.textView_skip_to_sign_in);
        this.mSkipTxt.setOnClickListener(this);
        this.mSkipTxt.setTextColor(getResources().getColor(R.color.liver));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_start_tour) {
            this.mStartTour.startAnimation(this.buttonClick);
            startActivity(new Intent(this, (Class<?>) SliderActivity.class));
        } else if (id == R.id.textView_skip_to_sign_in) {
            this.mSkipTxt.startAnimation(this.buttonClick);
            this.mSkipTxt.setTextColor(getResources().getColor(R.color.brickred));
            startActivity(new Intent(this, (Class<?>) LoginActivity.class));
        }
    }
}
