package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.Objects;

public abstract class BaseSchema<T> {

    protected Map<String, Predicate<T>> allChecks = new LinkedHashMap<>();
    public final void addNonNullCheck() {
        allChecks.put("nonNull", Objects::nonNull);
    }

    public final boolean isValid(T obj) {
        if (allChecks.isEmpty()) {
            return true;
        }
        return allChecks.values().stream()
                .allMatch(predicate -> predicate.test(obj));
    }
}
