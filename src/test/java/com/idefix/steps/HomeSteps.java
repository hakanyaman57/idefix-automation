package com.idefix.steps;

import com.idefix.base.DriverFactory;
import com.idefix.pages.HomePage;
import com.idefix.utils.ConfigReader;
import com.thoughtworks.gauge.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomeSteps {

    private static final Logger logger = LogManager.getLogger(HomeSteps.class);

    @Step("Idefix anasayfasi acilir")
    public void openHomePage() {
        logger.info("Opening Idefix home page.");
        new HomePage(DriverFactory.getDriver()).open(ConfigReader.get("base.url"));
    }

    @Step("Idefix anasayfasinin acildigi dogrulanir")
    public void verifyHomePageIsOpened() {
        logger.info("Verifying Idefix home page.");
        new HomePage(DriverFactory.getDriver()).verifyHomePageIsOpened(ConfigReader.get("base.url"));
    }
}
