package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema<K, V> extends BaseSchema<Map<K, V>> {

    public MapSchema<K, V> required() {
        addNonNullCheck();
        return this;
    }

    public MapSchema<K, V> sizeof(int size) {
        Predicate<Map<K, V>> sizeof = map -> map.size() == size;
        allChecks.put("sizeof", sizeof);
        return this;
    }

    public MapSchema<K, V> shape(Map<K, BaseSchema<V>> schemas) {
        Predicate<Map<K, V>> shapeCheck = map ->
                schemas.entrySet().stream()
                        .allMatch(entry -> {
                            K key = entry.getKey();
                            V value = map.get(key);
                            BaseSchema<V> schema = entry.getValue();
                            return schema.isValid(value);
                        });
        allChecks.put("shape", shapeCheck);
        return this;
    }
}
