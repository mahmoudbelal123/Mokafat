package quadrant.mokafat.points.view.vouchers_tiers;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.helper.GlideApp;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_vouchers.DataObjectDetails;

/* JADX INFO: loaded from: classes.dex */
public class TiersAdapter extends RecyclerView.Adapter<TiersAdapter.MyViewHolder> {
    private static LayoutInflater inflater = null;
    private Context context;
    private List<DataObjectDetails> listTiers;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView tierImage;

        public MyViewHolder(View view) {
            super(view);
            this.tierImage = (ImageView) view.findViewById(R.id.imageView_tier);
        }
    }

    public TiersAdapter(List<DataObjectDetails> listTiers, Context context) {
        this.listTiers = listTiers;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tier, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        GlideApp.with(this.context).load("http://199.247.4.89/mokafat/dev/public/uploads/large/" + this.listTiers.get(position).getAttributes().getMain_image()).into(holder.tierImage);
        holder.tierImage.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.vouchers_tiers.TiersAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                final Dialog dialog = new Dialog(TiersAdapter.this.context);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dialog.setContentView(R.layout.dialog_get_voucher);
                dialog.setCanceledOnTouchOutside(false);
                dialog.getWindow().setSoftInputMode(3);
                dialog.getWindow().setLayout(-1, -2);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                TextView yes = (TextView) dialog.findViewById(R.id.dialog_yes);
                yes.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.vouchers_tiers.TiersAdapter.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        if (((DataObjectDetails) TiersAdapter.this.listTiers.get(position)).getAttributes().getTotal_points() != null && Utilities.retrieveUserInfoSharedPreferences(TiersAdapter.this.context).getData().getTotal_points() != null && Integer.parseInt(((DataObjectDetails) TiersAdapter.this.listTiers.get(position)).getAttributes().getTotal_points()) < Integer.parseInt(Utilities.retrieveUserInfoSharedPreferences(TiersAdapter.this.context).getData().getTotal_points())) {
                            Toast.makeText(TiersAdapter.this.context, "there is no enough points to get this voucher", 1).show();
                            Toast.makeText(TiersAdapter.this.context, "there is no enough points to get this voucher", 0).show();
                            dialog.dismiss();
                        }
                    }
                });
                TextView cancel = (TextView) dialog.findViewById(R.id.dialog_cancel);
                cancel.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.vouchers_tiers.TiersAdapter.1.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.listTiers.size();
    }
}
