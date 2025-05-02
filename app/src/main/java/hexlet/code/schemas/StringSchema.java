package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        setRequired(true);
        addCheck("required", str -> str != null && !str.trim().isEmpty());
        return this;
    }

    public StringSchema minLength(int size) {
        addCheck("minLength", str -> str.length() >= size);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains", str -> str.contains(substring));
        return this;
    }
}
