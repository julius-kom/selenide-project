package com.julia.selenide.tests;

import com.julia.selenide.models.Product;
import com.julia.selenide.pages.LoginPage;
import com.julia.selenide.pages.ProductsPage;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.julia.selenide.data.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TestCheckProducts extends  BaseTest{
    ProductsPage productsPage = new ProductsPage();

    @Test
    @DisplayName("Check products")
    public void testCheckProducts(){

        Allure.step("Open and login");
        LoginPage loginPage = open("/", LoginPage.class);

        loginPage
                .setUserNameInput(STANDARD_USER)
                .setUserPass(STANDARD_PASSWORD)
                .clickOnBtnLogin();
        productsPage.checkHeaderIsVisible();

        Allure.step("Get products from screen");
        List<String> expectedProducts = List.of("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)");
        List<String> productsFromScreen  = productsPage.getProductNames();

        Allure.step("Check the products names from screen");
        assertThat(productsFromScreen).as("Incorrect products names")
                .containsExactlyInAnyOrderElementsOf(expectedProducts);

        Allure.step("Compare all products prices");
        assertThat(productsPage.getProductPrices()).as("Prices should be greater than zero")
                .isNotEmpty()
                .allMatch(price -> price > 0);

        Allure.step("Check all the products from screen");
        List<Product> actualProducts = productsPage.getProducts();

        assertThat(actualProducts).as("Doesn`t contain expected product")
                .containsExactlyElementsOf(EXPECTED_PRODUCTS);

    }
}
