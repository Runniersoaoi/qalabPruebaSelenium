package com.ntt.store.pages;

import com.ntt.store.core.BasePage;
import com.ntt.store.utils.MoneyUtils;
import org.openqa.selenium.By;

import java.math.BigDecimal;

public class CartPage extends BasePage {

    private static final By CART_CONTAINER    = By.cssSelector(".cart-grid, #main .cart-container");
    private static final By PAGE_HEADING      = By.cssSelector("#main h1, .cart-grid-body h1");
    private static final By PRODUCT_NAME      = By.cssSelector(".cart-item .product-line-info a.label");
    private static final By UNIT_PRICE        = By.cssSelector(".cart-item .current-price .price");
    private static final By QUANTITY_INPUT    = By.cssSelector(".cart-item input.js-cart-line-product-quantity");
    private static final By PRODUCTS_SUBTOTAL = By.cssSelector("#cart-subtotal-products .value, .cart-summary-subtotals-container .value");

    public CartPage() {
        waitForVisible(CART_CONTAINER);
    }

    public String getHeading() {
        return getDomText(PAGE_HEADING);
    }

    public String getProductName() {
        return getDomText(PRODUCT_NAME);
    }

    public int getQuantity() {
        return Integer.parseInt(getValue(QUANTITY_INPUT));
    }

    public BigDecimal getUnitPrice() {
        return MoneyUtils.parse(getText(UNIT_PRICE));
    }

    public BigDecimal getProductsSubtotal() {
        return MoneyUtils.parse(getText(PRODUCTS_SUBTOTAL));
    }

    public boolean isSubtotalCalculatedCorrectly() {
        BigDecimal expected = MoneyUtils.multiply(getUnitPrice(), getQuantity());
        return expected.compareTo(getProductsSubtotal()) == 0;
    }
}
