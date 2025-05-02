package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, String>> {

    public MapSchema required() {
        setRequired(true);
        addCheck("required", map -> map != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", map -> map.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        addCheck("shape", map -> {
            for (var entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<String> schema = entry.getValue();
                String value = map.get(key);
                if (!schema.isValid(value)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
