package com.idefix.context;

import com.idefix.models.TestData;
import com.idefix.utils.JsonDataReader;

import java.math.BigDecimal;

public final class TestContext {

    private static final TestData TEST_DATA = JsonDataReader.readTestData();
    private static String productName;
    private static String productPriceText;
    private static BigDecimal productPrice;

    private TestContext() {
    }

    public static TestData getTestData() {
        return TEST_DATA;
    }

    public static String getProductName() {
        return productName;
    }

    public static void setProductName(String productName) {
        TestContext.productName = productName;
    }

    public static String getProductPriceText() {
        return productPriceText;
    }

    public static void setProductPriceText(String productPriceText) {
        TestContext.productPriceText = productPriceText;
    }

    public static BigDecimal getProductPrice() {
        return productPrice;
    }

    public static void setProductPrice(BigDecimal productPrice) {
        TestContext.productPrice = productPrice;
    }
}
