package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseCase;
import utilities.ExcelDataProvider;

public class TC003LoginDataDrivenTest extends BaseCase {
    @Test(dataProvider = "loginData", dataProviderClass = ExcelDataProvider.class, groups = "smoke")
    public void verifyLoginDataDriven(String email, String password, String expectedResult) {
        logger.info("*** Starting TC003LoginDataDrivenTest ***");
        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            homePage.clickLogin();

            logger.info("Providing Login Details");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmailField(email);
            loginPage.setPasswordField(password);
            loginPage.enterLogin();

            AccountPage accountPage = new AccountPage(driver);
            boolean myAccountDisplayed = accountPage.checkMyAccountDisplayed();
            if (expectedResult.equalsIgnoreCase("Valid")) {
                if (myAccountDisplayed) {
                    accountPage.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.fail();
                }
            }
            if (expectedResult.equalsIgnoreCase("Invalid")) {
                if (myAccountDisplayed) {
                    accountPage.clickLogout();
                    Assert.fail();
                } else {
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("*** Finished TC003LoginDataDrivenTest ***");
    }
}
