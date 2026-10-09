package com.ntt.store.steps;

import com.ntt.store.core.ScenarioContext;
import com.ntt.store.pages.HomePage;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private final ScenarioContext context;

    public LoginSteps(ScenarioContext context) {
        this.context = context;
    }

    @Dado("estoy en la página de la tienda")
    public void iAmOnTheStorePage() {
        HomePage homePage = new HomePage();
        homePage.open();
        context.setHomePage(homePage);
    }

    @Y("me logueo con mi usuario {string} y clave {string}")
    public void iSignInWith(String username, String password) {
        context.setHomePage(
                context.getHomePage().goToLogin().signIn(username, password));
    }

    @Entonces("valido que el usuario se encuentre autenticado")
    public void iValidateTheUserIsAuthenticated() {
        assertTrue(
                context.getHomePage().isUserLoggedIn(),
                "Authentication failed: the session was not started. "
                        + "Expected the logout link to be visible in the header.");
    }
}
