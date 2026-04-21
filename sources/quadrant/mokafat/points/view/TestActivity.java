package quadrant.mokafat.points.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import quadrant.mokafat.points.R;

/* JADX INFO: loaded from: classes.dex */
public class TestActivity extends AppCompatActivity {
    private Button mCrashBtn;
    TextView txt;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebration3);
        this.txt = (TextView) findViewById(R.id.textView11_old);
        this.txt.setPaintFlags(this.txt.getPaintFlags() | 16);
        this.mCrashBtn = (Button) findViewById(R.id.button_test_crash);
    }
}
