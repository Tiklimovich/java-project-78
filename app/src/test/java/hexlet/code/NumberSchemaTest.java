package hexlet.code;

import org.junit.jupiter.api.Test;
import hexlet.code.schemas.NumberSchema;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    @Test
    public void testRequired() {
        var v = new Validator();
        NumberSchema schema = v.number();
        assertTrue(schema.isValid(null));
        assertFalse(schema.required().isValid(null));
    }
    @Test
    public void testPositive() {
        var v = new Validator();
        NumberSchema schema = v.number();
        assertTrue(schema.positive().isValid(5));
        assertFalse(schema.positive().isValid(-2));
    }
    @Test
    public void testRange() {
        var v = new Validator();
        NumberSchema schema = v.number();
        assertTrue(schema.range(1, 9).isValid(7));
        assertFalse(schema.range(1, 6).isValid(7));
    }
    @Test
    public void testAccum() {
        var v = new Validator();
        NumberSchema schema = v.number();
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(2));
        assertTrue(schema.isValid(-2));
        schema.positive();
        schema.range(2, 5);
        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid(6));
    }
    @Test
    public void testAll() {
        var v = new Validator();
        NumberSchema schema = v.number();
        assertTrue(schema.required().positive().range(1, 3).isValid(2));
        assertFalse(schema.required().positive().range(4, 6).isValid(3));
        assertFalse(schema.required().positive().range(7, 9).isValid(-8));
    }
}
