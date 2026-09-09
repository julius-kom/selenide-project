package com.julia.selenide.tests;

import com.julia.selenide.pages.CartPage;
import com.julia.selenide.pages.LoginPage;
import com.julia.selenide.pages.ProductsPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.julia.selenide.data.TestData.STANDARD_PASSWORD;
import static com.julia.selenide.data.TestData.STANDARD_USER;
import static org.assertj.core.api.Assertions.assertThat;

public class TestAddProductToCart extends  BaseTest{
    ProductsPage productsPage = new ProductsPage();
    CartPage cartPage = new CartPage();

    @Test
    @DisplayName("Add products to cart")
    public void testAddProductToCart(){

        Allure.step("Open and login");
        LoginPage loginPage = open("/", LoginPage.class);

        loginPage
                .setUserNameInput(STANDARD_USER)
                .setUserPass(STANDARD_PASSWORD)
                .clickOnBtnLogin();
        productsPage.checkHeaderIsVisible();

        String productName = "Sauce Labs Backpack";

        Allure.step("Add to cart");
        productsPage.clickOnBtnAddToCartByName(productName);

        assertThat(productsPage.getProductButtonText(productName))
                .as("Button text is not correct after adding product to cart")
                .isEqualTo("Remove");

        Allure.step("Open cart");
        productsPage.openCart();
        List<String> productsFromCart  = cartPage.getProductNames();
        assertThat(productsFromCart).as("Product is not displayed at the cart")
                .containsExactly(productName);
    }
}


