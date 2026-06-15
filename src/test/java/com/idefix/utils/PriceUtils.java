package com.idefix.utils;

import java.math.BigDecimal;

public final class PriceUtils {

    private PriceUtils() {
    }

    public static BigDecimal parseTurkishPrice(String priceText) {
        String normalizedPrice = priceText
                .replace("TL", "")
                .replace(".", "")
                .replace(",", ".")
                .replaceAll("[^0-9.]", "")
                .trim();

        if (normalizedPrice.isBlank()) {
            throw new IllegalArgumentException("Price text could not be parsed: " + priceText);
        }
        return new BigDecimal(normalizedPrice);
    }
}
