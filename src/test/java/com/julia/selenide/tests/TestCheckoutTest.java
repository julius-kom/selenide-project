package com.julia.selenide.tests;

import com.julia.selenide.pages.*;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.julia.selenide.data.TestData.STANDARD_PASSWORD;
import static com.julia.selenide.data.TestData.STANDARD_USER;
import static org.assertj.core.api.Assertions.assertThat;

public class TestCheckoutTest extends  BaseTest{
    ProductsPage productsPage = new ProductsPage();
    CartPage cartPage = new CartPage();
    CheckoutPage checkoutPage = new CheckoutPage();
    CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
    CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();


    @Test
    @DisplayName("Successful checkout test")
    public void testCheckoutTest(){

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

        cartPage.clickOnBtnCheckout();

        checkoutPage.fillAllFieldsAndPressContinue(
                "Julia",
                "Komarova",
                "11000"
        );

        Allure.step("Get checkout info");
        checkoutOverviewPage.checkPageIsOpened();
        assertThat(checkoutOverviewPage.getProductNames())
                .as("Product on Checkout Overview is not correct")
                .containsExactly(productName);

        checkoutOverviewPage.clickOnFinishBtn();

        Allure.step("Check finish steps");
        checkoutCompletePage.checkOrderIsCompleted();
    }
}
