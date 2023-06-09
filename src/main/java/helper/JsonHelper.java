package helper;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;

public class JsonHelper {

    private Map<String, String> elementMap = new HashMap<String, String>();
    public JsonHelper(String jsonFilePath) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(new FileReader(jsonFilePath));
            JSONObject elements = (JSONObject) json.get("elements");
            for (Object key : elements.keySet()) {
                String elementKey = (String) key;
                String elementValue = (String) elements.get(elementKey);
                elementMap.put(elementKey, elementValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public By getElementSelector(String elementKey) {
        return By.xpath(elementMap.get(elementKey));
    }
}
