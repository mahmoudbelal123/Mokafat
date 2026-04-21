package quadrant.mokafat.points.view.get_location_map;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import quadrant.mokafat.points.R;

/* JADX INFO: loaded from: classes.dex */
public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {
    private Context context;
    InfoWindowData info;
    RelativeLayout relativeLayout;

    public CustomInfoWindowGoogleMap(Context ctx, InfoWindowData info) {
        this.context = ctx;
        this.info = info;
    }

    @Override // com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override // com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
    public View getInfoContents(Marker marker) {
        View view = ((Activity) this.context).getLayoutInflater().inflate(R.layout.title_marker, (ViewGroup) null);
        TextView txtName = (TextView) view.findViewById(R.id.text_marker_name);
        TextView txtAddress = (TextView) view.findViewById(R.id.text_marker_address);
        txtName.setText(marker.getTitle());
        txtAddress.setText(marker.getSnippet());
        this.relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_marker);
        this.relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.get_location_map.CustomInfoWindowGoogleMap.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Toast.makeText(CustomInfoWindowGoogleMap.this.context, "test test ", 0).show();
            }
        });
        return view;
    }
}
