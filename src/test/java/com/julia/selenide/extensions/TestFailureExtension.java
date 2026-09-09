package com.julia.selenide.extensions;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.Selenide.screenshot;

public class TestFailureExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {

        byte[] screenshot = screenshot(OutputType.BYTES);

        Allure.addAttachment(
                "Screenshot on failure",
                "image/png",
                new ByteArrayInputStream(screenshot),
                ".png"
        );
    }
}