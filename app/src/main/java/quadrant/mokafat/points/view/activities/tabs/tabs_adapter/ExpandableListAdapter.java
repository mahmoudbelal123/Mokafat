package quadrant.mokafat.points.view.activities.tabs.tabs_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.models.objects.get_vouchers_tab.DataObjectDetails;

/* JADX INFO: loaded from: classes.dex */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private HashMap<String, List<DataObjectDetails>> _listDataChild;
    private List<String> _listDataHeader;

    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<DataObjectDetails>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        DataObjectDetails childText = (DataObjectDetails) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService("layout_inflater");
            convertView = infalInflater.inflate(R.layout.item_vouchers, (ViewGroup) null);
        }
        TextView txtProvider = (TextView) convertView.findViewById(R.id.text_provider);
        TextView txtCode = (TextView) convertView.findViewById(R.id.text_code);
        TextView txtService = (TextView) convertView.findViewById(R.id.text_service);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.text_price);
        TextView txtStartDate = (TextView) convertView.findViewById(R.id.text_start_date);
        TextView txtEndDate = (TextView) convertView.findViewById(R.id.text_end_date);
        txtProvider.setText(childText.getAttributes().getProvider());
        txtCode.setText(childText.getAttributes().getCode());
        txtService.setText(childText.getAttributes().getTarget());
        txtPrice.setText(childText.getAttributes().getValue());
        txtStartDate.setText(childText.getAttributes().getStart_date());
        txtEndDate.setText(childText.getAttributes().getEnd_date());
        return convertView;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService("layout_inflater");
            convertView = infalInflater.inflate(R.layout.list_header, (ViewGroup) null);
        }
        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, 1);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
