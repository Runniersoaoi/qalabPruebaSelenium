package com.ntt.store.pages;

import com.ntt.store.core.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private static final By EMAIL_INPUT    = By.cssSelector("#login-form input[name='email']");
    private static final By PASSWORD_INPUT = By.cssSelector("#login-form input[name='password']");
    private static final By SUBMIT_BUTTON  = By.id("submit-login");

    public LoginPage() {
        waitForVisible(EMAIL_INPUT);
    }

    public HomePage signIn(String email, String password) {
        type(EMAIL_INPUT, email);
        type(PASSWORD_INPUT, password);
        click(SUBMIT_BUTTON);
        return new HomePage();
    }
}
