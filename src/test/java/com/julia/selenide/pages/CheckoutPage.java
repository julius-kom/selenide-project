package com.julia.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$x;

public class CheckoutPage {

    private SelenideElement firstNameFld = $x("//input[@data-test='firstName']");
    private SelenideElement lastNameFld = $x("//input[@data-test='lastName']");
    private SelenideElement postalCodeFld =  $x("//input[@data-test='postalCode']");
    private SelenideElement continueButton = $x("//input[@data-test ='continue']");
    private SelenideElement errorMessage = $x("//h3[@data-test='error']");

    public CheckoutPage fillFirstNameInput(String firstName){
        firstNameFld.setValue(firstName);
        return this;
    }

    public CheckoutPage fillLastNameInput(String lastName){
        lastNameFld.setValue(lastName);
        return this;
    }

    public CheckoutPage fillPostalCodeInput(String postalCode){
        postalCodeFld.setValue(postalCode);
        return this;
    }

    public void clickOnContinueBtn(){
        continueButton.click();
    }

    @Step("Fill checkout information and continue")
    public void fillAllFieldsAndPressContinue(String firstName, String lastName, String postalCode){
        fillFirstNameInput(firstName)
                .fillLastNameInput(lastName)
                .fillPostalCodeInput(postalCode)
                .clickOnContinueBtn();
    }

    @Step("Check checkout error message")
    public String getErrorMessage() {
        return errorMessage.should(appear).getText();
    }
}