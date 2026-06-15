package com.idefix.pages;

import com.idefix.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePage extends BasePage {

    private final By searchInput = By.id("headerSearch-d");
    private final By loginLink = By.cssSelector("a[href='/giris']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open(String baseUrl) {
        for (int attempt = 1; attempt <= 3; attempt++) {
            driver.get(baseUrl);
            if (isSearchInputVisibleWithinSeconds(10)) {
                return;
            }
        }
    }

    public void verifyHomePageIsOpened(String expectedUrl) {
        findVisible(searchInput);
        assertThat(driver.getCurrentUrl()).contains("idefix.com");
    }

    public void goToLoginPage() {
        click(loginLink);
    }

    public void typeSearchKeyword(String keyword) {
        type(searchInput, keyword);
    }

    public void clearSearchKeyword() {
        clearWithKeyboard(searchInput);
    }

    public void pressEnterOnSearchInput() {
        findClickable(searchInput).sendKeys(Keys.ENTER);
    }

    private boolean isSearchInputVisibleWithinSeconds(int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(searchInput));
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }
}
