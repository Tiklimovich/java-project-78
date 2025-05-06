package hexlet.code.schemas;
import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        setRequired(true);
        addCheck("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive",  number -> number == 0 ||  number > 0);
        return this;
    }

    public NumberSchema range(int from, int to) {
        addCheck("range", number -> number >= from && number <= to);
        return this;
    }
}
