package com.ntt.store.steps;

import com.ntt.store.core.ScenarioContext;
import com.ntt.store.pages.CartPopup;
import com.ntt.store.pages.CategoryPage;
import com.ntt.store.pages.ProductPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductSteps {

    private final ScenarioContext context;

    public ProductSteps(ScenarioContext context) {
        this.context = context;
    }

    @Cuando("navego a la categoria {string} y subcategoria {string}")
    public void iNavigateToCategoryAndSubcategory(String category, String subcategory) {
        CategoryPage categoryPage =
                context.getHomePage().goToCategory(category, subcategory);
        context.setCategoryPage(categoryPage);

        assertTrue(
                categoryPage.getProductCount() > 0,
                "No products were listed in '" + subcategory
                        + "'. The database connection may be down.");
    }

    @Y("agrego {int} unidades del primer producto al carrito")
    public void iAddUnitsOfTheFirstProductToTheCart(int quantity) {
        ProductPage productPage = context.getCategoryPage().openFirstProduct();

        context.setExpectedProductName(productPage.getProductName());
        context.setExpectedUnitPrice(productPage.getUnitPrice());
        context.setExpectedQuantity(quantity);

        productPage.setQuantity(quantity);
        context.setCartPopup(productPage.addToCart());
    }

    @Entonces("valido en el popup la confirmación del producto agregado")
    public void iValidateTheConfirmationPopup() {
        CartPopup popup = context.getCartPopup();

        assertTrue(popup.isPopupDisplayed(),
                "The confirmation popup was not displayed.");

        assertTrue(popup.isConfirmationMessageDisplayed(),
                "Unexpected confirmation message: '" + popup.getConfirmationMessage() + "'");

        assertEquals(context.getExpectedProductName(), popup.getProductName(),
                "The product name in the popup does not match the one added.");

        assertEquals(context.getExpectedQuantity(), popup.getQuantity(),
                "The quantity in the popup does not match the one selected.");
    }

    @Y("valido en el popup que el monto total sea calculado correctamente")
    public void iValidateTheTotalInThePopup() {
        CartPopup popup = context.getCartPopup();

        assertTrue(
                popup.isTotalCalculatedCorrectly(),
                "Wrong total in the popup. Unit price: " + popup.getUnitPrice()
                        + " x " + popup.getQuantity()
                        + " should equal " + popup.getTotalAmount());
    }
}
