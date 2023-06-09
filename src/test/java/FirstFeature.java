
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import page.HomePage;

public class FirstFeature extends BaseTest {

    HomePage homePage ;


    @Test
    @Order(1)
    public void firstScenario() {
        homePage = new HomePage(driver);
        homePage.search("telefon");

    }

}

