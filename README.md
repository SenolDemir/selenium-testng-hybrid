# SELENIUM TESTNG HYBRID FRAMEWORK PROJECT

Testing framework developed based on POM (Page Object Model) design with Selenium and TestNG

## Data Driven Test
Login Data created in an Excel file in both positive and negative scenarios. Test assertion built make validations according to scneario types which has defined within test data. 
The external test data is under resources folder,  and data provider is in the same clas with test cases.

## Grouping Test
To be able to run test cases in different purpose, tests are grouped in test level.
grouingTestRunner.xml created to run in this purpose.

## Reporting

## Logging with Apache Log4J 2

Logging feature added to the framework by help of 'Apache Log4j 2' library.  
The logs are generated under target/test/log folder.