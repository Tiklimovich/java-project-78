package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema {
    private final Predicate<String> requiredCheck = str -> str != null && !str.isBlank();
    private final List<Predicate<String>> allChecks = new ArrayList<>();

    public boolean isValid(String obj) {
        if (obj == null) {
            // Проверяем, установлено ли правило required
            if (allChecks.contains(requiredCheck)) {
                return false;
            }
            return true;
        }
        if (allChecks.isEmpty()) {
            return true;
        }
        for (Predicate<String> predicate : allChecks) {
            if (!predicate.test(obj)) {
                return false;
            }
        }
        return true;
    }

    public StringSchema required() {
        allChecks.add(requiredCheck);
        return this;
    }

    public StringSchema minLength(int size) {
        Predicate<String> minLength = str -> str != null && str.length() >= size;
        allChecks.add(minLength);
        return this;
    }

    public StringSchema contains(String str) {
        Predicate<String> contains = s -> s != null && s.contains(str);
        allChecks.add(contains);
        return this;
    }
}
