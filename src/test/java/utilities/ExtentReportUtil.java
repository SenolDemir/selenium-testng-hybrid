package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportUtil implements ITestListener{


    String setReportName;
    //To generate UI of the report
    protected ExtentSparkReporter sparkReporter;
    //To populate common info for the report, like test name, browser name, system info and others
    protected ExtentReports extentReport;
    //To craete test entries and update test status on the report.
    protected ExtentTest extentTest;


    public void onStart(ITestContext testContext) {


        // Create timestamp for the report
        String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        extentReport=new ExtentReports();
        // set the report name with timestamp

        setReportName = "extent-report-" + timeStamp + ".html";

        sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/test-output/reports/" + setReportName);
        sparkReporter.config().setDocumentTitle("Test Automation Report");
        sparkReporter.config().setReportName("OpenCRM Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);


        extentReport.attachReporter(sparkReporter);
        extentReport.setSystemInfo("Application", "OpenHRM");
        extentReport.setSystemInfo("Environment", "QA");
        extentReport.setSystemInfo("OS", System.getProperty("os.name"));
        extentReport.setSystemInfo("Username", System.getProperty("user.name"));

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extentReport.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()){
            extentReport.setSystemInfo("Groups", includedGroups.toString());
        }

    }


    public void onTestSuccess(ITestResult result) {

        // Create a new entry in the report
        extentTest = extentReport.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.log(Status.PASS,  result.getName() + " successfully executed");

    }

    public void onTestFailure(ITestResult result) {

        // Create a new entry in the report
        extentTest = extentReport.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());

        extentTest.log(Status.FAIL, result.getName() + " failed");
        extentTest.log(Status.INFO, result.getThrowable().getLocalizedMessage());

        try {
            String imagePath = new TestBase().captureScreenShot(result.getName());
            extentTest.addScreenCaptureFromPath(imagePath);
        }catch (Exception e1){
            e1.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult result) {

        extentTest = extentReport.createTest(result.getClass().getName());
        extentTest.assignCategory(result.getMethod().getGroups());

        extentTest.log(Status.SKIP, result.getName() + " skipped");
        extentTest.log(Status.INFO, result.getThrowable().getLocalizedMessage());
    }

    public void onFinish(ITestContext context) {

        extentReport.flush();

        String extentReportPath = System.getProperty("user.dir")+ "/test-output/reports/" + setReportName;
        File extentReport = new File(extentReportPath);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        }catch (IOException e){
            e.printStackTrace();
        }

//        try {
//            URL url = new URL("file:///" +  System.getProperty("user.dir")+ "/test-output/reports/" + setReportName);
//            // Generate the email message
//            ImageHtmlEmail htmlEmail = new ImageHtmlEmail();
//            htmlEmail.setDataSourceResolver(new DataSourceUrlResolver(url));
//            htmlEmail.setHostName("smtp.googlemail.com");
//            htmlEmail.setSmtpPort(465);
//            htmlEmail.setAuthenticator(new DefaultAuthenticator("senoldemirtest@gmail.com", "Test1928"));
//            htmlEmail.setSSLOnConnect(true);
//            htmlEmail.setFrom("senoldemirtest@gmail.com"); // Sender
//            htmlEmail.setSubject("Test Result");
//            htmlEmail.setMsg("Please find atteached report");
//            htmlEmail.addTo("senoldemir@ymail.com");
//            htmlEmail.attach(url, "extent report", "please check report..");
//            htmlEmail.send(); // sending the mail
//
//        }catch (Exception e){
//
//            e.printStackTrace();
//        }

    }






}
