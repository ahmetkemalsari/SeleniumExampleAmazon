package page;

import helper.JsonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private JsonHelper elementMap = new JsonHelper("src/main/resources/pages/home.json");

    public By locator(String key){
        return elementMap.getElementSelector(key);
    }



    public void search(String searchValur){
        type(locator("search input"),searchValur);
        click(locator("search button"));

    }

}
