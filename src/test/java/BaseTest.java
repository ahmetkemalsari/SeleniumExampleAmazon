import helper.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BaseTest {

    WebDriver driver ;

    @BeforeAll
    public void setUp() {
        ConfigReader configReader = new ConfigReader("src/test/resources/config/environments/dev.json");
        String myWebsiteUrl = configReader.getBaseUrl("webSiteUrl");
        String defaultBrowserType = configReader.getDefaultBrowserType();
        String language = configReader.getDefaultLanguage();
        int pageLoadTimeout = configReader.getPageLoadTimeout();
        int elementLoadTimeout = configReader.getElementTimeout();
        boolean isHeadless = configReader.isHeadless();

        WebDriverManager.chromedriver().setup();


        if (defaultBrowserType.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--lang=" + language);
            options.setHeadless(isHeadless);
            driver = new ChromeDriver(options);
            options.setHeadless(isHeadless);

        } else {
            throw new UnsupportedOperationException("Unsupported browser type: " + defaultBrowserType);
        }
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout).toMillis(), TimeUnit.MILLISECONDS);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(elementLoadTimeout).toMillis(), TimeUnit.MILLISECONDS);


        driver.get(myWebsiteUrl);
        driver.manage().window().maximize();
    }

    @AfterAll
    public void tearDown(){
        driver.quit();
    }
}
