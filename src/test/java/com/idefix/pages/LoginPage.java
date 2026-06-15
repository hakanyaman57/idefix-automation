package com.idefix.pages;

import com.idefix.base.BasePage;
import com.idefix.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPage extends BasePage {

    private final By emailInput = By.cssSelector("input[name='email']");
    private final By passwordInput = By.cssSelector("input[name='password']");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By accountLink = By.cssSelector("a[href*='hesabim']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(User user) {
        clearAndType(emailInput, user.getEmail());
        clearAndType(passwordInput, user.getPassword());
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        click(loginButton);
        wait.until(driver -> !driver.getCurrentUrl().contains("/giris")
                || !driver.findElements(accountLink).isEmpty());
    }

    public void verifyUserIsLoggedIn() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(accountLink, 0));
        assertThat(driver.findElements(accountLink)).isNotEmpty();
    }
}
