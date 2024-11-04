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


    @DataProvider(name = "loginData")  // indices = {0,3}
    public Object[][] excelData() {

        String path = System.getProperty("user.dir") + "/src/test/resources/testdata/loginData.xlsx";
        ExcelUtils data = new ExcelUtils(path, "Sheet 1");
        String[][] dataArr = data.getDataArrayWithoutFirstRow();

        return dataArr;
    }


    /*
    This is data driven test on login function of app y using excel file as the data source.
    Above @DataProvider is to to reach the data of excel file named 'loginData' under resources folder.
    Both positive and negative scenarios implemented within same test case.
    Assertions to the both kind scenarios are created to work accordingly
     */

    @Test(dataProvider = "loginData")
    public void dataDrivenLoginTest(String username, String password, String result) {


        logger.info("Data Driven Login Test is started..");
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

        dashboardPage.login(username, password);
        boolean target = driver.getCurrentUrl().contains(expectedUrl);


        if (result.equalsIgnoreCase("vaild")) {

            if (target) {
                dashboardPage.logout();
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }

            if (result.equalsIgnoreCase("invaild")) {

                if (driver.getCurrentUrl().contains(expectedUrl)) {
                    driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }

        }


    }








}
