package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']") WebElement firstName;
    @FindBy(xpath = "//input[@id='input-lastname']") WebElement lastName;
    @FindBy(xpath = "//input[@id='input-email']") WebElement email;
    @FindBy(xpath = "//input[@id='input-telephone']") WebElement telephone;
    @FindBy(xpath = "//input[@id='input-password']") WebElement password;
    @FindBy(xpath = "//input[@id='input-confirm']") WebElement confirmPassword;
    @FindBy(xpath = "//input[@name='agree']") WebElement agreeTerms;
    @FindBy(xpath = "//input[@value='Continue']") WebElement continueButton;
    @FindBy(xpath = "//div[@id='content']/h1") WebElement accountCreationMsg;

    public void setFirstName(String fName) {
        firstName.sendKeys(fName);
    }

    public void setLastName(String lName) {
        lastName.sendKeys(lName);
    }

    public void setEmail(String emailId) {
        email.sendKeys(emailId);
    }

    public void setTelephone(String phoneNumber) {
        telephone.sendKeys(phoneNumber);
    }

    public void setPassword(String pass) {
        password.sendKeys(pass);
    }

    public void setConfirmPassword(String pass) {
        confirmPassword.sendKeys(pass);
    }

    public void clickAgreeTerms() {
        agreeTerms.click();
    }

    public void clickContinue() {
        continueButton.click();
    }

    public String getAccountCreationMsg() {
        try {
            return accountCreationMsg.getText();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
