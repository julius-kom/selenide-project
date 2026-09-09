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

public class TestCheckTextSorting extends  BaseTest {
    ProductsPage productsPage = new ProductsPage();

    static Stream<Arguments> sortingData(){
        return Stream.of(
                Arguments.of( "Name (A to Z)", Comparator.<String>naturalOrder()),
                Arguments.of("Name (Z to A)", Comparator.<String>reverseOrder())
        );
    }

    @ParameterizedTest(name = "Check sorting: {0}")
    @MethodSource("sortingData")
    public void testCheckTextSorting(String sorting, Comparator<String> comparator){

        Allure.step("Open and login");
        LoginPage loginPage = open("/", LoginPage.class);

        loginPage
                .setUserNameInput(STANDARD_USER)
                .setUserPass(STANDARD_PASSWORD)
                .clickOnBtnLogin();
        productsPage.checkHeaderIsVisible();

        Allure.step("Sorting the products");
        productsPage.selectFromDropdown(sorting);
        List<String> actualNames = productsPage.getProductNames();
        List<String> expectedNames = new ArrayList<>(actualNames);
        expectedNames.sort(comparator);

        Allure.step("Check sorting results");
        assertThat(actualNames)
                .as("Sorting '%s' didn't work", sorting)
                .isEqualTo(expectedNames);
    }
}
