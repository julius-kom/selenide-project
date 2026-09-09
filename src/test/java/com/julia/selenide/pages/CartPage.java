package com.julia.selenide.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage {

    private ElementsCollection productNames = $$x("//div[@data-test='inventory-item-name']");
    private SelenideElement buttonCheckout = $x("//button[@data-test='checkout']");

    @Step("Get product names from cart")
    public List<String> getProductNames() {
        return productNames.texts();
    }

    @Step("Wait and click checkout button")
    public CheckoutPage clickOnBtnCheckout(){
        buttonCheckout.should(enabled).click();
        return new CheckoutPage();
    }
}