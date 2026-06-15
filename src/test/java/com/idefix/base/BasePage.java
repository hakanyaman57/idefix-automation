package com.idefix.base;

import com.idefix.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("timeout.seconds")));
    }

    protected WebElement findVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement findClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        findClickable(locator).click();
    }

    protected void type(By locator, String text) {
        findClickable(locator).sendKeys(text);
    }

    protected void clearAndType(By locator, String text) {
        WebElement element = findClickable(locator);
        element.click();
        element.sendKeys(Keys.CONTROL, "a");
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(text);
    }

    protected void clearWithKeyboard(By locator) {
        WebElement element = findClickable(locator);
        element.click();
        element.sendKeys(Keys.CONTROL, "a");
        element.sendKeys(Keys.BACK_SPACE);
        wait.until(ExpectedConditions.attributeToBe(locator, "value", ""));
    }

    protected String getText(By locator) {
        return findVisible(locator).getText().trim();
    }

    protected boolean isVisible(By locator) {
        try {
            return driver.findElements(locator).stream().anyMatch(WebElement::isDisplayed);
        } catch (NoSuchElementException | StaleElementReferenceException exception) {
            return false;
        }
    }

    protected void waitUntilInvisible(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
