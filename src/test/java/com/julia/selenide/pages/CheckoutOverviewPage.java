package com.julia.selenide.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CheckoutOverviewPage {

    private SelenideElement header = $x("//span[@data-test='title']");
    private ElementsCollection productNames = $$x("//div[@data-test='inventory-item-name']");
    private SelenideElement finishButton = $x("//button[@data-test='finish']");

    @Step("Check Checkout Overview page is opened")
    public void checkPageIsOpened() {
        header.shouldHave(text("Checkout: Overview"));
    }

    @Step("Get product names from Checkout Overview")
    public List<String> getProductNames() {
        return productNames.texts();
    }

    @Step("Finish checkout")
    public void clickOnFinishBtn() {
        finishButton.click();
    }
}