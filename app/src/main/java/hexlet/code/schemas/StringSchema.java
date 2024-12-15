package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        Predicate<String> required = str -> str != null && !str.trim().isEmpty();
        allChecks.add(required);
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

