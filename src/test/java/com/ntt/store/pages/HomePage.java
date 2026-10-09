package com.ntt.store.pages;

import com.ntt.store.core.BasePage;
import com.ntt.store.core.ConfigReader;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private static final By SIGN_IN_LINK = By.cssSelector("div.user-info a[href*='iniciar-sesion']");
    private static final By LOGOUT_LINK  = By.cssSelector("div.user-info a.logout, div.user-info a[href*='mylogout']");
    private static final By STORE_LOGO   = By.cssSelector("#header .logo");

    public void open() {
        driver.get(ConfigReader.appUrl());
        waitForVisible(STORE_LOGO);
    }

    public LoginPage goToLogin() {
        click(SIGN_IN_LINK);
        return new LoginPage();
    }

    public boolean isUserLoggedIn() {
        return isDisplayed(LOGOUT_LINK);
    }

    public boolean isCategoryAvailable(String category) {
        return isDisplayed(topMenuItem(category));
    }

    public CategoryPage goToCategory(String category, String subcategory) {
        if (!isCategoryAvailable(category)) {
            throw new IllegalArgumentException(
                    "Category '" + category + "' does not exist in the top menu");
        }
        hoverOver(topMenuItem(category));
        click(topMenuItem(subcategory));
        return new CategoryPage();
    }

    private By topMenuItem(String name) {
        return By.xpath("//ul[@id='top-menu']//a[contains(normalize-space(.), '" + name + "')]");
    }
}
