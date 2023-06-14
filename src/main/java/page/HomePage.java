package page;

import helper.JsonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private JsonHelper elementMap = new JsonHelper("src/test/resources/config/pages/home.json");

    public By locator(String key){
        return elementMap.getElementSelector(key);
    }



    public void search(String searchValue){
        type(locator("search input"),searchValue);
        click(locator("search button"));
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
