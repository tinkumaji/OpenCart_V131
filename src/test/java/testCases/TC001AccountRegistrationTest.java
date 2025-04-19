package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseCase;

public class TC001AccountRegistrationTest extends BaseCase {
    @Test(groups = {"sanity", "master"})
    public void verifyRegistration() {
        logger.info("*** Starting TC001AccountRegistrationTest ***");
        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            logger.info("Clicked on My Account Link");

            homePage.clickRegister();
            logger.info("Clicked on Register Link");

            RegistrationPage registrationPage = new RegistrationPage(driver);
            logger.info("Providing Customer Details");
            registrationPage.setFirstName(randomString());
            registrationPage.setLastName(randomString());
            registrationPage.setEmail(randomEmail());
            registrationPage.setTelephone(randomNumber());

            String password = randomPassword();
            registrationPage.setPassword(password);
            registrationPage.setConfirmPassword(password);
            registrationPage.clickAgreeTerms();
            registrationPage.clickContinue();

            logger.info("Verifying Expected Message...");
            if (registrationPage.getAccountCreationMsg().equals("Your Account Has Been Created!")) {
                Assert.assertTrue(true);
            } else {
                logger.error("Test Failed...");
                Assert.fail();
            }
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("*** Finished TC001AccountRegistrationTest ***");
    }
}
