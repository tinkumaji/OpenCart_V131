package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseCase;

public class TC002LoginTest extends BaseCase {
    @Test(groups = {"regression", "master"})
    public void verifyLogin() {
        logger.info("*** Starting TC002LoginTest ***");
        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            logger.info("Clicked on My Account Link");

            homePage.clickLogin();
            logger.info("Clicked on Login Link");

            LoginPage loginPage = new LoginPage(driver);

            logger.info("Providing Login Details");
            loginPage.setEmailField(properties.getProperty("email"));
            loginPage.setPasswordField(properties.getProperty("password"));
            loginPage.enterLogin();

            AccountPage accountPage = new AccountPage(driver);
            boolean myAccountDisplayed = accountPage.checkMyAccountDisplayed();
            accountPage.clickLogout();
            if (myAccountDisplayed) {
                Assert.assertTrue(true);
            } else {
                logger.error("Test failed...");
                logger.debug("");
            }
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("*** Finished TC002LoginTest ***");
    }
}
