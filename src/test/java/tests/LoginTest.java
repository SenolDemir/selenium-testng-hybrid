package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import utilities.TestBase;

public class LoginTest extends TestBase {

    DashboardPage dashboardPage;


    @Test
    public void loginTest(){

        logger.info("*** Starting simple login test ..");

        try {

            logger.info("inserting logging creadentilas on the page");
            dashboardPage = new DashboardPage(driver);
            dashboardPage.login("Admin", "admin123");

            String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
            Assert.assertEquals(expectedUrl, driver.getCurrentUrl());

        }catch (Exception e){

            logger.error("Test failed..");
            logger.debug("Debugs log ... ");
            Assert.fail();
        }

        logger.info("Finishing login test");

    }






}
