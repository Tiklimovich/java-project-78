package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        setRequired(true);
        addCheck("required", number -> number != null);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive",  number -> number == null || number > 0);
        return this;
    }

    public NumberSchema range(int from, int to) {
        addCheck("range", number -> number >= from && number <= to);
        return this;
    }
}
