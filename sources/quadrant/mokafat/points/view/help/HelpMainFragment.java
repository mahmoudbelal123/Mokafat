package quadrant.mokafat.points.view.help;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import quadrant.mokafat.points.R;

/* JADX INFO: loaded from: classes.dex */
public class HelpMainFragment extends Fragment implements View.OnClickListener {
    private LinearLayout mLinearRewards;
    private TextView mRewardsTxt;
    private View mView;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_help, container, false);
        this.mLinearRewards = (LinearLayout) this.mView.findViewById(R.id.linear_rewards);
        this.mRewardsTxt = (TextView) this.mView.findViewById(R.id.textView_rewards);
        this.mLinearRewards.setOnClickListener(this);
        return this.mView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() == R.id.linear_rewards) {
            startHelpInnerActivity();
        }
    }

    private void startHelpInnerActivity() {
        Intent intent = new Intent(getActivity(), (Class<?>) HelpInnerOneActivity.class);
        intent.putExtra("header", this.mRewardsTxt.getText().toString());
        startActivity(intent);
    }
}
