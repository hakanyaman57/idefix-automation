package com.idefix.steps;

import com.idefix.base.DriverFactory;
import com.idefix.context.TestContext;
import com.idefix.pages.CartPage;
import com.idefix.utils.PriceUtils;
import com.thoughtworks.gauge.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class CartSteps {

    private static final Logger logger = LogManager.getLogger(CartSteps.class);
    private static final BigDecimal PRICE_TOLERANCE = new BigDecimal("0.05");

    @Step("Urun sayfasindaki fiyat ile sepetteki fiyat karsilastirilir")
    public void compareProductPriceWithCartPrice() {
        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        BigDecimal cartPrice = PriceUtils.parseTurkishPrice(cartPage.getCartPriceText());
        logger.info("Comparing product price {} with cart price {}.", TestContext.getProductPrice(), cartPrice);
        assertThat(cartPrice).isEqualByComparingTo(TestContext.getProductPrice());
    }

    @Step("Urun adedi ikiye cikarilir ve toplam fiyat dogrulanir")
    public void increaseQuantityAndVerifyTotalPrice() {
        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.increaseQuantity();
        cartPage.verifyQuantityIs(2);
        BigDecimal updatedCartPrice = PriceUtils.parseTurkishPrice(cartPage.getCartPriceText());
        BigDecimal expectedTotal = TestContext.getProductPrice().multiply(BigDecimal.valueOf(2));
        logger.info("Verifying updated cart total. Expected: {}, Actual: {}", expectedTotal, updatedCartPrice);
        assertThat(updatedCartPrice.subtract(expectedTotal).abs()).isLessThanOrEqualTo(PRICE_TOLERANCE);
    }

    @Step("Urun sepetten silinir")
    public void removeProductFromCart() {
        logger.info("Removing product from cart.");
        new CartPage(DriverFactory.getDriver()).removeProduct();
    }

    @Step("Sepetin bos oldugu dogrulanir")
    public void verifyCartIsEmptyAndTotalIsZero() {
        logger.info("Verifying empty cart state.");
        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.verifyCartIsEmpty();
    }
}
