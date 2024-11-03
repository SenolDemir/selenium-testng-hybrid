package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import utilities.TestBase;

public class LoginTest extends TestBase {

    /**
     * driver instance and browser version is craeted by xml file.
     * So tests can not be run within class directly
     * It is necessary to use xml file for execution
     */

    DashboardPage dashboardPage;


    @Test
    public void loginTest() {

        logger.info("*** Starting simple login test ..");

        try {

            logger.info("inserting logging creadentilas on the page");
            Thread.sleep(2000);
            dashboardPage = new DashboardPage(driver);
            dashboardPage.login("Admin", "admin123");

            String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
            Assert.assertEquals(expectedUrl, driver.getCurrentUrl());

        } catch (Exception e) {

            logger.error("Test failed..");
            logger.debug("Debugs log ... ");
            Assert.fail();
        }

        logger.info("Finishing login test");

    }


    @DataProvider
    public Object[][] credentials() {
        String[][] data = {
                {"username", "password"},
                {"", ""},
                {"Admin", ""},
                {"", "admin123"},
                {"mike", "123456"},

        };
        return data;
    }

    @Test(dataProvider = "credentials")
    public void negativeLoginTest(String username, String password) throws InterruptedException {

        dashboardPage = new DashboardPage(driver);
        Thread.sleep(3000);
        dashboardPage.login(username, password);
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        Assert.assertEquals(expectedUrl, driver.getCurrentUrl());

    }


}
