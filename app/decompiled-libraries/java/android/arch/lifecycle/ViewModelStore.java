package android.arch.lifecycle;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ViewModelStore {
    private final HashMap<String, ViewModel> mMap = new HashMap<>();

    final void put(String key, ViewModel viewModel) {
        ViewModel oldViewModel = this.mMap.get(key);
        if (oldViewModel != null) {
            oldViewModel.onCleared();
        }
        this.mMap.put(key, viewModel);
    }

    final ViewModel get(String key) {
        return this.mMap.get(key);
    }

    public final void clear() {
        for (ViewModel vm : this.mMap.values()) {
            vm.onCleared();
        }
        this.mMap.clear();
    }
}
