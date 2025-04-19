package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='email']") WebElement emailField;
    @FindBy(xpath = "//input[@name='password']") WebElement passwordField;
    @FindBy(xpath = "//input[@type='submit']") WebElement loginBtn;

    public void setEmailField(String email) {
        emailField.sendKeys(email);
    }

    public void setPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    public void enterLogin() {
        loginBtn.sendKeys(Keys.ENTER);
    }
}
