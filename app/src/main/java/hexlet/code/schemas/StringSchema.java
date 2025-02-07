package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addNonNullCheck();
        Predicate<String> required = str -> !str.trim().isEmpty();
        allChecks.put("required", required);
        return this;
    }

    public StringSchema minLength(int size) {
        Predicate<String> minLength = str -> str.length() >= size;
        allChecks.put("minLength", minLength);
        return this;
    }

    public StringSchema contains(String str) {
        Predicate<String> contains = s -> s.contains(str);
        allChecks.put("contains", contains);
        return this;
    }
}
