package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import utilities.ExcelUtils;
import utilities.TestBase;

public class LoginDataDrivenTest extends TestBase {


    /*
    This is data driven test on login function of app y using excel file as the data source.
    Above @DataProvider is to to reach the data of excel file named 'loginData' under resources folder.
    Both positive and negative scenarios implemented within same test case.
    Assertions to the both kind scenarios are created to work accordingly
     */

    DashboardPage dashboardPage;



    @DataProvider
    public Object[][] loginData() {

        String path = System.getProperty("user.dir") + "/src/test/resources/testdata/loginData.xlsx";
        ExcelUtils data = new ExcelUtils(path, "Sheet 1");
        String[][] dataArr = data.getDataArrayWithoutFirstRow();

        return dataArr;
    }



    @Test(dataProvider = "loginData", groups = "regression")
    public void dataDrivenLoginTest(String username, String password, String result, String emp1, String emp2) {

        dashboardPage = new DashboardPage(driver);

        logger.info("Data Driven Login Test is started..");
        System.out.println(username + " " + password + " " + result);
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

        dashboardPage.login(username, password);
        boolean target = driver.getCurrentUrl().contains(expectedUrl);


        if (result.equalsIgnoreCase("valid")) {

            if (target) {
                dashboardPage.logout();
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }

            if (result.equalsIgnoreCase("invalid")) {

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
