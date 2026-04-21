package quadrant.mokafat.points.view.profile.favorite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.models.objects.favorite.DataObjectDetails;

/* JADX INFO: loaded from: classes.dex */
public class FavoritessAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<DataObjectDetails> list;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(DataObjectDetails dataObjectDetails);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView favoriteImage;
        public TextView finalPriceTxt;
        public TextView sectionTxt;
        public TextView titleTxt;
        public TextView vendorTxt;

        public MyViewHolder(View view) {
            super(view);
            this.titleTxt = (TextView) view.findViewById(R.id.text_favorite_item_title);
            this.vendorTxt = (TextView) view.findViewById(R.id.text_favorite_vendor_title);
            this.sectionTxt = (TextView) view.findViewById(R.id.text_favorite_section_title);
            this.finalPriceTxt = (TextView) view.findViewById(R.id.text_favorite_price_title);
            this.favoriteImage = (ImageView) view.findViewById(R.id.imageView_favorite_in_favorite);
        }

        public void bind(final DataObjectDetails item, final OnItemClickListener listener) {
            this.favoriteImage.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.profile.favorite.FavoritessAdapter.MyViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public FavoritessAdapter(List<DataObjectDetails> list, Context context, OnItemClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.titleTxt.setText(this.list.get(position).getAttributes().getTitle());
        holder.vendorTxt.setText(this.list.get(position).getAttributes().getVendor());
        holder.sectionTxt.setText(this.list.get(position).getAttributes().getSection());
        holder.finalPriceTxt.setText(this.list.get(position).getAttributes().getFinal_price());
        holder.bind(this.list.get(position), this.listener);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }
}
