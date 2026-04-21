package quadrant.mokafat.points.view.reset_password;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import quadrant.mokafat.points.R;

/* JADX INFO: loaded from: classes.dex */
public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mBackTxt;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        this.mBackTxt = (TextView) findViewById(R.id.textView_forget_password_back);
        this.mBackTxt.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() == R.id.textView_forget_password_back) {
            onBackPressed();
        }
    }
}
