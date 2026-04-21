package quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.helper.GlideApp;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.get_vendors.partnersObject;
import quadrant.mokafat.points.view.single_partner_details.SinglePartnerActivity;

/* JADX INFO: loaded from: classes.dex */
public class VendorsAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<partnersObject> listVendors;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout constraintLayoutRow;
        public ImageView vendorLogo;
        public TextView vendorOffers;
        public TextView vendorTitle;

        public MyViewHolder(View view) {
            super(view);
            this.vendorTitle = (TextView) view.findViewById(R.id.textView30_title_vendor);
            this.vendorLogo = (ImageView) view.findViewById(R.id.imageView16_vendor_logo);
            this.constraintLayoutRow = (LinearLayout) view.findViewById(R.id.partner_item);
        }
    }

    public VendorsAdapter(List<partnersObject> listVendors, Context context) {
        this.listVendors = listVendors;
        this.context = context;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_partner, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.vendorTitle.setText(this.listVendors.get(position).getAttributes().getTitle());
        GlideApp.with(this.context).load2("http://199.247.4.89/mokafat/dev/public/uploads/small/" + this.listVendors.get(position).getAttributes().getLogo()).into(holder.vendorLogo);
        holder.constraintLayoutRow.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners.VendorsAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent intent = new Intent(VendorsAdapter.this.context, (Class<?>) SinglePartnerActivity.class);
                Utilities.VENDOR_ID = ((partnersObject) VendorsAdapter.this.listVendors.get(position)).getId();
                intent.putExtra("partner_title", ((partnersObject) VendorsAdapter.this.listVendors.get(position)).getAttributes().getTitle());
                VendorsAdapter.this.context.startActivity(intent);
            }
        });
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.listVendors.size();
    }
}
