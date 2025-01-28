package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;


public class MapSchema extends BaseSchema<Map<String, String>> {
    public MapSchema required() {
        Predicate<Map<String, String>> required  = Objects::nonNull;
        allChecks.put("required", required);
        return this;
    }
    public MapSchema sizeof(int size) {
        Predicate<Map<String, String>> sizeof = map -> map.size() == size;
        allChecks.put("sizeof", sizeof);
        return this;
    }
    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        Predicate<Map<String, String>> shapeCheck = map -> {
            for (var entry : schemas.entrySet()) {
                String key = entry.getKey();
                String value = map.get(key);
                BaseSchema<String> schema = entry.getValue();
                if (!schema.isValid(value)) {
                    return false;
                }
            }
            return true;
        };
        allChecks.put("shape", shapeCheck);
        return this;
    }

}
