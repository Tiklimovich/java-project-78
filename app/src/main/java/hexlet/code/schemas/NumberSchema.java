package hexlet.code.schemas;

import java.util.function.Predicate;
import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        Predicate<Integer> required = Objects::nonNull;
        allChecks.add(required);
        return this;
    }
    public NumberSchema positive() {
        Predicate<Integer> positive = value -> value > 0;
        allChecks.add(positive);
        return this;
    }
    public NumberSchema range(int from, int to) {
        Predicate<Integer> range = value -> value >= from && value <= to;
        allChecks.add(range);
        return this;
    }
}
