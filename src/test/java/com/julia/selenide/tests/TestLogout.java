package com.julia.selenide.tests;

import com.julia.selenide.pages.LoginPage;
import com.julia.selenide.pages.ProductsPage;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.julia.selenide.data.TestData.STANDARD_PASSWORD;
import static com.julia.selenide.data.TestData.STANDARD_USER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestLogout extends BaseTest{

    ProductsPage productsPage = new ProductsPage();

    @Test
    @DisplayName("Login and Logout")
    public void testLogout(){

        Allure.step("Open and login");
        LoginPage loginPage = open("/", LoginPage.class);

        loginPage
                .setUserNameInput(STANDARD_USER)
                .setUserPass(STANDARD_PASSWORD)
                .clickOnBtnLogin();
        productsPage.checkHeaderIsVisible();

        Allure.step("Click on button Logout");
        productsPage.clickOnBurgerButton();
        productsPage.clickOnLogoutButton();

        assertThat(loginPage.isUserNameInputVisible())
                .as("Login page is not opened after logout")
                .isTrue();
    }
}