package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class TestBase {

    public WebDriver driver;
    public Logger logger;

    @BeforeClass
    public void setUp(){

        logger = LogManager.getLogger(this.getClass());

        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        driver.get(ConfigurationReader.get("url"));
        driver.manage().window().maximize();


    }

    @AfterClass
    public void tearDown(){

        driver.quit();

    }
}
