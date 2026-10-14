package com.julia.selenide.tests;

import com.julia.selenide.pages.CartPage;
import com.julia.selenide.pages.LoginPage;
import com.julia.selenide.pages.ProductsPage;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.julia.selenide.data.TestData.STANDARD_PASSWORD;
import static com.julia.selenide.data.TestData.STANDARD_USER;
import static org.assertj.core.api.Assertions.assertThat;

public class TestAddMultipleProductsToCart extends BaseTest {

    ProductsPage productsPage = new ProductsPage();
    CartPage cartPage = new CartPage();

    @Test
    @DisplayName("Add two different products to cart and verify names and count")
    public void testAddMultipleProductsToCart() {

        Allure.step("Open and log in", () -> {
            LoginPage loginPage = open("/", LoginPage.class);
            loginPage
                    .setUserNameInput(STANDARD_USER)
                    .setUserPass(STANDARD_PASSWORD)
                    .clickOnBtnLogin();
        });

        productsPage.checkHeaderIsVisible();

        String firstProduct = "Sauce Labs Backpack";
        String secondProduct = "Sauce Labs Bike Light";

        Allure.step("Add two different products to the cart", () -> {
            productsPage.clickOnBtnAddToCartByName(firstProduct);
            productsPage.clickOnBtnAddToCartByName(secondProduct);
        });

        productsPage.checkCartBadge("2");

        Allure.step("Open the cart and verify the selected products", () -> {
            productsPage.openCart();
            List<String> cartProducts = cartPage.getProductNames();

            assertThat(cartProducts)
                    .as("Cart items are not correct")
                    .containsExactlyInAnyOrder(firstProduct, secondProduct);

            assertThat(cartProducts.size())
                    .as("Cart item count is not correct")
                    .isEqualTo(2);
        });
    }
}
