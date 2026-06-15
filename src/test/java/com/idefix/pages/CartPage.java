package com.idefix.pages;

import com.idefix.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class CartPage extends BasePage {

    private final By cartPrice = By.cssSelector("div > span.text-title-md");
    private final By quantityContainer = By.cssSelector("div.flex.rounded-sm");
    private final By increaseQuantityButton = By.xpath("(//div[contains(@class,'flex') and contains(@class,'rounded-sm')]//button[@title])[last()]");
    private final By quantityInput = By.cssSelector("div.flex.rounded-sm input.text-title-sm");
    private final By removeProductButton = By.cssSelector("span[class='lg:flex hidden']");
    private final By emptyCartMessage = By.cssSelector("p.font-medium.text-center");
    private final By loadingSkeleton = By.cssSelector("[class*='animate-pulse']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getCartPriceText() {
        return getText(cartPrice);
    }

    public void increaseQuantity() {
        findVisible(quantityContainer);
        click(increaseQuantityButton);
    }

    public void verifyQuantityIs(int expectedQuantity) {
        String actualQuantity = findVisible(quantityInput).getAttribute("value");
        assertThat(actualQuantity).isEqualTo(String.valueOf(expectedQuantity));
    }

    public void removeProduct() {
        click(removeProductButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(quantityInput));
    }

    public void verifyCartIsEmpty() {
        wait.until(driver -> !isVisible(loadingSkeleton) && isVisible(emptyCartMessage));
        assertThat(getText(emptyCartMessage)).isEqualTo("Sepetinizde henüz ürün bulunmuyor.");
    }
}
