package com.ntt.store.steps;

import com.ntt.store.core.ScenarioContext;
import com.ntt.store.pages.CartPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartSteps {

    private static final String EXPECTED_CART_TITLE = "Carrito";

    private final ScenarioContext context;

    public CartSteps(ScenarioContext context) {
        this.context = context;
    }

    @Cuando("finalizo la compra")
    public void iProceedToCheckout() {
        context.setCartPage(context.getCartPopup().proceedToCheckout());
    }

    @Entonces("valido el titulo de la pagina del carrito")
    public void iValidateTheCartPageTitle() {
        CartPage cartPage = context.getCartPage();

        assertFalse(cartPage.getPageTitle().isBlank(),
                "The cart page title is empty.");

        assertTrue(
                cartPage.getHeading().contains(EXPECTED_CART_TITLE),
                "Unexpected cart page title: '" + cartPage.getHeading() + "'");
    }

    @Y("vuelvo a validar el calculo de precios en el carrito")
    public void iValidateThePriceCalculationInTheCart() {
        CartPage cartPage = context.getCartPage();

        assertEquals(context.getExpectedProductName(), cartPage.getProductName(),
                "The product in the cart is not the one added.");

        assertEquals(context.getExpectedQuantity(), cartPage.getQuantity(),
                "The quantity in the cart does not match the one selected.");

        assertEquals(0, context.getExpectedUnitPrice().compareTo(cartPage.getUnitPrice()),
                "The unit price changed between the product page and the cart.");

        assertTrue(
                cartPage.isSubtotalCalculatedCorrectly(),
                "Wrong subtotal in the cart. Unit price: " + cartPage.getUnitPrice()
                        + " x " + cartPage.getQuantity()
                        + " should equal " + cartPage.getProductsSubtotal());
    }
}
