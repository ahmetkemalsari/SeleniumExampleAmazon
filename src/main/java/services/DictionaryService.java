package services;
import java.util.HashMap;
import java.util.Map;

public class DictionaryService {
    private static final Map<String, Object> dictionary = new HashMap<>();

    public static void putVariable(String key, Object value) {
        dictionary.put(key, value);
    }

    public static Object getVariable(String key) {
        return dictionary.get(key);
    }

    public static <T> T getVariableAs(String key, Class<T> clazz) {
        Object value = dictionary.get(key);
        if (value != null && clazz.isInstance(value)) {
            return clazz.cast(value);
        }
        return null;
    }
}