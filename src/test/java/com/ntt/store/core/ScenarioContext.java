package com.ntt.store.core;

import com.ntt.store.pages.CartPage;
import com.ntt.store.pages.CartPopup;
import com.ntt.store.pages.CategoryPage;
import com.ntt.store.pages.HomePage;

import java.math.BigDecimal;

public class ScenarioContext {

    private HomePage homePage;
    private CategoryPage categoryPage;
    private CartPopup cartPopup;
    private CartPage cartPage;

    private String expectedProductName;
    private BigDecimal expectedUnitPrice;
    private int expectedQuantity;

    // Pages

    public HomePage getHomePage() {
        return homePage;
    }

    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
    }

    public CategoryPage getCategoryPage() {
        return categoryPage;
    }

    public void setCategoryPage(CategoryPage categoryPage) {
        this.categoryPage = categoryPage;
    }

    public CartPopup getCartPopup() {
        return cartPopup;
    }

    public void setCartPopup(CartPopup cartPopup) {
        this.cartPopup = cartPopup;
    }

    public CartPage getCartPage() {
        return cartPage;
    }

    public void setCartPage(CartPage cartPage) {
        this.cartPage = cartPage;
    }

    // Test data carried between steps

    public String getExpectedProductName() {
        return expectedProductName;
    }

    public void setExpectedProductName(String expectedProductName) {
        this.expectedProductName = expectedProductName;
    }

    public BigDecimal getExpectedUnitPrice() {
        return expectedUnitPrice;
    }

    public void setExpectedUnitPrice(BigDecimal expectedUnitPrice) {
        this.expectedUnitPrice = expectedUnitPrice;
    }

    public int getExpectedQuantity() {
        return expectedQuantity;
    }

    public void setExpectedQuantity(int expectedQuantity) {
        this.expectedQuantity = expectedQuantity;
    }
}
