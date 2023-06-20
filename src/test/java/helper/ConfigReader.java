package helper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;

public class ConfigReader {
    private JsonObject config;
    private Gson gson;

    public ConfigReader(String filePath) {
        try {
            JsonParser parser = new JsonParser();
            this.config = parser.parse(new FileReader(filePath)).getAsJsonObject();
            this.gson = new Gson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBaseUrl(String key) {
        return config.getAsJsonObject("baseUrls").get(key).getAsString();
    }

    public String getDefaultBrowserType() {
        return config.getAsJsonObject("web").getAsJsonObject("browsers")
                .getAsJsonObject("default").get("type").getAsString();
    }

    public String getDefaultLanguage() {
        return config.getAsJsonObject("web").getAsJsonObject("browsers")
                .getAsJsonObject("default").get("language").getAsString();
    }

    public boolean isHeadless() {
        return config.getAsJsonObject("web").getAsJsonObject("browsers")
                .getAsJsonObject("default").get("headless").getAsBoolean();
    }


    public int getPageLoadTimeout() {
        return config.getAsJsonObject("web").get("pageLoadTimeout").getAsInt();
    }

    public int getElementTimeout() {
        return config.getAsJsonObject("web").get("elementTimeout").getAsInt();
    }
}
