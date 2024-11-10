# SELENIUM TESTNG HYBRID FRAMEWORK PROJECT

Testing framework developed based on POM (Page Object Model) design with Selenium and TestNG

## Perquisites

IDE Installed (IntelliJ)  
Java JDK Installed (Java SE 21)  
Maven Installed  
VCS (Git) Installed  

## Project Naming Convention

GroupId - a package of a new project (domain ismi ex: com.amazon)  
ArtifactId - a name of your project (selenium-testng-project)  

## Libraries (pom.xml dependencies)

		selenium
		testng
		javafaker (for random data)
		extentreport
		apache common email (for report mailing)

## Data Driven Test
Login Data created in an Excel file in both positive and negative scenarios. Test assertion built make validations according to scneario types which has defined within test data. 
The external test data is under resources folder,  and data provider is in the same clas with test cases.

## Grouping Test
To be able to run test cases in different purpose, tests are grouped in test level.
grouingTestRunner.xml created to run in this purpose.


## Reporting
Report generation is executed by using ExtentReport Library.
When the test cases are run report is generated and opened in the browser automatically

Mailing the report is possible within the automation. (By using Apache Common email Library)
The email addresses  can be configured within ExtentReportUtil class under the utilties folder


## Logging with Apache Log4J 2

Logging feature added to the framework by help of 'Apache Log4j 2' library.  
The logs are generated under target/test/log folder.

## Running Failed Tests

Essentially testng generates testng-failed.xml file under the test-output folder automatically with the contents to run failed tests.
But when Listener is used it is prevented to be generated

