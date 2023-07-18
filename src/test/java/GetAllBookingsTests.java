import api.RestClientSteps;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import services.DictionaryService;


public class GetAllBookingsTests
{
    @Test
    public void getAllBookingTest(){
        DictionaryService dictionaryService = new DictionaryService();
        dictionaryService.putVariable("my id",1070);
        Response response = RestClientSteps.callRestService("getBooker");

        response.prettyPrint();
        String jsonResponse = response.getBody().asString();

        JsonPath jsonPath = new JsonPath(jsonResponse);
        String test = jsonPath.getString("lastname");
        System.out.println(response.getBody().asString());
        System.out.println(test);
        Assert.assertEquals(200, response.getStatusCode());
    }

}
