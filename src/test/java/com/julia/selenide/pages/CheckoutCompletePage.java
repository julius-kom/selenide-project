package com.julia.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class CheckoutCompletePage {

    private SelenideElement completeHeader =
            $x("//h2[@data-test='complete-header']");

    @Step("Check order is completed")
    public void checkOrderIsCompleted() {
        completeHeader.shouldHave(text("Thank you for your order!"));
    }
}