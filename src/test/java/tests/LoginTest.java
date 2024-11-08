package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import utilities.ExcelUtils;
import utilities.TestBase;

public class LoginTest extends TestBase {

    /**
     * driver instance and browser version is configured by xml file.
     * So tests can not be run within class directly
     * It is necessary to use xml file for execution
     */

    DashboardPage dashboardPage;


    @Test(groups = {"smoke", "regression"})
    public void loginTest() {

        logger.info("*** Starting simple login test ..");

        try {

            logger.info("inserting logging creadentilas on the page");
            Thread.sleep(2000);
            dashboardPage = new DashboardPage(driver);
            dashboardPage.login("Admin", "admin123");

            String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
            Assert.assertEquals(expectedUrl, driver.getCurrentUrl());

            dashboardPage.logout();
            Thread.sleep(2000);
            driver.navigate().refresh();

        } catch (Exception e) {

            logger.error("Test failed..");
            logger.debug("Debugs log ... ");

        }

        logger.info("Finishing login test");

    }













}
