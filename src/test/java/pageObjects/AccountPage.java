package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='content']//h2[text()='My Account']") WebElement myAccountHeading;
    @FindBy(xpath = "//aside[@id='column-right']//a[text()='Logout']") WebElement logoutBtn;

    public boolean checkMyAccountDisplayed() {
       try {
           return myAccountHeading.isDisplayed();
       } catch (Exception e) {
           return false;
       }
    }

    public void clickLogout() {
        logoutBtn.click();
    }
}
