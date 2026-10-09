package com.ntt.store.pages;

import com.ntt.store.core.BasePage;
import com.ntt.store.utils.MoneyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;

public class ProductPage extends BasePage {

    private static final By PRODUCT_NAME    = By.cssSelector("h1.h1, h1[itemprop='name']");
    private static final By CURRENT_PRICE   = By.cssSelector(".current-price span[itemprop='price'], .current-price-value");
    private static final By QUANTITY_INPUT  = By.id("quantity_wanted");
    private static final By ADD_TO_CART_BTN = By.cssSelector("button.add-to-cart");

    public ProductPage() {
        waitForVisible(PRODUCT_NAME);
    }

    public String getProductName() {
        return getDomText(PRODUCT_NAME);
    }

    public BigDecimal getUnitPrice() {
        return MoneyUtils.parse(getText(CURRENT_PRICE));
    }

    public void setQuantity(int quantity) {
        WebElement field = waitForVisible(QUANTITY_INPUT);
        field.click();
        field.sendKeys(Keys.END);

        String currentValue = field.getDomProperty("value");
        for (int i = 0; i < currentValue.length(); i++) {
            field.sendKeys(Keys.BACK_SPACE);
        }

        field.sendKeys(String.valueOf(quantity));
        field.sendKeys(Keys.TAB);
    }

    public CartPopup addToCart() {
        scrollTo(ADD_TO_CART_BTN);
        click(ADD_TO_CART_BTN);
        return new CartPopup();
    }
}
