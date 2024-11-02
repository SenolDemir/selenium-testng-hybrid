package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class PIMPage extends BasePage {


    public PIMPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[normalize-space()='Add']")
    public WebElement addEmployeeButton;



    @FindBy(xpath = "//input[@name='firstName']")
    public WebElement employeeFirstnameInput;

    @FindBy(xpath = "//input[@name='firstName']")
    public WebElement employeeMiddleNameInput;

    @FindBy(xpath = "//input[@name='lastName']")
    public WebElement employeeLastNameInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveButton;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    public WebElement cancelButton;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]/..")
    public WebElement idInput;


    public void addEmployee(String firstname, String middleName, String lastname) throws InterruptedException {

        addEmployeeButton.click();
        Thread.sleep(2000);

        employeeFirstnameInput.sendKeys(firstname);
        employeeMiddleNameInput.sendKeys(middleName);
        employeeLastNameInput.sendKeys(lastname);
        saveButton.click();
        Thread.sleep(2000);

    }

}
