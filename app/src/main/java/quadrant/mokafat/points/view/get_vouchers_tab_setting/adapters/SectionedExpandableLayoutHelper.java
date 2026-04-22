package quadrant.mokafat.points.view.get_vouchers_tab_setting.adapters;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import quadrant.mokafat.points.models.objects.get_vouchers_tab.AttributesObjectDetails;
import quadrant.mokafat.points.view.get_vouchers_tab_setting.models.Item;

/* JADX INFO: loaded from: classes.dex */
public class SectionedExpandableLayoutHelper implements SectionStateChangeListener {
    RecyclerView mRecyclerView;
    private SectionedExpandableGridAdapter mSectionedExpandableGridAdapter;
    private LinkedHashMap<AttributesObjectDetails, ArrayList<Item>> mSectionDataMap = new LinkedHashMap<>();
    private ArrayList<Object> mDataArrayList = new ArrayList<>();
    private HashMap<String, AttributesObjectDetails> mSectionMap = new HashMap<>();

    public SectionedExpandableLayoutHelper(Context context, RecyclerView recyclerView, ItemClickListener itemClickListener, int gridSpanCount) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, gridSpanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        this.mSectionedExpandableGridAdapter = new SectionedExpandableGridAdapter(context, this.mDataArrayList, gridLayoutManager, itemClickListener, this);
        recyclerView.setAdapter(this.mSectionedExpandableGridAdapter);
        this.mRecyclerView = recyclerView;
    }

    public void notifyDataSetChanged() {
        generateDataList();
        this.mSectionedExpandableGridAdapter.notifyDataSetChanged();
    }

    public void addSection(String section, ArrayList<Item> items) {
        HashMap<String, AttributesObjectDetails> map = this.mSectionMap;
        AttributesObjectDetails newSection = new AttributesObjectDetails(section);
        map.put(section, newSection);
        this.mSectionDataMap.put(newSection, items);
    }

    public void addItem(String section, Item item) {
        this.mSectionDataMap.get(this.mSectionMap.get(section)).add(item);
    }

    public void removeItem(String section, Item item) {
        this.mSectionDataMap.get(this.mSectionMap.get(section)).remove(item);
    }

    public void removeSection(String section) {
        this.mSectionDataMap.remove(this.mSectionMap.get(section));
        this.mSectionMap.remove(section);
    }

    private void generateDataList() {
        this.mDataArrayList.clear();
        for (Map.Entry<AttributesObjectDetails, ArrayList<Item>> entry : this.mSectionDataMap.entrySet()) {
            ArrayList<Object> arrayList = this.mDataArrayList;
            AttributesObjectDetails key = entry.getKey();
            arrayList.add(key);
            if (!key.isExpanded) {
                this.mDataArrayList.addAll(entry.getValue());
            }
        }
    }

    @Override // quadrant.mokafat.points.view.get_vouchers_tab_setting.adapters.SectionStateChangeListener
    public void onSectionStateChanged(AttributesObjectDetails section, boolean isOpen) {
        if (!this.mRecyclerView.isComputingLayout()) {
            section.isExpanded = isOpen;
            notifyDataSetChanged();
        }
    }
}
