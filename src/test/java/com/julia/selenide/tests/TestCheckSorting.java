package com.julia.selenide.tests;

import com.julia.selenide.pages.LoginPage;
import com.julia.selenide.pages.ProductsPage;
import io.qameta.allure.Allure;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;
import static com.julia.selenide.data.TestData.STANDARD_PASSWORD;
import static com.julia.selenide.data.TestData.STANDARD_USER;
import static org.assertj.core.api.Assertions.assertThat;

public class TestCheckSorting extends  BaseTest {
    ProductsPage productsPage = new ProductsPage();

    static Stream<Arguments> sortingData() {
        return Stream.of(
                Arguments.of("Price (low to high)", Comparator.<Double>naturalOrder()),
                Arguments.of("Price (high to low)", Comparator.<Double>reverseOrder())
        );
    }

    @ParameterizedTest(name = "Check sorting: {0}")
    @MethodSource("sortingData")
    public void testCheckSorting(String sorting, Comparator<Double> comparator) {

        Allure.step("Open and login");
        LoginPage loginPage = open("/", LoginPage.class);
        Allure.step("Login");
        loginPage
                .setUserNameInput(STANDARD_USER)
                .setUserPass(STANDARD_PASSWORD)
                .clickOnBtnLogin();
        productsPage.checkHeaderIsVisible();

        Allure.step("Sorting products");
        productsPage.selectFromDropdown(sorting);
        List<Double> actualPrices = productsPage.getProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(comparator);

        assertThat(actualPrices)
                .as("Sorting '%s' didn't work", sorting)
                .isEqualTo(expectedPrices);

    }
}