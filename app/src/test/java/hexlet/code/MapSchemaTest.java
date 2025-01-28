package hexlet.code;

import org.junit.jupiter.api.Test;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    @Test
    public void testRequired() {
        var v = new Validator();
        MapSchema schema = v.map();

        assertTrue(schema.isValid(null));
        assertTrue(schema.required().isValid(new HashMap<>()));
        assertFalse(schema.isValid(null));

        HashMap<String, String> data = new HashMap<>();
        data.put("key", "value");

        assertTrue(schema.isValid(data));
    }
    @Test
    public void testSizeof() {
        var v = new Validator();
        MapSchema schema = v.map();
        HashMap<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");

        assertTrue(schema.sizeof(2).isValid(data));
        assertFalse(schema.sizeof(1).isValid(data));
    }
    @Test
    public void testShape() {
        var v = new Validator();
        MapSchema mapSchema = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        mapSchema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(mapSchema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(mapSchema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(mapSchema.isValid(human3));
    }
}
