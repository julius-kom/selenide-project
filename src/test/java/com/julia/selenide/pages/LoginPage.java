package com.julia.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private SelenideElement userNameInput = $("#user-name");
    private SelenideElement passInput = $("#password");
    private SelenideElement loginButton =  $("[data-test='login-button']");
    private SelenideElement errorText =  $x("//h3[@data-test='error']");

    @Step("Fill input userName")
    public LoginPage setUserNameInput(String name){
        userNameInput.setValue(name);
        return this;
    }

    @Step("Fill input password")
    public LoginPage setUserPass(String password){
        passInput.setValue(password);
        return this;
    }

    @Step("Click on button Login")
    public void clickOnBtnLogin(){
        loginButton.click();
    }

    @Step("Get text from error")
    public String getTextFromError(){
        return errorText.getText();
    }

    @Step("Check username field is visible")
    public boolean isUserNameInputVisible() {
        return userNameInput.isDisplayed();
    }
}