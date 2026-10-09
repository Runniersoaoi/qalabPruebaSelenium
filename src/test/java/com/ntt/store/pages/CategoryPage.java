package com.ntt.store.pages;

import com.ntt.store.core.BasePage;
import org.openqa.selenium.By;

public class CategoryPage extends BasePage {

    private static final By PRODUCT_LIST  = By.id("js-product-list");
    private static final By PRODUCT_CARDS = By.cssSelector("#js-product-list .product-miniature");
    private static final By FIRST_TITLE   = By.cssSelector("#js-product-list .product-miniature:first-of-type .product-title a");

    public CategoryPage() {
        waitForVisible(PRODUCT_LIST);
    }

    public int getProductCount() {
        return countElements(PRODUCT_CARDS);
    }

    public ProductPage openFirstProduct() {
        scrollTo(FIRST_TITLE);
        click(FIRST_TITLE);
        return new ProductPage();
    }
}
