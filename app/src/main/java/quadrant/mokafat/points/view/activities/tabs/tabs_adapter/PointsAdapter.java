package quadrant.mokafat.points.view.activities.tabs.tabs_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.models.objects.get_points.DataObjectDetails;

/* JADX INFO: loaded from: classes.dex */
public class PointsAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<DataObjectDetails> listPoints;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView dateTxt;
        public TextView pointsRedeemedTxt;
        public TextView voucherEarnedTxt;

        public MyViewHolder(View view) {
            super(view);
            this.pointsRedeemedTxt = (TextView) view.findViewById(R.id.textView_points_redeemed);
            this.voucherEarnedTxt = (TextView) view.findViewById(R.id.textView_voucher_earned);
            this.dateTxt = (TextView) view.findViewById(R.id.textView_date);
        }
    }

    public PointsAdapter(List<DataObjectDetails> listPoints, Context context) {
        this.listPoints = listPoints;
        this.context = context;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_points, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.pointsRedeemedTxt.setText(this.listPoints.get(position).getTier().get(0).getAttributes().getTotal_points());
        holder.voucherEarnedTxt.setText(this.listPoints.get(position).getTier().get(0).getAttributes().getValue());
        holder.dateTxt.setText(this.listPoints.get(position).getTier().get(0).getAttributes().getCreated_at());
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.listPoints.size();
    }
}
