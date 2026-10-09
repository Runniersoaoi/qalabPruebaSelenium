package com.ntt.store.pages;

import com.ntt.store.core.BasePage;
import com.ntt.store.utils.MoneyUtils;
import org.openqa.selenium.By;

import java.math.BigDecimal;

public class CartPopup extends BasePage {

    private static final By MODAL        = By.id("blockcart-modal");
    private static final By MODAL_TITLE  = By.cssSelector("#blockcart-modal .modal-title");
    private static final By PRODUCT_NAME = By.cssSelector("#blockcart-modal .product-name");
    private static final By UNIT_PRICE   = By.cssSelector("#blockcart-modal .product-price");
    private static final By QUANTITY     = By.cssSelector("#blockcart-modal .product-quantity");
    private static final By TOTAL        = By.cssSelector("#blockcart-modal .product-total .value");
    private static final By CHECKOUT_BTN = By.cssSelector("#blockcart-modal .cart-content-btn a.btn-primary");

    public CartPopup() {
        waitForVisible(MODAL);
    }

    public boolean isPopupDisplayed() {
        return isDisplayed(MODAL);
    }

    public String getConfirmationMessage() {
        return getText(MODAL_TITLE);
    }

    public boolean isConfirmationMessageDisplayed() {
        return getConfirmationMessage().toLowerCase().contains("correctamente");
    }

    public String getProductName() {
        return getDomText(PRODUCT_NAME);
    }

    public int getQuantity() {
        return Integer.parseInt(getText(QUANTITY).replaceAll("\\D", ""));
    }

    public BigDecimal getUnitPrice() {
        return MoneyUtils.parse(getText(UNIT_PRICE));
    }

    public BigDecimal getTotalAmount() {
        return MoneyUtils.parse(getText(TOTAL));
    }

    public boolean isTotalCalculatedCorrectly() {
        BigDecimal expected = MoneyUtils.multiply(getUnitPrice(), getQuantity());
        return expected.compareTo(getTotalAmount()) == 0;
    }

    public CartPage proceedToCheckout() {
        click(CHECKOUT_BTN);
        return new CartPage();
    }
}
