package api;

import api.model.ApiRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ApiUtils {
    private static final String BASE_API_REQUEST_PATH = "config/rest-clients/";

    public static ApiRequest getApiRequest(String requestName) throws IOException {


        String filePath = BASE_API_REQUEST_PATH + requestName + ".json";
        InputStream contentStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(contentStream, ApiRequest.class);
    }
}
