package com.ntt.store.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class MoneyUtils {

    private MoneyUtils() { }

    public static BigDecimal parse(String rawPrice) {
        if (rawPrice == null || rawPrice.isBlank()) {
            throw new IllegalArgumentException("Price text is empty");
        }
        String cleaned = rawPrice
                .replaceAll("[^0-9.,]", "")
                .replace(",", "");
        return new BigDecimal(cleaned).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal multiply(BigDecimal unitPrice, int quantity) {
        return unitPrice.multiply(BigDecimal.valueOf(quantity))
                .setScale(2, RoundingMode.HALF_UP);
    }
}