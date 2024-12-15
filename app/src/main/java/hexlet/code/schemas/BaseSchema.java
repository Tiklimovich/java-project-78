package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected final List<Predicate<T>> allChecks = new ArrayList<>();

    public boolean isValid(T obj) {
        if (allChecks.isEmpty()) {
            return true;
        }
        for (Predicate<T> predicate : allChecks) {
            if (!predicate.test(obj)) {
                return false;
            }
        }
        return true;
    }
}
