package com.ntt.store.core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage() {
        this.driver = DriverManager.get();
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(ConfigReader.explicitTimeout()));
    }

    // Explicit waits

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Actions

    protected void click(By locator) {
        waitForClickable(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement field = waitForVisible(locator);
        field.clear();
        field.sendKeys(text);
    }

    protected void scrollTo(By locator) {
        WebElement element = waitForVisible(locator);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    protected void hoverOver(By locator) {
        new Actions(driver).moveToElement(waitForVisible(locator)).perform();
    }

    // Queries

    protected String getText(By locator) {
        return waitForVisible(locator).getText().trim();
    }

    protected String getDomText(By locator) {
        return waitForVisible(locator).getDomProperty("textContent").trim();
    }

    protected String getValue(By locator) {
        return waitForVisible(locator).getDomProperty("value").trim();
    }

    protected int countElements(By locator) {
        return driver.findElements(locator).size();
    }

    protected boolean isDisplayed(By locator) {
        try {
            waitForVisible(locator);
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
