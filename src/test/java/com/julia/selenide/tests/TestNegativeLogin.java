package com.julia.selenide.tests;

import com.julia.selenide.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.julia.selenide.data.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TestNegativeLogin extends BaseTest {


    @Test
    @DisplayName("Login to website - unsuccessful")
    public void testNegativeLogin(){

        LoginPage loginPage = open("/", LoginPage.class);
        loginPage
                .setUserNameInput(STANDARD_USER)
                .setUserPass(WRONG_PASSWORD)
                .clickOnBtnLogin();

        assertThat(loginPage.getTextFromError()).as("Error text is not correct")
                .isEqualTo("Epic sadface: Username and password do not match any user in this service");
    }
}