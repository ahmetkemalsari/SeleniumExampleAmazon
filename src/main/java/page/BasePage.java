package page;

import helper.JsonHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    WebDriver driver ;


    public BasePage(WebDriver driver){
        this.driver = driver ;
    }

    public WebElement find(By locator){
        return driver.findElement(locator);
    }

    public List<WebElement> findAll(By locator){
        return driver.findElements(locator);
    }

    public void click(By locator){
        find(locator).click();
    }

    public void type(By locator , String text){
        find(locator).sendKeys(text);
    }

    public Boolean isDisplayed(By locator){
        return find(locator).isDisplayed();
    }

    public void wait(By locator,int time){
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isTruePage(String expectedUrl) {
        String currentPage = driver.getCurrentUrl();
        return currentPage.equals(expectedUrl);
    }

    public void scrollElementByLocator(By locator){

            WebElement  webElement = driver.findElement(locator);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})", webElement);

    }
    public void scrollElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})", element);
    }

    public void enterText(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.sendKeys(text);
    }

    public String getText(By locator) {
        WebElement element = driver.findElement(locator);
        return element.getText();
    }

    public boolean isElementDisplayed(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public void navigateToURL(String url) {
        driver.get(url);
    }
    public String getPageTitle() {
        return driver.getTitle();
    }

    public void waitForPageLoad() {

    }

    public static void staticWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void clickByText(String text) {
        WebElement element = driver.findElement(By.xpath("//*[text()='" + text + "']"));
        element.click();
    }
    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void clearText(By locator) {
        WebElement element = driver.findElement(locator);
        element.clear();
    }

    public boolean isElementSelected(By locator) {
        WebElement element = driver.findElement(locator);
        return element.isSelected();
    }

    public String getAttribute(By locator, String attributeName) {
        WebElement element = driver.findElement(locator);
        return element.getAttribute(attributeName);
    }

    public String getCssValue(By locator, String propertyName) {
        WebElement element = driver.findElement(locator);
        return element.getCssValue(propertyName);
    }

    public Dimension getElementSize(By locator) {
        WebElement element = driver.findElement(locator);
        return element.getSize();
    }

    public Point getElementLocation(By locator) {
        WebElement element = driver.findElement(locator);
        return element.getLocation();
    }

    public void hoverOverElement(By locator) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element).perform();
    }

    public void rightClickElement(By locator) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(locator);
        actions.contextClick(element).perform();
    }

    public void dragAndDrop(By sourceLocator, By targetLocator) {
        Actions actions = new Actions(driver);
        WebElement sourceElement = driver.findElement(sourceLocator);
        WebElement targetElement = driver.findElement(targetLocator);
        actions.dragAndDrop(sourceElement, targetElement).perform();
    }


}
