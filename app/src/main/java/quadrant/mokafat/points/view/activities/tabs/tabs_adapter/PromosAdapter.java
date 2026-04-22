package quadrant.mokafat.points.view.activities.tabs.tabs_adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.models.objects.redemptions.DataObjectDetails;

/* JADX INFO: loaded from: classes.dex */
public class PromosAdapter extends RecyclerView.Adapter<PromosAdapter.MyViewHolder> {
    private Context context;
    private List<DataObjectDetails> listPromos;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView earnedPointsTxt;
        public TextView targetTxt;
        public TextView totalPointsTxt;
        public TextView valueTxt;

        public MyViewHolder(View view) {
            super(view);
            this.targetTxt = (TextView) view.findViewById(R.id.text_target);
            this.totalPointsTxt = (TextView) view.findViewById(R.id.text_total_points);
            this.earnedPointsTxt = (TextView) view.findViewById(R.id.text_earned_points);
            this.valueTxt = (TextView) view.findViewById(R.id.text_value);
        }
    }

    public PromosAdapter(List<DataObjectDetails> listPromos, Context context) {
        this.listPromos = listPromos;
        this.context = context;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promos, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.targetTxt.setText(this.listPromos.get(position).getAttributes().getTarget());
        holder.totalPointsTxt.setText(this.listPromos.get(position).getAttributes().getTotal());
        holder.earnedPointsTxt.setText(this.listPromos.get(position).getAttributes().getEarned_points());
        holder.valueTxt.setText(this.listPromos.get(position).getAttributes().getValue());
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.listPromos.size();
    }
}
