package quadrant.mokafat.points.view.help;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import quadrant.mokafat.points.R;

/* JADX INFO: loaded from: classes.dex */
public class HelpInnerOneActivity extends AppCompatActivity implements View.OnTouchListener {
    private ImageView mBackToolbar;
    private TextView mTitleHeader;
    private TextView mTitleToolbar;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_inner_one);
        this.mTitleHeader = (TextView) findViewById(R.id.textView_header);
        this.mTitleToolbar = (TextView) findViewById(R.id.toolbar_title);
        this.mBackToolbar = (ImageView) findViewById(R.id.toolbar_back);
        this.mBackToolbar.setOnTouchListener(this);
        this.mTitleToolbar.setText("Help");
        if (getIntent().getStringExtra("header") != null) {
            String header = getIntent().getStringExtra("header").toString();
            this.mTitleHeader.setText(header);
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.toolbar_back) {
            finish();
            return false;
        }
        return false;
    }
}
