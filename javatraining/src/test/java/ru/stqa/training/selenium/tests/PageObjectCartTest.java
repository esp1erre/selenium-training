package ru.stqa.training.selenium.tests;

import org.junit.Test;

public class PageObjectCartTest extends TestBase{

    @Test
    public void cartHandlingTest () {
        app.addFirstProductsToCart(3);
        app.checkout();
        app.clearCart();
    }
}
