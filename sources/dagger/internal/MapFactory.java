package dagger.internal;

import java.util.Collections;
import java.util.Map;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class MapFactory<K, V> implements Factory<Map<K, V>> {
    private final Map<K, Provider<V>> contributingMap;

    private MapFactory(Map<K, Provider<V>> map) {
        this.contributingMap = Collections.unmodifiableMap(map);
    }

    public static <K, V> MapFactory<K, V> create(Provider<Map<K, Provider<V>>> mapProviderFactory) {
        Map<K, Provider<V>> map = mapProviderFactory.get();
        return new MapFactory<>(map);
    }

    @Override // javax.inject.Provider
    public Map<K, V> get() {
        Map<K, V> result = DaggerCollections.newLinkedHashMapWithExpectedSize(this.contributingMap.size());
        for (Map.Entry<K, Provider<V>> entry : this.contributingMap.entrySet()) {
            result.put(entry.getKey(), entry.getValue().get());
        }
        return Collections.unmodifiableMap(result);
    }
}
