package tests;

import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.PIMPage;
import utilities.TestBase;

public class PIMTest extends TestBase {

    DashboardPage dashboardPage;
    PIMPage pimPage;

    @Test
    public void addSingleEmployee() throws InterruptedException {

        dashboardPage = new DashboardPage(driver);
        pimPage = new PIMPage(driver);

        dashboardPage.login("Admin", "admin123");
        Thread.sleep(5000);
        dashboardPage.pimButton.click();
        Thread.sleep(3000);
        pimPage.addEmployee("Carlos", "", "Ancelotti");



    }



}
