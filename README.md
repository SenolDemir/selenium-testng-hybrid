# SELENIUM TESTNG HYBRID FRAMEWORK PROJECT

Testing framework developed based on POM (Page Object Model) design with Selenium and TestNG

## Prerequisites

IDE Installed (IntelliJ)  
Java JDK Installed (Java SE 21)  
Maven Installed  
VCS (Git) Installed  

## Project Naming Convention

GroupId - a package of a new project (domain ismi ex: com.amazon)  
ArtifactId - a name of your project (selenium-testng-project)  

## Libraries (pom.xml dependencies)

> selenium   
> testng  
> javafaker (for random data)

**reporting**
> extentreport   
> apache common email (for report mailing) 
> 
**To deal with external files**
> org.apache.poi » poi (excel files)  
> org.apache.poi » poi-ooxml (excel files)  
>com.opencsv » opencsv (CSV files)  

**Logging  with Apache log4j 2**
> log4j-api   
> log4j-core  
> log4j-slf4j-impl  

## Test Ecexution
driver instance and browser version is configured by xml file. So tests can not be run within class directly  
It is necessary to use xml file for execution.
Open the xml file which you choose to run (parallel, grouping or initial test runner). Right click on the file and click to run option in menu.

## Data Driven Test
Login Data created in an Excel file in both positive and negative scenarios. Test assertion are built to make validations according to scenario types which has defined within test data. 
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

