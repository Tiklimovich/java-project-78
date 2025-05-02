package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected final Map<String, Predicate<T>> allChecks = new LinkedHashMap<>();
    private boolean isRequired = false;

    public final boolean isValid(T obj) {
        if (!isRequired && obj == null) {
            return true;
        }

        for (Predicate<T> check : allChecks.values()) {
            if (!check.test(obj)) {
                return false;
            }
        }

        return true;
    }

    protected void setRequired(boolean required) {
        this.isRequired = required;
    }

    protected void addCheck(String name, Predicate<T> predicate) {
        allChecks.put(name, predicate);
    }
}
