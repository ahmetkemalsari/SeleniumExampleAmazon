import api.RestClientSteps;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;



public class GetAllBookingsTests
{
    @Test
    public void getAllBookingTest(){
        Response response = RestClientSteps.callRestService("getBooker");

        String jsonResponse = response.getBody().asString();

        JsonPath jsonPath = new JsonPath(jsonResponse);
        String test = jsonPath.getString("lastname");
        System.out.println(response.getBody().asString());
        System.out.println(test);
        Assert.assertEquals(200, response.getStatusCode());
    }

}
