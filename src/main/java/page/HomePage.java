package page;

import helper.JsonHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private JsonHelper elementMap = new JsonHelper("src/test/resources/config/pages/home.json");
    private static final Logger logger = Logger.getLogger(HomePage.class);


    public By locator(String key){
        return elementMap.getElementSelector(key);
    }



    public void search(String searchValue){
        click(locator("accept cookies button"));
        type(locator("search input"),searchValue);
        click(locator("search button"));
    }

    public void selectRandomElement(){
        List<WebElement> elements = findAll(locator("search result items"));
        if (elements.size() > 0) {
            logger.info( "Item found with given XPath.");
            Random random = new Random();
            int randomIndex = random.nextInt(elements.size());
            WebElement randomElement = elements.get(randomIndex);
            scrollElement(randomElement);
            randomElement.click();
        } else {
            logger.info( "No elements found with the given XPath.");
        }
    }

}
