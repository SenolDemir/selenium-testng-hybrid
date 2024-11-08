package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import utilities.TestBase;

public class LoginNegativeTest extends TestBase {

    DashboardPage dashboardPage;


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

    @Test(dataProvider = "credentials", groups = {"smoke", "regression"})
    public void negativeLoginTest(String username, String password) throws InterruptedException {

        dashboardPage = new DashboardPage(driver);
        Thread.sleep(3000);
        dashboardPage.login(username, password);
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        String actualUrl = driver.getCurrentUrl();
        if (!actualUrl.equals(expectedUrl)) {
            dashboardPage.logout();
        }
        Assert.assertEquals(expectedUrl, actualUrl);

    }
}
