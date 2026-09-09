package com.julia.selenide.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.julia.selenide.config.Config;
import com.julia.selenide.extensions.TestFailureExtension;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@ExtendWith(TestFailureExtension.class)

public class BaseTest {

    @BeforeAll
    static void setUpAllure() {
        SelenideLogger.addListener(
                "AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(false)
        );
    }

    @BeforeEach
    void setUp() {
        Configuration.baseUrl = Config.getBaseUrl();
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
