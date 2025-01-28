package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected Map<String, Predicate<T>> allChecks = new LinkedHashMap<>();

    public final boolean isValid(T obj) {
        if (allChecks.isEmpty()) {
            return true;
        }
        for (Predicate<T> predicate : allChecks.values()) {
            if (!predicate.test(obj)) {
                return false;
            }
        }
        return true;
    }
}
