package hexlet.code;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SrtingSchemaTest {
    @Test
    public void testRequired() {
        var v = new Validator();
        String str = "string";
        StringSchema schema = v.string();
        assertTrue(schema.required().isValid(str));
        assertFalse(schema.required().isValid(""));
        assertFalse(schema.required().isValid(null));
    }
    @Test
    public void testMinLength() {
        var v = new Validator();
        StringSchema schema = v.string();
        String str = "string";
        assertTrue(schema.minLength(4).isValid(str));
        assertFalse(schema.minLength(8).isValid(str));
    }
    @Test
    public void testContains() {
        var v = new Validator();
        StringSchema schema = v.string();
        String str = "string";
        assertTrue(schema.contains("tri").isValid(str));
        assertFalse(schema.contains("tro").isValid(str));
    }
    @Test
    public void testAccum() {
        var v = new Validator();
        StringSchema schema = v.string();
        String str = "hello";
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
        schema.required();
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(str));
        schema.minLength(4);
        assertTrue(schema.isValid(str));
        schema.contains("ell");
        assertTrue(schema.isValid(str));
        schema.contains("hex");
        assertFalse(schema.isValid(str));
    }
    @Test
    public void testAll() {
        var v = new Validator();
        StringSchema schema = v.string();
        String str = "hexlet";
        assertTrue(schema.required().minLength(3).contains("exl").isValid(str));
        assertFalse(schema.required().minLength(7).contains("let").isValid(str));
    }
}
