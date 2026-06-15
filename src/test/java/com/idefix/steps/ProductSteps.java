package com.idefix.steps;

import com.idefix.base.DriverFactory;
import com.idefix.context.TestContext;
import com.idefix.pages.ProductDetailPage;
import com.idefix.utils.FileWriterUtils;
import com.idefix.utils.PriceUtils;
import com.thoughtworks.gauge.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductSteps {

    private static final Logger logger = LogManager.getLogger(ProductSteps.class);

    @Step("Secilen urunun adi ve fiyati txt dosyasina yazilir")
    public void writeProductInfoToTextFile() {
        ProductDetailPage productDetailPage = new ProductDetailPage(DriverFactory.getDriver());
        String productName = productDetailPage.getProductName();
        String productPriceText = productDetailPage.getProductPriceText();

        TestContext.setProductName(productName);
        TestContext.setProductPriceText(productPriceText);
        TestContext.setProductPrice(PriceUtils.parseTurkishPrice(productPriceText));

        logger.info("Writing product info. Name: {}, Price: {}", productName, productPriceText);
        FileWriterUtils.writeProductInfo(productName, productPriceText);
    }

    @Step("Urun sepete eklenir ve sepet sayfasina gidilir")
    public void addProductToCartAndGoToCart() {
        logger.info("Adding selected product to cart.");
        ProductDetailPage productDetailPage = new ProductDetailPage(DriverFactory.getDriver());
        productDetailPage.addToCart();
        productDetailPage.goToCartFromModal();
    }
}
