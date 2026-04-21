package quadrant.mokafat.points.view.get_partners.tabs_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.models.objects.get_vendors.DataObjectDetails;

/* JADX INFO: loaded from: classes.dex */
public class ExpandableAllPartnersAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private HashMap<String, List<DataObjectDetails>> _listDataChild;
    private List<String> _listDataHeader;

    public ExpandableAllPartnersAdapter(Context context, List<String> listDataHeader, HashMap<String, List<DataObjectDetails>> listChildData) {
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
        quadrant.mokafat.points.models.objects.get_vouchers_tab.DataObjectDetails childText = (quadrant.mokafat.points.models.objects.get_vouchers_tab.DataObjectDetails) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService("layout_inflater");
            convertView = infalInflater.inflate(R.layout.item_partner, (ViewGroup) null);
        }
        TextView txtListChild = (TextView) convertView.findViewById(R.id.text_partner_name);
        txtListChild.setText(childText.getAttributes().getProvider());
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
            convertView = infalInflater.inflate(R.layout.list_header_partner, (ViewGroup) null);
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
