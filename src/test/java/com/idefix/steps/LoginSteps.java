package com.idefix.steps;

import com.idefix.base.DriverFactory;
import com.idefix.context.TestContext;
import com.idefix.pages.HomePage;
import com.idefix.pages.LoginPage;
import com.idefix.utils.ConfigReader;
import com.thoughtworks.gauge.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginSteps {

    private static final Logger logger = LogManager.getLogger(LoginSteps.class);

    @Step("Kullanici json dosyasindaki bilgilerle Idefix sitesine login olur")
    public void loginWithJsonData() {
        logger.info("Opening Idefix home page and starting login flow.");
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.open(ConfigReader.get("base.url"));
        homePage.goToLoginPage();

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login(TestContext.getTestData().getUser());
        loginPage.verifyUserIsLoggedIn();
    }
}
