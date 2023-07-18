package api;

import api.model.ApiRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import services.DictionaryService;


import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

public class ApiUtils {
    private static final String BASE_API_REQUEST_PATH = "config/rest-clients/";

    public static ApiRequest getApiRequest(String requestName) throws IOException {
        String filePath = BASE_API_REQUEST_PATH + requestName + ".json";
        InputStream contentStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(contentStream);

        // JSON dosyasındaki alanlara erişim için JsonNode'u dolaşarak değiştirme yapacağız
        replacePlaceholderWithDictionaryValue(rootNode);

        // Değiştirilmiş JSON dosyasını kullanarak ApiRequest nesnesini oluşturuyoruz
        return objectMapper.treeToValue(rootNode, ApiRequest.class);
    }

    private static void replacePlaceholderWithDictionaryValue(JsonNode node) {
        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;
            Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                JsonNode value = entry.getValue();
                if (value.isTextual()) {
                    String textValue = value.asText();
                    if (textValue.contains("{the ")) {
                        int startIndex = textValue.indexOf("{the ");
                        int endIndex = textValue.indexOf("}", startIndex);
                        if (endIndex != -1) {
                            String key = textValue.substring(startIndex + 5, endIndex).trim();
                            Object dictionaryValue = DictionaryService.getVariable(key);
                            if (dictionaryValue != null) {
                                String replacedValue = textValue.replace("{the " + key + "}", dictionaryValue.toString());
                                objectNode.put(entry.getKey(), replacedValue);
                            }
                        }
                    }
                }
                replacePlaceholderWithDictionaryValue(value);
            }
        } else if (node.isArray()) {
            node.forEach(ApiUtils::replacePlaceholderWithDictionaryValue);
        }
    }
}