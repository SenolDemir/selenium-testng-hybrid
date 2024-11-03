package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage{


    public DashboardPage(WebDriver driver) {

        super(driver);
    }

    @FindBy(name = "username")
    public WebElement usernameInput;

    @FindBy(name = "password")
    public WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submit;

    @FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
    public WebElement userProfileMenu;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    public WebElement logOutButton;

    @FindBy(xpath = "//span[text()='PIM']")
    public WebElement pimButton;


    public void login(String username, String password) {

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submit.click();

    }

    public void logout() {

        userProfileMenu.click();
        logOutButton.click();
    }









}
