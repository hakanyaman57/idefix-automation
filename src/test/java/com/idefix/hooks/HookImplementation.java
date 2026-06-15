package com.idefix.hooks;

import com.idefix.base.DriverFactory;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.AfterSpec;
import com.thoughtworks.gauge.AfterStep;
import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.BeforeSpec;
import com.thoughtworks.gauge.BeforeStep;
import com.thoughtworks.gauge.BeforeSuite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HookImplementation {

    private static final Logger logger = LogManager.getLogger(HookImplementation.class);

    @BeforeSuite
    public void beforeSuite() {
        logger.info("Test suite started.");
    }

    @AfterSuite
    public void afterSuite() {
        logger.info("Test suite finished.");
    }

    @BeforeSpec
    public void beforeSpec() {
        logger.info("Specification started.");
    }

    @AfterSpec
    public void afterSpec() {
        logger.info("Specification finished.");
    }

    @BeforeScenario
    public void beforeScenario() {
        logger.info("Scenario started.");
        DriverFactory.createDriver();
    }

    @AfterScenario
    public void afterScenario() {
        logger.info("Scenario finished.");
        DriverFactory.quitDriver();
    }

    @BeforeStep
    public void beforeStep() {
        logger.info("Step started.");
    }

    @AfterStep
    public void afterStep() {
        logger.info("Step finished.");
    }
}
