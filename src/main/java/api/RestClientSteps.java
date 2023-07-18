package api;

import api.model.ApiRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RestClientSteps {
    public static Response callRestService(String requestName) {
        try {
            ApiRequest apiRequest = ApiUtils.getApiRequest(requestName);

            RestAssured.baseURI = apiRequest.getUriPath();

            Response response;
            String requestBody;


            switch (apiRequest.getMethod()) {
                case "GET":
                    response = RestAssured.given()
                            .headers(apiRequest.getHeaders())
                            .get();
                    break;
                case "POST":
                     requestBody = getRequestTemplate(apiRequest.getDefaultBodyTemplateJson());
                    response = RestAssured.given()
                            .headers( apiRequest.getHeaders())
                            .body(requestBody)
                            .post();
                    break;
                case "PUT":
                    String putRequestBody = getRequestTemplate(apiRequest.getDefaultBodyTemplateJson());
                    response = RestAssured.given()
                            .headers( apiRequest.getHeaders())
                            .body(putRequestBody)
                            .put();
                    break;
                case "DELETE":
                    response = RestAssured.given()
                            .headers(apiRequest.getHeaders())
                            .delete();
                    break;


                default:
                    throw new IllegalArgumentException("Invalid HTTP method: " + apiRequest.getMethod());
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("API request failed.");
        }
    }

    private static String getRequestTemplate(String templateName) {
        try {
            String filePath = "src/test/resources/config/rest-template/" + templateName + ".json";
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not read request template file.");
        }
    }
}

