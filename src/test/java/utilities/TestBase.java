package utilities;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class TestBase {

    public static WebDriver driver;
    public Logger logger;



    @BeforeClass (groups = {"smoke", "regression"})
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

    @AfterClass (groups = {"smoke", "regression"})
    public void tearDown(){

        driver.quit();

    }


    public String captureScreenShot(String tName) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "/test-output/screenshots/" + tName + timeStamp;
        File targetFile= new File(targetFilePath);

        FileUtils.copyFile(sourceFile, targetFile);
        //sourceFile.renameTo(targetFile);

        return targetFilePath;

    }

}
