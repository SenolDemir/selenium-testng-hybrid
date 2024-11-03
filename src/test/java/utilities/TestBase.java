package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class TestBase {

    public WebDriver driver;
    public Logger logger;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser){

        logger = LogManager.getLogger(this.getClass());

        switch(browser.toLowerCase()){
            case "chrome":driver = new ChromeDriver(); break;
            case "chrome-headless": driver = new ChromeDriver(new ChromeOptions().addArguments("--headless")); break;
            case "firefox": driver = new FirefoxDriver(); break;
            case "safari": driver = new SafariDriver(); break;
            default:  System.out.println("Invalid browser name'"); break;
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        driver.get(ConfigurationReader.get("url"));
        driver.manage().window().maximize();


    }

    @AfterMethod
    public void tearDown(){

        driver.quit();

    }
}
